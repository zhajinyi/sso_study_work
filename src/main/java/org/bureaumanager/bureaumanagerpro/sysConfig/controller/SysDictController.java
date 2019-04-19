package org.bureaumanager.bureaumanagerpro.sysConfig.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.bureaumanager.bureaumanagerpro.base.BaseController;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.Msg;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysDictDto;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysUserDto;
import org.bureaumanager.bureaumanagerpro.sysConfig.service.impl.SysDictServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping(value = "/sys/Dict")
public class SysDictController  extends BaseController{

    @Autowired
    private SysDictServiceImpl sysDictServiceImpl;

    /*
    * 根据parentId查询
    * */
    @RequestMapping(value = "/all" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Msg findAllClass(@RequestBody(required = false) SysDictDto sysDictDto) {
        PageHelper.startPage(sysDictDto.getCurrentPage(),sysDictDto.getPageSize());
        String parentId = sysDictDto.getId();
        sysDictDto.setId(null);
        sysDictDto.setType("1");
        sysDictDto.setDelFlag("0");
        if("0".equals(parentId)){
            sysDictDto.setParentId(null);
        }else{
            sysDictDto.setParentId(parentId);
        }
        List<SysDictDto> list=sysDictServiceImpl.queryChildrenDictInfos(sysDictDto);
        PageInfo page = new PageInfo(list,5);
        return Msg.success().add("PageInfo",page);
    }

    /*
    * 建立业务字典树
    * */
    @RequestMapping("/tree")
    @ResponseBody
    public List<Map> selectCollegeTree() throws Exception{
        //获取所有的父级字典
        List<SysDictDto> list = sysDictServiceImpl.queryParentDictInfos("1");
        System.out.print(list);
        Map mapTree=new HashMap();
        List<Map<String,String>> mapItemList = new ArrayList<Map<String,String>>();
        if (list.size()>0)
            for (SysDictDto o : list){
                Map mapIt=new HashMap();
                mapIt.put("id",o.getId());
                mapIt.put("name",o.getValue());
                mapItemList.add(mapIt);
            }

        if (!mapItemList.isEmpty()){
            Map<String,List<Map<String,String>>> m = new HashMap <String,List<Map<String,String>>>();
            m.put("children",mapItemList);
            mapTree.putAll(m);
        }
        mapTree.put("id",0);
        mapTree.put("spread",true);
        mapTree.put("name","业务字典");

        List<Map> mapsList = new ArrayList<>();
        mapsList.add(mapTree);
        return mapsList;
    }



    /*
    * 新增数据字典组
    * */
    @RequestMapping(value = "/add/parents" , method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> addSysDictParentsInfo(@RequestBody SysDictDto sysDictDto) {
        SysUserDto userDto = (SysUserDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        String type =sysDictDto.getLabel();
        Map map=new HashMap();
        int suceessFalg=0;
        boolean suceess = true;
        if(type.equals("edit")){
            sysDictDto.setLabel(sysDictDto.getValue());
            sysDictDto.setUpdateBy(userDto.getId());
            sysDictDto.setUpdateDate(new Date());
            suceess = sysDictServiceImpl.updateEntityByPrimaryKeySelective(sysDictDto);
            map.put("falg",suceess);
            map.put("falg2",suceessFalg);
        }else{
            sysDictDto.setType("1");
            sysDictDto.setLabel(sysDictDto.getValue());
            sysDictDto.setCreateBy(userDto.getId());
            sysDictDto.setCreateDate(new Date());
            sysDictDto.setUpdateBy(userDto.getId());
            sysDictDto.setUpdateDate(new Date());
            sysDictDto.setDelFlag("0");
            sysDictDto.setParentId("0");
            int addCount = sysDictServiceImpl.countEntities(sysDictDto.getId());
            if (addCount>0){
                suceessFalg=1;
            }else{
                suceess = sysDictServiceImpl.insertEntity(sysDictDto);
            }
            map.put("falg",suceess);
            map.put("falg2",suceessFalg);
        }
        return map;
    }

    /*
   * 根据id查询业务字典
   * */
    @RequestMapping(value="/querySysDictInfoById" , method = {RequestMethod.POST})
    @ResponseBody
    public SysDictDto querySysDictInfoById(@RequestParam(value = "id",defaultValue = "")String id) {

        SysDictDto sysDictDto = sysDictServiceImpl.selectEntityByPrimaryKey(id);
        return sysDictDto;
    }

    /*
   * 新增/修改数据字典
   * */
    @RequestMapping(value = "/add/childrens" , method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> addSysDictChildrensInfo(@RequestBody SysDictDto sysDictDto) {
        SysUserDto userDto = (SysUserDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        String type =sysDictDto.getLabel();
        Map map=new HashMap();
        int suceessFalg=0;
        boolean suceess = true;
        if(type.equals("edit")){
            sysDictDto.setLabel(sysDictDto.getValue());
            sysDictDto.setUpdateBy(userDto.getId());
            sysDictDto.setUpdateDate(new Date());
            suceess = sysDictServiceImpl.updateEntityByPrimaryKeySelective(sysDictDto);
            map.put("falg",suceess);
            map.put("falg2",suceessFalg);
        }else{
            sysDictDto.setType("2");
            sysDictDto.setLabel(sysDictDto.getValue());
            sysDictDto.setCreateBy(userDto.getId());
            sysDictDto.setCreateDate(new Date());
            sysDictDto.setUpdateBy(userDto.getId());
            sysDictDto.setUpdateDate(new Date());
            sysDictDto.setDelFlag("0");
            int addCount = sysDictServiceImpl.countEntities(sysDictDto.getId());
            if (addCount>0){
                suceessFalg=1;
            }else{
                suceess = sysDictServiceImpl.insertEntity(sysDictDto);
            }

            map.put("falg",suceess);
            map.put("falg2",suceessFalg);
        }
        return map;
    }

    /*
   * 删除数据字典组
   * */
    @RequestMapping(value = "/delete/parent" , method = {RequestMethod.POST})
    @ResponseBody
    public Msg deleteSysDictParentsInfo(@RequestBody SysDictDto sysDictDto) {
        sysDictDto.setDelFlag("1");
        boolean suceess = true;
        boolean suceess1 = sysDictServiceImpl.updateEntityByPrimaryKeySelective(sysDictDto);
        sysDictDto.setParentId(sysDictDto.getId());
        boolean suceess2= sysDictServiceImpl.updateSysDictDtoInfoByParentId(sysDictDto);
        if(suceess1!=true||suceess2!=true){
            suceess = false;
        }
        return Msg.success().add("code",suceess);
    }

    /*
   * 删除数据字典
   * */
    @RequestMapping(value = "/delete/children" , method = {RequestMethod.POST})
    @ResponseBody
    public Msg deleteSysDictChildrensInfo(@RequestBody SysDictDto sysDictDto) {
        sysDictDto.setDelFlag("1");
        boolean suceess = sysDictServiceImpl.updateEntityByPrimaryKeySelective(sysDictDto);

        return Msg.success().add("code",suceess);
    }

    /*
    * 批量删除业务字典
    * */
    @RequestMapping(value = "/delete/childreninfos" , method = {RequestMethod.POST})
    @ResponseBody
    public Msg deleteSysDictChildrensInfos(@RequestParam(value = "ids",defaultValue = "") String ids) {
        String[] array=ids.split(",");
        boolean suceess = true;
        for(int i=0;i<array.length;i++){
            SysDictDto sysDictDto=new SysDictDto();
            sysDictDto.setDelFlag("1");
            sysDictDto.setId(array[i]);
            suceess= sysDictServiceImpl.updateEntityByPrimaryKeySelective(sysDictDto);
            if (!suceess){
                return Msg.success().add("code",suceess);
            }
        }
        return Msg.success().add("code",suceess);
    }
}

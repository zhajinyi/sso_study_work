package org.bureaumanager.bureaumanagerpro.sysConfig.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.Msg;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysEmpInfoDto;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysOrganizationDto;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysUserDto;
import org.bureaumanager.bureaumanagerpro.sysConfig.service.impl.SysOrganizationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: BaoLing
 * @Date: 2019/1/22 14:17
 * @Description: OrganizationController
 * @Version: 1.0
 */
@Controller
@RequestMapping("/user/org")
public class OrganizationController {
    @Autowired
    SysOrganizationServiceImpl sysOrganizationService;

    private Map mapTreew=new HashMap();
    private Map mapTreew2=new HashMap();
    private List<Map<String,String>> mapItemList2 = new ArrayList<Map<String,String>>();
    private Map mapIt=new HashMap();

    @RequestMapping("/all")
    @ResponseBody
    public Msg findAllOrg() {
        List<SysOrganizationDto> orgList=sysOrganizationService.selectAllEntities();
        return Msg.success().add("orgList",orgList);
    }
    @RequestMapping(value = "/findIds",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public List<Map> findIdAndNameByRealName(@RequestParam(value = "q",defaultValue = "") String q){
        List<SysOrganizationDto> orgList = sysOrganizationService.selectIdsByName(q);
        List<Map> orgIds = new ArrayList<>();
        for (SysOrganizationDto o : orgList){
            Map orgMap= new HashMap();
            orgMap.put("id",o.getId());
            orgMap.put("text",o.getOrgName());
            orgIds.add(orgMap);
        }
        return orgIds;
    }
    /*
    * 建立业务字典树
    * */
    @RequestMapping("/tree")
    @ResponseBody
    public JSONObject selectCollegeTree() throws Exception{
        //获取所有的父级字典
        List<SysOrganizationDto> list = sysOrganizationService.queryAllSysOrganization();
        Map mapTree=new HashMap();
        List<Map<String,String>> mapItemList = new ArrayList<Map<String,String>>();
        if (list.size()>0)
            for (SysOrganizationDto o : list){
                if (o.getId().equals("0")){

                }else{
                    Map mapIt=new HashMap();
                    String grade=o.getOrgGrade();
                    int level= Integer.parseInt(grade);
                    mapIt.put("id",o.getId());
                    mapIt.put("name",o.getOrgName());
                    mapIt.put("level",level);
                    mapIt.put("parentId",o.getParentId());
                    mapItemList.add(mapIt);
                }
            }
        if (!mapItemList.isEmpty()){
            Map<String,List<Map<String,String>>> m = new HashMap <String,List<Map<String,String>>>();
            m.put("children",mapItemList);
            mapTree.putAll(m);
        }
        mapTree.put("id","0");
        mapTree.put("level",1);
        mapTree.put("parentId","");
        mapTree.put("spread",true);
        mapTree.put("name","苏州工业园区服务外包职业学校");

        return new JSONObject(mapTree);
    }

    public void sort2(int level,List<SysOrganizationDto> data,SysOrganizationDto sysOrganization,Map mapTree){
        level +=1;
        for (SysOrganizationDto sysOrganizationDto:data) {
            if (sysOrganizationDto.getParentId().equals(sysOrganization.getId())){
                Map mapIt2=new HashMap();
                List<Map<String,String>> mapItemList = new ArrayList<Map<String,String>>();
                mapIt2.put("id",sysOrganizationDto.getId());
                mapIt2.put("name",sysOrganizationDto.getOrgName());
                mapIt2.put("level",level);
                mapIt2.put("parentId",sysOrganizationDto.getParentId());
                mapItemList.add(mapIt2);
                List<Map<String,String>>  lis = (List<Map<String,String>>)mapTree.get("children");
                if(lis.size()>0&&lis.get(lis.size()-1).get("id").equals(sysOrganization.getId())){
                    Map<String,List<Map<String,String>>> m = new HashMap <String,List<Map<String,String>>>();
                    m.put(("children"+level).toString(),mapItemList);
                    mapTree.putAll(m);
                    this.sort2(level,data,sysOrganizationDto,mapTree);
                }
            }
        }
    }

    public void sort(String id,List<SysOrganizationDto> data){
        Map mapTree=new HashMap();
        List<Map<String,String>> list=new ArrayList<>();
        List<SysOrganizationDto> data1 = new ArrayList<SysOrganizationDto>();
        data1=data;
        int falg=0;
        for(int i=0;i<data1.size();i++){
            String parentId=data1.get(i).getParentId();
            if(parentId.equals(id)){
                mapTreew2.put("children",data1.get(i));
                this.sort(data1.get(i).getId(), data1);
                list.add(mapTreew2);
            }else {
                falg=1;
            }
        }
        if (!list.isEmpty()&&falg==1){
            Map<String,List<Map<String,String>>> m = new HashMap <>();
            m.put("children",list);
            mapTree.putAll(m);
        }
        mapTreew2.putAll(mapTree);
    }

    /*
    * 建立业务字典树
    * */
    @RequestMapping("/treeTable")
    @ResponseBody
    public Msg selectCollegeTree2() throws Exception{
        //获取所有的父级字典
        List<SysOrganizationDto> list = sysOrganizationService.queryAllSysOrganization();
       /* Map mapTree=new HashMap();
        List<Map<String,String>> mapItemList = new ArrayList<Map<String,String>>();
        if (list.size()>0)
            for (SysOrganizationDto o : list){
                Map mapIt=new HashMap();
                mapIt.put("id",o.getId());
                mapIt.put("name",o.getOrgName());
                mapIt.put("level",o.getOrgGrade());
                mapIt.put("parentId",o.getParentId());
                mapItemList.add(mapIt);
            }
        if (!mapItemList.isEmpty()){
            Map<String,List<Map<String,String>>> m = new HashMap <String,List<Map<String,String>>>();
            m.put("children",mapItemList);
            mapTree.putAll(m);
        }
        mapTree.put("id","0");
        mapTree.put("level",1);
        mapTree.put("parentId","");
        mapTree.put("spread",true);
        mapTree.put("name","苏州工业园区服务外包职业学校");

        return new JSONObject(mapTree);*/
        Msg.success().add("count",list.size());
        return Msg.success().add("data",list);
    }

    @RequestMapping("/queryOne")
    @ResponseBody
    public Msg queryOrfanizationInfoOne(@RequestParam(value = "id",defaultValue = "")String id) {
        SysOrganizationDto orgInfo=sysOrganizationService.selectEntityByPrimaryKey(id);
        return Msg.success().add("orgList",orgInfo);
    }
    @RequestMapping("/addExit/orgnaization")
    @ResponseBody
    public Msg addOrgnizationInfo(@RequestBody SysOrganizationDto sysOrganizationDto) {
        SysUserDto userDto = (SysUserDto) SecurityUtils.getSubject().getSession().getAttribute("user");

        Date myDate=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String type=sysOrganizationDto.getParentIds();
        Map map=new HashMap();
        int suceessFalg=0;
        boolean suceess = true;
        if (type.equals("edit")){
            sysOrganizationDto.setParentIds("");
            sysOrganizationDto.setUpdateBy(userDto.getId());
            sysOrganizationDto.setUpdateDate(sdf.format(myDate));
            suceess = sysOrganizationService.updateEntityByPrimaryKeySelective(sysOrganizationDto);

            map.put("falg1",suceess);
            map.put("falg2",suceessFalg);
        }else{
            int addCount =sysOrganizationService.countEntities(sysOrganizationDto.getId());
            if (addCount>0){

                suceessFalg=1;
            }else{
                sysOrganizationDto.setParentIds("");
                sysOrganizationDto.setCreateBy(userDto.getId());
                sysOrganizationDto.setCreateDate(sdf.format(myDate));
                if (sysOrganizationDto.getOrgGrade().equals("")){
                    sysOrganizationDto.setOrgGrade("2");
                }
                sysOrganizationDto.setUpdateBy(userDto.getId());
                sysOrganizationDto.setUpdateDate(sdf.format(myDate));
                sysOrganizationDto.setDelFlag("0");
                suceess = sysOrganizationService.insertEntitySelective(sysOrganizationDto);
            }
            map.put("falg1",suceess);
            map.put("falg2",suceessFalg);
        }
        return Msg.success().add("falgs",map);
    }

    /*
   * 删除数据字典组
   * */
    @RequestMapping(value = "/delete/organization" , method = {RequestMethod.POST})
    @ResponseBody
    public Msg deleteSysDictParentsInfo(@RequestBody SysOrganizationDto sysOrganizationDto) {
        sysOrganizationDto.setDelFlag("1");
        boolean suceess = sysOrganizationService.updateEntityByPrimaryKeySelective(sysOrganizationDto);
        return Msg.success().add("code",suceess);
    }
}

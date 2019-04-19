package org.bureaumanager.bureaumanagerpro.model.dormitoryManager.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.bureaumanager.bureaumanagerpro.model.dormitoryManager.pojo.dto.SisoDormInfoDto;
import org.bureaumanager.bureaumanagerpro.model.dormitoryManager.service.impl.SisoDormInfoServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sisoDormInfo")
public class SisoDormInfoController{

    @Autowired
    SisoDormInfoServiceImpl sisoDormInfoService;

    @RequestMapping(value = "/all",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Msg selectSisoDormByBuildingNum(@RequestParam(value="currentPage",defaultValue="1")Integer currentPage
            , @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize
            , @RequestParam(value = "id") String id){
        if(id!=null&&id!=""){
            if(id.equals("0")){
                id="";
            }else{
                id=id.substring(0,1);
            }
        }
        PageHelper.startPage(currentPage, pageSize);
        List<SisoDormInfoDto> sisodormInfos = sisoDormInfoService.selectSisoDormByBuildingNum(id);
        PageInfo page = new PageInfo(sisodormInfos,5);
        return Msg.success().add("PageInfo",page);
    }

    @RequestMapping(value = "/selective",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Msg selectSisoDormByParameters(@RequestBody(required = false) SisoDormInfoDto sisoDormInfoDto){
        sisoDormInfoDto = sisoDormInfoDto==null? new SisoDormInfoDto(): sisoDormInfoDto;
        PageHelper.startPage(sisoDormInfoDto.getCurrentPage(),sisoDormInfoDto.getPageSize());
        if ("".equals(sisoDormInfoDto.getArea())) {
            sisoDormInfoDto.setArea(null);
        }
        List<SisoDormInfoDto> list = sisoDormInfoService.selectEntitiesByTemplate(sisoDormInfoDto);
        PageInfo page = new PageInfo(list,5);
        return Msg.success().add("PageInfo",page);
    }

    /**
     *
     * @param sisoDormInfoDto
     * @return
     */
    @RequestMapping(value = "/update/selective",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Msg updateSelective(@RequestBody(required = false)SisoDormInfoDto sisoDormInfoDto){
        Boolean flag = sisoDormInfoService.updateEntityByPrimaryKeySelective(sisoDormInfoDto);
        if (flag){
            return Msg.success();
        }else {
            return Msg.failure();
        }
    }

    /**
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete/selective",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Msg deleteEntities(@RequestBody List<String> ids){
        boolean flag = sisoDormInfoService.deleteEntityByPrimaryKeys(ids);
        if (flag){
            return Msg.success();
        }else {
            return Msg.failure();
        }
    }

    /**
     *
     * @param sisoDormInfoDto
     * @return
     */
    @RequestMapping(value = "/insert/selective",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Msg insertEntity(@RequestBody(required = true)SisoDormInfoDto sisoDormInfoDto){
        boolean flag = sisoDormInfoService.insertEntitySelective(sisoDormInfoDto);
        if (flag){
            return Msg.success();
        }else {
            return Msg.failure();
        }
    }
    @RequestMapping(value = "/tree",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public List<Map> selectCollegeByDormInfo() throws Exception{
        List<SisoDormInfoDto> sisoDormInfoDtos=sisoDormInfoService.selectCollegeByDormInfo();
        Map mapTreeOrgin = new HashMap();
        Map mapTreeOther = new HashMap();
        List<Map<String,String>> mapItemList_other = new ArrayList<Map<String,String>>();
        List<Map<String,String>> mapItemList_orgin = new ArrayList<Map<String,String>>();
        if (sisoDormInfoDtos.size()>0)
            for (SisoDormInfoDto sisoDormInfoDto : sisoDormInfoDtos){
                Map mapIt=new HashMap();
                if("其他".equals(sisoDormInfoDto.getArea())){
                    mapIt.put("id", sisoDormInfoDto.getBuildingNum());
                    mapIt.put("level", "1");
                    mapIt.put("name", sisoDormInfoDto.getBuildingNum());
                    mapItemList_other.add(mapIt);
                }else{
                mapIt.put("id",sisoDormInfoDto.getBuildingNum());
                mapIt.put("level","1");
                mapIt.put("name",sisoDormInfoDto.getBuildingNum()+"号楼");
                mapItemList_orgin.add(mapIt);
                }
            }

        if (!mapItemList_orgin.isEmpty()){
            Map<String,List<Map<String,String>>> orginMap = new HashMap <String,List<Map<String,String>>>();
            orginMap.put("children",mapItemList_orgin);
            mapTreeOrgin.putAll(orginMap);
            mapTreeOrgin.put("id", "0");
            mapTreeOrgin.put("spread", true);
            mapTreeOrgin.put("level","0");
            mapTreeOrgin.put("name","校本部");
        }
        if (!mapItemList_other.isEmpty()){
            Map<String,List<Map<String,String>>> otherMap = new HashMap <String,List<Map<String,String>>>();
            otherMap.put("children",mapItemList_other);
            mapTreeOther.putAll(otherMap);
            mapTreeOther.put("id", "1");
            mapTreeOther.put("spread", true);
            mapTreeOther.put("level","0");
            mapTreeOther.put("name","其他");
        }
        List<Map> maps = new ArrayList<Map>();
        maps.add(mapTreeOrgin);
        maps.add(mapTreeOther);
        return maps;
    }
}

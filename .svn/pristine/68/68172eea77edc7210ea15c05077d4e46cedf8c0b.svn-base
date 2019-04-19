package org.bureaumanager.bureaumanagerpro.sysConfig.controller;

import com.alibaba.fastjson.JSONObject;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysOrganizationDto;
import org.bureaumanager.bureaumanagerpro.sysConfig.service.impl.SysOrganizationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user/org2")
public class OrganizationTreeController {
    @Autowired
    SysOrganizationServiceImpl sysOrganizationService;

    //基本树
    private Map mapTree=new HashMap();
    /*
    * 建立业务字典树
    * */
    @RequestMapping("/tree")
    @ResponseBody
    public JSONObject selectCollegeTree() throws Exception{
        //获取所有的父级字典
        List<SysOrganizationDto> list = sysOrganizationService.queryAllSysOrganization();
        for (SysOrganizationDto sysOrganizationDto:list) {
            if (sysOrganizationDto.getId().equals("")){
                mapTree.put("id",sysOrganizationDto.getId());
                mapTree.put("name",sysOrganizationDto.getOrgName());
                mapTree.put("level","1");
                mapTree.put("parentId","");
            }else {
                this.sort2(sysOrganizationDto.getId(),list);
            }
        }
        return new JSONObject(mapTree);
    }

    public void sort2(String s_id,List<SysOrganizationDto> data){
        List<Map> listMap=new ArrayList<>();
        for (SysOrganizationDto sysOrganizationDto:data){
            if (sysOrganizationDto.getParentId().equals(s_id)){
                Map mapIt=new HashMap();
                mapIt.put("id",sysOrganizationDto.getId());
                mapIt.put("name",sysOrganizationDto.getOrgName());
                mapIt.put("level","2");
                mapIt.put("parentId",sysOrganizationDto.getParentId());
                listMap.add(mapIt);
            }
        }
        if (listMap.size()>0){
            mapTree.put("childer",listMap);
            this.sort(listMap,data);
        }

    }

    public void sort(List<Map> list,List<SysOrganizationDto> data){
        List<Map> listMap=new ArrayList<>();
        for (Map map:list) {
            for (SysOrganizationDto sysOrganizationDto:data){
                if (map.get("id").equals(sysOrganizationDto.getParentId())){
                    Map mapIt=new HashMap();
                    mapIt.put("id",sysOrganizationDto.getId());
                    mapIt.put("name",sysOrganizationDto.getOrgName());
                    mapIt.put("level","3");
                    mapIt.put("parentId",sysOrganizationDto.getParentId());
                    listMap.add(mapIt);
                }
            }
            if (listMap.size()>0&&map.get("id").equals(map.get("parentId"))){

            }
        }
    }

}

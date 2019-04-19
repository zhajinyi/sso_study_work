package org.bureaumanager.bureaumanagerpro.sysConfig.controller;

import org.bureaumanager.bureaumanagerpro.base.BaseController;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.pojo.dto.SisoClassInfoDto;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.pojo.dto.SisoStudentInfoDto;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.service.impl.SisoStudentInfoServiceImpl;
import org.bureaumanager.bureaumanagerpro.model.sysManager.service.impl.SysEmpInfoServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.Msg;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysEmpInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: BaoLing
 * @Date: 2019/2/27 14:37
 * @Description: EmpInfoController
 * @Version: 1.0
 */
@Controller
@RequestMapping("/empInfo")
public class EmpInfoController extends BaseController {

    @Autowired
    SysEmpInfoServiceImpl sysEmpInfoService;
    @Autowired
    SisoStudentInfoServiceImpl sisoStudentInfoService;
    @RequestMapping(value = "/findIds",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public List<Map> findIdAndNameByRealName(@RequestParam(value = "q",defaultValue = "") String q){
        List<SysEmpInfoDto> empInfolist = sysEmpInfoService.selectIdsByName(q);
        List<Map> empIds = new ArrayList<>();
        for (SysEmpInfoDto c : empInfolist){
            Map empMap= new HashMap();
            empMap.put("id",c.getId());
            empMap.put("text",c.getRealname());
            empIds.add(empMap);
        }
        return empIds;
    }

    @RequestMapping(value = "/selectIdsByName", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Msg selectIdsByName(@RequestParam(value = "q",defaultValue = "") String q){

        List<SysEmpInfoDto> sysEmpInfoDtos = sysEmpInfoService.selectIdsByName(q);
        return Msg.success().add("sysEmpInfoDtos",sysEmpInfoDtos);
    }

    @RequestMapping(value = "/updateEmpInfo", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Msg updateEmpInfoById(@RequestBody SysEmpInfoDto sysEmpInfoDto){
        if(sysEmpInfoDto.getOnShool()==null){
            sysEmpInfoDto.setOnShool("");
        }
        if(sysEmpInfoDto.getShoolRoll()==null){
            sysEmpInfoDto.setShoolRoll("");
        }
        if(sysEmpInfoDto.getBankCard()==null){
            sysEmpInfoDto.setBankCard("");
        }
        SisoStudentInfoDto sisoStudentInfoDto = new SisoStudentInfoDto();
        sisoStudentInfoDto.setOnShool(sysEmpInfoDto.getOnShool());
        sisoStudentInfoDto.setShoolRoll(sysEmpInfoDto.getShoolRoll());
        sisoStudentInfoDto.setBankCard(sysEmpInfoDto.getBankCard());
        sisoStudentInfoDto.setId(sysEmpInfoDto.getId());
        boolean flg = sysEmpInfoService.updateEntityByPrimaryKeySelective(sysEmpInfoDto);

        boolean flg1 = sisoStudentInfoService.updateByTemplateById(sisoStudentInfoDto);
        if (flg&&flg1){
            return Msg.success();
        }else {
            return Msg.failure();
        }

    }

}

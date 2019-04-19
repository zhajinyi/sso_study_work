package org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.bureaumanager.bureaumanagerpro.base.BaseController;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo.dto.SisoExcellclassInfoDto;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.service.impl.SisoExcellclassInfoServiceImpl;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.pojo.dto.SisoStudentInfoDto;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.service.impl.SisoStudentInfoServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.Msg;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysUserDto;
import org.bureaumanager.bureaumanagerpro.sysConfig.service.impl.SysOrganizationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/excellClass")
public class ExellclassControlller extends BaseController{

    @Autowired
    SisoExcellclassInfoServiceImpl sisoExcellclassInfoService;
    @Autowired
    SysOrganizationServiceImpl sysOrganizationService;
    @Autowired
    SisoStudentInfoServiceImpl sisoStudentInfoServiceImpl;

    @RequestMapping(value = "/all" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Msg findAllClass(@RequestBody(required = false) SisoExcellclassInfoDto sisoExcellclassInfoDto) {
        PageHelper.startPage(sisoExcellclassInfoDto.getCurrentPage(),sisoExcellclassInfoDto.getPageSize());
        String orgid = sisoExcellclassInfoDto.getOrgid();
        if("0".equals(orgid)){
            sisoExcellclassInfoDto.setOrgid(null);
        }
        List<SisoExcellclassInfoDto> list=sisoExcellclassInfoService.queryByTemplate(sisoExcellclassInfoDto);
        PageInfo page = new PageInfo(list,5);
        return Msg.success().add("PageInfo",page);
    }

    @RequestMapping(value = "/all2" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Msg findAllClass2(@RequestBody(required = false) SisoExcellclassInfoDto sisoExcellclassInfoDto) {
        PageHelper.startPage(sisoExcellclassInfoDto.getCurrentPage(),sisoExcellclassInfoDto.getPageSize());
        String orgid=sisoExcellclassInfoDto.getOrgid();
        if (null == orgid || "0".equals(orgid)){
            sisoExcellclassInfoDto.setOrgid(null);
        }
        List<SisoExcellclassInfoDto> list=sisoExcellclassInfoService.selectEntitiesByTemplate(sisoExcellclassInfoDto);
        PageInfo page = new PageInfo(list,5);
            return Msg.success().add("PageInfo",page);
    }
    /**
     * 获取学院tree
     * @return
     * @throws Exception
     */
    @RequestMapping("/tree")
    @ResponseBody
    @Override
    public List<Map> selectCollegeAndClassTree(@RequestParam(value = "grade",defaultValue = "",required = false) String grade) throws Exception{
//        String trueGrade = grade.equals("请选择")||grade.equals("全部")?"":grade;
        return super.selectCollegeAndClassTree(grade);
    }

    @RequestMapping(value = "/onestudent/excellClassInfos" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Msg selectHighCadreOfStudent(@RequestParam(value="currentPage",defaultValue="1")Integer currentPage
            ,@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize
            ,@Param(value = "t") SisoExcellclassInfoDto t){
        if (null== SecurityUtils.getSubject().getSession()){
            //logger.info("Session has been invalidated! Can not select HighExcellent Of Student!");
            return Msg.failure();
        }
        SysUserDto userDto = (SysUserDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        SisoStudentInfoDto sisoStudentInfoDto = sisoStudentInfoServiceImpl.selectStudentInfoByUserId(userDto.getId());

        SisoExcellclassInfoDto sisoExcellclassInfoDto = new SisoExcellclassInfoDto();
        if (null != t){
            sisoExcellclassInfoDto = t;
        }
        sisoExcellclassInfoDto.setClassid(sisoStudentInfoDto.getClassCode());

        PageHelper.startPage(currentPage, pageSize);
        List<SisoExcellclassInfoDto> pList = sisoExcellclassInfoService.selectExcellClassInfoByClassid(sisoExcellclassInfoDto);
        PageInfo page = new PageInfo(pList,5);
        return Msg.success().add("PageInfo",page);
    }

//   @RequestMapping("/onestudent/highThreeGood")
//    @ResponseBody
//    public Msg selectHighThreeGoodOfStudent(@RequestParam(value="currentPage",defaultValue="1")Integer currentPage
//            ,@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize
//            ,@Param(value = "t") SisoHighExcellentDto t){
//        if (null== SecurityUtils.getSubject().getSession()){
//            //logger.info("Session has been invalidated! Can not select HighExcellent Of Student!");
//            return Msg.failure();
//        }
//        SysUserDto userDto = (SysUserDto) SecurityUtils.getSubject().getSession().getAttribute("user");
//        SisoHighExcellentDto highExcellentDto = new SisoHighExcellentDto();
//        if (null != t){
//            highExcellentDto = t;
//        }
//        highExcellentDto.setEmpid(userDto.getId());
//        highExcellentDto.setAwardType("三好学生");
//
//        PageHelper.startPage(currentPage, pageSize);
//        List<SisoHighExcellentDto> pList = highExcellentService.selectEntitiesByTemplate(highExcellentDto);
//        PageInfo page = new PageInfo(pList,5);
//        return Msg.success().add("PageInfo",page);
//    }
}

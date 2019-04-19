package org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.bureaumanager.bureaumanagerpro.base.BaseController;
import org.bureaumanager.bureaumanagerpro.model.dormitoryManager.pojo.dto.SisoDormInfoDto;
import org.bureaumanager.bureaumanagerpro.model.dormitoryManager.service.impl.SisoDormInfoServiceImpl;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo.dto.SisoPunishmentDto;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.service.impl.SisoPunishmentServiceImpl;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.pojo.dto.SisoClassInfoDto;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.pojo.dto.SisoStudentInfoDto;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.service.impl.SisoClassInfoServiceImpl;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.service.impl.SisoStudentInfoServiceImpl;
import org.bureaumanager.bureaumanagerpro.model.sysManager.service.impl.SysEmpInfoServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.Msg;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysEmpInfoDto;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysUserDto;
import org.bureaumanager.bureaumanagerpro.sysConfig.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/stu")
public class StudentInfoController extends BaseController{

    @Autowired
    private SisoClassInfoServiceImpl sisoClassInfoService;
    @Autowired
    private SysUserServiceImpl sysUserService;
    @Autowired
    private SysEmpInfoServiceImpl sysEmpInfoService;
    @Autowired
    private SisoStudentInfoServiceImpl sisoStudentInfoService;
    @Autowired
    private SisoDormInfoServiceImpl sisoDormInfoService;
    @Autowired
    private SisoPunishmentServiceImpl sisoPunishmentService;

    @RequestMapping(value = "/commTree2",method ={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public List<Map> selectCollegeAndClassTree(@RequestBody String data) throws Exception{
        Map<String,String> o = (Map)JSON.parse(data);
        String grade = o.get("grade");
        return super.selectCollegeAndClassTree(grade);
    }


    @RequestMapping(value = "/queryByParams",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Msg selectStudentInfoTables(@RequestBody(required = false)SisoStudentInfoDto t){
        PageHelper.startPage(t.getCurrentPage(),t.getPageSize());
        List<SisoStudentInfoDto> stus = sisoStudentInfoService.selectEntitiesByTemplate(t);
        PageInfo page = new PageInfo(stus,5);
        return Msg.success().add("PageInfo",page);
    }


    @RequestMapping(value = "/insertOneStudentInfo",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Msg insertOneStudentInfoByTemplate(@RequestBody SisoStudentInfoDto t) {
        SisoClassInfoDto classInfoDto = sisoClassInfoService.selectEntityByPrimaryKey(t.getClassCode());
        t.setSpecialtyCode(classInfoDto.getSpecialtyCode());
        t.setAdviserCode(classInfoDto.getAdviserCode());
        boolean flg = sisoStudentInfoService.insertByTemplate(t);
        if (flg){
            SysEmpInfoDto empInfoDto = new SysEmpInfoDto();
            empInfoDto.setId(t.getId());
            empInfoDto.setEmpId(t.getId());
            empInfoDto.setOrgId(t.getOrgId());
            empInfoDto.setSpecialty(t.getSpecialtyCode());
            empInfoDto.setRealname(t.getEmpName());
            empInfoDto.setCardtype(t.getCardType());
            empInfoDto.setCardno(t.getCardNo());
            SysUserDto userDto = new SysUserDto();
            userDto.setId(t.getId());
            userDto.setPassword("000000");
            userDto.setUserType("S");
            String Md5_Pwd = DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes());
            userDto.setPassword(Md5_Pwd);
            if (sysEmpInfoService.countByPrimaryKey(t.getId())){
                sysEmpInfoService.updateEntityByPrimaryKeySelective(empInfoDto);
            }else {
                sysEmpInfoService.insertEntitySelective(empInfoDto);
            }
            if (sysUserService.countByPrimaryKey(t.getId())){
                sysUserService.updateEntityByPrimaryKeySelective(userDto);
            }else {
                sysUserService.insertEntitySelective(userDto);
            }
            return Msg.success();
        }else {
            return Msg.failure();
        }
    }

    @RequestMapping(value = "/updateOneStudentInfo",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Msg updateOneStudentInfoByTemplate(@RequestBody SisoStudentInfoDto t) {
        SisoClassInfoDto classInfoDto = sisoClassInfoService.selectEntityByPrimaryKey(t.getClassCode());
        t.setSpecialtyCode(classInfoDto.getSpecialtyCode());
        t.setAdviserCode(classInfoDto.getAdviserCode());
        boolean flg = sisoStudentInfoService.updateByTemplate(t);
        if (flg){
            SysEmpInfoDto empInfoDto = new SysEmpInfoDto();
            empInfoDto.setId(t.getId());
            empInfoDto.setEmpId(t.getId());
            empInfoDto.setOrgId(t.getOrgId());
            empInfoDto.setSpecialty(t.getSpecialtyCode());
            empInfoDto.setRealname(t.getEmpName());
            empInfoDto.setCardtype(t.getCardType());
            empInfoDto.setCardno(t.getCardNo());
            if (sysEmpInfoService.countByPrimaryKey(t.getId())){
                sysEmpInfoService.updateEntityByPrimaryKeySelective(empInfoDto);
            }else {
                sysEmpInfoService.insertEntitySelective(empInfoDto);
            }
            return Msg.success();
        }else {
            return Msg.failure();
        }
    }

    @RequestMapping(value = "/deleteSelectedStudentInfo",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Msg deleteStudentInfoBySelected(@RequestBody List<String> ids) {
        boolean flg = sisoStudentInfoService.deleteEntityByPrimaryKeys(ids);
        if (flg){
            sysEmpInfoService.deleteEntityByPrimaryKeys(ids);
            sysUserService.deleteEntityByPrimaryKeys(ids);
            return Msg.success();
        }else {
            return Msg.failure();
        }
    }

    @RequestMapping("/studentInfo")
    @ResponseBody
    public SisoStudentInfoDto selectStudentInfoByUserId(){
        SisoStudentInfoDto studentInfo =new SisoStudentInfoDto();
        if (null==SecurityUtils.getSubject().getSession()){
            return studentInfo;
        }
            SysUserDto userDto = (SysUserDto) SecurityUtils.getSubject().getSession().getAttribute("user");
            studentInfo = sisoStudentInfoService.selectDetailsStudentInfoByUserId(userDto.getId());
            //studentInfo.setNativePlace(studentInfo.getFamilyAddress().substring(0,3));
            //if ("f".equals(studentInfo.getGender().trim())){
           //     studentInfo.setGender("女");
            //}else if ("m".equals(studentInfo.getGender().trim())){
            //    studentInfo.setGender("男");
           // }
        return studentInfo;
    }

    @RequestMapping("/dormInfo")
    @ResponseBody
    public SisoDormInfoDto selectDromOfStudent(){
        SisoDormInfoDto dormInfoDto = new SisoDormInfoDto();
        if (null==SecurityUtils.getSubject().getSession()){
            logger.info("Session has been invalidated! Can not select Drom Of Student!");
            return dormInfoDto;
        }
        SysUserDto userDto = (SysUserDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        SisoStudentInfoDto studentInfo = sisoStudentInfoService.selectStudentInfoByUserId(userDto.getId());
        dormInfoDto = sisoDormInfoService.selectEntityByPrimaryKey(studentInfo.getDromCode());
        return dormInfoDto;
    }
    @RequestMapping(value = "/findDormIdsByAllot",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public List<Map> findDormIdsByAllot(@RequestParam(value = "q",defaultValue = "") String q){
        SisoDormInfoDto dormInfoDto = new SisoDormInfoDto();
        dormInfoDto.setId(q);
        dormInfoDto.setBunkNum(4);//学生寝室一般4人一间
        dormInfoDto.setIsAllot("Y");
        List<SisoDormInfoDto> sisodormInfos = sisoDormInfoService.selectIdsByName(q);
        List<Map> dormIds = new ArrayList <>();
        for (SisoDormInfoDto d : sisodormInfos){

            //判断是否住满4人
            Integer counts = sisoStudentInfoService.countByDormCode(d.getId());
            if (counts >= 4){
                continue;
            }
            Map dormMap= new HashMap();
            dormMap.put("id",d.getId());
            dormMap.put("text",d.getId());
            dormIds.add(dormMap);
        }
        return dormIds;
    }
    @RequestMapping("/punishInfo")
    @ResponseBody
    public Msg selectPunishInfoOfStudent(@RequestParam(value="currentPage",defaultValue="1")Integer currentPage
            ,@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize
            ,@Param(value = "t") SisoPunishmentDto t){
        if (null==SecurityUtils.getSubject().getSession()){
            logger.info("Session has been invalidated! Can not select Drom Of Student!");
            return Msg.failure();
        }
        SysUserDto userDto = (SysUserDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        SisoPunishmentDto punishmentDto = new SisoPunishmentDto();
        if (null != t){
            punishmentDto = t;
        }
        punishmentDto.setEmpId(userDto.getId());

        PageHelper.startPage(currentPage, pageSize);
        List<SisoPunishmentDto> pList = sisoPunishmentService.selectEntitiesByTemplate(punishmentDto);
        PageInfo page = new PageInfo(pList,5);
        return Msg.success().add("PageInfo",page);
    }
}

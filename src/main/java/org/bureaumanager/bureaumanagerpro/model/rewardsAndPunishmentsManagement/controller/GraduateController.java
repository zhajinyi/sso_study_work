package org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.bureaumanager.bureaumanagerpro.base.BaseController;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo.dto.SisoGraduateInfoDto;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.service.impl.SisoGraduateInfoServiceImpl;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.pojo.dto.SisoClassInfoDto;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.service.impl.SisoClassInfoServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.Msg;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysUserDto;
import org.bureaumanager.bureaumanagerpro.sysConfig.utils.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: WangJiaWei
 * @Date: 2019-02-15 15:31
 * @Description:
 */
@Controller
@RequestMapping("/graduate")
public class GraduateController extends BaseController{

    @Autowired
    SisoGraduateInfoServiceImpl sisoGraduateInfoService;

    @RequestMapping(value = "/all" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Msg findAllClass(@RequestBody(required = false) SisoGraduateInfoDto sisoGraduateInfoDto){
        String orgid = sisoGraduateInfoDto.getOrgid();
        if ("0".equals(orgid)){
            sisoGraduateInfoDto.setOrgid(null);
        }
        PageHelper.startPage(sisoGraduateInfoDto.getCurrentPage(),sisoGraduateInfoDto.getPageSize());
        List<SisoGraduateInfoDto> list = sisoGraduateInfoService.queryByTemplate(sisoGraduateInfoDto);
        PageInfo page = new PageInfo(list,5);
        return Msg.success().add("PageInfo", page);
    }

    @RequestMapping(value = "/tree",method =  {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @Override
    public List<Map> selectCollegeAndClassTree(@RequestParam(value = "grade",defaultValue = "",required = false) String grade) throws Exception{
        return super.selectCollegeAndClassTree(grade);
    }

    @RequestMapping("/onestudent")
    @ResponseBody
    public Msg selectBursaryOfStudent(@RequestParam(value="currentPage",defaultValue="1")Integer currentPage
            ,@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize
            ,@Param(value = "t") SisoGraduateInfoDto t){
        if (null== SecurityUtils.getSubject().getSession()){
            //logger.info("Session has been invalidated! Can not select Bursary Of Student!");
            return Msg.failure();
        }
        SysUserDto userDto = (SysUserDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        SisoGraduateInfoDto sisoGraduateInfoDto = new SisoGraduateInfoDto();
        if (t != null){
            sisoGraduateInfoDto = t;
        }
        sisoGraduateInfoDto.setEmpid(userDto.getId());

        PageHelper.startPage(currentPage, pageSize);
        List<SisoGraduateInfoDto> pList = sisoGraduateInfoService.selectEntitiesByTemplate(sisoGraduateInfoDto);
        PageInfo page = new PageInfo(pList,5);
        return Msg.success().add("PageInfo",page);
    }


    private static Long countByJdbc(String orgid) {
        Connection conn = null;
        Statement stm = null;
        ResultSet res = null;
        Long num = Long.valueOf(0);
        try {
            //1.连接数据库
            conn = JdbcUtil.getConnection();
            //2.获取到statement对象
            stm = conn.createStatement();
            //3.执行sql语句
            String sql = "select count(id) num from siso_graduate_info where orgid=" + orgid;
            res = stm.executeQuery(sql);
            while(res.next()){
                //提倡通过列名获取
                num = res.getLong("num");
            }
            //关闭资源
            JdbcUtil.close(conn, stm, res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
}

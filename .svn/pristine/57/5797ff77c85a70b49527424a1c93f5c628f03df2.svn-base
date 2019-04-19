package org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.bureaumanager.bureaumanagerpro.base.BaseController;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo.SisoAdvancedInfo;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo.dto.SisoAdvancedInfoDto;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.service.impl.SisoAdvancedInfoServiceImpl;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.pojo.dto.SisoClassInfoDto;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.service.impl.SisoClassInfoServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.Msg;
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
 * @Date: 2019-02-15 09:25
 * @Description:
 */

@Controller
@RequestMapping("/advancedInfo")
public class AdvancedInfoController extends BaseController {

    @Autowired
    SisoAdvancedInfoServiceImpl sisoAdvancedInfoService;

    @RequestMapping(value = "/all" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Msg findAllClass(@RequestBody(required = false) SisoAdvancedInfoDto sisoAdvancedInfoDto) {
        String orgid = sisoAdvancedInfoDto.getOrgid();
        if (null == orgid || "0".equals(orgid)){
            sisoAdvancedInfoDto.setOrgid(null);
        }
        PageHelper.startPage(sisoAdvancedInfoDto.getCurrentPage(),sisoAdvancedInfoDto.getPageSize());
        List<SisoAdvancedInfoDto> list = sisoAdvancedInfoService.selectEntitiesByTemplate(sisoAdvancedInfoDto);
        PageInfo page = new PageInfo(list,5);
        return Msg.success().add("PageInfo", page);
    }

    @RequestMapping(value = "/tree",method =  {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @Override
    public List<Map> selectCollegeAndClassTree(@RequestParam(value = "grade",defaultValue = "",required = false) String grade) throws Exception{
        return super.selectCollegeAndClassTree(grade);
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
            String sql = "select count(id) num from siso_advanced_info where orgid=" + orgid;
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

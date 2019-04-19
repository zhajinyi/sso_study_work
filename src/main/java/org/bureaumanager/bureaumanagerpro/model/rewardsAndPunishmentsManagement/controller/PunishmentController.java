package org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.bureaumanager.bureaumanagerpro.base.BaseController;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo.dto.SisoPunishmentDto;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.service.impl.SisoPunishmentServiceImpl;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.pojo.dto.SisoClassInfoDto;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.service.impl.SisoClassInfoServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.Msg;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.SysOrganization;
import org.bureaumanager.bureaumanagerpro.sysConfig.utils.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: BaoLing
 * @Date: 2019/1/11 16:05
 * @Description: PunishmentController
 * @Version: 1.0
 */
@Controller
@RequestMapping("/punish")
public class PunishmentController extends BaseController {
    @Autowired
    SisoPunishmentServiceImpl sisoPunishmentService;
    @Autowired
    SisoClassInfoServiceImpl sisoClassInfoService;

    @RequestMapping(value = "/infos",method = {RequestMethod.POST, RequestMethod.GET})
    public String  getPunishList(HttpServletRequest request) throws Exception {
        List<SisoPunishmentDto> list= sisoPunishmentService.selectAllEntities();
        request.setAttribute("p",list);
        return "models/punishAndAward/punishList";
    }

    @RequestMapping(value = "/all",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Msg findAllPunishment(@RequestBody(required = false) SisoPunishmentDto sisoPunishmentDto) {
        String orgid = sisoPunishmentDto.getOrgid();
        if ("".equals(orgid) || "0".equals(orgid)){
            sisoPunishmentDto.setOrgid(null);
        }
        String result = sisoPunishmentDto.getResult();
        if ("1".equals(result))
            sisoPunishmentDto.setResult("已解除");
        else if ("2".equals(result))
            sisoPunishmentDto.setResult("未解除");
        else
            sisoPunishmentDto.setResult(null);
        String level = sisoPunishmentDto.getLevel();
        if ("1".equals(level))
            sisoPunishmentDto.setLevel("警告");
        else if ("2".equals(level))
            sisoPunishmentDto.setLevel("严重警告");
        else if ("3".equals(level))
            sisoPunishmentDto.setLevel("记过");
        else if ("4".equals(level))
            sisoPunishmentDto.setLevel("留校察看");
        else if ("5".equals(level))
            sisoPunishmentDto.setLevel("开除学籍");
        else
            sisoPunishmentDto.setLevel(null);
        PageHelper.startPage(sisoPunishmentDto.getCurrentPage(), sisoPunishmentDto.getPageSize());
        List<SisoPunishmentDto> punishList = sisoPunishmentService.queryByTemplate(sisoPunishmentDto);
        PageInfo page = new PageInfo(punishList,5);
        return Msg.success().add("PageInfo",page);
    }

    @RequestMapping(value = "/tree",method =  {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @Override
    public List<Map> selectCollegeAndClassTree(@RequestParam(value = "grade",defaultValue = "",required = false) String grade) throws Exception{
        return super.selectCollegeAndClassTree(grade);
    }

    @RequestMapping(value = "/getAllOrg",method =  {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public SysOrganization[] getAllOrg() {
        return selectAllOrg();
    }

    @RequestMapping(value = "/getAllClass",method =  {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public SisoClassInfoDto[] getAllClass(@RequestParam String orgid) {
        Long id = Long.valueOf(orgid);
        if (!(id > 0))
            return null;
        List<SisoClassInfoDto> list = sisoClassInfoService.selectEntitiesByOrgId(id.toString(), "");
        SisoClassInfoDto[] sisoClassInfoDtos = new SisoClassInfoDto[list.size()];
        for (int i = 0; i < sisoClassInfoDtos.length; i++)
            sisoClassInfoDtos[i] = list.get(i);
        return sisoClassInfoDtos;
    }

    private static SysOrganization[] selectAllOrg() {
        Connection conn = null;
        Statement stm = null;
        ResultSet res = null;
        List<SysOrganization> list = new ArrayList<SysOrganization>();
        try {
            //1.连接数据库
            conn = JdbcUtil.getConnection();
            //2.获取到statement对象
            stm = conn.createStatement();
            //3.执行sql语句
            String sql = "select * from sys_organization where id != 0 and org_name like \'%学院\' order by sort asc";
            res = stm.executeQuery(sql);
            while(res.next()){
                //提倡通过列名获取
                SysOrganization sysOrganization = new SysOrganization();
                sysOrganization.setId(res.getString("id"));
                sysOrganization.setOrgName(res.getString("org_name"));
                list.add(sysOrganization);
            }
            //关闭资源
            JdbcUtil.close(conn, stm, res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SysOrganization[] sysOrganizations = new SysOrganization[list.size()];
        for (int i = 0; i < sysOrganizations.length; i++)
            sysOrganizations[i] = list.get(i);
        return sysOrganizations;
    }
}

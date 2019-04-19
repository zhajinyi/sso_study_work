package org.bureaumanager.bureaumanagerpro.model.sysManager.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.bureaumanager.bureaumanagerpro.model.sysManager.service.impl.SysEmpInfoServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.Msg;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysEmpInfoDto;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysOrganizationDto;
import org.bureaumanager.bureaumanagerpro.sysConfig.service.impl.SysOrganizationServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.service.impl.SysUserServiceImpl;
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
 * @Date: 2019-02-19 09:15
 * @Description:
 */
@Controller
@RequestMapping("/sysemp")
public class EmpController {
    @Autowired
    SysEmpInfoServiceImpl sysEmpInfoService;
    @Autowired
    SysOrganizationServiceImpl sysOrganizationService;
    @Autowired
    SysUserServiceImpl sysUserService;

    @RequestMapping(value = "/emps" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Msg findAllEmps(@RequestBody(required = false) SysEmpInfoDto sysEmpInfoDto) {
        String orgid = sysEmpInfoDto.getOrgId();
        if (null == orgid || "0".equals(orgid)){
            sysEmpInfoDto.setOrgId(null);
        }
        sysEmpInfoDto.setUserType("T");
        PageHelper.startPage(sysEmpInfoDto.getCurrentPage(),sysEmpInfoDto.getPageSize());
        List<SysEmpInfoDto> list = sysEmpInfoService.selectEntitiesByTemplate(sysEmpInfoDto);
        PageInfo page = new PageInfo(list,5);
        return Msg.success().add("PageInfo", page);
    }

    @RequestMapping(value = "/getbyid" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public SysEmpInfoDto getById(@RequestParam("id") String id){
        if (id == null || "".equals(id)) return null;
        SysEmpInfoDto sysEmpInfoDto = sysEmpInfoService.selectEntityByPrimaryKey(id);
        return sysEmpInfoDto;
    }

    @RequestMapping(value = "/update" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String update(@RequestBody(required = false) SysEmpInfoDto sysEmpInfoDto){
        if (sysEmpInfoDto.getId() == null || "".equals(sysEmpInfoDto.getId()))
            return "0";
        boolean flag = sysEmpInfoService.updateEntityByPrimaryKey(sysEmpInfoDto);
        String msg = flag == true ? "1" : "2";
        return msg;
    }

    @RequestMapping(value = "/allorg" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public SysOrganizationDto[] getAllOrg(){
        List<SysOrganizationDto> list = sysOrganizationService.selectAllEntities();
        SysOrganizationDto [] outs = new SysOrganizationDto[list.size()];
        for (int i = 0; i < list.size(); i++) {
            outs[i] = list.get(i);
        }
        return outs;
    }

    @RequestMapping("/tree")
    @ResponseBody
    public JSONObject getTree() throws Exception{
        return selectCollegeTree();
    }

    /**
     * 获取学院tree
     * @return
     * @throws Exception
     */
    private JSONObject selectCollegeTree() throws Exception{
        //获取学院list
        List<SysOrganizationDto> list = sysOrganizationService.selectAllEntities();
        Map mapTree=new HashMap();
        List<Map<String,String>> mapItemList = new ArrayList<Map<String,String>>();
        if (list.size()>0)
            for (SysOrganizationDto o : list){
                if ("0".equals(o.getId())) continue;
                Map mapIt=new HashMap();
                //Long counts=countByJdbc(o.getId());
                String orgName = o.getOrgName()/*+"("+counts+")"*/;
                mapIt.put("id",o.getId());
                mapIt.put("name",orgName);
                mapItemList.add(mapIt);
            }
        if (!mapItemList.isEmpty()){
            Map<String,List<Map<String,String>>> m = new HashMap <String,List<Map<String,String>>>();
            m.put("children",mapItemList);
            mapTree.putAll(m);
        }
        mapTree.put("id",0);
        mapTree.put("spread",true);
        mapTree.put("name","苏州工业园区服务外包职业学院");
        return new JSONObject(mapTree);
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
            String sql = "select count(e.id) num from sys_emp_info e,sys_user u where e.id=u.id and u.user_type='T' and e.org_id=" + orgid;
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

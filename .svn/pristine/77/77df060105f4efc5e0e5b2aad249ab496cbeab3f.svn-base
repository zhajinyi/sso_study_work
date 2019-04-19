package org.bureaumanager.bureaumanagerpro.model.sysManager.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.bureaumanager.bureaumanagerpro.model.sysManager.service.impl.SysRoleServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.Msg;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.SysUserRoleKey;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysOrganizationDto;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysRoleDto;
import org.bureaumanager.bureaumanagerpro.sysConfig.service.impl.SysOrganizationServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.service.impl.SysUserRoleKeyServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.service.impl.SysRoleMenuServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.utils.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * @Author: WangJiaWei
 * @Date: 2019-03-08 09:56
 * @Description:
 */
@Controller
@RequestMapping("/sysRole")
public class RoleController {
    @Autowired
    SysRoleServiceImpl sysRoleService;
    @Autowired
    SysOrganizationServiceImpl sysOrganizationService;
    @Autowired
    SysUserRoleKeyServiceImpl sysUserRoleKeyService;
    @RequestMapping(value = "/roles" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Msg queryAllRoles(@RequestBody(required = false)SysRoleDto sysRoleDto){
        sysRoleDto.setDelFlag("0");
        List<SysRoleDto> list = sysRoleService.selectByIdOrName(sysRoleDto);
        PageHelper.startPage(sysRoleDto.getCurrentPage(),sysRoleDto.getPageSize());
        PageInfo page = new PageInfo(list,5);
        return Msg.success().add("PageInfo", page);
    }

    @RequestMapping(value = "/getById" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public SysRoleDto getById(@RequestParam String id){
        if (id == null || "".equals(id))
            return null;
        SysRoleDto sysRoleDto = new SysRoleDto();
        sysRoleDto.setId(id);
        sysRoleDto.setDelFlag("0");
        List<SysRoleDto> list = sysRoleService.selectEntitiesByTemplate(sysRoleDto);
        if (list.size() == 1)
            return list.get(0);
        else
            return null;
    }

    @RequestMapping(value = "/getRolesBySelective",method = {RequestMethod.POST})
    @ResponseBody
    public Msg getRolesBySelective(@RequestBody SysRoleDto sysRoleDto){
        List<SysRoleDto> sysRoleDtos = sysRoleService.selectEntitiesByTemplate(sysRoleDto);
        PageInfo pageInfo = new PageInfo(sysRoleDtos,5);
    return Msg.success().add("PageInfo",pageInfo);
    }

    @RequestMapping(value = "/addRole" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public boolean addRole(@RequestBody SysRoleDto sysRoleDto){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        sysRoleDto.setCreateDate(timestamp);
        sysRoleDto.setUpdateDate(timestamp);
        if (sysRoleDto.getName() == null || "".equals(sysRoleDto.getName()))
            return false;
        sysRoleDto.setDelFlag("0");
        Long id = getMaxIdByJdbc() + 1;
        sysRoleDto.setId(id.toString());
        boolean flag = sysRoleService.insertEntitySelective(sysRoleDto);
        return flag;
    }

    @RequestMapping(value = "/updateRole" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public boolean updateRole(@RequestBody SysRoleDto sysRoleDto){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        sysRoleDto.setUpdateDate(timestamp);
        sysRoleDto.setCreateBy(null);
        if (sysRoleDto.getId() == null || "".equals(sysRoleDto.getId()))
            return false;
        sysRoleDto.setDelFlag("0");
        boolean flag = sysRoleService.updateByPrimaryKeySelective(sysRoleDto);
        return flag;
    }

    @RequestMapping(value = "/delRole" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public boolean delRole(@RequestBody SysRoleDto[] sysRoleDtos){
        if (sysRoleDtos.length >= 1) {
            StringBuffer ids = new StringBuffer();
            for (SysRoleDto sysRoleDto : sysRoleDtos) {
                ids.append(Long.valueOf(sysRoleDto.getId()).toString() + ",");
            }
            String sqlIds = ids.substring(0, ids.length()-1);
            String sql = "UPDATE sys_role SET del_flag=1 WHERE id IN(" + sqlIds + ")";
            String[] jdbcSql = new String[1];
            jdbcSql[0] = sql;
            int count = updateOrDelete(jdbcSql);
            return count > 0 ? true : false;
        } else
            return false;
    }

    @RequestMapping(value = "/deleteRoles",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public boolean deleteRoles(@RequestBody List<SysRoleDto> list) {
        boolean flg1 = sysRoleService.deleteByIds(list);
        String roleId = list.get(0).getId();
        boolean flg2 = sysUserRoleKeyService.deleteByRoleId(roleId);
        if (flg1&&flg2){
            return true;
        }else {
            return false;
        }

    }
    @RequestMapping(value = "/allOrg" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public SysOrganizationDto[] allOrg(){
        List<SysOrganizationDto> orgs= getAllorg();
        SysOrganizationDto[] sysOrganizationDtos = new SysOrganizationDto[orgs.size()];
        for (int i = 0; i < orgs.size(); i++){
            sysOrganizationDtos[i] = orgs.get(i);
        }
        return sysOrganizationDtos;
    }

    @RequestMapping(value = "/findIds",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public List<Map> findIdAndNameByRealName(@RequestParam(value = "q",defaultValue = "") String q){
        List<SysOrganizationDto> OrgList = sysOrganizationService.selectIdsByNameAll(q);
        List<Map> org = new ArrayList<>();
        for (SysOrganizationDto s : OrgList){
            Map orgMap= new HashMap();
            orgMap.put("id",s.getId());
            orgMap.put("text",s.getOrgName());
            org.add(orgMap);
        }
        return org;
    }



    private static Long getMaxIdByJdbc() {
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
            String sql = "select max(id+0) num from sys_role";
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

    private static List<String[]> selectByJdbc(String sql, Integer cols) {
        Connection conn = null;
        Statement stm = null;
        ResultSet res = null;
        List<String[]> list = new ArrayList<String[]>();
        try {
            //1.连接数据库
            conn = JdbcUtil.getConnection();
            //2.获取到statement对象
            stm = conn.createStatement();
            //3.执行sql语句
            res = stm.executeQuery(sql);
            while(res.next()){
                String[] strs = new String[cols];
                for (int i = 0; i < cols; i++) {
                    strs[i] = res.getString(i+1);
                }
                list.add(strs);
            }
            //关闭资源
            JdbcUtil.close(conn, stm, res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static List<SysOrganizationDto> getAllorg() {
        Connection conn = null;
        Statement stm = null;
        ResultSet res = null;
        List<SysOrganizationDto> list = new ArrayList<SysOrganizationDto>();
        try {
            //1.连接数据库
            conn = JdbcUtil.getConnection();
            //2.获取到statement对象
            stm = conn.createStatement();
            //3.执行sql语句
            String sql = "SELECT id,org_name FROM sys_organization ORDER BY sort ASC";
            res = stm.executeQuery(sql);
            while(res.next()){
                SysOrganizationDto sysOrganization = new SysOrganizationDto();
                sysOrganization.setId(res.getString("id"));
                sysOrganization.setOrgName(res.getString("org_name"));
                list.add(sysOrganization);
            }
            //关闭资源
            JdbcUtil.close(conn, stm, res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static int updateOrDelete(String[] sqls) {
        Connection conn = null;
        Statement stm = null;
        int count = 0;
        try {
            //1.连接数据库
            conn = JdbcUtil.getConnection();
            conn.setAutoCommit(false);
            //2.获取到statement对象
            stm = conn.createStatement();
            //3.执行sql语句
            for (String sql : sqls)
                count += stm.executeUpdate(sql);
            conn.commit();
        } catch (SQLException e) {
            count = 0;
            e.printStackTrace();
        }
        //关闭资源
        JdbcUtil.close(conn, stm);
        return count;
    }

    @Autowired
    SysRoleMenuServiceImpl sysRoleMenuService;

    /**
     * 根据条件进行所有角色的查询，用于菜单管理中的分配权限页面；
     * @param sysRoleDto 前台传过来的查询信息
     * @return
     */
    @RequestMapping(value = "/selectRolesByMenuIds", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Msg selectRolesByMenuIds(@RequestBody SysRoleDto sysRoleDto){
        List<String> menuIds = sysRoleMenuService.getRolebyMenuId(sysRoleDto.getMenuId());
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("menuIds", menuIds);map.put("sysRoleDto",sysRoleDto);
        List<SysRoleDto> roleIds = sysRoleService.selectRolesByMenuIds(map);
        PageInfo pageInfo = new PageInfo(roleIds,5);
        return Msg.success().add("PageInfo", pageInfo);
    }



}

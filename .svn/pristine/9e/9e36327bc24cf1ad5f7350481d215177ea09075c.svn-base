package org.bureaumanager.bureaumanagerpro.sysConfig.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.Msg;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysUserRoleKeyDto;
import org.bureaumanager.bureaumanagerpro.sysConfig.service.impl.SysUserRoleKeyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/userRole")
public class UserRoleController {
    @Autowired
    SysUserRoleKeyServiceImpl sysUserRoleKeyService;

    @RequestMapping(value = "/getInRole" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Msg findInRole(@RequestBody(required = false) SysUserRoleKeyDto sysUserRoleKeyDto) {
        PageHelper.startPage(sysUserRoleKeyDto.getCurrentPage(),sysUserRoleKeyDto.getPageSize());
        List<SysUserRoleKeyDto> list = sysUserRoleKeyService.selectInRole(sysUserRoleKeyDto);
        PageInfo page = new PageInfo(list,5);
        return Msg.success().add("PageInfo", page);
    }

    @RequestMapping(value = "/getNotInRole" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Msg findNotInRole(@RequestBody(required = false) SysUserRoleKeyDto sysUserRoleKeyDto) {
        PageHelper.startPage(sysUserRoleKeyDto.getCurrentPage(),sysUserRoleKeyDto.getPageSize());
        List<SysUserRoleKeyDto> list = sysUserRoleKeyService.selectNotInRole(sysUserRoleKeyDto);
        PageInfo page = new PageInfo(list,5);
        return Msg.success().add("PageInfo", page);
    }

    @RequestMapping(value = "/insertRoles",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public boolean insertRoles(@RequestBody List<SysUserRoleKeyDto> list) {

        boolean flg = sysUserRoleKeyService.insertList(list);
        if (flg){
            return true;
        }else {
            return false;
        }

    }


    @RequestMapping(value = "/deleteRoles",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public boolean deleteRoles(@RequestBody List<SysUserRoleKeyDto> list) {
        boolean flg = sysUserRoleKeyService.deleteByuserIdAndRoleId(list);
        if (flg){
            return true;
        }else {
            return false;
        }

    }

    @RequestMapping(value = "/selectNotRoles" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Msg selectNotRoles(@RequestBody String orgId,String realName,String roleId) {
        SysUserRoleKeyDto sysUserRoleKeyDto = new SysUserRoleKeyDto();
        sysUserRoleKeyDto.setOrgId(orgId);
        sysUserRoleKeyDto.setRealName(realName);
        sysUserRoleKeyDto.setRoleId(roleId);
        PageHelper.startPage(sysUserRoleKeyDto.getCurrentPage(),sysUserRoleKeyDto.getPageSize());
        List<SysUserRoleKeyDto> list = sysUserRoleKeyService.selectInRole(sysUserRoleKeyDto);
        PageInfo page = new PageInfo(list,5);
        return Msg.success().add("PageInfo", page);
    }

}

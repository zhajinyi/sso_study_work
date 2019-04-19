package org.bureaumanager.bureaumanagerpro.sysConfig.controller;

import com.github.pagehelper.PageInfo;
import org.bureaumanager.bureaumanagerpro.base.BaseController;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.Msg;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysRoleMenuKeyDto;
import org.bureaumanager.bureaumanagerpro.sysConfig.service.impl.SysRoleMenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhajinyi
 * @create 2019-03-13 17:44
 */
@Controller
@RequestMapping("/menuRole")
public class MenuRoleController extends BaseController {

    @Autowired
    SysRoleMenuServiceImpl sysRoleMenuService;

    @RequestMapping(value = "/entity/insert", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Msg insertEntity(@RequestParam(value = "roleIds") String roleIds ,@RequestParam(value = "menuId") String menuId, @RequestParam(value ="menuPermission" ) String menuPermission){
        String[] roleIdArray = roleIds.split(",");
        List<SysRoleMenuKeyDto> sysRoleMenuKeyDtos = new ArrayList<SysRoleMenuKeyDto>();
        boolean flag = false;
        for(String roleId : roleIdArray){
            SysRoleMenuKeyDto sysRoleMenuKeyDto = new SysRoleMenuKeyDto();
            sysRoleMenuKeyDto.setMenuId(menuId);sysRoleMenuKeyDto.setRoleId(roleId);sysRoleMenuKeyDto.setMenuPermission(menuPermission);
            sysRoleMenuKeyDtos.add(sysRoleMenuKeyDto);
        }
        flag = sysRoleMenuService.insertByBatch(sysRoleMenuKeyDtos);
        return flag?Msg.success(): Msg.failure();
    }

    @RequestMapping(value = "/entity/update",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Msg updateEntity(@RequestBody SysRoleMenuKeyDto sysRoleMenuKeyDto){
        boolean flag = sysRoleMenuService.updateByPrimaryKeySelective(sysRoleMenuKeyDto);
        return flag? Msg.success():Msg.failure();
    }


    @RequestMapping(value = "/entity/select", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Msg selectBySelective(@RequestBody SysRoleMenuKeyDto sysRoleMenuKeyDto){
        List<SysRoleMenuKeyDto> sysRoleMenuKeyDtos = sysRoleMenuService.selectRoleMenuBySelectivce(sysRoleMenuKeyDto);
        PageInfo pageInfo = new PageInfo(sysRoleMenuKeyDtos,5);
        return Msg.success().add("PageInfo",pageInfo);
    }

    @RequestMapping(value = "/entity/delete", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Msg deleteEntityByMenuIdandRoleId(@RequestBody List<SysRoleMenuKeyDto> sysRoleMenuKeyDtos){
        boolean flag = false;
        for (SysRoleMenuKeyDto sysRoleMenuKeyDto : sysRoleMenuKeyDtos){
            Map<String,String> map = new HashMap<String, String>();
            map.put("menuId",sysRoleMenuKeyDto.getMenuId());
            map.put("roleId",sysRoleMenuKeyDto.getRoleId());
            flag = sysRoleMenuService.deleteEntityByMenuIdandRoleId(map);
        }

        return flag? Msg.success(): Msg.failure();
    }


}

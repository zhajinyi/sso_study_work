package org.bureaumanager.bureaumanagerpro.sysConfig.service.impl;

import org.bureaumanager.bureaumanagerpro.base.BaseServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.dao.SysUserRoleMapper;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysUserRoleKeyDto;
import org.bureaumanager.bureaumanagerpro.sysConfig.service.SysUserRoleKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysUserRoleKeyServiceImpl extends BaseServiceImpl<SysUserRoleKeyDto,SysUserRoleMapper> implements SysUserRoleKeyService{
    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysUserRoleKeyDto> selectInRole(SysUserRoleKeyDto t) {
        return sysUserRoleMapper.selectInRole(t);
    }

    @Override
    public List<SysUserRoleKeyDto> selectNotInRole(SysUserRoleKeyDto t) {
        return sysUserRoleMapper.selectNotInRole(t);
    }

    @Override
    public boolean insertList(List<SysUserRoleKeyDto> list) {
        return sysUserRoleMapper.insertList(list)> 0;
    }
    @Override
    public boolean deleteByuserIdAndRoleId(List<SysUserRoleKeyDto> list) {
        String roleId = list.get(0).getRoleId();
        return sysUserRoleMapper.deleteByuserIdAndRoleId(roleId,list)> 0;
    }
    @Override
    public boolean deleteByRoleId(String userId) {
        return sysUserRoleMapper.deleteByRoleId(userId)>= 0;
    }

}

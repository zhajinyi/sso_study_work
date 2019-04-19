package org.bureaumanager.bureaumanagerpro.model.sysManager.service.impl;

import org.bureaumanager.bureaumanagerpro.base.BaseServiceImpl;
import org.bureaumanager.bureaumanagerpro.model.sysManager.service.SysRoleService;
import org.bureaumanager.bureaumanagerpro.sysConfig.dao.SysRoleMapper;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleDto, SysRoleMapper> implements SysRoleService {
    @Autowired
    SysRoleMapper sysRoleMapper;
    public List<SysRoleDto> selectByIdOrName (SysRoleDto t) {

        return sysRoleMapper.selectByIdOrName(t);
    }
    @Override
    public boolean updateByPrimaryKeySelective(SysRoleDto t) {
        return sysRoleMapper.updateByPrimaryKeySelective(t)>0;
    }
    @Override
    public boolean deleteByIds(List<SysRoleDto> list) {
        return sysRoleMapper.deleteByIds(list)> 0;
    }

    @Override
    public List<SysRoleDto> selectRolesByMenuIds(Map<String,Object> map) {
        return sysRoleMapper.selectRolesByMenuIds(map);
    }
}

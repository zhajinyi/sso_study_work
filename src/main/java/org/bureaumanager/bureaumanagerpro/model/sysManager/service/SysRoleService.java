package org.bureaumanager.bureaumanagerpro.model.sysManager.service;

import org.bureaumanager.bureaumanagerpro.base.BaseService;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysRoleDto;

import java.util.List;
import java.util.Map;

public interface SysRoleService extends BaseService<SysRoleDto> {
    List<SysRoleDto> selectByIdOrName(SysRoleDto sysRoleDto);
    boolean updateByPrimaryKeySelective(SysRoleDto t);
    boolean deleteByIds(List<SysRoleDto> sysRoleDto);
    List<SysRoleDto> selectRolesByMenuIds(Map<String,Object> map);
}

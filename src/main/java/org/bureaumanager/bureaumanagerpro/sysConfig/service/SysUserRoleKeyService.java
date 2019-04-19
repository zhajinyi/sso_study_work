package org.bureaumanager.bureaumanagerpro.sysConfig.service;

import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.SysUserRoleKey;
import java.util.List;
import org.bureaumanager.bureaumanagerpro.base.BaseService;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysUserRoleKeyDto;

public interface SysUserRoleKeyService extends BaseService<SysUserRoleKeyDto> {

    List<SysUserRoleKeyDto> selectInRole(SysUserRoleKeyDto sysUserRoleKeyDto);
    List<SysUserRoleKeyDto> selectNotInRole(SysUserRoleKeyDto sysUserRoleKeyDto);
    boolean insertList(List<SysUserRoleKeyDto> list);
    boolean deleteByuserIdAndRoleId(List<SysUserRoleKeyDto> sysUserRoleKeyDto);
    boolean deleteByRoleId(String userId);

}

package org.bureaumanager.bureaumanagerpro.sysConfig.service;

import org.apache.ibatis.annotations.Param;
import org.bureaumanager.bureaumanagerpro.base.BaseService;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysUserDto;


public interface SysUserService extends BaseService<SysUserDto> {
    SysUserDto findByUsername(@Param("username") String username);

}

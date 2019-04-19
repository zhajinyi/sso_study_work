package org.bureaumanager.bureaumanagerpro.sysConfig.dao;

import org.apache.ibatis.annotations.Param;
import org.bureaumanager.bureaumanagerpro.base.BaseMapper;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.SysUserRoleKey;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysUserRoleKeyDto;

import java.util.List;
public interface SysUserRoleMapper extends BaseMapper <SysUserRoleKeyDto>{
    List<SysUserRoleKeyDto> selectInRole(@Param("t") SysUserRoleKeyDto t);
    List<SysUserRoleKeyDto> selectNotInRole(@Param("t") SysUserRoleKeyDto t);
    int insertList(List<SysUserRoleKeyDto> list);
    int deleteByuserIdAndRoleId(@Param("roleId")String roleId,@Param("list")List<SysUserRoleKeyDto> list);
    int deleteByRoleId(@Param("roleId")String roleId);
}
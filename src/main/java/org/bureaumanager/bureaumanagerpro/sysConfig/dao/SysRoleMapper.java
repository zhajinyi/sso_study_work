package org.bureaumanager.bureaumanagerpro.sysConfig.dao;

import org.apache.ibatis.annotations.Param;
import org.bureaumanager.bureaumanagerpro.base.BaseMapper;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysRoleDto;

import java.util.List;
import java.util.Map;

public interface SysRoleMapper extends BaseMapper<SysRoleDto> {
    List<SysRoleDto> selectByIdOrName(@Param("t") SysRoleDto t);
    int updateByPrimaryKeySelective(SysRoleDto t);
    List<SysRoleDto> selectRolesByUserId(String userId);
    int deleteByIds(@Param("list")List<SysRoleDto> list);
    List<SysRoleDto> selectRolesByMenuIds(Map<String,Object> map);

}
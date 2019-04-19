package org.bureaumanager.bureaumanagerpro.sysConfig.dao;

import org.apache.ibatis.annotations.Param;
import org.bureaumanager.bureaumanagerpro.base.BaseMapper;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysRoleMenuKeyDto;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenuKeyDto> {

    /**
     * 查询角色被授予的权限
     * @param roleId
     * @return
     */
    Set<SysRoleMenuKeyDto> selectPermssionsByRoleId(String roleId);

    List<String> getRolebyMenuId(@Param("menuId") String menuId);

    List<SysRoleMenuKeyDto> selectRoleMenuBySelectivce(SysRoleMenuKeyDto sysRoleMenuKeyDto);

    int updateByPrimaryKeySelective(SysRoleMenuKeyDto sysRoleMenuKeyDto);

    int deleteEntityByMenuIdandRoleId(Map<String,String> map);

    int insertByBatch(List<SysRoleMenuKeyDto> sysRoleMenuKeyDtos);


}
package org.bureaumanager.bureaumanagerpro.sysConfig.service;

import org.bureaumanager.bureaumanagerpro.base.BaseService;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysRoleMenuKeyDto;

import java.util.List;
import java.util.Map;

/**
 * @author zhajinyi
 * @create 2019-03-13 17:35
 */

public interface SysRoleMenuService extends BaseService<SysRoleMenuKeyDto> {
    //根据菜单的主键，找到对应的role_id集合
    List<String> getRolebyMenuId(String menuId);

    //根据联合主键更新菜单的权限范围
    boolean updateByPrimaryKeySelective(SysRoleMenuKeyDto sysRoleMenuKeyDto);

    //根据对应的条件查询对象;
    List<SysRoleMenuKeyDto> selectRoleMenuBySelectivce(SysRoleMenuKeyDto sysRoleMenuKeyDto);

    //根据menuID和roleID删除对象；
    boolean deleteEntityByMenuIdandRoleId(Map<String,String> map);

    //批量插入数据;
    boolean insertByBatch(List<SysRoleMenuKeyDto> sysRoleMenuKeyDtos);
    
}

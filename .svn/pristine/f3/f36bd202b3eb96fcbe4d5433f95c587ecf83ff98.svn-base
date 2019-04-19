package org.bureaumanager.bureaumanagerpro.sysConfig.dao;

import org.bureaumanager.bureaumanagerpro.base.BaseMapper;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysMenuDto;

import java.util.List;

public interface SysMenuMapper extends BaseMapper<SysMenuDto> {
    /**
     * 判断是否有子项，返回子项的个数
     * @param parentId
     * @return
     */
    int countByParentId(String parentId);

    /**
     * 查找parentId=“0”的顶级菜单
     * @return
     */
    List<SysMenuDto> selectTopMenus();

    /**
     * 查找子项
     * @param parentId
     * @return
     */
    List<SysMenuDto> selectChildrenMenus(String parentId);

    /**
     * 查找父项
     * @param id
     * @return
     */
    SysMenuDto selectParentMenus(String id);

}
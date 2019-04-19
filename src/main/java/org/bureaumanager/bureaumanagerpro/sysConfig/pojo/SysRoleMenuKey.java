package org.bureaumanager.bureaumanagerpro.sysConfig.pojo;

import org.bureaumanager.bureaumanagerpro.base.BaseEntity;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysRoleMenuKeyDto;

public class SysRoleMenuKey extends BaseEntity<SysRoleMenuKeyDto> {
    private String roleId;

    private String menuId;

    private String menuPermission;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public String getMenuPermission() {
        return menuPermission;
    }

    public void setMenuPermission(String menuPermission) {
        this.menuPermission = menuPermission == null ? null : menuPermission.trim();
    }
}
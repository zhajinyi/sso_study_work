package org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto;


import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.SysRole;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.SysRoleMenuKey;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: BaoLing
 * @Date: 2019/1/3 11:12
 * @Description: SysRoleDto
 * @Version: 1.0
 */
public class SysRoleDto extends SysRole {
    private Set<SysRoleMenuKey> permissions = new HashSet<>();//一个角色有多个权限
    private String orgName;
    private Set<SysUserDto> users = new HashSet<>();
    private String menuId;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Set <SysRoleMenuKey> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set <SysRoleMenuKey> permissions) {
        this.permissions = permissions;
    }

    public Set <SysUserDto> getUsers() {
        return users;
    }

    public void setUsers(Set <SysUserDto> users) {
        this.users = users;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}

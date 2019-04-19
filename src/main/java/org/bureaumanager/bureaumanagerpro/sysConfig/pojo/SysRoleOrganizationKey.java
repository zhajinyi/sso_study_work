package org.bureaumanager.bureaumanagerpro.sysConfig.pojo;

import org.bureaumanager.bureaumanagerpro.base.BaseEntity;

public class SysRoleOrganizationKey extends BaseEntity {
    private String roleId;

    private String organizationId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId == null ? null : organizationId.trim();
    }
}
package org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto;

import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.SysUserRoleKey;

public class SysUserRoleKeyDto extends SysUserRoleKey {
    private String empId;
    private String realName;
    private String orgName;
    private String orgId;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}

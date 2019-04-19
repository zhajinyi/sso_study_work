package org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo;

import org.bureaumanager.bureaumanagerpro.base.BaseEntity;

public class SisoAdvancedInfo extends BaseEntity {
    private String empid;

    private String empname;

    private String orgid;

    private String orgname;

    private String graduate;

    private String banjiName;

    private String detail;

    private String dept;

    private String suozaixi;

    private String advancedTime;

    private String banjiId;

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid == null ? null : empid.trim();
    }

    public String getBanjiId() {
        return banjiId;
    }

    public void setBanjiId(String banjiId) {
        this.banjiId = banjiId == null ? null : banjiId.trim();
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname == null ? null : empname.trim();
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid == null ? null : orgid.trim();
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname == null ? null : orgname.trim();
    }

    public String getGraduate() {
        return graduate;
    }

    public void setGraduate(String graduate) {
        this.graduate = graduate == null ? null : graduate.trim();
    }

    public String getBanjiName() {
        return banjiName;
    }

    public void setBanjiName(String banjiName) {
        this.banjiName = banjiName == null ? null : banjiName.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }

    public String getSuozaixi() {
        return suozaixi;
    }

    public void setSuozaixi(String suozaixi) {
        this.suozaixi = suozaixi == null ? null : suozaixi.trim();
    }

    public String getAdvancedTime() {
        return advancedTime;
    }

    public void setAdvancedTime(String advancedTime) {
        this.advancedTime = advancedTime == null ? null : advancedTime.trim();
    }
}
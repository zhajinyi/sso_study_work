package org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.pojo;

import org.bureaumanager.bureaumanagerpro.base.BaseEntity;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.pojo.dto.SisoClassInfoDto;

public class SisoClassInfo extends BaseEntity<SisoClassInfoDto> {

    private String className;

    private String orgId;

    private String grade;

    private String specialtyCode;

    private String adviserCode;

    private String monitorCode;

    private String remark;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getSpecialtyCode() {
        return specialtyCode;
    }

    public void setSpecialtyCode(String specialtyCode) {
        this.specialtyCode = specialtyCode == null ? null : specialtyCode.trim();
    }

    public String getAdviserCode() {
        return adviserCode;
    }

    public void setAdviserCode(String adviserCode) {
        this.adviserCode = adviserCode == null ? null : adviserCode.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getMonitorCode() {
        return monitorCode;
    }

    public void setMonitorCode(String monitorCode) {
        this.monitorCode = monitorCode;
    }
}
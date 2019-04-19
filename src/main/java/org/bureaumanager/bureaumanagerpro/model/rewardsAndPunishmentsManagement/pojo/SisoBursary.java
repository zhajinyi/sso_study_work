package org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo;

import org.bureaumanager.bureaumanagerpro.base.BaseEntity;

public class SisoBursary extends BaseEntity {
    private String empId;
    private String orgId;
    private String empName;
    private String orgName;
    private String className;
    private String sex;
    private String political;
    private String xueKeJiaQuanFen;
    private String xueKeDeFen;
    private String zongHeSuYangFen;
    private String deYuDeFen;
    private String zongHeFen;
    private String bursaryType;
    private String bursaryTime;
    private String remark;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = "".equals(id)?null:id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = "".equals(empId)?null:empId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = "".equals(orgId)?null:orgId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = "".equals(empName)?null:empName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = "".equals(orgName)?null:orgName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = "".equals(className)?null:className;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = "".equals(sex)?null:sex;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = "".equals(political)?null:political;
    }

    public String getXueKeJiaQuanFen() {
        return xueKeJiaQuanFen;
    }

    public void setXueKeJiaQuanFen(String xueKeJiaQuanFen) {
        this.xueKeJiaQuanFen = "".equals(xueKeJiaQuanFen)?null:xueKeJiaQuanFen;
    }

    public String getXueKeDeFen() {
        return xueKeDeFen;
    }

    public void setXueKeDeFen(String xueKeDeFen) {
        this.xueKeDeFen = "".equals(xueKeDeFen)?null:xueKeDeFen;
    }

    public String getZongHeSuYangFen() {
        return zongHeSuYangFen;
    }

    public void setZongHeSuYangFen(String zongHeSuYangFen) {
        this.zongHeSuYangFen = "".equals(zongHeSuYangFen)?null:zongHeSuYangFen;
    }

    public String getDeYuDeFen() {
        return deYuDeFen;
    }

    public void setDeYuDeFen(String deYuDeFen) {
        this.deYuDeFen = "".equals(deYuDeFen)?null:deYuDeFen;
    }

    public String getZongHeFen() {
        return zongHeFen;
    }

    public void setZongHeFen(String zongHeFen) {
        this.zongHeFen = "".equals(zongHeFen)?null:zongHeFen;
    }

    public String getBursaryType() {
        return bursaryType;
    }

    public void setBursaryType(String bursaryType) {
        this.bursaryType = "".equals(bursaryType)?null:bursaryType;
    }

    public String getBursaryTime() {
        return bursaryTime;
    }

    public void setBursaryTime(String bursaryTime) {
        this.bursaryTime = "".equals(bursaryTime)?null:bursaryTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = "".equals(remark)?null:remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SisoBursary that = (SisoBursary) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (empId != null ? !empId.equals(that.empId) : that.empId != null) return false;
        if (orgId != null ? !orgId.equals(that.orgId) : that.orgId != null) return false;
        if (empName != null ? !empName.equals(that.empName) : that.empName != null) return false;
        if (orgName != null ? !orgName.equals(that.orgName) : that.orgName != null) return false;
        if (className != null ? !className.equals(that.className) : that.className != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (political != null ? !political.equals(that.political) : that.political != null) return false;
        if (xueKeJiaQuanFen != null ? !xueKeJiaQuanFen.equals(that.xueKeJiaQuanFen) : that.xueKeJiaQuanFen != null)
            return false;
        if (xueKeDeFen != null ? !xueKeDeFen.equals(that.xueKeDeFen) : that.xueKeDeFen != null) return false;
        if (zongHeSuYangFen != null ? !zongHeSuYangFen.equals(that.zongHeSuYangFen) : that.zongHeSuYangFen != null)
            return false;
        if (deYuDeFen != null ? !deYuDeFen.equals(that.deYuDeFen) : that.deYuDeFen != null) return false;
        if (zongHeFen != null ? !zongHeFen.equals(that.zongHeFen) : that.zongHeFen != null) return false;
        if (bursaryType != null ? !bursaryType.equals(that.bursaryType) : that.bursaryType != null) return false;
        if (bursaryTime != null ? !bursaryTime.equals(that.bursaryTime) : that.bursaryTime != null) return false;
        return remark != null ? remark.equals(that.remark) : that.remark == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (empId != null ? empId.hashCode() : 0);
        result = 31 * result + (orgId != null ? orgId.hashCode() : 0);
        result = 31 * result + (empName != null ? empName.hashCode() : 0);
        result = 31 * result + (orgName != null ? orgName.hashCode() : 0);
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (political != null ? political.hashCode() : 0);
        result = 31 * result + (xueKeJiaQuanFen != null ? xueKeJiaQuanFen.hashCode() : 0);
        result = 31 * result + (xueKeDeFen != null ? xueKeDeFen.hashCode() : 0);
        result = 31 * result + (zongHeSuYangFen != null ? zongHeSuYangFen.hashCode() : 0);
        result = 31 * result + (deYuDeFen != null ? deYuDeFen.hashCode() : 0);
        result = 31 * result + (zongHeFen != null ? zongHeFen.hashCode() : 0);
        result = 31 * result + (bursaryType != null ? bursaryType.hashCode() : 0);
        result = 31 * result + (bursaryTime != null ? bursaryTime.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SisoBursary{" +
                "id='" + id + '\'' +
                ", empId='" + empId + '\'' +
                ", orgId='" + orgId + '\'' +
                ", empName='" + empName + '\'' +
                ", orgName='" + orgName + '\'' +
                ", className='" + className + '\'' +
                ", sex='" + sex + '\'' +
                ", political='" + political + '\'' +
                ", xueKeJiaQuanFen='" + xueKeJiaQuanFen + '\'' +
                ", xueKeDeFen='" + xueKeDeFen + '\'' +
                ", zongHeSuYangFen='" + zongHeSuYangFen + '\'' +
                ", deYuDeFen='" + deYuDeFen + '\'' +
                ", zongHeFen='" + zongHeFen + '\'' +
                ", bursaryType='" + bursaryType + '\'' +
                ", bursaryTime='" + bursaryTime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

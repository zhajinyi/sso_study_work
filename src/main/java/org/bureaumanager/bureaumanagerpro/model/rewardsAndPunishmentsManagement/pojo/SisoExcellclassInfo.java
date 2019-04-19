package org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo;

import org.bureaumanager.bureaumanagerpro.base.BaseEntity;

public class SisoExcellclassInfo extends BaseEntity {

    private String empcode;

    private String empname;

    private String orgid;

    private String orgname;

    private String excellclassTime;

    private String className;

    private String excellYear;

    private String cardId;

    private String phone;

    private String mainStrong;

    private String classid;

    public String getEmpcode() {
        return empcode;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode == null ? null : empcode.trim();
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid == null ? null : classid.trim();
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

    public String getExcellclassTime() {
        return excellclassTime;
    }

    public void setExcellclassTime(String excellclassTime) {
        this.excellclassTime = excellclassTime == null ? null : excellclassTime.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getExcellYear() {
        return excellYear;
    }

    public void setExcellYear(String excellYear) {
        this.excellYear = excellYear == null ? null : excellYear.trim();
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getMainStrong() {
        return mainStrong;
    }

    public void setMainStrong(String mainStrong) {
        this.mainStrong = mainStrong == null ? null : mainStrong.trim();
    }
}
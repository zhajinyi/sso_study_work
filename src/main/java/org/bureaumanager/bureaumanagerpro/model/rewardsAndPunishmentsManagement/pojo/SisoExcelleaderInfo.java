package org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo;

import org.bureaumanager.bureaumanagerpro.base.BaseEntity;

public class SisoExcelleaderInfo extends BaseEntity<SisoExcelleaderInfo>{


    private String empid;

    private String orgid;

    private String empname;

    private String sex;

    private String orgname;

    private String empcode;

    private String banji;

    private String phone;

    private String admissionnumber;

    private String job;

    private String excellentyear;

    private String selfassessment;

    private String avegrafed;

    private String lowgrafed;

    private String classrank;

    private String endtime;

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid == null ? null : empid.trim();
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid == null ? null : orgid.trim();
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname == null ? null : empname.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname == null ? null : orgname.trim();
    }

    public String getEmpcode() {
        return empcode;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode == null ? null : empcode.trim();
    }

    public String getBanji() {
        return banji;
    }

    public void setBanji(String banji) {
        this.banji = banji == null ? null : banji.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAdmissionnumber() {
        return admissionnumber;
    }

    public void setAdmissionnumber(String admissionnumber) {
        this.admissionnumber = admissionnumber == null ? null : admissionnumber.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getExcellentyear() {
        return excellentyear;
    }

    public void setExcellentyear(String excellentyear) {
        this.excellentyear = excellentyear == null ? null : excellentyear.trim();
    }

    public String getSelfassessment() {
        return selfassessment;
    }

    public void setSelfassessment(String selfassessment) {
        this.selfassessment = selfassessment == null ? null : selfassessment.trim();
    }

    public String getAvegrafed() {
        return avegrafed;
    }

    public void setAvegrafed(String avegrafed) {
        this.avegrafed = avegrafed == null ? null : avegrafed.trim();
    }

    public String getLowgrafed() {
        return lowgrafed;
    }

    public void setLowgrafed(String lowgrafed) {
        this.lowgrafed = lowgrafed == null ? null : lowgrafed.trim();
    }

    public String getClassrank() {
        return classrank;
    }

    public void setClassrank(String classrank) {
        this.classrank = classrank == null ? null : classrank.trim();
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime == null ? null : endtime.trim();
    }
}
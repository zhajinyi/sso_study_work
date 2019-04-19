package org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo;

import org.bureaumanager.bureaumanagerpro.base.BaseEntity;

public class SisoGraduateInfo extends BaseEntity<SisoGraduateInfo>{
    private Integer id;

    private String empid;

    private String empname;

    private String orgid;

    private String orgname;

    private String referrer;

    private String remark;

    private String phone;

    private String classid;

    private String classname;

    private String result;

    private String avgsort1;

    private String avgsort2;

    private String awardinfo;

    private String filetype;

    private String gender;

    private String endtime;

    public Integer getId2() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid == null ? null : empid.trim();
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

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer == null ? null : referrer.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid == null ? null : classid.trim();
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public String getAvgsort1() {
        return avgsort1;
    }

    public void setAvgsort1(String avgsort1) {
        this.avgsort1 = avgsort1 == null ? null : avgsort1.trim();
    }

    public String getAvgsort2() {
        return avgsort2;
    }

    public void setAvgsort2(String avgsort2) {
        this.avgsort2 = avgsort2 == null ? null : avgsort2.trim();
    }

    public String getAwardinfo() {
        return awardinfo;
    }

    public void setAwardinfo(String awardinfo) {
        this.awardinfo = awardinfo == null ? null : awardinfo.trim();
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype == null ? null : filetype.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime == null ? null : endtime.trim();
    }
}
package org.bureaumanager.bureaumanagerpro.model.stuSystem.pojo;

import org.bureaumanager.bureaumanagerpro.base.BaseEntity;
import org.bureaumanager.bureaumanagerpro.model.stuSystem.pojo.dto.SisoWalkSchoolDto;

public class SisoWalkSchool extends BaseEntity {

    private String processinstid;

    private String empId;

    private String empCode;

    private String empName;

    private String orgId;

    private String orgName;

    private String grade;

    private String className;

    private String dormCode;

    private String address;

    private String flatmate;

    private String phone;

    private String walkTime;

    private String reason;

    private String empty1;

    private String empty2;

    private String empty3;

    private String empty4;



    public String getProcessinstid() {
        return processinstid;
    }

    public void setProcessinstid(String processinstid) {
        this.processinstid = processinstid;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId == null ? null : empId.trim();
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode == null ? null : empCode.trim();
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getDormCode() {
        return dormCode;
    }

    public void setDormCode(String dormCode) {
        this.dormCode = dormCode == null ? null : dormCode.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getFlatmate() {
        return flatmate;
    }

    public void setFlatmate(String flatmate) {
        this.flatmate = flatmate == null ? null : flatmate.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getWalkTime() {
        return walkTime;
    }

    public void setWalkTime(String walkTime) {
        this.walkTime = walkTime == null ? null : walkTime.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getEmpty1() {
        return empty1;
    }

    public void setEmpty1(String empty1) {
        this.empty1 = empty1 == null ? null : empty1.trim();
    }

    public String getEmpty2() {
        return empty2;
    }

    public void setEmpty2(String empty2) {
        this.empty2 = empty2 == null ? null : empty2.trim();
    }

    public String getEmpty3() {
        return empty3;
    }

    public void setEmpty3(String empty3) {
        this.empty3 = empty3 == null ? null : empty3.trim();
    }

    public String getEmpty4() {
        return empty4;
    }

    public void setEmpty4(String empty4) {
        this.empty4 = empty4 == null ? null : empty4.trim();
    }
}
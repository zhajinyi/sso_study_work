package org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo;

import org.bureaumanager.bureaumanagerpro.base.BaseEntity;



public class SisoPunishment extends BaseEntity{

    private String empId;

    private String result;

    private String recordTime;

    private String punishTime;

    private String level;

    private String reason;

    private String relieveTime;

    private String remark;


    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId == null ? null : empId.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getPunishTime() {
        return punishTime;
    }

    public void setPunishTime(String punishTime) {
        this.punishTime = punishTime;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getRelieveTime() {
        return relieveTime;
    }

    public void setRelieveTime(String relieveTime) {
        this.relieveTime = relieveTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
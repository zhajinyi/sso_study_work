package org.bureaumanager.bureaumanagerpro.model.dormitoryManager.pojo;

import org.bureaumanager.bureaumanagerpro.base.BaseEntity;
import org.bureaumanager.bureaumanagerpro.model.dormitoryManager.pojo.dto.SisoDormInfoDto;

public class SisoDormInfo extends BaseEntity<SisoDormInfoDto> {

    private String area;

    private String buildingNum;

    private String floor;

    private String state;

    private String type;

    private Integer currentNum;

    private Integer bunkNum;

    private String leader;

    private String orientation;

    private String isAllot;

    private String remark;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum == null ? null : buildingNum.trim();
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor == null ? null : floor.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(Integer currentNum) {
        this.currentNum = currentNum;
    }

    public Integer getBunkNum() {
        return bunkNum;
    }

    public void setBunkNum(Integer bunkNum) {
        this.bunkNum = bunkNum;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation == null ? null : orientation.trim();
    }

    public String getIsAllot() {
        return isAllot;
    }

    public void setIsAllot(String isAllot) {
        this.isAllot = isAllot == null ? null : isAllot.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
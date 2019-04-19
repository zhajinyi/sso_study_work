package org.bureaumanager.bureaumanagerpro.model.dormitoryManager.dao;

import org.bureaumanager.bureaumanagerpro.base.BaseMapper;
import org.bureaumanager.bureaumanagerpro.model.dormitoryManager.pojo.SisoDormInfo;
import org.bureaumanager.bureaumanagerpro.model.dormitoryManager.pojo.dto.SisoDormInfoDto;

import java.util.List;

public interface SisoDormInfoMapper extends BaseMapper<SisoDormInfoDto> {
    //根据宿舍楼选择数据
    List<SisoDormInfoDto> selectSisoDormByBuildingNum(String id);

    int countDormByBuildingNum(String id);

    List<SisoDormInfoDto> selectCollegeByDormInfo();
}
package org.bureaumanager.bureaumanagerpro.model.dormitoryManager.service;

import org.bureaumanager.bureaumanagerpro.base.BaseService;
import org.bureaumanager.bureaumanagerpro.model.dormitoryManager.pojo.dto.SisoDormInfoDto;

import java.util.List;

public interface SisoDormInfoService extends BaseService<SisoDormInfoDto> {

    List<SisoDormInfoDto> selectSisoDormByBuildingNum(String BuildingNum);


    int countDormByBuildingNum(String id);

    List<SisoDormInfoDto> selectCollegeByDormInfo();
}

package org.bureaumanager.bureaumanagerpro.model.dormitoryManager.service.impl;

import org.bureaumanager.bureaumanagerpro.base.BaseServiceImpl;
import org.bureaumanager.bureaumanagerpro.model.dormitoryManager.dao.SisoDormInfoMapper;
import org.bureaumanager.bureaumanagerpro.model.dormitoryManager.pojo.dto.SisoDormInfoDto;
import org.bureaumanager.bureaumanagerpro.model.dormitoryManager.service.SisoDormInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SisoDormInfoServiceImpl extends BaseServiceImpl<SisoDormInfoDto, SisoDormInfoMapper> implements SisoDormInfoService {

    @Autowired
    SisoDormInfoMapper sisoDormInfoMapper;

    @Override
    public List<SisoDormInfoDto> selectSisoDormByBuildingNum(String id) {
        return sisoDormInfoMapper.selectSisoDormByBuildingNum(id);
    }

    @Override
    public int countDormByBuildingNum(String id) {
        return sisoDormInfoMapper.countDormByBuildingNum(id);
    }

    @Override
    public List<SisoDormInfoDto> selectCollegeByDormInfo() {
        return sisoDormInfoMapper.selectCollegeByDormInfo();
    }
}

package org.bureaumanager.bureaumanagerpro.sysConfig.service.impl;

import org.bureaumanager.bureaumanagerpro.base.BaseServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.dao.SysOrganizationMapper;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysOrganizationDto;
import org.bureaumanager.bureaumanagerpro.sysConfig.service.SysOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BaoLing
 * @Date: 2019/1/22 14:10
 * @Description: SysOrganizationServiceImpl
 * @Version: 1.0
 */
@Service
public class SysOrganizationServiceImpl extends BaseServiceImpl<SysOrganizationDto,SysOrganizationMapper> implements SysOrganizationService {

    @Autowired
    SysOrganizationMapper sysOrganizationMapper;

    @Override
    public List<SysOrganizationDto> selectCollegeTree() {
        return sysOrganizationMapper.selectCollegeTree();
    }

    @Override
    public List<SysOrganizationDto> queryAllSysOrganization() {
        return sysOrganizationMapper.queryAllSysOrganization();
    }

    @Override
    public List <SysOrganizationDto> selectIdsByNameAll(String name) {
        return sysOrganizationMapper.selectIdsByNameAll(name);
    }
}

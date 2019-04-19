package org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.service.impl;

import org.bureaumanager.bureaumanagerpro.base.BaseServiceImpl;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.dao.SisoAdvancedInfoMapper;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.dao.SisoExcellclassInfoMapper;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo.dto.SisoExcellclassInfoDto;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.service.SisoExcellclassInfoService;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysOrganizationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SisoExcellclassInfoServiceImpl extends BaseServiceImpl<SisoExcellclassInfoDto, SisoExcellclassInfoMapper> implements SisoExcellclassInfoService {


    @Autowired
    private SisoExcellclassInfoMapper sisoExcellclassInfoMapper;

    @Override
    public List<SisoExcellclassInfoDto> querySisoExcellClassInfosById(String orgid) {
        return sisoExcellclassInfoMapper.querySisoExcellClassInfosById(orgid);
    }

    @Override
    public List<SisoExcellclassInfoDto> selectExcellClassInfoByClassid(SisoExcellclassInfoDto sisoExcellclassInfoDto) {
        return sisoExcellclassInfoMapper.selectExcellClassInfoByClassid(sisoExcellclassInfoDto);
    }
    @Override
    public List<SisoExcellclassInfoDto> queryByTemplate(SisoExcellclassInfoDto sisoExcellclassInfoDto) {
        return sisoExcellclassInfoMapper.queryByTemplate(sisoExcellclassInfoDto);
    }

}

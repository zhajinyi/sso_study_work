package org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.service.impl;

import org.bureaumanager.bureaumanagerpro.base.BaseServiceImpl;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.dao.SisoExcelleaderInfoMapper;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo.dto.SisoExcelleaderInfoDto;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.service.SisoExcelleaderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SisoExcelleaderInfoServiceImpl extends BaseServiceImpl<SisoExcelleaderInfoDto,SisoExcelleaderInfoMapper> implements SisoExcelleaderInfoService{

    @Autowired
    private SisoExcelleaderInfoMapper sisoExcelleaderInfoMapper;

    @Override
    public List<SisoExcelleaderInfoDto> queryByTemplate(SisoExcelleaderInfoDto sisoExcelleaderInfoDto) {
        return sisoExcelleaderInfoMapper.queryByTemplate(sisoExcelleaderInfoDto);
    }
}

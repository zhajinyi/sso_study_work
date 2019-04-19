package org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.service.impl;

import org.bureaumanager.bureaumanagerpro.base.BaseServiceImpl;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.dao.SisoGraduateInfoMapper;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo.dto.SisoGraduateInfoDto;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.service.SisoGraduateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class SisoGraduateInfoServiceImpl extends BaseServiceImpl<SisoGraduateInfoDto,SisoGraduateInfoMapper> implements SisoGraduateInfoService{
    @Autowired
    private SisoGraduateInfoMapper sisoGraduateInfoMapper;
    @Override
    public List<SisoGraduateInfoDto> queryByTemplate(SisoGraduateInfoDto sisoGraduateInfoDto) {
        return sisoGraduateInfoMapper.queryByTemplate(sisoGraduateInfoDto);
    }
}

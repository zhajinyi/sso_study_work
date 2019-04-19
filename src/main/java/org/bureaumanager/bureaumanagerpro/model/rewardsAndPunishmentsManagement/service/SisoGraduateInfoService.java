package org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.service;

import org.bureaumanager.bureaumanagerpro.base.BaseService;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo.dto.SisoGraduateInfoDto;

import java.util.List;

public interface SisoGraduateInfoService extends BaseService<SisoGraduateInfoDto> {

    List<SisoGraduateInfoDto> queryByTemplate(SisoGraduateInfoDto sisoGraduateInfoDto);
}

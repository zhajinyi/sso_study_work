package org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.service;

import org.bureaumanager.bureaumanagerpro.base.BaseService;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo.dto.SisoExcelleaderInfoDto;

import java.util.List;

public interface SisoExcelleaderInfoService extends BaseService<SisoExcelleaderInfoDto> {

    List<SisoExcelleaderInfoDto> queryByTemplate(SisoExcelleaderInfoDto sisoExcelleaderInfoDto);
}

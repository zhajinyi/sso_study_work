package org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.dao;

import org.bureaumanager.bureaumanagerpro.base.BaseMapper;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo.dto.SisoBursaryDto;

public interface SisoBursaryMapper extends BaseMapper<SisoBursaryDto> {
    Integer countByEmpIdAndTime(SisoBursaryDto sisoBursaryDto);
    Integer getMaxId();
    SisoBursaryDto selectByEmpIdAndTime(SisoBursaryDto sisoBursaryDto);
    Integer updateBursary(SisoBursaryDto sisoBursaryDto);
    Integer addBursary(SisoBursaryDto sisoBursaryDto);
}

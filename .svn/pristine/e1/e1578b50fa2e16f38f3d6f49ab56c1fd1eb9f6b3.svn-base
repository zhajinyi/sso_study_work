package org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.service;

import org.apache.ibatis.annotations.Param;
import org.bureaumanager.bureaumanagerpro.base.BaseService;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo.dto.SisoPunishmentDto;

import java.util.List;

public interface SisoPunishmentService extends BaseService<SisoPunishmentDto> {
    List<SisoPunishmentDto> selectByOrgId(String orgId);

    List<SisoPunishmentDto> selectByEmpId(String empId);
    List<SisoPunishmentDto> selectByEmpIdAndLevel(@Param("empId") String empId, @Param("level") String level);

}

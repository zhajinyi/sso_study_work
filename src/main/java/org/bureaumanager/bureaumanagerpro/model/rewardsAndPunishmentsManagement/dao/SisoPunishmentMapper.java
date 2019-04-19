package org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.dao;

import org.apache.ibatis.annotations.Param;
import org.bureaumanager.bureaumanagerpro.base.BaseMapper;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo.dto.SisoPunishmentDto;

import java.util.List;

public interface SisoPunishmentMapper extends BaseMapper<SisoPunishmentDto>{
    List<SisoPunishmentDto> selectByOrgId(@Param("orgId")String orgId);
    List<SisoPunishmentDto> selectByEmpId(String empId);
    List<SisoPunishmentDto> queryByTemplate(@Param("spd") SisoPunishmentDto sisoPunishmentDto);
    List<SisoPunishmentDto> selectByEmpIdAndLevel(@Param("empId") String empId,@Param("level") String level);
}
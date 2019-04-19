package org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.dao;

import org.apache.ibatis.annotations.Param;
import org.bureaumanager.bureaumanagerpro.base.BaseMapper;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo.dto.SisoExcellclassInfoDto;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysOrganizationDto;

import java.util.List;


public interface SisoExcellclassInfoMapper extends BaseMapper<SisoExcellclassInfoDto>{

    List<SisoExcellclassInfoDto> querySisoExcellClassInfosById(@Param("orgid") String orgid);

    List<SisoExcellclassInfoDto> selectExcellClassInfoByClassid(@Param("t") SisoExcellclassInfoDto sisoExcellclassInfoDto);

    List<SisoExcellclassInfoDto> queryByTemplate(@Param("t") SisoExcellclassInfoDto sisoExcellclassInfoDto);
}
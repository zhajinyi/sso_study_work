package org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.dao;

import org.apache.ibatis.annotations.Param;
import org.bureaumanager.bureaumanagerpro.base.BaseMapper;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.pojo.SisoStudentInfo;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.pojo.dto.SisoStudentInfoDto;

import java.util.List;

public interface SisoStudentInfoMapper extends BaseMapper<SisoStudentInfoDto> {
    SisoStudentInfoDto selectStudentInfoByUserId(String id);
    SisoStudentInfoDto selectStudentInfoByEmpid(@Param("empid") String empid);
    SisoStudentInfoDto selectDetailsStudentInfoByUserId(@Param("empid") String empid);
    Integer countByDormCode(String dormCode);
    int insertByTemplate(SisoStudentInfoDto t);
    int updateByTemplate(SisoStudentInfoDto t);
    int updateByTemplateById(SisoStudentInfoDto t);
}
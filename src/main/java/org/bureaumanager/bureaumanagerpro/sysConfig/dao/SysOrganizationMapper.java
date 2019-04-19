package org.bureaumanager.bureaumanagerpro.sysConfig.dao;

import org.apache.ibatis.annotations.Param;
import org.bureaumanager.bureaumanagerpro.base.BaseMapper;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysOrganizationDto;

import java.util.List;

public interface SysOrganizationMapper extends BaseMapper<SysOrganizationDto> {

    List<SysOrganizationDto> selectCollegeTree();

    //查询所有的组织机构
    List<SysOrganizationDto> queryAllSysOrganization();

    List<SysOrganizationDto> selectIdsByNameAll(@Param(value = "name") String name);
}
package org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.dao;

import org.bureaumanager.bureaumanagerpro.base.BaseMapper;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.pojo.dto.SisoClassInfoDto;


public interface SisoClassInfoMapper extends BaseMapper<SisoClassInfoDto> {

    int countStudentByClass(String id);
    SisoClassInfoDto selectClassInfoByUserId(String userId);

}
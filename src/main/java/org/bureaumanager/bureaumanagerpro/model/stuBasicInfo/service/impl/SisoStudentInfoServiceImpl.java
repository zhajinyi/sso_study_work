package org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.service.impl;

import org.apache.ibatis.annotations.Param;
import org.bureaumanager.bureaumanagerpro.base.BaseServiceImpl;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.dao.SisoStudentInfoMapper;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.pojo.dto.SisoStudentInfoDto;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.service.SisoStudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SisoStudentInfoServiceImpl extends BaseServiceImpl<SisoStudentInfoDto, SisoStudentInfoMapper> implements SisoStudentInfoService {

    @Autowired
    SisoStudentInfoMapper sisoStudentInfoMapper;
    @Override
    public SisoStudentInfoDto selectStudentInfoByUserId(String id) {
        return sisoStudentInfoMapper.selectStudentInfoByUserId(id);
    }

    @Override
    public SisoStudentInfoDto selectDetailsStudentInfoByUserId(String id) {
        return sisoStudentInfoMapper.selectDetailsStudentInfoByUserId(id);
    }

    @Override
    public boolean insertByTemplate(SisoStudentInfoDto t) {
        return sisoStudentInfoMapper.insertByTemplate(t)>0;
    }

    @Override
    public boolean updateByTemplate(SisoStudentInfoDto t) {
        return sisoStudentInfoMapper.updateByTemplate(t)>0;
    }

    @Override
    public boolean updateByTemplateById(SisoStudentInfoDto t) {
        return sisoStudentInfoMapper.updateByTemplateById(t)>0;
    }

    @Override
    public SisoStudentInfoDto selectStudentInfoByEmpid(String empid) {
        return sisoStudentInfoMapper.selectStudentInfoByEmpid(empid);
    }
    public Integer countByDormCode(String dormCode){
        return sisoStudentInfoMapper.countByDormCode(dormCode);
    }
}

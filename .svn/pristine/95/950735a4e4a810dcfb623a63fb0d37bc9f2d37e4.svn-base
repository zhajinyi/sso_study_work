package org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.service.impl;

import org.apache.ibatis.annotations.Param;
import org.bureaumanager.bureaumanagerpro.base.BaseServiceImpl;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.dao.SisoPunishmentMapper;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo.dto.SisoPunishmentDto;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.service.SisoPunishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SisoPunishmentServiceImpl extends BaseServiceImpl<SisoPunishmentDto, SisoPunishmentMapper> implements SisoPunishmentService {
    @Autowired
    private SisoPunishmentMapper sisoPunishmentMapper;

    @Override
    public List<SisoPunishmentDto> selectByOrgId(String orgId){
        return  sisoPunishmentMapper.selectByOrgId(orgId);
    }

    @Override
    public List <SisoPunishmentDto> selectByEmpId(String empId) {
        return sisoPunishmentMapper.selectByEmpId(empId);
    }

    @Override
    public List <SisoPunishmentDto> selectByEmpIdAndLevel(@Param("empId") String empId,@Param("level") String level) {
        return sisoPunishmentMapper.selectByEmpIdAndLevel(empId,level);
    }

    public List<SisoPunishmentDto> queryByTemplate(SisoPunishmentDto sisoPunishmentDto){
        return sisoPunishmentMapper.queryByTemplate(sisoPunishmentDto);
    }

}

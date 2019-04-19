package org.bureaumanager.bureaumanagerpro.sysConfig.service.impl;

import org.bureaumanager.bureaumanagerpro.base.BaseServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.dao.SysUserMapper;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysUserDto;
import org.bureaumanager.bureaumanagerpro.sysConfig.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDto,SysUserMapper> implements SysUserService {

    @Autowired
    SysUserMapper mapper;

    @Override
    public int countEntities(SysUserDto userDto) {
        return super.countEntities(userDto);
    }

    @Override
    public boolean insertEntity(SysUserDto userDto) {
        return super.insertEntity(userDto);
    }

    @Override
    public boolean insertEntitySelective(SysUserDto userDto) {
        return super.insertEntitySelective(userDto);
    }

    @Override
    public boolean deleteEntity(SysUserDto userDto) {
        return super.deleteEntity(userDto);
    }

    @Override
    public boolean deleteEntityByPrimaryKey(String id) {
        return super.deleteEntityByPrimaryKey(id);
    }

    @Override
    public boolean updateEntityByPrimaryKey(SysUserDto userDto) {
        return super.updateEntityByPrimaryKey(userDto);
    }

    @Override
    public boolean updateEntityByPrimaryKeySelective(SysUserDto userDto) {
        return super.updateEntityByPrimaryKeySelective(userDto);
    }

    @Override
    public SysUserDto selectEntityByPrimaryKey(String id) {
        return super.selectEntityByPrimaryKey(id);
    }

    @Override
    public List<SysUserDto> selectAllEntities() {
        return super.selectAllEntities();
    }

    @Override
    public List<SysUserDto> selectEntitiesByTemplate(SysUserDto userDto) {
        return super.selectEntitiesByTemplate(userDto);
    }

    @Override
    public SysUserDto findByUsername(String username) {
        return mapper.findByUsername(username);
    }
}

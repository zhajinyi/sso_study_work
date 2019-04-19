package org.bureaumanager.bureaumanagerpro.sysConfig.service.impl;

import org.bureaumanager.bureaumanagerpro.base.BaseServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.dao.SysRoleMenuMapper;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysRoleMenuKeyDto;
import org.bureaumanager.bureaumanagerpro.sysConfig.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zhajinyi
 * @create 2019-03-13 17:35
 */
@Service
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenuKeyDto, SysRoleMenuMapper> implements SysRoleMenuService {
    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<String> getRolebyMenuId(String menuId) {
        return sysRoleMenuMapper.getRolebyMenuId(menuId);
    }

    @Override
    public boolean updateByPrimaryKeySelective(SysRoleMenuKeyDto sysRoleMenuKeyDto) {
        return sysRoleMenuMapper.updateByPrimaryKeySelective(sysRoleMenuKeyDto) > 0;
    }

    @Override
    public List<SysRoleMenuKeyDto> selectRoleMenuBySelectivce(SysRoleMenuKeyDto sysRoleMenuKeyDto) {
        return sysRoleMenuMapper.selectRoleMenuBySelectivce(sysRoleMenuKeyDto);
    }

    @Override
    public boolean deleteEntityByMenuIdandRoleId(Map<String, String> map) {
        return sysRoleMenuMapper.deleteEntityByMenuIdandRoleId(map) > 0;
    }

    @Override
    public boolean insertByBatch(List<SysRoleMenuKeyDto> sysRoleMenuKeyDtos) {
        return sysRoleMenuMapper.insertByBatch(sysRoleMenuKeyDtos) > 0;
    }
}

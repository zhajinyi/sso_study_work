package org.bureaumanager.bureaumanagerpro.sysConfig.service.impl;

import org.bureaumanager.bureaumanagerpro.base.BaseServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.dao.SysDictMapper;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysDictDto;
import org.bureaumanager.bureaumanagerpro.sysConfig.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDictServiceImpl extends BaseServiceImpl<SysDictDto,SysDictMapper> implements SysDictService {

    @Autowired
    private SysDictMapper sysDictMapper;
    @Override
    public List<SysDictDto> queryParentDictInfos(String type) {
        return sysDictMapper.queryParentDictInfos(type);
    }

    @Override
    public List<SysDictDto> queryChildrenDictInfos(SysDictDto sysDictDto) {
        return sysDictMapper.queryChildrenDictInfos(sysDictDto);
    }

    @Override
    public boolean updateSysDictDtoInfoByParentId(SysDictDto sysDictDto) {
        int count=sysDictMapper.countByParentId(sysDictDto.getParentId());
        if (count>0){
            return sysDictMapper.updateSysDictDtoInfoByParentId(sysDictDto)==count;
        }else{
            return true;
        }
    }
}

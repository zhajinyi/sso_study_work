package org.bureaumanager.bureaumanagerpro.sysConfig.service;

import org.bureaumanager.bureaumanagerpro.base.BaseService;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysDictDto;

import java.util.List;

public interface SysDictService extends BaseService<SysDictDto> {

    //查询所有父数据字典
    List<SysDictDto> queryParentDictInfos(String type);
    //查询子数据字典
    List<SysDictDto> queryChildrenDictInfos(SysDictDto sysDictDto);
    //根据parentId修改
    boolean updateSysDictDtoInfoByParentId(SysDictDto sysDictDto);
}

package org.bureaumanager.bureaumanagerpro.sysConfig.dao;

import org.apache.ibatis.annotations.Param;
import org.bureaumanager.bureaumanagerpro.base.BaseMapper;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysDictDto;

import java.util.List;

public interface SysDictMapper extends BaseMapper<SysDictDto> {
    //查询所有父数据字典
    List<SysDictDto> queryParentDictInfos(@Param("type")String type);
    //查询子数据字典
    List<SysDictDto> queryChildrenDictInfos(@Param("t") SysDictDto sysDictDto);
    //根据parentId查询
    int countByParentId(@Param("parentId") String parentId);
    //根据parentId修改
    int updateSysDictDtoInfoByParentId(SysDictDto sysDictDto);

}
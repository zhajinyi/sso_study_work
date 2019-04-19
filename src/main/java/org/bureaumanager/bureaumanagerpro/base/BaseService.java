package org.bureaumanager.bureaumanagerpro.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: BaoLing
 * @Date: 2018/12/19 17:04
 * @Description: BaseService
 * @Version: 1.0
 */
public interface BaseService<T> {

    /**
     * 判断id是否存在
     * @param id
     * @return
     */
    boolean countByPrimaryKey(String id);

    //通用型:获取数量
    int countEntities(T t);

    //通用型:获取数量
    boolean countByTemplate(T t);

    //通用型：查询实体
    boolean insertEntity(T t);

    //通用型：根据模板查询实体
    boolean insertEntitySelective(T t);

    //通用型：根据实体删除对象
    boolean deleteEntity(T t);

    //通用型：通过主键删除对象
    boolean deleteEntityByPrimaryKey(String id);

    //通用型：通过传输多个主键来实现批量删除
    boolean deleteEntityByPrimaryKeys(List<String> ids);

    //通用型：根据主键来更新
    boolean updateEntityByPrimaryKey(T t);

    //通用型：通过主键查询，根据模板来更新对象
    boolean updateEntityByPrimaryKeySelective(T t);

    //通用型：根据主键查询记录
    T selectEntityByPrimaryKey(String id);

    //通用型：查询所有的实体;
    List<T> selectAllEntities ();

    //通用型：通过模板查询实体;
    List<T> selectEntitiesByTemplate(T t);

    //部分与organization关联的实体查询
    List<T> selectEntitiesByOrgId(String orgId,String grade);

    //通用型：通过名字查询id和name;
    List<T> selectIdsByName(String name);
    //通用型：通过模板查询Id;
    String selectIdByTemplate(T t);
}

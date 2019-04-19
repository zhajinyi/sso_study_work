package org.bureaumanager.bureaumanagerpro.base;



import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: BaoLing
 * @Date: 2018/12/19 16:39
 * @Description: BaseMapper
 * @Version: 1.0
 */
public interface BaseMapper<T extends BaseEntity> {
    int countByPrimaryKey(String id);

    int countByTemplate(@Param(value = "t") T t);

    int insert(T t);

    int insertSelective(T t);

    int delete(T t);

    int deleteByPrimaryKey(String id);

    int deleteByPrimaryKeys(List<String> ids);

    int updateByPrimaryKey(T t);

    int updateByPrimaryKeySelective(T t);

    String selectIdByTemplate(T t);

    List<T> selectIdsByName(@Param(value = "name")String name);

    T selectByPrimaryKey(String id);

    List<T> selectAll();

    List<T> selectByTemplate(@Param(value = "t") T t);

    List<T> selectByOrgId(@Param(value = "orgId") String orgId, @Param(value = "grade") String grade);

    List<T> selectByOrgId(@Param(value = "orgId") String orgId);

}
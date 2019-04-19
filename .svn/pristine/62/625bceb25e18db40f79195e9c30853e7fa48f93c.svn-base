package org.bureaumanager.bureaumanagerpro.base;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: BaoLing
 * @Date: 2018/12/19 16:39
 * @Description: BaseServiceImpl
 * @Version: 1.0
 */
public class BaseServiceImpl<T extends BaseEntity, R extends BaseMapper<T>> implements BaseService<T> {

    @Autowired
    R baseMapper;


    @Override
    public boolean countByPrimaryKey(String id) {
        return baseMapper.countByPrimaryKey(id)>0;
    }

    @Override
    public int countEntities(T t) {
        return baseMapper.countByTemplate(t);
    }
    @Override
    public boolean countByTemplate(T t) {
        return baseMapper.countByTemplate(t)>0;
    }
    public int countEntities(String id) {
        return baseMapper.countByPrimaryKey(id);
    }
    @Override
    public boolean insertEntity(T t) {
        return baseMapper.insert(t) > 0;
    }

    @Override
    public boolean insertEntitySelective(T t) {
        return baseMapper.insertSelective(t) > 0;
    }

    @Override
    public boolean deleteEntity(T t) {
        return baseMapper.delete(t) > 0;
    }

    @Override
    public boolean deleteEntityByPrimaryKey(String id) {
        return baseMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean deleteEntityByPrimaryKeys(List<String> ids) {
        return baseMapper.deleteByPrimaryKeys(ids) > 0;
    }

    @Override
    public boolean updateEntityByPrimaryKey(T t) {
        return baseMapper.updateByPrimaryKey(t) > 0;
    }

    @Override
    public boolean updateEntityByPrimaryKeySelective(T t) {
        return baseMapper.updateByPrimaryKeySelective(t) > 0;
    }

    @Override
    public T selectEntityByPrimaryKey(String id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> selectAllEntities() {
        return baseMapper.selectAll();
    }

    @Override
    public List<T> selectEntitiesByTemplate(T t) {
        return baseMapper.selectByTemplate(t);
    }

    @Override
    public List <T> selectEntitiesByOrgId(String orgId,String grade) {
        return baseMapper.selectByOrgId(orgId,grade);
    }

    @Override
    public List <T> selectIdsByName(String name) {
        return baseMapper.selectIdsByName(name);
    }

    @Override
    public String selectIdByTemplate(T t) {
        return baseMapper.selectIdByTemplate(t);
    }
}

package org.bureaumanager.bureaumanagerpro.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.SysUser;
import org.bureaumanager.bureaumanagerpro.sysConfig.utils.PageBean;
import org.bureaumanager.bureaumanagerpro.sysConfig.utils.StringUtils;

import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Map;

/**
 * @Author: BaoLing
 * @Date: 2018/12/19 16:39
 * @Description: BaseEntity,提取公共信息如：id、currentUser、pageBean、sqlMap
 * @Version: 1.0
 */
@JsonIgnoreProperties(value = {"handler"})
public class BaseEntity<T> implements Serializable {
    /**
     * implements Serializable的类定义的版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 实体编号（唯一标识）
     */
    protected String id;

    /**
     * 当前用户
     */
    protected SysUser currentUser;

    /**
     * 当前实体分页对象
     */
    protected PageBean<T> pageBean;

    /**
     * 自定义SQL（SQL标识，SQL内容）
     */
    protected Map<String, String> sqlMap;

    /**
     * 封装分页信息，currentPage为当年页码；
     * pageSize为分页数量
     */
    protected Integer currentPage = 1;
    protected Integer pageSize = 10;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
     * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
     */
    protected boolean isNewRecord = false;

    public BaseEntity() {

    }

    public BaseEntity(String id) {
        this();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    @JsonIgnore
    @XmlTransient
    public SysUser getCurrentUser() {
//        if(currentUser == null){
//            currentUser = UserUtils.getUser();
//        }
        return currentUser;
    }

    public void setCurrentUser(SysUser currentUser) {
        this.currentUser = currentUser;
    }

    @JsonIgnore
    @XmlTransient
    public PageBean<T> getPage() {
        if (pageBean == null){
            pageBean = new PageBean<T>();
        }
        return pageBean;
    }

    public PageBean<T> setPage(PageBean<T> page) {
        this.pageBean = page;
        return page;
    }

    @JsonIgnore
    @XmlTransient
    public Map<String, String> getSqlMap() {
        if (sqlMap == null){
            sqlMap = Maps.newHashMap();
        }
        return sqlMap;
    }

    public void setSqlMap(Map<String, String> sqlMap) {
        this.sqlMap = sqlMap;
    }

    /**
     * 插入之前执行方法，子类实现
     */
//    public abstract void preInsert();

    /**
     * 更新之前执行方法，子类实现
     */
//    public abstract void preUpdate();

    /**
     * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
     * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
     * @return
     */
    public boolean getIsNewRecord() {
        return isNewRecord || StringUtils.isBlank(getId());
    }

    /**
     * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
     * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
     */
    public void setIsNewRecord(boolean isNewRecord) {
        this.isNewRecord = isNewRecord;
    }

}

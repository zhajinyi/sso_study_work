package org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.SysMenu;
import org.bureaumanager.bureaumanagerpro.sysConfig.utils.UserUtils;

import java.util.List;

/**
 * @Author: BaoLing
 * @Date: 2019/1/3 11:33
 * @Description: SysMenuDto
 * @Version: 1.0
 */
public class SysMenuDto extends SysMenu {
//    private String userId;
    private SysMenuDto parentMenu;	// 父级菜单
    private List<SysMenuDto> childrenMenu;	// 子级菜单
//    public SysMenuDto(){
//        super();
//    }
//    public SysMenuDto(String id){
//        super(id);
//    }
//    public String getUserId() {
//        return userId;
//    }

//    public void setUserId(String userId) {
//        this.userId = userId;
//    }

    public SysMenuDto getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(SysMenuDto parentMenu) {
        this.parentMenu = parentMenu;
    }

    public List <SysMenuDto> getChildrenMenu() {
        return childrenMenu;
    }

    public void setChildrenMenu(List <SysMenuDto> childrenMenu) {
        this.childrenMenu = childrenMenu;
    }

}

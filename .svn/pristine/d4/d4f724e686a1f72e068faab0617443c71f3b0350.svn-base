package org.bureaumanager.bureaumanagerpro.model.sysManager.util;

import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysMenuDto;

import java.util.List;

public class MenuTreeUtil {
    public static MenuTree[] menuListToTreeArray(List<SysMenuDto> list){
        if(list.size() > 0){
            MenuTree[] trees = new MenuTree[list.size()];
            for (int i = 0; i < list.size(); i++) {
                SysMenuDto menu = list.get(i);
                MenuTree tree = new MenuTree();
                tree.setTitle(menu.getName());
                tree.setValue(menu.getId());
                tree.setDisabled(!menu.getIsShow().equals("1"));
                trees[i] = tree;
            }
            return trees;
        } else
            return null;
    }

    public static MenuTree addChildren(MenuTree ptree,List<SysMenuDto> list){
        if(list.size() > 0){
            MenuTree[] trees = new MenuTree[list.size()];
            for (int i = 0; i < list.size(); i++) {
                SysMenuDto menu = list.get(i);
                MenuTree tree = new MenuTree();
                tree.setTitle(menu.getName());
                tree.setValue(menu.getId());
                tree.setDisabled(!menu.getIsShow().equals("1"));
                trees[i] = tree;
            }
            ptree.setData(trees);
        }
        return ptree;
    }

    public static MenuTree addChildren(MenuTree ptree,MenuTree[] children){
        if (children.length > 0)
            ptree.setData(children);
        return ptree;
    }
}

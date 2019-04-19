package org.bureaumanager.bureaumanagerpro.model.sysManager.controller;

import org.bureaumanager.bureaumanagerpro.model.sysManager.util.MenuTree;
import org.bureaumanager.bureaumanagerpro.model.sysManager.util.MenuTreeUtil;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.Msg;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysMenuDto;
import org.bureaumanager.bureaumanagerpro.sysConfig.service.impl.SysMenuServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.utils.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: WangJiaWei
 * @Date: 2019-02-21 14:34
 * @Description:
 */
@Controller
@RequestMapping("/menuManager")
public class MenuManagerController {
    @Autowired
    SysMenuServiceImpl sysMenuService;

    @RequestMapping(value = "/all" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public MenuTree[] findAllMenu(){
        SysMenuDto temp = new SysMenuDto();
        temp.setParentId("0");
        List<SysMenuDto> list = selectByParentId(temp);//查询出所有未删除、可显示的一级目录
        String[] menuids = new String[list.size()];//存储这些一级目录的id
        for (int i = 0; i < list.size(); i++) {
            menuids[i] = list.get(i).getId();
        }
        MenuTree[] trees = MenuTreeUtil.menuListToTreeArray(list);//把list转化为前端XTree需要的数组格式
        for(String pid : menuids) { //遍历一级目录id
            temp = new SysMenuDto();
            temp.setParentId(pid);
            list.clear();
            list = selectByParentId(temp);//获取到某一个一级目录下的所有未删除、可显示的二级目录
            if (list.size() > 0) {
                for(int i = 0; i < trees.length; i++){ //遍历之前查询出的一级目录
                    if(trees[i].getValue().equals(list.get(0).getParentId())){ //判断这个一级目录是不是二级目录list对应的父目录
                        String[] ids = new String[list.size()]; //存储二级目录的id
                        for (int j = 0; j < list.size(); j++) {
                            ids[j] = list.get(j).getId();
                        }
                        MenuTree[] ptree = MenuTreeUtil.menuListToTreeArray(list);//把list转化为前端XTree需要的数组格式
                        for (String id : ids) { //遍历二级目录id
                            temp = new SysMenuDto();
                            temp.setParentId(id);
                            list.clear();
                            list = selectByParentId(temp);//获取某一个二级目录下的所有三级目录
                            if (list.size() > 0) {
                                for(int j = 0; j < ptree.length; j++){ //遍历二级目录
                                    if (ptree[j].getValue().equals(list.get(0).getParentId())) {//判断这个二级目录是不是三级目录list的父目录
                                        ptree[j] = MenuTreeUtil.addChildren(ptree[j], list);//把三级目录存进对应的二级目录
                                        break;
                                    }
                                }
                            }
                        }
                        trees[i] = MenuTreeUtil.addChildren(trees[i], ptree);//把二级目录加进一级目录
                        break;
                    }
                }
            }
        }
        return trees;
    }

    @RequestMapping(value = "/xtree" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public MenuTree[] showXtree(){
        List<SysMenuDto> allMenu = queryAll();
        List<SysMenuDto> list = selectByParentId(allMenu,"0");//查询出所有未删除、可显示的一级目录
        String[] menuids = new String[list.size()];//存储这些一级目录的id
        for (int i = 0; i < list.size(); i++) {
            menuids[i] = list.get(i).getId();
        }
        MenuTree[] trees = MenuTreeUtil.menuListToTreeArray(list);//把list转化为前端XTree需要的数组格式
        for(String pid : menuids) { //遍历一级目录id
            list.clear();
            list = selectByParentId(allMenu, pid);//获取到某一个一级目录下的所有未删除、可显示的二级目录
            if (list.size() > 0) {
                for(int i = 0; i < trees.length; i++){ //遍历之前查询出的一级目录
                    if(trees[i].getValue().equals(list.get(0).getParentId())){ //判断这个一级目录是不是二级目录list对应的父目录
                        String[] ids = new String[list.size()]; //存储二级目录的id
                        for (int j = 0; j < list.size(); j++) {
                            ids[j] = list.get(j).getId();
                        }
                        MenuTree[] ptree = MenuTreeUtil.menuListToTreeArray(list);//把list转化为前端XTree需要的数组格式
                        for (String id : ids) { //遍历二级目录id
                            list.clear();
                            list = selectByParentId(allMenu,id);//获取某一个二级目录下的所有三级目录
                            if (list.size() > 0) {
                                for(int j = 0; j < ptree.length; j++){ //遍历二级目录
                                    if (ptree[j].getValue().equals(list.get(0).getParentId())) {//判断这个二级目录是不是三级目录list的父目录
                                        ptree[j] = MenuTreeUtil.addChildren(ptree[j], list);//把三级目录存进对应的二级目录
                                        break;
                                    }
                                }
                            }
                        }
                        trees[i] = MenuTreeUtil.addChildren(trees[i], ptree);//把二级目录加进一级目录
                        break;
                    }
                }
            }
        }
        return trees;
    }

    @RequestMapping(value = "/getFirstMenu" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public SysMenuDto[] getFirstMenu(){
        SysMenuDto temp = new SysMenuDto();
        temp.setParentId("0");
        List<SysMenuDto> list = selectByParentId(temp);
        SysMenuDto[] sysMenuDtos = new SysMenuDto[list.size()];
        for (int i = 0; i < list.size(); i++) {
            sysMenuDtos[i] = list.get(i);
        }
        return sysMenuDtos;
    }

    @RequestMapping(value = "/getSencondMenu" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public SysMenuDto[] getSencondMenu(@RequestParam("parentid") String parentid){
        Integer pid = Integer.valueOf(parentid);
        if (pid > 0) {
            SysMenuDto temp = new SysMenuDto();
            temp.setParentId(pid.toString());
            List<SysMenuDto> list = selectByParentId(temp);
            SysMenuDto[] sysMenuDtos = new SysMenuDto[list.size()];
            for (int i = 0; i < list.size(); i++) {
                sysMenuDtos[i] = list.get(i);
            }
            return sysMenuDtos;
        } else
            return null;
    }

    @RequestMapping(value = "/getOneMenu" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public SysMenuDto getMenuById(@RequestParam("id") String sid){
        Long id = Long.valueOf(sid);
        if (id > 0) {
            SysMenuDto sysMenuDto = getOneMenuById(id.toString());
            return sysMenuDto;
        } else
            return null;
    }

    @RequestMapping(value = "/addMenu" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Msg addMenu(@RequestBody(required = true) SysMenuDto sysMenuDto){
        Long num = getMaxIdByJdbc() + 1;
        sysMenuDto.setId(num.toString());
        if ("0".equals(sysMenuDto.getParentIds())) {
            sysMenuDto.setParentIds(num.toString());
        } else {
            sysMenuDto.setParentIds(sysMenuDto.getParentIds() + "." + num.toString());
        }
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        boolean flag = sysMenuService.insertEntity(sysMenuDto);
        /*String sql = "insert into sys_menu (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, " +
                "      create_by, create_date, update_by, update_date, remarks, del_flag) values (\'" +
                sysMenuDto.getId() + "\',\'" +
                sysMenuDto.getParentId() + "\',\'" +
                sysMenuDto.getParentIds() + "\',\'" +
                sysMenuDto.getName() + "\',\'" +
                sysMenuDto.getSort() + "\',\'" +
                sysMenuDto.getHref() + "\',\'" +
                sysMenuDto.getTarget() + "\',\'" +
                sysMenuDto.getIcon() + "\',\'" +
                sysMenuDto.getIsShow() + "\',\'" +
                sysMenuDto.getPermission() + "\',\'" +
                sysMenuDto.getCreateBy() + "\',\'" +
                sp.format(sysMenuDto.getCreateDate()) + "\',\'" +
                sysMenuDto.getUpdateBy() + "\',\'" +
                sp.format(sysMenuDto.getUpdateDate()) + "\',\'" +
                sysMenuDto.getRemarks() + "\',\'0\')";
        boolean flag = updateByJdbc(sql) > 0 ? true : false;*/
        return Msg.success().add("flag", flag);
    }

    @RequestMapping(value = "/editMenu" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Msg editMenu(@RequestBody(required = true) SysMenuDto sysMenuDto){
        sysMenuDto.setDelFlag("0");
        boolean flag = sysMenuService.updateEntityByPrimaryKey(sysMenuDto);
        return Msg.success().add("flag", flag);
    }

    @RequestMapping(value = "/delMenu" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Msg delMenu(@RequestParam("ids") String ids){
        String[] menuIds = ids.split(",");
        String trueids = "";
        for (String id : menuIds) {
            if (Long.valueOf(id) > 0)
                trueids += id + ",";
        }
        trueids = trueids.substring(0, trueids.length()-1);
        Integer flag = 0;
        if (trueids.equals(ids)) {
            flag = delByJdbc(trueids);
        } else
            return Msg.success().add("flagMsg", "参数不合法");
        if (flag == menuIds.length)
            return Msg.success().add("flagMsg", "成功");
        else
            return Msg.success().add("flagMsg", "删除过程中出现错误");
    }

    private List<SysMenuDto> selectByParentId(List<SysMenuDto> allMenu, String parentId) {
        List<SysMenuDto> list = new ArrayList<SysMenuDto>();
        Iterator<SysMenuDto> iterator = allMenu.iterator();
        while (iterator.hasNext()) {
            SysMenuDto sysMenuDto = iterator.next();
            if (parentId.equals(sysMenuDto.getParentId()))
                list.add(sysMenuDto);
        }
        return  list;
    }


    private static List<SysMenuDto> selectByParentId(SysMenuDto sysMenuDto) {
        Connection conn = null;
        Statement stm = null;
        ResultSet res = null;
        List<SysMenuDto> list = new ArrayList<SysMenuDto>();
        try {
            //1.连接数据库
            conn = JdbcUtil.getConnection();
            //2.获取到statement对象
            stm = conn.createStatement();
            //3.执行sql语句
            String sql = "select * from sys_menu where parent_id=\'"+sysMenuDto.getParentId()+"\' and del_flag=\'0\' order by sort asc";
            res = stm.executeQuery(sql);
            while(res.next()){
                //提倡通过列名获取
                SysMenuDto sysMenuDto1 = new SysMenuDto();
                sysMenuDto1.setId(res.getString("id"));
                sysMenuDto1.setName(res.getString("name"));
                sysMenuDto1.setIsShow(res.getString("is_show"));
                sysMenuDto1.setParentId(res.getString("parent_id"));
                list.add(sysMenuDto1);
            }
            //关闭资源
            JdbcUtil.close(conn, stm, res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static List<SysMenuDto> queryAll() {
        Connection conn = null;
        Statement stm = null;
        ResultSet res = null;
        List<SysMenuDto> list = new ArrayList<SysMenuDto>();
        try {
            //1.连接数据库
            conn = JdbcUtil.getConnection();
            //2.获取到statement对象
            stm = conn.createStatement();
            //3.执行sql语句
            String sql = "select * from sys_menu where del_flag=\'0\' order by sort asc";
            res = stm.executeQuery(sql);
            while(res.next()){
                //提倡通过列名获取
                SysMenuDto sysMenuDto1 = new SysMenuDto();
                sysMenuDto1.setId(res.getString("id"));
                sysMenuDto1.setName(res.getString("name"));
                sysMenuDto1.setIsShow(res.getString("is_show"));
                sysMenuDto1.setParentId(res.getString("parent_id"));
                list.add(sysMenuDto1);
            }
            //关闭资源
            JdbcUtil.close(conn, stm, res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static Long getMaxIdByJdbc() {
        Connection conn = null;
        Statement stm = null;
        ResultSet res = null;
        Long num = Long.valueOf(0);
        try {
            //1.连接数据库
            conn = JdbcUtil.getConnection();
            //2.获取到statement对象
            stm = conn.createStatement();
            //3.执行sql语句
            String sql = "select max(id+0) num from sys_menu";
            res = stm.executeQuery(sql);
            while(res.next()){
                //提倡通过列名获取
                num = res.getLong("num");
            }
            //关闭资源
            JdbcUtil.close(conn, stm, res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    private static Integer delByJdbc(String ids) {
        Connection conn = null;
        Statement stm = null;
        Integer flag = 0;
        try {
            //1.连接数据库
            conn = JdbcUtil.getConnection();
            //2.获取到statement对象
            stm = conn.createStatement();
            //3.执行sql语句
            String sql = "update sys_menu set del_flag = \'1\' where id in (" + ids + ")";
            flag = stm.executeUpdate(sql);
            //关闭资源
            JdbcUtil.close(conn, stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    private static SysMenuDto getOneMenuById(String id) {
        Connection conn = null;
        Statement stm = null;
        ResultSet res = null;
        SysMenuDto sysMenuDto = new SysMenuDto();
        try {
            //1.连接数据库
            conn = JdbcUtil.getConnection();
            //2.获取到statement对象
            stm = conn.createStatement();
            //3.执行sql语句
            String sql = "select * from sys_menu where id=" + id;
            res = stm.executeQuery(sql);
            while(res.next()){
                //提倡通过列名获取
                sysMenuDto.setId(id);
                sysMenuDto.setParentIds(res.getString("parent_ids"));
                sysMenuDto.setParentId(res.getString("parent_id"));
                sysMenuDto.setDelFlag(res.getString("del_flag"));
                sysMenuDto.setIsShow(res.getString("is_show"));
                sysMenuDto.setName(res.getString("name"));
                sysMenuDto.setCreateBy(res.getString("create_by"));
                sysMenuDto.setCreateDate(res.getDate("create_date"));
                sysMenuDto.setHref(res.getString("href"));
                sysMenuDto.setIcon(res.getString("icon"));
                sysMenuDto.setPermission(res.getString("permission"));
                sysMenuDto.setRemarks(res.getString("remarks"));
                sysMenuDto.setSort(res.getInt("sort"));
                sysMenuDto.setTarget(res.getString("target"));
                sysMenuDto.setUpdateBy(res.getString("update_by"));
                sysMenuDto.setUpdateDate(res.getDate("update_date"));
            }
            //关闭资源
            JdbcUtil.close(conn, stm, res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sysMenuDto;
    }

    private static Integer updateByJdbc(String sql) {
        Connection conn = null;
        Statement stm = null;
        Integer flag = 0;
        try {
            //1.连接数据库
            conn = JdbcUtil.getConnection();
            //2.获取到statement对象
            stm = conn.createStatement();
            //3.执行sql语句
            flag = stm.executeUpdate(sql);
            //关闭资源
            JdbcUtil.close(conn, stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}

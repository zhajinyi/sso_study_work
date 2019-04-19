package org.bureaumanager.bureaumanagerpro.sysConfig.webService.fwdtBursaryAndHighExcellent;

import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo.dto.SisoBursaryDto;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.service.impl.SisoBursaryServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.utils.DateUtils;
import org.bureaumanager.bureaumanagerpro.sysConfig.utils.JdbcUtil;
import org.bureaumanager.bureaumanagerpro.sysConfig.utils.WebServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class StudentAwardClient {
    private static final Logger logger= LoggerFactory.getLogger(StudentAwardClient.class);
    private static StudentAwardService service = new StudentAwardServiceService().getPort(StudentAwardService.class);
    @Autowired
    private SisoBursaryServiceImpl wImpl;
    private static StudentAwardClient wClient;
    @PostConstruct
    public void init() {
        wClient = this;
        wClient.wImpl = this.wImpl;
    }
    //获取年份，过去n年就传参数n
    //今年
    private static String currentYear= DateUtils.getYear();
    //去年
    private static String beforeYear1= DateUtils.pastYears(1);
    //前年
    private static String beforeYear2= DateUtils.pastYears(2);
    //大前年
    private static String beforeYear3= DateUtils.pastYears(3);

    //当前年份数据
    private static String str =service.queryBursary(currentYear);
    private static String str1 =service.queryBursary(beforeYear1);
    private static String str2 =service.queryBursary(beforeYear2);
    private static String ex = service.queryHighExcellent("三好学生", currentYear);
    private static String ex1 = service.queryHighExcellent("三好学生", beforeYear1);
    private static String ex2 = service.queryHighExcellent("优秀学生干部", currentYear);
    private static String ex3 = service.queryHighExcellent("优秀学生干部", beforeYear1);
    private static String K = "bursarys";
    private static String K1 = "excellent";

  public static void main(String[] args) {

  }
    /**
     *运行方法，在WebServiceUtils定时调用
     */
    public static void doRun() throws SQLException{
//判断是否有数据传过来，有则返回List，无则返回null
        List<Map> wList,w1List,w2List;
        if (str == null)
            wList = null;
        else
            wList = WebServiceUtils.isHaveMap(str,K);
        if (str1 == null)
            w1List = null;
        else
            w1List = WebServiceUtils.isHaveMap(str1,K);
        if (str2 == null)
            w2List = null;
        else
            w2List = WebServiceUtils.isHaveMap(str2,K);
        //如果数据不为空就执行保存方法
        if (null!=wList){
            doSave(wList);
        }else {
            logger.info(currentYear+"年没有奖学金记录。");
        }
        if (null!=w1List){
            doSave(w1List);
        }else {
            logger.info(beforeYear1+"年没有奖学金记录。");
        }
        if (null!=w2List){
            doSave(w2List);
        }else {
            logger.info(beforeYear2+"年没有奖学金记录。");
        }


        List<Map> w3List;
        if (ex == null)
            wList = null;
        else
            wList = WebServiceUtils.isHaveMap(ex,K1);
        if (ex1 == null)
            w1List = null;
        else
            w1List = WebServiceUtils.isHaveMap(ex1,K1);
        if (ex2 == null)
            w2List = null;
        else
            w2List = WebServiceUtils.isHaveMap(ex2,K1);
        if (ex3 == null)
            w3List = null;
        else
            w3List = WebServiceUtils.isHaveMap(ex3,K1);

        if (null!=wList){
            doSave1(wList);
        }else {
            logger.info(currentYear+"年没有省级三好学生记录。");
        }
        if (null!=w1List){
            doSave1(w1List);
        }else {
            logger.info(beforeYear1+"年没有省级三好学生记录。");
        }
        if (null!=w2List){
            doSave1(w2List);
        }else {
            logger.info(currentYear+"年没有省级优秀学生干部记录。");
        }
        if (null!=w3List){
            doSave1(w3List);
        }else {
            logger.info(beforeYear1+"年没有省级优秀学生干部记录。");
        }
    }

    /**
     * 保存数据方法
     * @param list
     */
    private static void doSave(List<Map> list) throws SQLException{
        Connection conn = JdbcUtil.getConnection();;
        Statement stm = conn.createStatement();
        for (Map w:list){
            SisoBursaryDto wDto =new SisoBursaryDto();
            String empid  = w.get("empid").toString();
            wDto.setEmpId(empid);
            wDto.setEmpName(w.get("empname").toString());
            wDto.setBursaryType(w.get("ptype").toString());
            String endTime = w.get("endtime").toString().substring(0,19).replace('T',' ');//年月日时分秒
            wDto.setBursaryTime(endTime);
            wDto.setClassName(w.get("classname").toString());
            wDto.setXueKeDeFen(w.get("cjdf").toString());
            wDto.setDeYuDeFen(w.get("dydf").toString());
            wDto.setZongHeSuYangFen(w.get("dyf").toString());
            wDto.setXueKeJiaQuanFen(w.get("jqf").toString());
            wDto.setOrgId(w.get("orgid").toString());
            wDto.setOrgName(w.get("orgname").toString());
            wDto.setPolitical(w.get("political").toString());
            wDto.setSex(w.get("sex").toString());
            wDto.setZongHeFen(w.get("zf").toString());
            Long num = countByJdbc(empid, endTime, 0, stm);
            boolean isUpdate = num > 0 ? true : false;
            if (isUpdate){
                String id = countByJdbc(empid, endTime, 1, stm).toString();
                String sql = "update siso_bursary set empid='" + empid
                        + "',orgid='" + wDto.getOrgId()
                        + "',empname='" + wDto.getEmpName()
                        + "',orgname='" + wDto.getOrgName()
                        + "',classname='" + wDto.getClassName()
                        + "',sex='" + wDto.getSex()
                        + "',political='" + wDto.getPolitical()
                        + "',xueke_jiaquan_fen='" + wDto.getXueKeJiaQuanFen()
                        + "',xueke_defen='" + wDto.getXueKeDeFen()
                        + "',zonghesuyang_fen='" + wDto.getZongHeSuYangFen()
                        + "',deyu_defen='" + wDto.getDeYuDeFen()
                        + "',zonghe_fen='" + wDto.getZongHeFen()
                        + "',bursary_type='" + wDto.getBursaryType()
                        + "',bursary_time='" + wDto.getBursaryTime()
                        + "' where id='" + id + "'";
                logger.info("update : K为"+K+", id = "+id+"的数据");
                saveByJdbc(sql,stm);
            }else {
                Long id = countByJdbc(empid, endTime, 2, stm);
                if (id == null) id = Long.valueOf(1);
                else id += 1;
                String sql = "insert into siso_bursary (id,empid,orgid,empname,orgname,classname,sex,political,xueke_jiaquan_fen,xueke_defen,zonghesuyang_fen,deyu_defen,zonghe_fen,bursary_type,bursary_time,remark) values ("
                        + "'" + id.toString() + "',"
                        + "'" + empid + "',"
                        + "'" + wDto.getOrgId() + "',"
                        + "'" + wDto.getEmpName() + "',"
                        + "'" + wDto.getOrgName() + "',"
                        + "'" + wDto.getClassName() + "',"
                        + "'" + wDto.getSex() + "',"
                        + "'" + wDto.getPolitical() + "',"
                        + "'" + wDto.getXueKeJiaQuanFen() + "',"
                        + "'" + wDto.getXueKeDeFen() + "',"
                        + "'" + wDto.getZongHeSuYangFen() + "',"
                        + "'" + wDto.getDeYuDeFen() + "',"
                        + "'" + wDto.getZongHeFen() + "',"
                        + "'" + wDto.getBursaryType() + "',"
                        + "'" + wDto.getBursaryTime() + "',"
                        + "'" + (wDto.getRemark() == null ? "" : wDto.getRemark()) + "')";
                logger.info("insert : K为"+K+", id = "+id+"的数据");
                saveByJdbc(sql,stm);
            }
        }
        JdbcUtil.close(conn,stm);
    }

    private static void doSave1(List<Map> list) throws SQLException{
        Connection conn = JdbcUtil.getConnection();;
        Statement stm = conn.createStatement();
        for (Map w:list){
            String empid  = w.get("empid").toString();
            String empname  = w.get("empname").toString();
            String award_type = w.get("ptype").toString();
            String deteal = w.get("deteal").toString();
            String main_content = w.get("content").toString();
            String award_time = w.get("endtime").toString().substring(0,19).replace('T',' ');

            Long num = countByJdbc1(empid, award_time, 0, stm);
            boolean isUpdate = num > 0 ? true : false;
            if (isUpdate){
                String id = countByJdbc1(empid, award_time, 1, stm).toString();
                String sql = "update siso_highexcellent set empid='" + empid
                        + "',empname='" + empname
                        + "',award_type='" + award_type
                        + "',deteal='" + deteal
                        + "',main_content='" + main_content
                        + "',award_time='" + award_time
                        + "' where id='" + id + "'";
                logger.info("update : K为"+K1+", id = "+id+"的数据");
                saveByJdbc(sql, stm);
            }else {
                Long id = countByJdbc1(empid, award_time, 2, stm);
                if (id == null) id = Long.valueOf(1);
                else id += 1;
                String sql = "insert into siso_highexcellent (id,empid,empname,award_type,deteal,main_content,award_time,remark) values ("
                        + "'" + id.toString() + "',"
                        + "'" + empid + "',"
                        + "'" + empname + "',"
                        + "'" + award_type + "',"
                        + "'" + deteal + "',"
                        + "'" + main_content + "',"
                        + "'" + award_time + "',"
                        + "'')";
                logger.info("insert : K为"+K1+", id = "+id+"的数据");
                saveByJdbc(sql, stm);
            }
        }
        JdbcUtil.close(conn,stm);
    }

    //用JDBC查询
    private static Long countByJdbc(String empid, String time, int type, Statement stm) {
        Long num = Long.valueOf(0);
        try {
            String sql = "select count(id) num from siso_bursary where empid='" + empid + "' and bursary_time='" + time + "'";
            if (type == 1) sql = "select id num from siso_bursary where empid='" + empid + "' and bursary_time='" + time + "'";
            else if (type == 2) sql = "select max(id) num from siso_bursary";
            ResultSet res = stm.executeQuery(sql);
            while(res.next()){
                //提倡通过列名获取
                num = res.getLong("num");
            }
            //关闭资源
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
    private static Long countByJdbc1(String empid, String time, int type, Statement stm) {
        Long num = Long.valueOf(0);
        try {
            String sql = "select count(id) num from siso_highexcellent where empid='" + empid + "' and award_time='" + time + "'";
            if (type == 1) sql = "select id num from siso_highexcellent where empid='" + empid + "' and award_time='" + time + "'";
            else if (type == 2) sql = "select max(id) num from siso_highexcellent";
            ResultSet res = stm.executeQuery(sql);
            while(res.next()){
                //提倡通过列名获取
                num = res.getLong("num");
            }
            //关闭资源
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    //用JDBC执行SQL语句的增删改
    private static Integer saveByJdbc(String sql, Statement stm) {
        Integer num = 0;
        try {
            num = stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
}

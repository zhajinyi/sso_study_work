package org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.service.impl;

import org.bureaumanager.bureaumanagerpro.base.BaseServiceImpl;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.dao.SisoBursaryMapper;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo.dto.SisoBursaryDto;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.service.SisoBursaryService;
import org.bureaumanager.bureaumanagerpro.sysConfig.utils.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class SisoBursaryServiceImpl extends BaseServiceImpl<SisoBursaryDto,SisoBursaryMapper> implements SisoBursaryService {
    @Autowired
    SisoBursaryMapper sisoBursaryMapper;

    public Integer countByEmpIdAndTime(SisoBursaryDto sisoBursaryDto){
        return sisoBursaryMapper.countByEmpIdAndTime(sisoBursaryDto);
    }

    public Integer getMaxId(){
        return sisoBursaryMapper.getMaxId();
    }

    public SisoBursaryDto selectByEmpIdAndTime(SisoBursaryDto sisoBursaryDto){
        return sisoBursaryMapper.selectByEmpIdAndTime(sisoBursaryDto);
    }

    public Integer updateBursary(SisoBursaryDto sisoBursaryDto){
        return sisoBursaryMapper.updateBursary(sisoBursaryDto);
    }

    public Integer addBursary(SisoBursaryDto sisoBursaryDto){
        return sisoBursaryMapper.addBursary(sisoBursaryDto);
    }

    public List<SisoBursaryDto> selectByOrgId (String orgid) {
        return countByJdbc(orgid);
    }

    public List<SisoBursaryDto> queryByTemplate (SisoBursaryDto sisoBursaryDto) {
        return selectByTemp(sisoBursaryDto);
    }
    /*public List<SisoBursaryDto> selectEntitiesByTemplate (SisoBursaryDto sisoBursaryDto) {
        return selectByTemp(sisoBursaryDto);
    }*/
    private static List<SisoBursaryDto> countByJdbc(String orgid) {
        Connection conn = null;
        Statement stm = null;
        ResultSet res = null;
        List<SisoBursaryDto> list = new ArrayList<SisoBursaryDto>();
        try {
            //1.连接数据库
            conn = JdbcUtil.getConnection();
            //2.获取到statement对象
            stm = conn.createStatement();
            //3.执行sql语句
            String sql = "select * from siso_bursary where orgid=" + orgid;
            if (orgid == null || "0".equals(orgid)) sql = "select * from siso_bursary";
            res = stm.executeQuery(sql);
            while(res.next()){
                //提倡通过列名获取
                SisoBursaryDto sisoBursaryDto = new SisoBursaryDto();
                sisoBursaryDto.setId(res.getString("id"));
                sisoBursaryDto.setEmpId(res.getString("empid"));
                sisoBursaryDto.setOrgId(orgid);
                sisoBursaryDto.setEmpName(res.getString("empname"));
                sisoBursaryDto.setOrgName(res.getString("orgname"));
                sisoBursaryDto.setClassName(res.getString("classname"));
                sisoBursaryDto.setSex(res.getString("sex"));
                sisoBursaryDto.setPolitical(res.getString("political"));
                sisoBursaryDto.setXueKeJiaQuanFen(res.getString("xueke_jiaquan_fen"));
                sisoBursaryDto.setXueKeDeFen(res.getString("xueke_defen"));
                sisoBursaryDto.setZongHeSuYangFen(res.getString("zonghesuyang_fen"));
                sisoBursaryDto.setDeYuDeFen(res.getString("deyu_defen"));
                sisoBursaryDto.setZongHeFen(res.getString("zonghe_fen"));
                sisoBursaryDto.setBursaryType(res.getString("bursary_type"));
                sisoBursaryDto.setBursaryTime(res.getString("bursary_time"));
                sisoBursaryDto.setRemark(res.getString("remark"));
                list.add(sisoBursaryDto);
            }
            //关闭资源
            JdbcUtil.close(conn, stm, res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static List<SisoBursaryDto> selectByTemp(SisoBursaryDto sisoBursary) {
        Connection conn = null;
        Statement stm = null;
        ResultSet res = null;
        List<SisoBursaryDto> list = new ArrayList<SisoBursaryDto>();
        List<String> sqlwhere = new ArrayList<String>();
        if (sisoBursary.getId() != null)
            sqlwhere.add("b.id=\'" + sisoBursary.getId() + "\'");
        if (sisoBursary.getOrgName() != null)
            sqlwhere.add("b.orgname like \"%" + sisoBursary.getOrgName() + "%\"");
        if (sisoBursary.getEmpId() != null)
            sqlwhere.add("b.empid=\'" + sisoBursary.getEmpId() + "\'");
        if (sisoBursary.getBursaryTime() != null)
            sqlwhere.add("b.bursary_time like \"%" + sisoBursary.getBursaryTime() + "%\"");
        if (sisoBursary.getBursaryType() != null)
            sqlwhere.add("b.bursary_type=\'" + sisoBursary.getBursaryType() + "\'");
        if (sisoBursary.getClassName() != null)
            sqlwhere.add("b.classname like \"%" + sisoBursary.getClassName() + "%\"");
        if (sisoBursary.getDeYuDeFen() != null)
            sqlwhere.add("b.deyu_defen=\'" + sisoBursary.getDeYuDeFen() + "\'");
        if (sisoBursary.getEmpName() != null)
            sqlwhere.add("b.empname like \"%" + sisoBursary.getEmpName() + "%\"");
        if (sisoBursary.getOrgId() != null)
            sqlwhere.add("o.id=\'" + sisoBursary.getOrgId() + "\'");
        if (sisoBursary.getPolitical() != null)
            sqlwhere.add("b.political=\'" + sisoBursary.getPolitical() + "\'");
        if (sisoBursary.getSex() != null)
            sqlwhere.add("b.sex=\'" + sisoBursary.getSex() + "\'");
        if (sisoBursary.getXueKeDeFen() != null)
            sqlwhere.add("b.xueke_defen=\'" + sisoBursary.getXueKeDeFen() + "\'");
        if (sisoBursary.getXueKeJiaQuanFen() != null)
            sqlwhere.add("b.xueke_jiaquan_fen=\'" + sisoBursary.getXueKeJiaQuanFen() + "\'");
        if (sisoBursary.getZongHeFen() != null)
            sqlwhere.add("b.zonghe_fen=\'" + sisoBursary.getZongHeFen() + "\'");
        if (sisoBursary.getZongHeSuYangFen() != null)
            sqlwhere.add("b.zonghesuyang_fen=\'" + sisoBursary.getZongHeSuYangFen() + "\'");
        if (sisoBursary.getGrade() != null)
            sqlwhere.add("s.grade=\'" + sisoBursary.getGrade() + "\'");
        if (sisoBursary.getClassId() != null)
            sqlwhere.add("c.id=\'" + sisoBursary.getClassId() + "\'");
        try {
            //1.连接数据库
            conn = JdbcUtil.getConnection();
            //2.获取到statement对象
            stm = conn.createStatement();
            //3.执行sql语句
            String sql = "select b.*,c.id as classid,o.id as org_id,s.grade as grade from siso_bursary b,siso_student_info s,sys_organization o,siso_class_info c where b.empid=s.id and s.college_code=o.id and s.class_code=c.id";
            if (!sqlwhere.isEmpty()) {
                for (String s : sqlwhere) {
                    sql += (" and " + s);
                }
            }
            res = stm.executeQuery(sql);
            while(res.next()){
                //提倡通过列名获取
                SisoBursaryDto sisoBursaryDto = new SisoBursaryDto();
                sisoBursaryDto.setId(res.getString("id"));
                sisoBursaryDto.setEmpId(res.getString("empid"));
                sisoBursaryDto.setOrgId(res.getString("org_id"));
                sisoBursaryDto.setEmpName(res.getString("empname"));
                sisoBursaryDto.setOrgName(res.getString("orgname"));
                sisoBursaryDto.setClassName(res.getString("classname"));
                sisoBursaryDto.setSex(res.getString("sex"));
                sisoBursaryDto.setPolitical(res.getString("political"));
                sisoBursaryDto.setXueKeJiaQuanFen(res.getString("xueke_jiaquan_fen"));
                sisoBursaryDto.setXueKeDeFen(res.getString("xueke_defen"));
                sisoBursaryDto.setZongHeSuYangFen(res.getString("zonghesuyang_fen"));
                sisoBursaryDto.setDeYuDeFen(res.getString("deyu_defen"));
                sisoBursaryDto.setZongHeFen(res.getString("zonghe_fen"));
                sisoBursaryDto.setBursaryType(res.getString("bursary_type"));
                sisoBursaryDto.setBursaryTime(res.getString("bursary_time"));
                sisoBursaryDto.setRemark(res.getString("remark"));
                sisoBursaryDto.setClassId(res.getString("classid"));
                sisoBursaryDto.setGrade(res.getString("grade"));
                list.add(sisoBursaryDto);
            }
            //关闭资源
            JdbcUtil.close(conn, stm, res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

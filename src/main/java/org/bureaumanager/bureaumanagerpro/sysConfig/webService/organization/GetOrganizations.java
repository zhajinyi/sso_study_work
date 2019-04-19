package org.bureaumanager.bureaumanagerpro.sysConfig.webService.organization;

import org.apache.commons.lang3.StringUtils;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.SysOrganization;
import org.bureaumanager.bureaumanagerpro.sysConfig.utils.JdbcUtil;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/**
 * @author WangJiaWei
 * @date 2019-01-24 09:29:07
 *
 */
public class GetOrganizations {
    public static void getAllOrganizations() throws Exception{
        System.out.println("sisoOrganization update start...");
        Document doc;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        // 获取调用接口后返回的流
        InputStream is = getSoapInputStream();
        doc = db.parse(is);
        NodeList nl = doc.getElementsByTagName("getOrginazationResult");
        int len = nl.getLength();
        SysOrganization sysOrganization = new SysOrganization();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Connection conn = JdbcUtil.getConnection();;
        Statement stm = conn.createStatement();
        for (int i = 0; i < len; i++) {
            String id = validatenode("ORGID",i,doc);
            sysOrganization.setId(Integer.valueOf(id.substring(1,id.length()-1)).toString());
            sysOrganization.setOrgName(validatenode("ORGNAME",i,doc));
            String parentId = validatenode("PARENT_ID",i,doc);
            if (parentId == null)
                sysOrganization.setParentId("'0'".equals(id) ? parentId : "'0'");
            else
                sysOrganization.setParentId(parentId);
            String parentIds = validatenode("PARENT_IDS",i,doc);
            sysOrganization.setParentIds(parentIds == null ? "''" : parentIds);
            String areaId = validatenode("AREA_ID",i,doc);
            //sysOrganization.setAreaId(areaId == null ? "''" : areaId);
            //sysOrganization.setCode(validatenode("CODE",i,doc));
            String orgType = validatenode("ORGTYPE",i,doc);
            sysOrganization.setOrgType(orgType == null ? "''" : orgType);
            String orgGrade = validatenode("ORGDEGREE",i,doc);
            if (orgGrade == null)
                sysOrganization.setOrgGrade("'0'".equals(id) ? "'1'" : "'2'");
            else
                sysOrganization.setOrgGrade("''");
            //sysOrganization.setAddress(validatenode("ORGADDR",i,doc));
            //sysOrganization.setZipCode(validatenode("ZIPCODE",i,doc));
            //sysOrganization.setPhone(validatenode("LINKTEL",i,doc));
            //sysOrganization.setFax(validatenode("FAX",i,doc));
            //sysOrganization.setEmail(validatenode("EMAIL",i,doc));
            //sysOrganization.setUseable("'Y'".equals(validatenode("USEABLE",i,doc)) ? "'1'" : "'0'");
            //sysOrganization.setPrimaryPerson(validatenode("MANAGERID",i,doc));
            //sysOrganization.setDeputyPerson(validatenode("DEPUTY_PERSION",i,doc));
            sysOrganization.setCreateBy(validatenode("CREATE_BY",i,doc));
            String createDate = validatenode("CREATE_DATE",i,doc);
//            sysOrganization.setCreateDate(sdf.parse(createDate.substring(1,createDate.length()-1)));
            sysOrganization.setUpdateBy(validatenode("UPDATE_BY",i,doc));
            String updateDate = validatenode("UPDATE_DATE",i,doc);
//            sysOrganization.setUpdateDate(sdf.parse(updateDate.substring(1,updateDate.length()-1)));
            //sysOrganization.setRemarks(validatenode("REMARKS",i,doc));
            sysOrganization.setDelFlag(validatenode("DEL_FLAG",i,doc));
            sysOrganization.setSort(Long.valueOf(0));
            int num = countByJdbc(Integer.valueOf(id.substring(1,id.length()-1)).toString(),stm);
//            String sql = num > 0 ? sysOrganization.getUpdateSql() : sysOrganization.getInsertSql();
//            saveByJdbc(sql);
            String sql = null;
            if (num > 0)
                sql = "update sys_organization set "
                        + "org_name=" + sysOrganization.getOrgName()
                        + ",parent_id=" + sysOrganization.getParentId()
                        + ",sort=" + sysOrganization.getSort()
                        + ",parent_ids=" + sysOrganization.getParentIds()
                        + ",org_grade=" + sysOrganization.getOrgGrade()
                        + ",useable=" + sysOrganization.getUseable()
                        + ",create_by=" + sysOrganization.getCreateBy()
                        + ",update_by=" + sysOrganization.getUpdateBy()
                        + (sysOrganization.getCreateDate() == null ? ",create_date=" + sysOrganization.getCreateDate() : ",create_date=DATE_FORMAT(" + sysOrganization.getCreateDate() + ",'%Y-%m-%d')")
                        + (sysOrganization.getUpdateDate() == null ? ",update_date=" + sysOrganization.getUpdateDate() : ",update_date=DATE_FORMAT(" + sysOrganization.getUpdateDate() + ",'%Y-%m-%d')")
                        + ",del_flag=" + sysOrganization.getDelFlag()
                        + ",remark=" + sysOrganization.getRemark()
                        +" where id="+id;
            else
                sql = "insert into sys_user (id,org_name,parent_id,parent_ids,sort,org_type,org_grade,useable,create_by,update_by,create_date,update_date,del_flag,remark) values ("
                        + id + ","
                        + sysOrganization.getOrgName() + ","
                        + sysOrganization.getParentId() + ","
                        + sysOrganization.getSort() + ","
                        + sysOrganization.getParentIds() + ","
                        + sysOrganization.getOrgType() + ","
                        + sysOrganization.getOrgGrade() + ","
                        + sysOrganization.getUseable() + ","
                        + sysOrganization.getCreateBy() + ","
                        + sysOrganization.getUpdateBy() + ","
                        + (sysOrganization.getCreateDate() == null ? sysOrganization.getCreateDate()+"," : "DATE_FORMAT(" + sysOrganization.getCreateDate() + ",'%Y-%m-%d'),")
                        + (sysOrganization.getUpdateDate() == null ? sysOrganization.getUpdateDate()+"," : "DATE_FORMAT(" + sysOrganization.getUpdateDate() + ",'%Y-%m-%d'),")
                        + sysOrganization.getDelFlag() + ","
                        + sysOrganization.getRemark()
                        + ")";
            saveByJdbc(sql,stm);
        }
        JdbcUtil.close(conn,stm);
        is.close();
        System.out.println("sisoOrganization update end!");
    }

    /*
     * 用户把SOAP请求发送给服务器端，并返回服务器点返回的输入流
     *
     *
     * @return 服务器端返回的输入流，供客户端读取
     *
     * @throws Exception
     *
     * @备注：有四种请求头格式1、SOAP 1.1； 2、SOAP 1.2 ； 3、HTTP GET； 4、HTTP POST
     * 参考---》http://
     *
     */
    private static InputStream getSoapInputStream() throws Exception {
        try {
            // 获取请求规范
            String soap = getSoapRequestString();
            if (soap == null) {
                return null;
            }
            //调用的webserviceURL
            URL url = new URL("http://192.168.200.89:5888/services/ws_xgxt_orginazation");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Length", Integer.toString(soap.length()));
            conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            conn.setRequestProperty("SOAPAction", "http://www.apusic.com/esb/ws_xgxt_orginazation");
            conn.setRequestProperty("WSS-Password Type", "PasswordText");
            OutputStream os = conn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
            osw.write(soap);
            osw.flush();
            osw.close();
            InputStream is = null;
            int code = conn.getResponseCode();
            if (code == 200) {
                is = conn.getInputStream(); // 得到网络返回的输入流
            } else {
                is = conn.getErrorStream(); // 得到网络返回的输入流}
            }
            return is;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * 获取SOAP的请求头，并替换其中的标志符号为用户输入的城市
     *
     * @param city： 用户输入的城市名称
     *
     * @return 客户将要发送给服务器的SOAP请求规范
     *
     * @备注：有四种请求头格式1、SOAP 1.1； 2、SOAP 1.2 ； 3、HTTP GET； 4、HTTP POST
     * 参考---》http://
     *
     * 本文采用：SOAP 1.1格式
     */
    private static String getSoapRequestString() {
        StringBuffer sb = new StringBuffer();
        String username = "sisoservice";
        String password = "123@abcd";
        sb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://www.apusic.com/esb/ws_xgxt_orginazation\">");
        sb.append("	<soapenv:Header> ");
        sb.append("		<wsse:Security soapenv:mustUnderstand=\"1\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\"> ");
        sb.append("			<wsse:UsernameToken wsu:Id=\"UsernameToken-1\"> ");
        sb.append("				<wsse:Username>"+username+"</wsse:Username> ");
        sb.append("				<wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">"+password+"</wsse:Password>  ");
        sb.append("			</wsse:UsernameToken> ");
        sb.append("		</wsse:Security> ");
        sb.append("	</soapenv:Header> ");
        sb.append("	<soapenv:Body>");
        sb.append("		<ws:getOrginazation/>");
        sb.append("	</soapenv:Body>");
        sb.append("</soapenv:Envelope>");
        return sb.toString();
    }

    private static String validatenode(String Tagname, int i, Document doc) {
        if (null == doc.getElementsByTagName(Tagname) || "null".equals(doc.getElementsByTagName(Tagname))) {
            return null;
        }
        if (null == doc.getElementsByTagName(Tagname).item(i) || "null".equals(doc.getElementsByTagName(Tagname).item(i))) {
            return null;
        }
        if (null == doc.getElementsByTagName(Tagname).item(i).getFirstChild() || "null".equals(doc.getElementsByTagName(Tagname).item(i).getFirstChild())) {
            return null;
        }
        return !StringUtils.isNotBlank(doc.getElementsByTagName(Tagname).item(i).getFirstChild().getNodeValue())? null : "'"+doc.getElementsByTagName(Tagname).item(i).getFirstChild().getNodeValue()+"'";
    }

    //用JDBC查询
    private static Integer countByJdbc(String id, Statement stm) {
        Integer num = 0;
        try {
            String sql = "select count(id) num from sys_organization where id=" + id;
            ResultSet res = stm.executeQuery(sql);
            while(res.next()){
                //提倡通过列名获取
                num = res.getInt("num");
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

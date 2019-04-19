package org.bureaumanager.bureaumanagerpro.sysConfig.webService.empDataInterface;

import org.apache.commons.lang3.StringUtils;
import org.bureaumanager.bureaumanagerpro.sysConfig.utils.JdbcUtil;
import org.springframework.stereotype.Component;
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

@Component
public class GetEmpInfos {

    public static void getAllEmployee() throws Exception{
        System.out.println("employee update start...");
        Document doc;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        // 获取调用接口后返回的流
        InputStream is = getSoapInputStream();
        doc = db.parse(is);
        NodeList nl = doc.getElementsByTagName("getEmployeeInfoResult");
        int len = nl.getLength();
        Connection conn = JdbcUtil.getConnection();;
        Statement stm = conn.createStatement();
        for (int i = 0; i < len; i++) {
            String EMPID = validatenode("EMPID",i,doc);
            String EMPCODE = validatenode("EMPCODE",i,doc);
            String OPERATORID = validatenode("OPERATORID",i,doc);
            String USERID = validatenode("USERID",i,doc);
            String EMPNAME = validatenode("EMPNAME",i,doc);
            String REALNAME = validatenode("REALNAME",i,doc);
            String GENDER = validatenode("GENDER",i,doc);
            String BIRTHDATE = validatenode("BIRTHDATE",i,doc);
            BIRTHDATE = BIRTHDATE == null ? null : utcToString(BIRTHDATE.substring(0, 20) + "'");
            String POSITION = validatenode("POSITION",i,doc);
            String EMPSTATUS = validatenode("EMPSTATUS",i,doc);
            String CARDTYPE = validatenode("CARDTYPE",i,doc);
            String CARDNO = validatenode("CARDNO",i,doc);
            String INDATE = validatenode("INDATE",i,doc);
            INDATE = INDATE == null ? null : utcToString(INDATE.substring(0, 20) + "'");
            String OUTDATE = null;
            String OTEL = validatenode("OTEL",i,doc);
            String OADDRESS = validatenode("OADDRESS",i,doc);
            String OZIPCODE = validatenode("OZIPCODE",i,doc);
            String OEMAIL = validatenode("OEMAIL",i,doc);
            String FAXNO = validatenode("FAXNO",i,doc);
            String MOBILENO = validatenode("MOBILENO",i,doc);
            String QQ = validatenode("QQ",i,doc);
            String HTEL = validatenode("HTEL",i,doc);
            String HADDRESS = validatenode("HADDRESS",i,doc);
            String HZIPCODE = validatenode("HZIPCODE",i,doc);
            String PEMAIL = validatenode("PEMAIL",i,doc);
            String PARTY = validatenode("PARTY",i,doc);
            String DEGREE = validatenode("DEGREE",i,doc);
            String SORTNO = validatenode("SORTNO",i,doc);
            String MAJOR = validatenode("MAJOR",i,doc);
            String SPECIALTY = validatenode("SPECIALTY",i,doc);
            String WORKEXP = validatenode("WORKEXP",i,doc);
            String REGDATE = validatenode("REGDATE",i,doc);
            REGDATE = REGDATE == null ? null : utcToString(REGDATE.substring(0, 20) + "'");
            String CREATETIME = validatenode("CREATETIME",i,doc);
            CREATETIME = CREATETIME == null ? null : utcToString(CREATETIME.substring(0, 20) + "'");
            String LASTMODYTIME = validatenode("LASTMODYTIME",i,doc);
            LASTMODYTIME = LASTMODYTIME == null ? null : utcToString(LASTMODYTIME.substring(0, 20) + "'");
            String ORGIDLIST = validatenode("ORGIDLIST",i,doc);
            String ORGID = validatenode("ORGID",i,doc);
            String REMARK = validatenode("REMARK",i,doc);
            String TENANT_ID = validatenode("TENANT_ID",i,doc);
            String APP_ID = validatenode("APP_ID",i,doc);
            String WEIBO = validatenode("WEIBO",i,doc);
            String EMP_TYPE = validatenode("EMP_TYPE",i,doc);
            String NATIONALITY = validatenode("NATIONALITY",i,doc);
            String NATION = validatenode("NATION",i,doc);

            int num = countByJdbc(EMPID, stm);
            String sql = null;
            if (num > 0)
                sql = "update sys_emp_info set "
                        + "emp_id=" + EMPCODE
                        + ",sortno=" + SORTNO
                        + ",nickname=" + EMPNAME
                        + ",realname=" + REALNAME
                        + ",gender=" + GENDER
                        + (BIRTHDATE == null ? ",birthdate=" + BIRTHDATE : ",birthdate=DATE_FORMAT(" + BIRTHDATE + ",'%Y-%m-%d')")
                        + ",cardtype=" + CARDTYPE
                        + ",cardno=" + CARDNO
                        + ",telephone=" + OTEL
                        + ",family_address=" + HADDRESS
                        + ",native_place=" + OADDRESS
                        + ",email=" + OEMAIL
                        + ",faxno=" + FAXNO
                        + ",mobileno=" + MOBILENO
                        + ",QQ=" + QQ
                        + ",weibo=" + WEIBO
                        + ",degree=" + DEGREE
                        + ",specialty=" + SPECIALTY
                        + ",postcode=" + HZIPCODE
                        + ",nation=" + NATION
                        + ",politics_status=" + PARTY
                        + ",nationality=" + NATIONALITY
                        + ",org_id=" + ORGID
                        + ",remark=" + REMARK
                        + " where id=" + EMPID;
            else
                sql = "insert into sys_emp_info(id,emp_id,org_id,nickname,sortno,realname,gender,birthdate,cardtype,cardno,\n" +
                        "faxno,mobileno,telephone,family_address,native_place,email,faxno,QQ,weibo,postcode,nation,politics_status,nationality,degree,specialty,remark) values ("
                        + EMPID + ","
                        + EMPCODE + ","
                        + ORGID + ","
                        + EMPNAME + ","
                        + SORTNO + ","
                        + REALNAME + ","
                        + GENDER + ","
                        + (BIRTHDATE == null ? BIRTHDATE + "," : "DATE_FORMAT((" + BIRTHDATE + ",'%Y-%m-%d'),")
                        + CARDTYPE + ","
                        + CARDNO + ","
                        + FAXNO + ","
                        + MOBILENO + ","
                        + OTEL + ","
                        + HADDRESS + ","
                        + OADDRESS + ","
                        + OEMAIL + ","
                        + FAXNO + ","
                        + QQ + ","
                        + WEIBO + ","
                        + HZIPCODE + ","
                        + NATION + ","
                        + PARTY + ","
                        + NATIONALITY + ","
                        + DEGREE + ","
                        + SPECIALTY + ","
                        + REMARK
                        + ")";
            saveByJdbc(sql, stm);
        }
        JdbcUtil.close(conn, stm);
        is.close();
        System.out.println("employee update end!");
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
            URL url = new URL("http://192.168.200.89:5888/services/ws_bsdt_employee");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Length", Integer.toString(soap.length()));
            conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            conn.setRequestProperty("SOAPAction", "http://www.apusic.com/esb/ws_bsdt_employee/getEmployeeInfo");
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
        sb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://www.apusic.com/esb/ws_bsdt_employee\">");
        sb.append("	<soapenv:Header> ");
        sb.append("		<wsse:Security soapenv:mustUnderstand=\"1\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\"> ");
        sb.append("			<wsse:UsernameToken wsu:Id=\"UsernameToken-1\"> ");
        sb.append("				<wsse:Username>"+username+"</wsse:Username> ");
        sb.append("				<wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">"+password+"</wsse:Password>  ");
        sb.append("			</wsse:UsernameToken> ");
        sb.append("		</wsse:Security> ");
        sb.append("	</soapenv:Header> ");
        sb.append("	<soapenv:Body>");
        sb.append("		<ws:getEmployeeInfo/>");
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
    private static Integer countByJdbc(String empid, Statement stm) {
        Integer num = 0;
        try {
            String sql = "select count(id) num from sys_emp_info where emp_id= " + empid ;
            ResultSet res = stm.executeQuery(sql);
            while(res.next()){
                //提倡通过列名获取
                num = res.getInt("num");
            }
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

    //处理时间格式
    private static String utcToString(String utc) {
        if (utc == null)
            return null;
        String[] str = utc.split("T");
        return str[0] + " " + str[1];
    }
}

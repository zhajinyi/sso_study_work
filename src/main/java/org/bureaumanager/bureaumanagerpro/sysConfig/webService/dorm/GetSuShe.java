package org.bureaumanager.bureaumanagerpro.sysConfig.webService.dorm;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.bureaumanager.bureaumanagerpro.sysConfig.utils.JdbcUtil;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * @author WangJiaWei
 * @date 2019-01-23 10:01:30
 *
 */
public class GetSuShe {
    public static void getAllSuShe() throws Exception{
        System.out.println("sisoDormInfo update start...");
        Document doc;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        // 获取调用接口后返回的流
        InputStream is = getSoapInputStream();
        doc = db.parse(is);
        NodeList nl = doc.getElementsByTagName("getSusheResult");
        int len = nl.getLength();
        Connection conn = JdbcUtil.getConnection();;
        Statement stm = conn.createStatement();
        for (int i = 0; i < len; i++) {
            String id = validatenode("ID",i,doc);
            String area = validatenode("AREA",i,doc);
            String building_num = validatenode("BUILDING_NUM",i,doc);
            String floor = validatenode("FLOOR",i,doc);
            String state = validatenode("STATE",i,doc);
            String type = validatenode("TYPE",i,doc);
            String current_num = validatenode("CURRENT_NUM",i,doc);
            String bunk_num = validatenode("BUNK_NUM",i,doc);
            String leader = validatenode("LEADER",i,doc);
            String orientation = validatenode("ORIENTATION",i,doc);
            String is_allot = validatenode("IS_ALLOT",i,doc);
            String remark = validatenode("REMARK",i,doc);

            int num = countByJdbc(id, stm);
            String sql = null;
            if (num > 0)
                sql = "update siso_dorm_info set "
                        + "area=" + area
                        + ",building_num=" + building_num
                        + ",floor=" + floor
                        + ",state=" + state
                        + ",type=" + type
                        + ",current_num=" + current_num
                        + ",bunk_num=" + bunk_num
                        + ",leader=" + leader
                        + ",orientation=" + orientation
                        + ",is_allot=" + is_allot
                        + ",remark=" + remark
                        + " where id=" + id;
            else
                sql = "insert into siso_dorm_info (id,area,building_num,floor,state,type,current_num,bunk_num,leader,orientation,is_allot,remark) values ("
                        + id + ","
                        + area + ","
                        + building_num + ","
                        + floor + ","
                        + state + ","
                        + type + ","
                        + current_num + ","
                        + bunk_num + ","
                        + leader + ","
                        + orientation + ","
                        + is_allot + ","
                        + remark + ")";
            saveByJdbc(sql, stm);
        }
        JdbcUtil.close(conn, stm);
        is.close();
        System.out.println("sisoDormInfo update end!");
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
            URL url = new URL("http://192.168.200.89:5888/services/ws_xgxt_sushe");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Length", Integer.toString(soap.length()));
            conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            conn.setRequestProperty("SOAPAction", "http://www.apusic.com/esb/ws_xgxt_sushe");
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
        sb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://www.apusic.com/esb/ws_xgxt_sushe\">");
        sb.append("	<soapenv:Header> ");
        sb.append("		<wsse:Security soapenv:mustUnderstand=\"1\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\"> ");
        sb.append("			<wsse:UsernameToken wsu:Id=\"UsernameToken-1\"> ");
        sb.append("				<wsse:Username>"+username+"</wsse:Username> ");
        sb.append("				<wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">"+password+"</wsse:Password>  ");
        sb.append("			</wsse:UsernameToken> ");
        sb.append("		</wsse:Security> ");
        sb.append("	</soapenv:Header> ");
        sb.append("	<soapenv:Body>");
        sb.append("		<ws:getSushe/>");
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
            String sql = "select count(id) num from siso_dorm_info where id=" + id;
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
}

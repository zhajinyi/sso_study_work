package org.bureaumanager.bureaumanagerpro.sysConfig.utils;

import java.sql.*;


public class JdbcUtil {
	private static PropertiesLoader loader = new PropertiesLoader("application.properties");
	private static String url =loader.getProperty("spring.datasource.url");//获得配置文件中的数据库地址
	private static String user = loader.getProperty("spring.datasource.username");//用户名
	private static String password =loader.getProperty("spring.datasource.password");//密码
	private static String driver =loader.getProperty("spring.datasource.driverClassName");//驱动

//	public static void main(String[] args) {
//		//测试得到的配置文件数据是否正确
//		System.out.println(loader.getProperty("spring.datasource.url"));
//	}

	//链接数据库
	static{

		//1.加载驱动
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	//连接数据库
	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	//关闭资源
	public static void close (Connection conn ,Statement stm){
		if(stm!=null){
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
				new RuntimeException(e);
			}finally{
				if(conn!=null){
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						new RuntimeException(e);
					}
				}
			}
		}
	}

	//关闭资源
	public static void close (Connection conn ,Statement stm,ResultSet res){
		if(res!= null){
			try {
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
				new RuntimeException(e);
			}finally{
				if(stm!=null){
					try {
						stm.close();
					} catch (SQLException e) {
						e.printStackTrace();
						new RuntimeException(e);
					}finally{
						if(conn!=null){
							try {
								conn.close();
							} catch (SQLException e) {
								e.printStackTrace();
								new RuntimeException(e);
							}
						}
					}
				}
			}
		}
	}

	//使用示例
//	public static void main(String[] args) {
//		Connection conn = null;
//		Statement stm = null;
//		ResultSet res = null;
//
//		try {
//			//1.连接数据库
//			conn = JdbcUtil.getConn();
//			//2.获取到statement对象
//			stm = conn.createStatement();
//			//3.写Sql语句
//			String sql = "SELECT e.EMPID,e.EMPNAME,e.CARDNO,u.\"PASSWORD\" FROM ORG_EMPLOYEE e,CAP_USER u WHERE e.EMPID=u.OPERATOR_ID AND "+
//					"e.EMPNAME LIKE '%王佳伟%'";
//			//4.执行sql语句
//			res = stm.executeQuery(sql);
//
//			String[] props = new String[4];
//			props[0] = "empid";
//			props[1] = "empname";
//			props[2] = "cardno";
//			props[3] = "password";
//			ArrayList<Temp> list = new ArrayList<Temp>();
//			Temp temp = new Temp();
//			while(res.next()){
//				//提倡通过列名获取
//				for(String str:props){
//					temp.set(str, res.getString(str));
//				}
//				list.add(temp);
//				temp = new Temp();
//			}
//			for (Temp temp2 : list) {
//				System.out.println(temp2);
//			}
//
//			//关闭资源
//			JdbcUtil.close(conn, stm, res);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			new RuntimeException(e);
//		}
//	}
}


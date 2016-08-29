package yang.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils {
	private static String driver = null;
	private static String url = null;
	private static String username = null;
	private static String password = null;
	
	 static{
	        try{
	            //读取db.properties文件中的数据库连接信息
	            InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
	            Properties prop = new Properties();
	            prop.load(in);
	            
	            //获取数据库连接驱动
	            driver = prop.getProperty("driver");
	            //获取数据库连接URL地址
	            url = prop.getProperty("url");
	            //获取数据库连接用户名
	            username = prop.getProperty("username");
	            //获取数据库连接密码
	            password = prop.getProperty("password");
	            
	            //加载数据库驱动
	            Class.forName(driver);
	            
	        }catch (Exception e) {
	            throw new ExceptionInInitializerError(e);
	        }
	    }
	 
	 	/**
	    * @Method: getConnection
	    * @Description: 获取数据库连接对象
	    * @Anthor:yangzhen
	    * 
	    * @return Connection数据库连接对象
	    * @throws SQLException
	    */ 
	    public static Connection getConnection() throws SQLException{
	        return DriverManager.getConnection(url, username,password);
	    }
	    
	    /**
	     * @Method: release
	     * @Description: 释放资源，
	     *     要释放的资源包括Connection数据库连接对象，负责执行SQL命令的Statement对象，存储查询结果的ResultSet对象
	     * @Anthor:yangzhen
	     *
	     * @param conn
	     * @param st
	     * @param rs
	     */ 
	     public static void release(Connection conn,Statement st,ResultSet rs){
	         if(rs!=null){
	             try{
	                 //关闭存储查询结果的ResultSet对象
	                 rs.close();
	             }catch (Exception e) {
	                 e.printStackTrace();
	             }
	             rs = null;
	         }
	         if(st!=null){
	             try{
	                 //关闭负责执行SQL命令的Statement对象
	                 st.close();
	             }catch (Exception e) {
	                 e.printStackTrace();
	             }
	         }
	         
	         if(conn!=null){
	             try{
	                 //关闭Connection数据库连接对象
	                 conn.close();
	             }catch (Exception e) {
	                 e.printStackTrace();
	             }
	         }
	     }
}

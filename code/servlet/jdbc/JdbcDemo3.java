package yang.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

public class JdbcDemo3{

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//要连接的数据库URL
      String url = "jdbc:mysql://localhost:3306/test";
      System.out.println("The database you will connect is :"+url);
      //连接的数据库时使用的用户名
      String username = "root";
      //连接的数据库时使用的密码
      String password = "root";
      
      //1.加载驱动
      //DriverManager.registerDriver(new com.mysql.jdbc.Driver());不推荐使用这种方式来加载驱动
		System.out.println("Now load the Driver!");
      Class.forName("com.mysql.jdbc.Driver");//推荐使用这种方式来加载驱动
      //2.获取与数据库的链接
      Connection conn = DriverManager.getConnection(url, username, password);
      System.out.println("Now get the connection!");
      //3.获取用于向数据库发送sql语句的statement
      Statement st = conn.createStatement();
		String sql = null;
		ResultSet rs =null;
		List<JSONObject> val = new ArrayList<JSONObject>();
		Calendar c = Calendar.getInstance();
		long times = c.getTimeInMillis();
		
		System.out.println("当前毫秒数是:"+times);
		SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd HH");
		
		for(int i=0;i<24;i++){
			Date date1 = new Date(times-((long)i*60*60*1000));
			String now = sdf0.format(date1);
			System.out.println(now);
			sql = "select hedstate,pfdate from h_pub_hedstate where pfdate like \'"+now+"%\' limit 2";
			System.out.println("The sql is:"+sql);
			
			rs = st.executeQuery(sql);
			
			  System.out.println("The result is:");
		      while(rs.next()){
		    	  System.out.println("hedstate:"+rs.getObject("hedstate"));
					System.out.println("pfdate"+rs.getObject("pfdate"));
					JSONObject json = new JSONObject().put("hedstate", rs.getObject("hedstate"));
					json.put("pfdate", rs.getObject("pfdate"));
					val.add(json);
					
		      }
		}
	
      System.out.println("Now close the connection!");
      rs.close();
      st.close();
      conn.close();

	}

}

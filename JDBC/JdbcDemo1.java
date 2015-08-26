//package yang.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemo1 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//要连接的数据库URL
        String url = "jdbc:mysql://localhost:3306/sushe";
        //连接的数据库时使用的用户名
        String username = "root";
        //连接的数据库时使用的密码
        String password = "root";
        
        //1.加载驱动
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());不推荐使用这种方式来加载驱动
        Class.forName("com.mysql.jdbc.Driver");//推荐使用这种方式来加载驱动
        //2.获取与数据库的链接
        Connection conn = DriverManager.getConnection(url, username, password);
        
        //3.获取用于向数据库发送sql语句的statement
        Statement st = conn.createStatement();
        
        String sql = "select Student_ID,Student_Name,Student_Password,Student_Class,Student_Sex from student";
        //4.向数据库发sql,并获取代表结果集的resultset
        ResultSet rs = st.executeQuery(sql);
        
        //5.取出结果集的数据
        while(rs.next()){
            System.out.println("Student_ID=" + rs.getObject("Student_ID"));
            System.out.println("Student_Name=" + rs.getObject("Student_Name"));
            System.out.println("Student_Password=" + rs.getObject("Student_Password"));
            System.out.println("Student_Class=" + rs.getObject("Student_Class"));
            System.out.println("Student_Sex=" + rs.getObject("Student_Sex"));
        }
        
        //6.关闭链接，释放资源
        rs.close();
        st.close();
        conn.close();

	}

}

//package yang.jdbc;

/**
* javac  -cp .:postgresql-42.1.4.jar JdbcDemo1.java
* java  -cp .:postgresql-42.1.4.jar JdbcDemo1
ctreate table student(
Student_ID serial,
Student_Name varchar(50),
Student_Password varchar(50),
Student_State varchar(10),
Student_Class varchar(10),
Student_Sex varchar(5),
Student_DomitoryID smallint,
Student_Username varchar(50),
PRIMARY KEY Student_ID
)

create  table "public"."student"
(
"Student_ID" serial NOT NULL ,
"Student_Name" varchar NOT NULL ,
"Student_Password" varchar NOT NULL ,
"Student_State" varchar NOT NULL ,
"Student_Class" varchar NOT NULL ,
"Student_Sex" varchar NOT NULL ,
"Student_DomitoryID" smallint NOT NULL ,
"Student_Username" varchar NOT NULL ,
CONSTRAINT "pk_public_student" PRIMARY KEY ("Student_ID")
)
WITH (
FILLFACTOR = 100,
OIDS = FALSE
)
;
COMMENT ON TABLE "public"."student" IS 'student';

insert into "public"."student"("Student_Name","Student_Password","Student_State","Student_Class","Student_Sex","Student_DomitoryID","Student_Username") values('yangzhen','yangzhen','up','1','1',1,'yang');
insert into "public"."student"("Student_Name","Student_Password","Student_State","Student_Class","Student_Sex","Student_DomitoryID","Student_Username") values('yang','yang','yang','yang','yang',12,'yang');
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemo1 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//要连接的数据库URL
        String url = "jdbc:postgresql://localhost:32768/postgres";
        System.out.println("The database you will connect is :"+url);
        //连接的数据库时使用的用户名
        String username = "postgres";
        //连接的数据库时使用的密码
        String password = "";

        //1.加载驱动
        DriverManager.registerDriver(new org.postgresql.Driver());//不推荐使用这种方式来加载驱动
        // DriverManager.registerDriver(Class.forName("org.postgresql.Driver"));//推荐使用这种方式来加载驱动
        //2.获取与数据库的链接
        Connection conn = DriverManager.getConnection(url, username, "");

        //3.获取用于向数据库发送sql语句的statement
        Statement st = conn.createStatement();
				//参考：http://blog.csdn.net/zengchaoyue/article/details/8279744
				//参考：PostgreSQL中表名、字段名大小写问题
        String sql = "select \"Student_ID\",\"Student_Name\",\"Student_Password\",\"Student_Class\",\"Student_Sex\" from student";
        System.out.println("The sql is:"+sql);
        //4.向数据库发sql,并获取代表结果集的resultset
        ResultSet rs = st.executeQuery(sql);

        //5.取出结果集的数据
        System.out.println("The result is:");
        while(rs.next()){
            System.out.println("Student_ID=" + rs.getObject("Student_ID"));
            System.out.println("Student_Name=" + rs.getObject("Student_Name"));
            System.out.println("Student_Password=" + rs.getObject("Student_Password"));
            System.out.println("Student_Class=" + rs.getObject("Student_Class"));
            System.out.println("Student_Sex=" + rs.getObject("Student_Sex"));
        }
        //6.使用excuteUpdate(String sql)方法完成数据的添加操作
        String sql2="insert into student(\"Student_DomitoryID\",\"Student_Username\",\"Student_Password\",\"Student_Name\",\"Student_Sex\",\"Student_Class\",\"Student_State\")"
        		+ "values(1,'001','123','yang','男','信管1101','入住')";
        System.out.println(">Insert into a new list:"+sql2);
        int num = st.executeUpdate(sql2);
        if(num>0){
        	System.out.println("Insert Success!!");
        }
        //6.关闭链接，释放资源
        System.out.println("Now close the connection!");
        rs.close();
        st.close();
        conn.close();

	}

}

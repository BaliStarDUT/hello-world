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
mysql DMS
CREATE TABLE `student` (
	`Student_ID` int(11) NOT NULL,
	`Student_Name` varchar(32) NULL,
	`Student_Password` varchar(32) NULL,
	`Student_State` varchar(32) NULL,
	`Student_Class` varchar(32) NULL,
	`Student_Sex` varchar(32) NULL,
	`Student_DomitoryID` varchar(32) NULL,
	`Student_Username` varchar(32) NULL,
	PRIMARY KEY (`Student_ID`)
) ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;
ALTER TABLE `student`
	MODIFY COLUMN `Student_ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT FIRST;


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
insert into student(Student_DomitoryID,Student_Username,Student_Password,Student_Name,Student_Sex,Student_Class,Student_State) values(1,'001','123','yang','男','信管1101','入住')

*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.io.File;
import java.io.IOException;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class JdbcDemo1 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		List<String> soundList = JdbcDemo1.listDirectory(new File("/Users/air/Pictures/lol/sound_champions/"),"mp3");
		int i= 0;
		for(String sound : soundList){
			String heroName = sound.substring(0,sound.length()-4);
			System.out.println(heroName);
			boolean exists = JdbcDemo1.fileExists("/Users/air/Pictures/lol/image_champions",heroName);
			if (!exists) {
				soundList.remove(sound);
			}
			i++;
		}
		System.out.println(i);
			//要连接的数据库URL
        String url = "jdbc:h2:/Users/air/Documents/GitHub/DX3907/dx3907.mv.db";
        System.out.println("The database you will connect is :"+url);
        //连接的数据库时使用的用户名
        String username = "sa";
        //连接的数据库时使用的密码
        String password = "";

        //1.加载驱动
        DriverManager.registerDriver(new org.h2.Driver());//不推荐使用这种方式来加载驱动
        // DriverManager.registerDriver(Class.forName("org.h2.Driver"));//推荐使用这种方式来加载驱动
        //2.获取与数据库的链接
        Connection conn = DriverManager.getConnection(url, username, "");

        //3.获取用于向数据库发送sql语句的statement
        Statement st = conn.createStatement();
				//参考：http://blog.csdn.net/zengchaoyue/article/details/8279744
				//参考：PostgreSQL中表名、字段名大小写问题
				String  sql = "select ID,NICKNAME,NAME_CN,NAME_EN,TYPE,STORY,HEADPIC_URL,SOUNDS_URL from HEROS";
        // String sql = "select \"Student_ID\",\"Student_Name\",\"Student_Password\",\"Student_Class\",\"Student_Sex\" from student";
        System.out.println("The sql is:"+sql);
        //4.向数据库发sql,并获取代表结果集的resultset
        ResultSet rs = st.executeQuery(sql);

        //5.取出结果集的数据
        System.out.println("The result is:");
        while(rs.next()){
            System.out.println("Student_ID=" + rs.getObject("NICKNAME"));
            System.out.println("Student_Name=" + rs.getObject("NAME_CN"));
            System.out.println("Student_Password=" + rs.getObject("NAME_EN"));
            System.out.println("Student_Class=" + rs.getObject("HEADPIC_URL"));
            System.out.println("Student_Sex=" + rs.getObject("SOUNDS_URL"));
        }
				st.close();
        //6.使用excuteUpdate(String sql)方法完成数据的添加操作
				// String  sql2 = "insert into HEROS(ID,NICKNAME,NAME_CN,NAME_EN,TYPE,STORY,HEADPIC_URL,SOUNDS_URL) "+
				// "values(1,'探险家','伊泽瑞尔','Ezreal','Marksman','勇敢的年轻探险家伊泽瑞尔已经探索过符文之地上的一些最为偏远和荒废的地区。','Zac_Square_0.png','Zac.mp3')";
				String  sql2 = "insert into HEROS(ID,NICKNAME,NAME_CN,NAME_EN,TYPE,STORY,HEADPIC_URL,SOUNDS_URL) "+
				"values(?,?,?,?,'Marksman','勇敢的年轻探险家伊泽瑞尔已经探索过符文之地上的一些最为偏远和荒废的地区。',?,?)";
				PreparedStatement	st2 = conn.prepareStatement(sql2);
				int j= 1;
				for(String sound : soundList){
					String heroName = sound.substring(0,sound.length()-4);
					System.out.println(heroName);
					j++;
					st2.setInt(1,j);
					st2.setString(2,heroName);
					st2.setString(3,heroName);
					st2.setString(4,heroName);
					st2.setString(5,heroName+"_Square_0.png");
					st2.setString(6,sound);
					System.out.println(">Insert into:"+st2.toString());
					int num = st2.executeUpdate();
					if(num>0){
	        	System.out.println("Insert Success!!");
	        }
				}
			  // String sql2="insert into student(\"Student_DomitoryID\",\"Student_Username\",\"Student_Password\",\"Student_Name\",\"Student_Sex\",\"Student_Class\",\"Student_State\")"
        // 		+ "values(1,'001','123','yang','男','信管1101','入住')";
        // System.out.println(">Insert into a new list:"+sql2);
        // int num = st.executeUpdate(sql2);
        // if(num>0){
        // 	System.out.println("Insert Success!!");
        // }
        //6.关闭链接，释放资源
        System.out.println("Now close the connection!");
        rs.close();
        st2.close();
        conn.close();

	}
	public static List<String> listDirectory(File dir,String filter) {
	        if(!dir.exists()){
	            throw new IllegalArgumentException("Directory:"+dir+" doesn`t exists!");
	        }
	        if(!dir.isDirectory()){
	            throw new IllegalArgumentException(dir+" is now a directory!");
	        }
	        List<String> fileList = new ArrayList<>();
	        // System.out.println(dir.list());
	        // String[] filenames = dir.list();
	        // for(String string:filenames){
	        // 	System.out.println(dir+"/"+string);
	        // }
	        //返回直接子目录的File对象
	        File[] files = dir.listFiles();
	        if(files!=null && files.length>0){
	            for(File file:files){
	                String filename = file.getName();
	                if(file.isDirectory()){
	                    //递归操作
	//                    System.out.println(filename+"-");
	                    listDirectory(file,filter);
	                }else if(JdbcDemo1.filterFile(filename,filter)){
	//                    System.out.println(filename);
	                    fileList.add(filename);
	                }
	            }
	        }
	        return  fileList;
	    }
	    private static boolean filterFile(String fileName,String filter){
	        if(fileName.toLowerCase().contains(filter.toLowerCase())){
	            return  true;
	        }
	        return false;
	    }
			private static boolean fileExists(String directory,String fileName){
				File file = new File(directory+"/"+fileName+"_Square_0.png");
				File file2 = new File(directory+"/"+fileName+"_square_0.png");
				System.out.println(file.exists()+"--"+file2.exists());
				if(file.exists() || file2.exists()){
					return true;
				}else{
					return false;
				}
			}
			// private static String findFile(String directory,String fileName){
			// 	// FilenameFilter filter =
			// 	FileFilter filter =
			// }
}
// private static
 class ImageFilter implements FileFilter {
		private final String fileFilter;
        private ImageFilter(String fileFilter) {
					this.fileFilter = fileFilter;
        }

        public boolean accept(File file) {
						if(file.getName().toLowerCase().contains("square") &&
						file.getName().toLowerCase().contains(fileFilter) ){
							return true;
						}
						return false;
        }
    }

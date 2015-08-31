//package yang.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import yang.jdbc.JdbcUtils;
import org.junit.Test;

/**
* @ClassName: JdbcOperaBlob
* @Description: 使用JDBC操作MySQL的二进制数据(例如图像、声音、二进制文)
* @author: yangzhen
* @date: 2014-9-19 下午10:10:04
*
*/ 
public class JdbcOperaBlob {

    /**
    * @Method: add
    * @Description:向数据库中插入二进制数据
    * @Anthor:yangzhen
    *
    */ 
    @Test
    public void add(){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.getConnection();
            String sql = "insert into log(Log_image) values(?)";
            st = conn.prepareStatement(sql);
            //这种方式获取的路径，其中的空格会被使用“%20”代替
            String path = JdbcOperaBlob.class.getClassLoader().getResource("QQ.png").getPath();
            //将“%20”替换会空格
            path = path.replaceAll("%20", " ");
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);//生成的流
            st.setBinaryStream(1, fis,(int) file.length());
            int num = st.executeUpdate();
            if(num>0){
                System.out.println("插入成功！！");
            }
            fis.close();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.release(conn, st, rs);
        }
    }
    
    /**
    * @Method: read
    * @Description: 读取数据库中的二进制数据
    * @Anthor:孤傲苍狼
    *
    */ 
    @Test
    public void read() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select Log_image from log where Log_ID=?";
            st = conn.prepareStatement(sql);
            st.setInt(1, 4);
            rs = st.executeQuery();
            if (rs.next()) {
                //InputStream in = rs.getBlob("image").getBinaryStream();//这种方法也可以
                InputStream in = rs.getBinaryStream("Log_image");
                int len = 0;
                byte buffer[] = new byte[1024];
                
                FileOutputStream out = new FileOutputStream("D:\\1.jpg");
                while ((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
                in.close();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
    public static void main(String[] args){
    	JdbcOperaBlob job = new JdbcOperaBlob();
    	job.add();
    	job.read();
    	
    }
}

package yang.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import yang.jdbc.JdbcUtils;

//import org.junit.Test;

/**
* @ClassName: JdbcCRUDByStatement
* @Description: 通过Statement对象完成对数据库的CRUD操作
* @author: yangzhen
* @date: 2014-9-15 下午11:22:12
*
*/ 
public class JdbcCRUDByStatement {

    @Test
    public void insert(){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            //获取一个数据库连接
            conn = JdbcUtils.getConnection();
            //通过conn对象获取负责执行SQL命令的Statement对象
            st = conn.createStatement();
            //要执行的SQL命令
            String sql="insert into student(Student_DomitoryID,Student_Username,Student_Password,Student_Name,Student_Sex,Student_Class,Student_State)"
            		+ "values(1,'001','123','yang','男','信管1101','入住')";            //执行插入操作，executeUpdate方法返回成功的条数
            int num = st.executeUpdate(sql);
            if(num>0){
                System.out.println("插入成功！！");
            }
            
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            //SQL执行完成之后释放相关资源
            JdbcUtils.release(conn, st, rs);
        }
    }
    
    @Test
    public void delete(){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.getConnection();
            String sql = "delete from student where Student_ID=3";
            st = conn.createStatement();
            int num = st.executeUpdate(sql);
            if(num>0){
                System.out.println("删除成功！！");
            }
        }catch (Exception e) {
            e.printStackTrace();
            
        }finally{
            JdbcUtils.release(conn, st, rs);
        }
    }
    
    @Test
    public void update(){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.getConnection();
            String sql = "update users set name='孤傲苍狼',email='gacl@sina.com' where id=3";
            st = conn.createStatement();
            int num = st.executeUpdate(sql);
            if(num>0){
                System.out.println("更新成功！！");
            }
        }catch (Exception e) {
            e.printStackTrace();
            
        }finally{
            JdbcUtils.release(conn, st, rs);
        }
    }
    
    @Test
    public void find(){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.getConnection();
            String sql = "select * from student";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if(rs.next()){
                System.out.println(rs.getString("Student_Name"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.release(conn, st, rs);
        }
    }
}
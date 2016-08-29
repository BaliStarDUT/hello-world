package yang.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import yang.jdbc.JdbcUtils;

import org.junit.Test;

/**
* @ClassName: JdbcCRUDByPreparedStatement
* @Description: 通过PreparedStatement对象完成对数据库的CRUD操作
* @author: yangzhen
* @date: 2014-9-15 下午11:21:42
*
*/ 
public class JdbcCRUDByPreparedStatement {

    @Test
    public void insert(){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            //获取一个数据库连接
            conn = JdbcUtils.getConnection();
            //要执行的SQL命令，SQL中的参数使用?作为占位符
            String sql = "insert into users(id,name,password,email,birthday) values(?,?,?,?,?)";
            //通过conn对象获取负责执行SQL命令的prepareStatement对象
            st = conn.prepareStatement(sql);
            //为SQL语句中的参数赋值，注意，索引是从1开始的
            /**
             * SQL语句中各个字段的类型如下：
             *  +----------+-------------+
                | Field    | Type        |
                +----------+-------------+
                | id       | int(11)     |
                | name     | varchar(40) |
                | password | varchar(40) |
                | email    | varchar(60) |
                | birthday | date        |
                +----------+-------------+
             */
            st.setInt(1, 1);//id是int类型的
            st.setString(2, "白虎神皇");//name是varchar(字符串类型)
            st.setString(3, "123");//password是varchar(字符串类型)
            st.setString(4, "bhsh@sina.com");//email是varchar(字符串类型)
            st.setDate(5, new java.sql.Date(new Date().getTime()));//birthday是date类型
            //执行插入操作，executeUpdate方法返回成功的条数
            int num = st.executeUpdate();
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
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.getConnection();
            String sql = "delete from users where id=?";
            st = conn.prepareStatement(sql);
            st.setInt(1, 1);
            int num = st.executeUpdate();
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
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.getConnection();
            String sql = "update users set name=?,email=? where id=?";
            st = conn.prepareStatement(sql);
            st.setString(1, "gacl");
            st.setString(2, "gacl@sina.com");
            st.setInt(3, 2);
            int num = st.executeUpdate();
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
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.getConnection();
            String sql = "select * from users where id=?";
            st = conn.prepareStatement(sql);
            st.setInt(1, 1);
            rs = st.executeQuery();
            if(rs.next()){
                System.out.println(rs.getString("name"));
            }
        }catch (Exception e) {
            
        }finally{
            JdbcUtils.release(conn, st, rs);
        }
    }
}

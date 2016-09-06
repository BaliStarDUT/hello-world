package yang.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

/**
* @ClassName: JdbcBatchHandleByStatement
* @Description: 使用Statement实现JDBC批处理操作
* @author: yangzhen
* @date: 2014-9-20 下午10:05:45
*
*/ 
public class JdbcBatchHandleByStatement {

	public static void main(String[] args) {
		JdbcBatchHandleByStatement jb = new JdbcBatchHandleByStatement();
		//jb.testJdbcBatchHandleByStatement();
		jb.JdbcBatchHandleByPrepareStatement();
		
	}
	public void JdbcBatchHandleByPrepareStatement(){
		long starttime = System.currentTimeMillis();
		Connection con =null;
		PreparedStatement st = null;
		ResultSet rs = null; 
		Calendar ca = Calendar.getInstance();
		java.sql.Date date = new java.sql.Date(ca.getTimeInMillis());
		try{
			con = JdbcUtils.getConnection();
			String sql = "insert into log(Log_Content,Log_Date,Log_Time) values(?,?,?)";
			st = con.prepareStatement(sql);
			for(int i =1;i<10;i++){
				st.setString(1, "I am the root"+i+"!");
				st.setString(2, new Date(starttime).toString());
				st.setDate(3, date);
				st.addBatch();
			}
			st.executeBatch();
			st.clearBatch();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtils.release(con, st, rs);
		}
		long endtime = System.currentTimeMillis();
		System.out.println("use tiem:"+(endtime- starttime)+"ms");
	}
	public void testJdbcBatchHandleByStatement(){
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtils.getConnection();
			String sql1 = "insert into log(Log_Content) values('I am The root1!')";
			String sql2 = "insert into log(Log_Content) values('I am The root2!')";
			String sql3 = "insert into log(Log_Content) values('I am The root3!')";
			String sql4 = "insert into log(Log_Content) values('I am The root4!')";
			String sql5 = "insert into log(Log_Content) values('I am The root5!')";
			System.out.println(sql5);
			st=conn.createStatement();
			st.addBatch(sql1);
			st.addBatch(sql2);
			st.addBatch(sql3);
			st.addBatch(sql4);
			st.addBatch(sql5);
			
			st.executeBatch();
			rs = st.getGeneratedKeys();
			if(rs.next()){
				System.out.println(rs.getInt(1));
			}
			
			st.clearBatch();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
	}

}

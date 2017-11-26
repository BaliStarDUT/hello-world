package dao;

import util.DBPool;
import com.alibaba.druid.pool.DruidPooledConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
/**
* java -cp .:/Users/aliyun/.m2/repository/junit/junit/4.12/junit-4.12.jar:/Users/aliyun/.m2/repository/com/alibaba/druid/1.0.29/druid-1.0.29.jar:/Users/aliyun/.m2/repository/mysql/mysql-connector-java/5.1.39/mysql-connector-java-5.1.39.jar dao/DruidDao
* javac -cp .:/Users/aliyun/.m2/repository/junit/junit/4.12/junit-4.12.jar:/Users/aliyun/.m2/repository/com/alibaba/druid/1.0.29/druid-1.0.29.jar dao/DruidDao.java
*/

public class DruidDao{
  public void query(String sql) throws SQLException{
      DBPool dbPool = new DBPool();
      DruidPooledConnection connection = dbPool.getConnection();
      PreparedStatement ps = connection.prepareStatement(sql);
      ResultSet result = ps.executeQuery();
      System.out.println(result);

      while(result.next()){
          System.out.println(result.getString("Database"));
      }
      ps.close();
      connection.close();
  }
  public static void main(String[] args) throws SQLException{
    DruidDao dao = new DruidDao();
    String sql = "show databases";
    dao.query(sql);
  }
}

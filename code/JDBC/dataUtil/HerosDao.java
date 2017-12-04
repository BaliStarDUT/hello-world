import java.io.File;
import com.alibaba.druid.pool.DruidPooledConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.Iterator;

import util.DBPool;
import dao.DruidDao;

/**
* javac -cp ../druid/:/Users/aliyun/.m2/repository/junit/junit/4.12/junit-4.12.jar:/Users/aliyun/.m2/repository/com/alibaba/druid/1.0.29/druid-1.0.29.jar:/Users/aliyun/.m2/repository/mysql/mysql-connector-java/5.1.39/mysql-connector-java-5.1.39.jar HerosDao.java
* java -cp .:../druid/:/Users/aliyun/.m2/repository/junit/junit/4.12/junit-4.12.jar:/Users/aliyun/.m2/repository/com/alibaba/druid/1.0.29/druid-1.0.29.jar:/Users/aliyun/.m2/repository/mysql/mysql-connector-java/5.1.39/mysql-connector-java-5.1.39.jar HerosDao
* javac -cp .:../../java.io/:../druid/:/Users/aliyun/.m2/repository/junit/junit/4.12/junit-4.12.jar:/Users/aliyun/.m2/repository/com/alibaba/druid/1.0.29/druid-1.0.29.jar:/Users/aliyun/.m2/repository/mysql/mysql-connector-java/5.1.39/mysql-connector-java-5.1.39.jar HerosDao.java
* java -cp .:../../java.io/:../druid/:/Users/aliyun/.m2/repository/junit/junit/4.12/junit-4.12.jar:/Users/aliyun/.m2/repository/com/alibaba/druid/1.0.29/druid-1.0.29.jar:/Users/aliyun/.m2/repository/mysql/mysql-connector-java/5.1.39/mysql-connector-java-5.1.39.jar HerosDao
*/

public class HerosDao{
  private static int i=1;
  public static void main(String[] args) throws Exception{
    HerosDao heroDao = new HerosDao();
    DBPool dbPool = new DBPool();
    DruidPooledConnection connection = dbPool.getConnection();
    heroDao.saveHeros(connection);
  }
  private void saveHeros(DruidPooledConnection connection) throws Exception{
    String sql = "insert into lolheros(id,name_cn,name_en,nickname,story,type,headpic_url,sounds_url) values(?,?,?,?,?,?,?,?)";
    PreparedStatement ps = connection.prepareStatement(sql);
    Thread.sleep(10000);
    List<String> herosList = FileTest.listDirectory(new File("/Users/aliyun/Downloads/sound_champions"));
    Iterator<String> it = herosList.iterator();
    while(it.hasNext()){
      String filename = it.next();
      int length = filename.length();
      String heroname = filename.substring(0,length-4);
      ps.setInt(1,i);
      ps.setString(2,heroname);
      ps.setString(3,heroname);
      ps.setString(4,heroname);
      ps.setString(5,heroname);
      ps.setString(6,"mages");
      ps.setString(7,filename);
      ps.setString(8,filename);
      int num = ps.executeUpdate();
      if(num>0){
        i++;
        System.out.println("插入成功"+filename);
      }
    }
    ps.close();
    connection.close();
  }

}

package util;

import static org.junit.Assert.*;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
/**
* javac -cp .:/Users/aliyun/.m2/repository/junit/junit/4.12/junit-4.12.jar:/Users/aliyun/.m2/repository/com/alibaba/druid/1.0.29/druid-1.0.29.jar util/DBPool.java
* java -cp .:/Users/aliyun/.m2/repository/junit/junit/4.12/junit-4.12.jar:/Users/aliyun/.m2/repository/com/alibaba/druid/1.0.29/druid-1.0.29.jar util/DBPool
*/
public class DBPool{
    private static DBPool dbPool = null;
    private static DruidDataSource druidDataSource = null;
    static{
      try{
        Properties properties = loadProperties("db.properties");
        System.out.println(properties);
        druidDataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(properties);
      }catch(Exception e){
        e.printStackTrace();
      }
    }
    public DruidPooledConnection getConnection() throws SQLException{
        return druidDataSource.getConnection();
    }
    private static Properties loadProperties(String fileName) throws IOException,FileNotFoundException{
      if(null==fileName|| fileName.equals("")){
        throw new IllegalArgumentException("Properties file path can not be null:" + fileName);
      }
      // ClassLoader loader = DBPool.class.getClassLoader();
      // System.out.println(loader);
      // assertTrue(null!=loader);
      // URL fileURL = loader.getResource(fileName);
      // System.out.println(fileURL);
      // assertTrue(null!=fileURL);
      // File propertiesFile = new File(fileURL.getPath());
      Properties properties = new Properties();
      properties.load(new FileInputStream(new File(DBPool.class.getClassLoader().getResource(fileName).getPath())));
      return properties;
    }
    public static void main(String[] args) {
      // System.out.println(properties);
    }
}

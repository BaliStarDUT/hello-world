package reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import hibernate.ShadowSocksLog;
import hibernate.HibernateSessionTest;
import reader.DataTimeFormatTest;

// javac -cp .:../JDBC/:/Users/aliyun/.m2/repository/org/hibernate/hibernate-core/5.0.9.Final/hibernate-core-5.0.9.Final.jar:/Users/aliyun/.m2/repository/org/jboss/logging/jboss-logging/3.3.1.Final/jboss-logging-3.3.1.Final.jar:/Users/aliyun/.m2/repository/javax/transaction/javax.transaction-api/1.2/javax.transaction-api-1.2.jar:/Users/aliyun/.m2/repository/dom4j/dom4j/1.6.1/dom4j-1.6.1.jar:/Users/aliyun/.m2/repository/org/hibernate/common/hibernate-commons-annotations/5.0.1.Final/hibernate-commons-annotations-5.0.1.Final.jar:/Users/aliyun/.m2/repository/org/hibernate/javax/persistence/hibernate-jpa-2.1-api/1.0.0.Final/hibernate-jpa-2.1-api-1.0.0.Final.jar:/Users/aliyun/.m2/repository/mysql/mysql-connector-java/5.1.39/mysql-connector-java-5.1.39.jar:/Users/aliyun/.m2/repository/javassist/javassist/3.12.1.GA/javassist-3.12.1.GA.jar reader/ReadShadowLog.java
// 2017-06-28 04:35:08 INFO     connecting qbwup.imtt.qq.com:80 from ::ffff:111.198.29.132:46090
// 2017-06-28 04:35:27 INFO     connecting notify3.note.youdao.com:80 from ::ffff:111.198.29.132:46103

public class ReadShadowLog{
    public static void main(String[] args) throws Exception{
        // String line = "2017-06-28 04:35:27 INFO     connecting notify3.note.youdao.com:80 from ::ffff:111.198.29.132:46103";
        // ReadShadowLog.parseLine(line);
        String file = "/Users/aliyun/Documents/GitHub/hello-world/code/java.io/reader/shadowsocks.log";
        Path filePath = Paths.get(file);
        ReadShadowLog.readLogFile(filePath);
    }
    public static void readLogFile(Path filePath) throws Exception{
        HibernateSessionTest session = new HibernateSessionTest();
        if(Files.exists(filePath) && Files.isRegularFile(filePath)){
            BufferedReader bufferedReader = new BufferedReader(Files.newBufferedReader(filePath,Charset.forName("UTF-8")));
            String line =null;
            int totalLines =0;
            while((line = bufferedReader.readLine())!=null){
              System.out.println(line);
              ShadowSocksLog log = ReadShadowLog.parseLine(line);
              session.saveShadowLog(log);
              totalLines++;
            }
            System.out.format("TotalLines:%d%n", totalLines);
        }
    }

    private static ShadowSocksLog parseLine(String line) throws Exception{
        String pattern = "{0} {1} {2}     connecting {3} from {4}";
        MessageFormat format = new MessageFormat(pattern);
        ShadowSocksLog log = new ShadowSocksLog();
        try{
            Object[] result = format.parse(line);
            String datetime = (String)result[0] + (String)result[1];
            Date date = DataTimeFormatTest.parseDateTime(datetime);
            log.setDatetime(date);
            log.setType((String)result[2]);
            log.setRequest((String)result[3]);
            log.setFromAd((String)result[4]);
            for(int i=0;i<result.length;i++){
                    System.out.println(result[i]);
            }
        }catch(Exception e){
            e.printStackTrace();
            log.setDatetime(new Date());
            log.setType("ERROR");
            log.setRequest(line);
            log.setFromAd("");
        }

        return log;

    }
}

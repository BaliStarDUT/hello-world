package reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import hibernate.NginxLog;
import hibernate.HibernateSessionTest;

//javac -cp ../../JDBC:. ReadLogFile.java

public class ReadLogFile{
  private static Map<String,Integer> remote_addr = new HashMap<String,Integer>();

  public static void main(String[] args)throws Exception,ParseException{
    String file = "/Users/aliyun/code/web/access.log";
    Path filePath = Paths.get(file);
    ReadLogFile.readLogFile(filePath);
    // ReadLogFile.parseLine();
  }
  public static void readLogFile(Path filePath) throws Exception, ParseException{
      HibernateSessionTest session = new HibernateSessionTest();
      if(Files.exists(filePath) && Files.isRegularFile(filePath)){
        BufferedReader bufferedReader = new BufferedReader(Files.newBufferedReader(filePath,Charset.forName("UTF-8")));
        String line =null;
        int totalLines =0;
        System.out.println(line + totalLines);
        while((line = bufferedReader.readLine())!=null){
          System.out.println(line);
          NginxLog log = ReadLogFile.parseLine(line);
          session.saveNginxLog(log);
          totalLines++;
        }
        System.out.format("TotalLines:%d%n", totalLines);
        // System.out.println(remote_addr);
        List<Map.Entry<String,Integer>> list= new ArrayList<Map.Entry<String,Integer>>(remote_addr.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){
          public int compare(Entry<String,Integer> item1,Entry<String,Integer> item2){
            return item2.getValue().compareTo(item1.getValue());
          }
        });
        for(Map.Entry<String,Integer> mapping:list){
             System.out.println(mapping.getKey()+":"+mapping.getValue());
        }
      }else{
        System.out.format("%d is not a file.%n", filePath);
      }
  }
  private static NginxLog parseLine(String line) throws Exception{
    // String line = "45.78.0.13 - - [06/May/2017:07:02:05 -0400] '050100' 400 173 '-' '-' '-'";
    // String line = "45.78.0.13 - - [07/May/2017:19:02:43 -0400] "GET / HTTP/1.0" 400 271 "-" "-" "-"";
    String pattern = "{0} {1} {2} [{3}] \"{4}\" {5} {6} \"{7}\" \"{8}\" \"{9}\"";
    //实例化MessageFormat对象，并装载相应的模式字符串
    MessageFormat format = new MessageFormat(pattern);

    //格式化模式字符串，参数数组中指定占位符相应的替换对象
    Object[] result = format.parse(line);
    System.out.println(result.length);
    System.out.println("remote_addr:"+result[0]);
    if(remote_addr.containsKey(result[0])){
      Integer num = remote_addr.get(result[0]);
      remote_addr.put((String)result[0],num+1);
    }else{
      remote_addr.put((String)result[0],1);
    }
    System.out.println("remote_user:"+result[1]);
    System.out.println("-:"+result[2]);
    System.out.println("time_local:"+result[3]);
    System.out.println("request:"+result[4]);
    System.out.println("status:"+result[5]);
    System.out.println("request_length:"+result[6]);
    System.out.println("http_referer:"+result[7]);
    System.out.println("http_user_agent:"+result[8]);
    System.out.println("request_time:"+result[9]);
    NginxLog log = new NginxLog();
    log.setRemote_addr((String)result[0]);
    log.setRemote_user((String)result[1]);
    Date date = DataTimeFormatTest.parseData((String)result[3]);
    log.setTime_local((date));
    String requst = (String)result[4];
    if(requst.length()>300){
        log.setRequest(requst.substring(0,300));
    }else{
        log.setRequest(requst);
    }
    log.setStatus(Integer.parseInt((String)result[5]));
    log.setRequest_length(Integer.parseInt((String)result[6]));
    log.setHttp_referer((String)result[7]);
    String agent = (String)result[8];
    if(agent.length()>300){
        log.setHttp_user_agent(agent.substring(0,300));
    }else{
        log.setHttp_user_agent(agent);
    }
    log.setRequest_time((String)result[9]);
    return log;
    // for(int i=0;i<result.length;i++){
    //     switch(i){
    //       case 0:
    //         // System.out.println("remote_addr:"+result[i]);
    //     }
    // }
  }
}

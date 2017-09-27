import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.text.ParseException;

public class ReadLogFile{
  public static void main(String[] args)throws IOException,ParseException{
    String file = "/Users/air/code/access.log";
    Path filePath = Paths.get(file);
    ReadLogFile.readLogFile(filePath);
    // ReadLogFile.parseLine();
  }
  private static void readLogFile(Path filePath) throws IOException, ParseException{
      if(Files.exists(filePath) && Files.isRegularFile(filePath)){
        BufferedReader bufferedReader = new BufferedReader(Files.newBufferedReader(filePath,Charset.forName("UTF-8")));
        String line =null;
        int totalLines =0;
        System.out.println(line + totalLines);
        while((line = bufferedReader.readLine())!=null){
          System.out.println(line);
          ReadLogFile.parseLine(line);
          totalLines++;
        }
        System.out.format("TotalLines:%d%n", totalLines);
      }else{
        System.out.format("%d is not a file.%n", filePath);
      }
  }
  private static void parseLine(String line) throws ParseException{
    // String line = "45.78.0.13 - - [06/May/2017:07:02:05 -0400] '050100' 400 173 '-' '-' '-'";
    // String line = "45.78.0.13 - - [07/May/2017:19:02:43 -0400] "GET / HTTP/1.0" 400 271 "-" "-" "-"";
    String pattern = "{0} {1} {2} [{3}] \"{4}\" {5} {6} \"{7}\" \"{8}\" \"{9}\"";
    //实例化MessageFormat对象，并装载相应的模式字符串
    MessageFormat format = new MessageFormat(pattern);

    //格式化模式字符串，参数数组中指定占位符相应的替换对象
    Object[] result = format.parse(line);
    for(Object ob : result){
          System.out.println("环境输出:"+ob);
    }
  }
}

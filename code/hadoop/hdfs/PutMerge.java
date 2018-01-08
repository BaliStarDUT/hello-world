import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
//javac -cp .:../hadoop-1.2.1/hadoop-core-1.2.1.jar:../hadoop-1.2.1/conf:../hadoop-1.2.1/lib/* PutMerge.java
//./bin/hadoop jar hadoop-examples-1.2.1.jar wordcount file:///media/james/linux/yangz/software/hadoop/input/ file:///media/james/linux/yangz/software/hadoop/output1/
public class PutMerge{
  public static void main(String[] args) throws IOException {
    Configuration conf = new Configuration();
    FileSystem hdfs = FileSystem.get(conf);
    FileSystem local = FileSystem.getLocal(conf);
    Path inputDir = new Path(args[0]);
    Path hdfsFile = new Path(args[1]);
    try{
      FileStatus[] inputFiles = local.listStatus(inputDir);
      for(int i=0;i<inputFiles.length;i++){
        System.out.println(inputFiles[i].getPath().getName());
      }
    }catch(IOException e){
      e.printStackTrace();
    }
  }
}

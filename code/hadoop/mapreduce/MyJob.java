import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.KeyValueTextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.util.ToolRunner;
import  org.apache.hadoop.util.Tool;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapreduce.Job;
// import java.nio.file.Path;


import java.util.Iterator;

 // javac -cp .:/Users/air/Downloads/software/hadoop/hadoop-1.2.1/hadoop-core-1.2.1.jar:/Users/air/Downloads/software/hadoop/hadoop-1.2.1/conf:/Users/air/Downloads/software/hadoop/hadoop-1.2.1/lib/** MyJob.java
public class MyJob extends Configured implements Tool{
  public static class MapClass extends MapReduceBase implements Mapper<Text,Text,Text,Text>{
    public void map(Text key,Text value,OutputCollector<Text,Text> output,Reporter reporter) throws IOException{
      output.collect(value,key);
    }
  }
  public static class ReduceClass extends MapReduceBase implements Reducer<Text,Text,Text,Text>{
    public void reduce(Text key,Iterator<Text> values,OutputCollector<Text,Text> output,Reporter reporter) throws IOException{
      String csv = "";
      while (values.hasNext()){
        if (csv.length()>0)
          csv +=",";
        csv +=values.next().toString();
      }
      output.collect(key,new Text(csv));
    }
  }
  public int run(String[] args) throws Exception{
    Configuration conf = new Configuration();
    JobConf job = new JobConf(conf,MyJob.class);
    // job.

    Path in = new Path(args[0]);
    Path out = new Path(args[1]);
    System.out.println(in.toString());
    System.out.println(out.toString());
    job.setJobName("myjob");
    job.setMapperClass(MapClass.class);
    job.setReducerClass(ReduceClass.class);
    job.setInputFormat(KeyValueTextInputFormat.class);
    job.setOutputFormat(TextOutputFormat.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    job.set("key.value.seperator.in.input.line",",");
    // Job job1 = new Job(job);
    FileInputFormat.setInputPaths(job,in);
    FileOutputFormat.setOutputPath(job,out);
    JobClient.runJob(job);
    return 0;
  }
  public static void main(String[] args) throws Exception {
     if(args.length!=2){
       System.out.println("args: 2");
       return;
     }else {
       System.out.println("args: "+args[0]+","+args[1]);
     }
      int res = ToolRunner.run(new Configuration(), new MyJob(),args);
      System.exit(res);
  }
}

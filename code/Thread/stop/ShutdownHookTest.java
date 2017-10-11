import java.io.FileWriter;
import java.util.Date;
import java.util.stream.IntStream;
import java.io.IOException;

class ShutdownHookTest{
  public static void main(String[] args) throws IOException,InterruptedException{
    Runtime.getRuntime().addShutdownHook(new Thread(()->{
      try(FileWriter fw = new FileWriter("hook.log");){
        fw.write("完成销毁操作，回收内存!"+(new Date()).toString());
      }catch(IOException e){
        e.printStackTrace();
      }
      System.out.println("通过shutdownHook优雅退出程序!");
    }));
    IntStream.range(0,10).forEach(i ->{
      try{
        System.out.println("正在工作--->"+i);
        Thread.sleep(1_000L);
      }catch(InterruptedException e){
        e.printStackTrace();
      }
    });
  }
}

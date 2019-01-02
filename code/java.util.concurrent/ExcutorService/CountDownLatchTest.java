import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;
import java.util.ArrayList;

public class CountDownLatchTest{
    private static final int POOL_COUNT=10;
    private static final int SERVICE_COUNT=100;
    public static void main(String[] args){
      ExecutorService executorService = Executors.newFixedThreadPool(POOL_COUNT);
      List resultList = new ArrayList();
      System.out.println(System.currentTimeMillis());
      for(int i=0;i<SERVICE_COUNT; i++ ){
          resultList.add(i);
      }
      // 多个服务异步调用
      for(int i=0;i<SERVICE_COUNT;i++){
            Object j = resultList.get(i);
            executorService.execute(new Runnable(){
              @Override
              public void run(){
                try{
                    Thread.sleep(1000);
                    System.out.println("service add:"+j);
                    resultList.add(100+(int)j);
                }catch(Exception e){
                    System.out.println(e);
                }finally{
                    System.out.println("finally");
                }
              }
            });
            System.out.println("service run:"+j);
      }
      // for(Object item : resultList){
      //     System.out.println(item);
      // }
      // 如果有任务没有跑完就访问会出问题
      System.out.println(resultList.get(SERVICE_COUNT-1+50));
      // executorService.shutdown();
    }
}

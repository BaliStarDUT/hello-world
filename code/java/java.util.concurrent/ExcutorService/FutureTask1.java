import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Callable;
public class FutureTask1{
    private static final int POOL_COUNT=10;
    private static final int SERVICE_COUNT=100;

    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(POOL_COUNT);
        List resultList = new ArrayList();
        for(int i=0;i<SERVICE_COUNT; i++ ){
            resultList.add(i);
        }
        List<FutureTask> taskList = new ArrayList<FutureTask>();
        // 多个服务异步调用
        for(int i=0;i<SERVICE_COUNT;i++){
            Object j = resultList.get(i);
            FutureTask<Integer> task = new FutureTask<Integer>(new Callable(){
                @Override
                public Object call(){
                  try{
                      Thread.sleep(1000);
                      System.out.println("service add:"+j);
                      return j;
                      // resultList.add(100+(int)j);
                  }catch(Exception e){
                      System.out.println(e);
                  }finally{
                      System.out.println("finally coutDown");
                      return j;
                  }
                }
            });
            executorService.execute(task);
            taskList.add(task);
        }
        for(FutureTask task : taskList){
            try{
                if(task.get()!=null){
                    System.out.println(task.get());
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }catch(ExecutionException e){
                e.printStackTrace();
            }
        }
    }
}

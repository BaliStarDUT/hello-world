import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;

class CallableTask{
  public static void main(String[] args) throws Exception{
    Callable<Integer> task = () -> {
      try {
          TimeUnit.SECONDS.sleep(10);
          return 123;
      }catch (InterruptedException e) {
          throw new IllegalStateException("task interrupted", e);
      }
    };
    ExecutorService executor = Executors.newFixedThreadPool(1);
    Future<Integer> future =  executor.submit(task);
    System.out.println("future done? " + future.isDone());

    Integer result = future.get(3, TimeUnit.SECONDS);

    System.out.println("future done? " + future.isDone());
    System.out.print("result: " + result);
  }
}

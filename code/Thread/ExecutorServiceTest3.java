import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.List;
import java.util.Arrays;

class ExecutorServiceTest3{
   static Callable<String> callable(String result, long sleepSeconds) {
    return () -> {
        TimeUnit.SECONDS.sleep(sleepSeconds);
        return result;
    };
  }
  public static void main(String[] args) throws Exception{
    ExecutorService executor = Executors.newWorkStealingPool();
    List<Callable<String>> callables = Arrays.asList(
      callable("task1", 2),
      callable("task2", 1),
      callable("task3", 3));
    String result = executor.invokeAny(callables);
    System.out.println(result);
  }
}

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.List;
import java.util.Arrays;

class ExecutorServiceTest2{
  public static void main(String[] args) throws Exception{
    ExecutorService executor = Executors.newWorkStealingPool();
    List<Callable<String>> callables = Arrays.asList(
            () -> "task1",
            () -> "task2",
            () -> "task3");
    executor.invokeAll(callables)
        .stream()
        .map(future -> {
            try {
                return future.get();
            }
            catch (Exception e) {
                throw new IllegalStateException(e);
            }
        })
        .forEach(System.out::println);
  }
}

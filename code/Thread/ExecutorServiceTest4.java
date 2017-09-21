import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.Callable;
import java.util.List;
import java.util.Arrays;

class ExecutorServiceTest4{
  public static void main(String[] args) throws Exception{

    Runnable task = () -> {
      try {
          TimeUnit.SECONDS.sleep(2);
          System.out.println("Scheduling: " + System.nanoTime());
      }      catch (InterruptedException e) {
          System.err.println("task interrupted");
      }
    };
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    Runnable task1 = () -> System.out.println("Scheduling: " + System.nanoTime());
    ScheduledFuture<?> future = executor.schedule(task1, 3, TimeUnit.SECONDS);
    TimeUnit.MILLISECONDS.sleep(1337);

    long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
    System.out.printf("Remaining Delay: %sms", remainingDelay);

    int initialDelay = 0;
    int period = 1;
    executor.scheduleWithFixedDelay(task, initialDelay, period, TimeUnit.SECONDS);
    // executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);

  }
}


import org.apache.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;

// java -cp .:/:../log/:/Users/air/.m2/repository/log4j/log4j/1.2.17/log4j-1.2.17.jar:. ExcutorServiceTest

class ExcutorServiceTest{
  static Logger logger = Logger.getLogger(ExcutorServiceTest.class);
  public static void main(String[] args) {
    useExecutors();
  }
  private static void test1(){
    ExecutorService executor = Executors.newSingleThreadExecutor();

    executor.submit(() -> {
      String name = Thread.currentThread().getName();
      System.out.println("Hello " + name);
    });
    try {
      System.out.println("attempt to shutdown executor");
      executor.shutdown();
      executor.awaitTermination(5, TimeUnit.SECONDS);
    }catch (InterruptedException e) {
      System.err.println("tasks interrupted");
    }finally {
      if (!executor.isTerminated()) {
          System.err.println("cancel non-finished tasks");
      }
      executor.shutdownNow();
      System.out.println("shutdown finished");
  }
}
  private static void useExecutors(){
    ExecutorService excutor = Executors.newSingleThreadExecutor();
    TaskCase task = new TaskCase();
    SimpleDateFormat simp = new SimpleDateFormat("dd/MMM/yyyy:kk:mm:ss Z");
    String startDate = simp.format(new Date());

    logger.info("start:"+startDate);

    Future<?> future = excutor.submit(task);
    String endDate = simp.format(new Date());
    logger.info("end:"+endDate);
    logger.info("attempt to shutdown executor");
    try{
      excutor.shutdown();
      excutor.awaitTermination(5,TimeUnit.SECONDS);
    }catch (InterruptedException e) {
      System.err.println("tasks interrupted");
    }finally {
      if (!excutor.isTerminated()) {
          System.err.println("cancel non-finished tasks");
      }
      excutor.shutdownNow();
      System.out.println("shutdown finished");
  }
}
}



class TaskCase implements Runnable{
  @Override
  public void run(){

    String name = Thread.currentThread().getName();
    // logger.Info("Hello " + name);
    System.out.println("Hello " + name);
    try{
      Thread.sleep(1000);
    }catch (InterruptedException e){
      e.printStackTrace();
    }
  }
}

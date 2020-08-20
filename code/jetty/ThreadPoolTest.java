import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;

// javac -cp ~/.m2/repository/org/eclipse/jetty/jetty-server/9.2.7.v20150116/jetty-server-9.2.7.v20150116.jar:~/.m2/repository/org/eclipse/jetty/jetty-util/9.2.7.v20150116/jetty-util-9.2.7.v20150116.jar:/Users/air/.m2/repository/org/eclipse/jetty/aggregate/jetty-all/9.1.5.v20140505/jetty-all-9.1.5.v20140505.jar JettyTest.java
// java -cp .:/Users/air/.m2/repository/org/eclipse/jetty/aggregate/jetty-all/9.1.5.v20140505/jetty-all-9.1.5.v20140505.jar:~/.m2/repository/javax/servlet/javax.servlet-api/3.1.0/javax.servlet-api-3.1.0.jar:/Users/air/.m2/repository/javax/servlet/javax.servlet-api/3.1.0/javax.servlet-api-3.1.0.jar JettyTest

public class ThreadPoolTest {
  private static final Logger LOG = Log.getLogger(ThreadPoolTest.class);
  public static void main(String[] args){
     QueuedThreadPool pool  = new QueuedThreadPool();
     pool.setName("threadpool");
     try{
          pool.start();
     }catch (Exception e){
       e.printStackTrace();
     }

     LOG.info(pool.toString());
     LOG.info(""+pool.isRunning());

     pool.execute(() -> {
       String name = Thread.currentThread().getName();
       System.out.println("Hello " + name);
       try{
         Thread.sleep(1000);
       }catch (InterruptedException e){
         e.printStackTrace();
       }
     });
     LOG.info(pool.toString());
     LOG.info(""+pool.isRunning());

     pool.execute(() -> {
       String name = Thread.currentThread().getName();
       System.out.println("Hello " + name);
       try{
         Thread.sleep(1000);
       }catch (InterruptedException e){
         e.printStackTrace();
       }
     });
     LOG.info(pool.toString());
     LOG.info(""+pool.isRunning());
     pool.execute(() -> {
       String name = Thread.currentThread().getName();
       System.out.println("Hello " + name);
       try{
         Thread.sleep(1000);
       }catch (InterruptedException e){
         e.printStackTrace();
       }
     });
     LOG.info(pool.toString());
     LOG.info(""+pool.isRunning());

  }
}

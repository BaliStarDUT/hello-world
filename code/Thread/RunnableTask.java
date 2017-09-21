import java.util.concurrent.TimeUnit;
class RunnableTask{
  public static void main(String[] args) {
      Runnable task = () -> {
        try{
          String name = Thread.currentThread().getName();
           System.out.println("Foo " + name);
           TimeUnit.SECONDS.sleep(1);
           System.out.println("Bar " + name);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
      };
      task.run();
      Thread thread = new Thread(task);
      thread.start();
      System.out.println("Done!");
  }

}

public class ThreadExceptionTest{
  public static void main(String[] args){
    // ThreadExceptionTest.unCatch();
    ThreadExceptionTest.useUncaughtExceptionHandler();

  }
  //使用UncaughtExceptionHandler来捕获线程中的异常
  private static void useUncaughtExceptionHandler(){
    ConnectionPool.create();
    Thread thread = new Thread(()->System.out.println(Integer.parseInt("ABC")),"T1");
    thread.setUncaughtExceptionHandler((t,e)->{
      e.printStackTrace();
      System.out.println("[线程] - [" + t.getName() + "] - [消息] - [" + e.getMessage() + "]");
      ConnectionPool.close();
    });
    thread.start();
  }
  //该方法并没有捕获到子线程的异常
  private static void unCatch(){
    ConnectionPool.create();
    try{
      Thread thread = new Thread(()->{System.out.println(Integer.parseInt("ABC"));}, "T2");
      thread.start();
    }catch(Exception e){
      e.printStackTrace();
      ConnectionPool.close();
    }
  }
}
class ConnectionPool {
    static void create() {
        System.out.println("初始化连接池...");
    }
    static void close() {
        System.out.println("关闭连接池...");
    }
}

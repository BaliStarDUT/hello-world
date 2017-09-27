public class SyncObject{
  // 类锁1：通过static方法加同步锁
  public static synchronized void syncMethod1(){
    try {
            System.out.println("testMethod1 start!");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("testMethod1 end!");
  }
  public void syncMethod2(){
    // 类锁2：通过同步语句块传递Class类型参数
    synchronized(SyncObject.class){
      try {
               System.out.println("testMethod2 start!");
               Thread.sleep(3000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           } finally {
               System.out.println("testMethod2 end!");
           }
    }

  }
}

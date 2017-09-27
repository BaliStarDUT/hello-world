public class SyncObject1{
  // 对象锁1（方法锁）：通过方法加同步锁
  public  synchronized void syncMethod1(){
    try {
            System.out.println("testMethod1 start!");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("testMethod1 end!");
  }
  public void syncMethod2(){
    // 对象锁2：通过同步语句块传递Object类型参数
    synchronized(this){
    // 此处的参数可以是本实例this，也可以是其它实例比如new SyncObject()，传入哪个实例就对哪个实例加锁
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

class ProductAndConsumer2{
  private final static byte[] LOCK = new byte[0];//定义一个锁对象

  private static int i = 0;
  private static boolean isProduct = true;
  static void product() {//生产者
    synchronized(LOCK){
      if(isProduct){//如果标示位为生产状态,则继续生产
        System.out.println("P->" + (++i));
        isProduct = false;
        LOCK.notify();//消费者可以消费了
      }else{
        //说明生产出来的数据还未被消费掉
        try{
          LOCK.wait();
        }catch(InterruptedException e){
          e.printStackTrace();
        }
      }
    }
    System.out.println("Production is Running");
  }
  static void consumer() {//消费者
    synchronized(LOCK){
      if(isProduct){//如果当前还在生产,那么就暂停消费者线程
        try{
          LOCK.wait();
        }catch(InterruptedException e){
          e.printStackTrace();
        }
      }else{
        System.out.println("C->" + i);
        isProduct = true;
        LOCK.notify();//通知我已经消费完毕了
      }
    }
    System.out.println("Consumer is Running");
  }
  public static void main(String[] args) {
      new Thread(() -> {
          while (true) {
              product();
          }
      }).start();
      new Thread(() -> {
          while (true) {
              consumer();
          }
      }).start();
  }
}

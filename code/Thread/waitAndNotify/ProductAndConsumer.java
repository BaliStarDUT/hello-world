class ProductAndConsumer{
  private static int i = 0;
  private static boolean isProduct = true;
  static void product() {//生产者
    if(isProduct){
      System.out.println("P->" + (++i));
      isProduct = false;
    }
    System.out.println("Production is Running");
  }
  static void consumer() {//消费者
    if(!isProduct){
      System.out.println("C->" + i);
      isProduct = true;
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

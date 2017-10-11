public class DeadLockTest2{
  public static void main(String[] args) {

        /**
         * 死锁演示线程初始化
         */
        ResourceManager resourceManager = new ResourceManager();
        ResourceUsedThread customizedThread0 = new ResourceUsedThread(resourceManager,1,2);
        ResourceUsedThread customizedThread1 = new ResourceUsedThread(resourceManager,2,4);

        /**
         * 死锁演示线程启动
         */
        customizedThread0.start();
        customizedThread1.start();
    }
}

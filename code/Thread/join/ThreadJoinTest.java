  public class ThreadJoinTest {
      public static void main(String[] args) throws InterruptedException {
          long startTime = System.currentTimeMillis();
          Thread t1 = new Thread(new CaptureRunnable("M1", 5_000L));
          Thread t2 = new Thread(new CaptureRunnable("M2", 3_000L));
          Thread t3 = new Thread(new CaptureRunnable("M3", 2_000L));
          t1.start();
          t2.start();
          t3.start();
          // t1.join();
          t2.join();
          t3.join();
          System.out.println("采集完成,消耗 " + (System.currentTimeMillis() - startTime));
      }
  }
  class CaptureRunnable implements Runnable {
      private String machineName;//采集任务名
      private Long spendTime;//采集工作消耗时长
      public CaptureRunnable(String machineName, Long spendTime) {
          this.machineName = machineName;
          this.spendTime = spendTime;
      }
      @Override
      public void run() {
          try {
              System.out.println(machineName + "开始采集");
              Thread.sleep(spendTime);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
  }

import java.util.Date;
import org.apache.log4j.Logger;

class Log4jTest{
	public static void main(String[] args) {
		test();
  }
	public void log4jTest(){
		Logger logger = Logger.getLogger(Log4jTest.class);
		logger.info("hello {}");
    logger.debug("hello {}");
    logger.error("hello {}");
    logger.trace("hello {}");
    logger.warn("hello {}");
	}

	public void testThread() throws InterruptedException {
      int THREAD_NUM = 100;
      final int LOOP_NUM = 100000;

      final CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);
      long start = System.currentTimeMillis();
      for(int x= 0;x < THREAD_NUM;x++){
          new Thread(new Runnable() {
              public void run() {
                  for (int y = 0; y < LOOP_NUM; y++) {
                      logger.info("Info Message!");
                  }
                  countDownLatch.countDown();
              }
          }).start();
      }
      countDownLatch.await();
      System.out.println(System.currentTimeMillis() - start);
  }

	public void test() throws InterruptedException {
        int X_NUM = 100;
        int Y_NUM = 100000;

        long start = System.currentTimeMillis();
        for(int x=0;x < X_NUM;x++) {
            for (int y = 0; y < Y_NUM; y++) {
                logger.info("Info Message!");
            }
        }
        System.out.print(System.currentTimeMillis() - start);
    }
}

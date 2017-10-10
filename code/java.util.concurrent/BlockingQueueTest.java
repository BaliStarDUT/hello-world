import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class BlockingQueueTest{
  public static void main(String[] args) throws Exception{
    BlockingQueue<String> blockQueue = new ArrayBlockingQueue<String>(1024);
    Producer producer = new Producer(blockQueue);
    Consumer consumer = new Consumer(blockQueue);

    new Thread(producer).start();
    new Thread(consumer).start();

    Thread.sleep(5000);
  }
}

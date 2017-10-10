import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

    protected BlockingQueue queue = null;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            System.out.println(queue.take()+"a");
            System.out.println(queue.take()+"b");
            System.out.println(queue.take()+"c");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Worker extends Thread {
    private volatile boolean isShutdown = true;
    public void shutdown() {
        System.out.println("接收到关闭通知......");
        this.isShutdown = false;
        interrupt();
    }
    @Override
    public void run() {
        while (this.isShutdown) {
            System.out.println("正在工作:" + System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("打断正在工作的线程......");
            }
        }
        System.out.println("销毁......");
    }
}
public class ThreadCloseTest {
    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        worker.start();//开始工作
        Thread.sleep(3 * 1000);
        worker.shutdown();//优雅关闭
    }
}

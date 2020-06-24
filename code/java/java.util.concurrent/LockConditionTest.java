import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LockConditionTest{
  public static void main(String[] args){
    BankAccountTest myCount = new BankAccountTest("yangzhen",1_0000);
    ExecutorService pool = Executors.newFixedThreadPool(3);
    Thread t1 = new SaveThread("张三", myCount, 2000);
    Thread t2 = new SaveThread("李四", myCount, 3600);
    Thread t3 = new DrawThread("王五", myCount, 2700);
    Thread t4 = new SaveThread("老张", myCount, 600);
    Thread t5 = new DrawThread("老牛", myCount, 1300);
    Thread t6 = new DrawThread("胖子", myCount, 800);
    pool.execute(t1);
     pool.execute(t2);
     pool.execute(t3);
     pool.execute(t4);
     pool.execute(t5);
     pool.execute(t6);
     //关闭线程池
     pool.shutdown();
  }
}
class SaveThread extends Thread {
        private String name;                //操作人
        private BankAccountTest myCount;        //账户
        private int x;                            //存款金额

        SaveThread(String name, BankAccountTest myCount, int x) {
                this.name = name;
                this.myCount = myCount;
                this.x = x;
        }

        public void run() {
                myCount.saving(x, name);
        }
}
class DrawThread extends Thread {
        private String name;                //操作人
        private BankAccountTest myCount;        //账户
        private int x;                            //存款金额

        DrawThread(String name, BankAccountTest myCount, int x) {
                this.name = name;
                this.myCount = myCount;
                this.x = x;
        }

        public void run() {
                myCount.drawing(x, name);
        }
}

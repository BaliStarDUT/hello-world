import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.Random;

public class ReadWriteLockTest{
  public static void main(String[] args){
    final QueueCase queue = new QueueCase();
    for(int i=0;i<3;i++){
      new Thread(()->{
        while(true){
          queue.get();
        }
      },"getThread"+i).start();
    }
    for(int i=0;i<3;i++){
      new Thread(()->{
        while(true){
          queue.put(new Random().nextInt(1000));
        }
      },"putThread"+i).start();
    }
  }
}
class QueueCase{
  //共享数据，只能有一个线程能写该数据，但可以有多个线程同时读该数据。
  private Object data = null;
  private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

  public void get(){
    // lock.readLock().lock();//上读锁，其他线程只能读不能写
    // System.out.println(Thread.currentThread().getName() + " be ready to read data!");
    try{
      Thread.sleep((long)(Math.random()*1000));
    }catch(InterruptedException e){
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread().getName() + " have read data :" + data);
    // lock.readLock().unlock();//释放读锁，最好放在finnaly里面
  }
  public void put(Object data){
    // lock.writeLock().lock();//上写锁，不允许其他线程读也不允许写
    // System.out.println(Thread.currentThread().getName() + " be ready to write data!");
    try{
      Thread.sleep((long)(Math.random()*1000));
    }catch(InterruptedException e){
      e.printStackTrace();
    }
    this.data = data;
    System.out.println(Thread.currentThread().getName() + " have write data: " + data);
    // lock.writeLock().lock();//释放写锁
  }
}

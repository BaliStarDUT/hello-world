import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class BankAccountTest{
  private String oid;
  private int cash;
  private Lock lock = new ReentrantLock();
  private Condition _save = lock.newCondition(); //存款条件
  private Condition _draw = lock.newCondition(); //取款条件

  BankAccountTest(String oid,int cash){
    this.oid = oid;
    this.cash = cash;
  }
  public void saving(int x,String name){
    lock.lock();
    if(x>0){
      cash += x;
      System.out.println(name + "存款" + x + "，当前余额为" + cash);
    }
    _draw.signalAll();
    lock.unlock();
  }

  public void drawing(int x,String name) {
    lock.lock();
    if(cash-x<0){
      try{
        _draw.await();
      }catch(InterruptedException e){
        e.printStackTrace();
      }
    }else{
      cash -= x;
       System.out.println(name + "取款" + x + "，当前余额为" + cash);
    }
    _save.signalAll();
    lock.unlock();
  }
}

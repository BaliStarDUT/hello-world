public class Goods{
  private final int max_size = 100;
  private int curNum;

  public Goods(int curNum){
    this.curNum = curNum;
  }

  public synchronized void produce(int needNum) throws InterruptedException{
    while(true){
      while(this.curNum + needNum > this.max_size){
        System.out.println(Thread.currentThread().getName() + "要生产的产品数量" + needNum + "已经超出剩余库存容量" + (this.max_size - this.curNum) +"，暂时不能进行生产任务！");
        this.wait();
      }
      Thread.sleep(1_000L);
      this.curNum += needNum;
      System.out.println(Thread.currentThread().getName() + "已经生产了" + needNum + "，现存库存量是为：" + this.curNum);
      this.notifyAll();
    }
  }

  public synchronized void consum(int needNum) throws InterruptedException{
    while(true){
      while(this.curNum<needNum){
        System.out.println(Thread.currentThread().getName() + "要消费的产品数量" + needNum + "已经超出剩余库存量" + this.curNum + "，暂时不能进行消费！");
        this.wait();
      }
      Thread.sleep(1_000L);
      this.curNum -= needNum;
      System.out.println(Thread.currentThread().getName() + "已经消费了" + needNum + "，现存库存量是为：" + this.curNum);
      this.notifyAll();
    }
  }
}

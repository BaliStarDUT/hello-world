class Producer extends Thread{
  private int needNum;
  private Goods goods;

  public Producer(int needNum,Goods goods){
    this.needNum = needNum;
    this.goods = goods;
  }

  // @Override
  public void run() {
    try{
      this.goods.produce(this.needNum);
    }catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

class Consumer extends Thread{
  private int needNum;
  private Goods goods;

  public Consumer(int needNum,Goods goods){
    this.needNum = needNum;
    this.goods = goods;
  }
  // @Override
  public void run(){
    try{
      this.goods.consum(this.needNum);
    }catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

public class GoodsProAndCon {
    public static void main(String[] args) {

        /**
         * 仓库初始化
         */
        Goods godown = new Goods(30);

        /**
         * 消费者初始化
         */
        Consumer consumer0 = new Consumer(30,godown);
        Consumer consumer1 = new Consumer(20,godown);
        Consumer consumer2 = new Consumer(40,godown);

        /**
         * 生产者初始化
         */
        Producer producer0 = new Producer(10,godown);
        Producer producer1 = new Producer(10,godown);
        Producer producer2 = new Producer(10,godown);
        Producer producer3 = new Producer(10,godown);
        Producer producer4 = new Producer(10,godown);
        Producer producer5 = new Producer(10,godown);
        Producer producer6 = new Producer(10,godown);
        Producer producer7 = new Producer(10,godown);

        /**
         * 标记每个生产者/消费者
         */
        consumer0.setName("consumer0");
        consumer1.setName("consumer1");
        consumer2.setName("consumer2");
        producer0.setName("producer0");
        producer1.setName("producer1");
        producer2.setName("producer2");
        producer3.setName("producer3");
        producer4.setName("producer4");
        producer5.setName("producer5");
        producer6.setName("producer6");
        producer7.setName("producer7");

        /**
         * 开始进行生产－消费
         */
        consumer0.start();
        consumer1.start();
        consumer2.start();
        producer0.start();
        producer1.start();
        producer2.start();
        producer3.start();
        producer4.start();
        producer5.start();
        producer6.start();
        producer7.start();
    }
}

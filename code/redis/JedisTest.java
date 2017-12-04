import redis.clients.jedis.Jedis;

public class JedisTest{
  public static void main(String[] args) throws Exception{
    Jedis jedis = new Jedis("localhost",32768);
    System.out.println("连接成功:"+jedis.toString());
    //查看服务是否运行
    System.out.println("服务正在运行: "+jedis.ping());
    // RedisTool tool = new RedisTool();
    System.out.println("尝试获取锁: "+"yang");
    boolean getResult = RedisTool.tryGetDistributedLock(jedis,"yang","macair",5000);
    System.out.println("获取锁: "+getResult);
    Thread.sleep(4000);
    System.out.println("尝试释放锁: "+"yang");
    boolean releaseResult = RedisTool.releaseDistributedLock(jedis,"yang","macair");
    System.out.println("释放锁: "+releaseResult);

  }
}

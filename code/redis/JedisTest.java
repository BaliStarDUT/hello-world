import redis.clients.jedis.Jedis;

public class JedisTest{
  public static void main(String[] args){
    Jedis jedis = new Jedis("localhost",32770);
    System.out.println("连接成功:"+jedis.toString());
    //查看服务是否运行
    System.out.println("服务正在运行: "+jedis.ping());
  }
}

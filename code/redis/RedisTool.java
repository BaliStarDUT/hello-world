import java.util.Collections;
import redis.clients.jedis.Jedis;

public class RedisTool{
  private static final String Lock_SUCCESS = "OK";
  private static final String Release_Success ="OK";
  private static final String Set_If_not_Exist = "NX";
  private static final String Set_With_Exprie_Time = "PX";

  public static boolean tryGetDistributedLock(Jedis jedis,String lockKey,String requestId,int expireTime){
    String result = jedis.set(lockKey,requestId,Set_If_not_Exist,Set_With_Exprie_Time,expireTime);
    String getResult = jedis.get(lockKey);
    System.out.println("设置成功: "+getResult);
    if(Lock_SUCCESS.equals(result)){
      return true;
    }
    return false;
  }

  public static boolean releaseDistributedLock(Jedis jedis,String lockKey,String requestId){
    // String script = "if redis.call('get',KEYS[1])==ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
    // Object result = jedis.eval(script,Collections.singletonList(lockKey),Collections.singletonList(requestId));
    System.out.println(jedis.get(lockKey));
    if(requestId.equals(jedis.get(lockKey))){
      Long result = jedis.del(lockKey);
      System.out.println(result);
      if(result>0l){
        return true;
      }
    }
    return false;
  }
}

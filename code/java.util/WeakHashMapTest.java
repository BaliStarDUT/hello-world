/**
* javac -cp .:/Users/aliyun/.m2/repository/junit/junit/4.12/junit-4.12.jar:/Users/aliyun/.m2/repository/org/awaitility/awaitility/3.0.0/awaitility-3.0.0.jar:/Users/aliyun/.m2/repository/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar WeakHashMapTest.java
* java -cp .:/Users/aliyun/.m2/repository/junit/junit/4.12/junit-4.12.jar:/Users/aliyun/.m2/repository/org/awaitility/awaitility/3.0.0/awaitility-3.0.0.jar:/Users/aliyun/.m2/repository/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar WeakHashMapTest
*/
import static org.junit.Assert.*;
import static org.awaitility.Awaitility.*;
import static org.awaitility.Duration.*;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

public class WeakHashMapTest{
  public static void main(String[] args) throws InterruptedException{
    WeakHashMap<String,String> map = new WeakHashMap<>();
    String key = new String("key");
    String value = new String("vale");
    map.put(key,value);
    System.out.println(map.isEmpty());
    assertTrue(map.containsKey(key));

    key = null;
    System.gc();
    // Thread.sleep(5000);
    await().atMost(10,TimeUnit.SECONDS).until(map::isEmpty);
    System.out.println(map.isEmpty());
  }
}

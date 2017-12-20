import java.lang.reflect.Field;
import sun.misc.Unsafe;

/**
* VM args:-Xmx20m -XX:MaxDirectMemorySize=10M
*/
public class DirectMemoryOOM {
  private static final int _1MB = 1024*1024;
  public static void main(String[] args) throws IllegalAccessException{
    Field unsafeField = Unsafe.class.getDeclaredFields()[0];
    unsafeField.setAccessible(true);
    Unsafe unsafe = (Unsafe)unsafeField.get(null);
    while(true){
      System.out.println("allocat:"+_1MB);
      unsafe.allocateMemory(_1MB);
    }
  }
}

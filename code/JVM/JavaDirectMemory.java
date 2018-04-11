import java.lang.reflect.Field;

public class JavaDirectMemory {
  public static void main(String[] args) throws Exception{
    Class<?> c = Class.forName("java.nio.Bits");
    Field maxMemory = c.getDeclaredField("maxMemory");
    maxMemory.setAccessible(true);
    Field reservedMemory = c.getDeclaredField("reservedMemory");
    reservedMemory.setAccessible(true);
    // Long maxMemoryValue = (Long)maxMemory.get(null);
    // Long reservedMemoryValue = (Long)reservedMemory.get(null);
    // System.out.println("maxMemoryValue:"+maxMemoryValue);
    // System.out.println("reservedMemoryValue:"+reservedMemoryValue);
    System.out.println("maxMemoryValue:"+maxMemory.get(null));
    System.out.println("reservedMemoryValue:"+reservedMemory.get(null));

  }
}

import java.util.Map;
import java.util.HashMap;

public class TryCatchDemo {
    public static void main(String []args){
        try{
            String a;
            a = "aaaaaa";
            System.out.println(a);
            Map<String ,Object> map = new HashMap();

            System.out.println(map);

            // Double d1=new Double(1/0);
            // Class classa = Class.forName("com.james.springboot.ClassA");
            // ClassA classA = (ClassA) classa.newInstance();
            // System.out.println(classA.add(2,4));
            // throw new Exception("error");
        }catch (ArithmeticException e){
            System.out.println("1");
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("2");
            e.printStackTrace();
        }
    }
}

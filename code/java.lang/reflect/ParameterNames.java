import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ParameterNames {
    public static void main(String[] args) throws Exception {
        Method method2 = ClassA.class.getMethod("add",int.class,int.class);
        for(final Parameter parameter : method2.getParameters()){
            System.out.println( "Parameter: " + parameter.getName() );
        }
   }
}

class ClassA{
    public  int add(int a,int b){
        return a+b;
    }
    public  int add(int a,int b,int c){
        return a+b+c;
    }
}

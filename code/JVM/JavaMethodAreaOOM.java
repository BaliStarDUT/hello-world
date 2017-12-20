import org.assertj.core.internal.cglib.proxy.Enhancer;
import org.assertj.core.internal.cglib.proxy.MethodInterceptor;
import org.assertj.core.internal.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

public class JavaMethodAreaOOM{
  public static void main(String[] args) {
    while(true){
      Enhancer enhancer = new Enhancer();
      enhancer.setSuperclass(OOMObject.class);
      enhancer.setUseCache(false);
      enhancer.setCallback(new MethodInterceptor(){
        public Object intercept(Object obj,Method method,Object[] args,MethodProxy proxy) throws Throwable{
          System.out.println("call back...");
          return proxy.invokeSuper(obj,args);
        }
      });
      enhancer.create();
    }
  }
  static class OOMObject{

  }
}

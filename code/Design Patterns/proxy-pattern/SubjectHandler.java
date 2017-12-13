import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SubjectHandler implements InvocationHandler{
    private Subject subject;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)throws Throwable{
        System.out.println("向代理服务器发起请求");
        //如果第一次调用，生成真实主题
        if (subject == null) {
            subject = new RealSubject();
        }
        subject.request();
        //返回真实主题完成实际的操作
        System.out.println("代理服务器响应请求");
		//如果返回值可以直接 return subject.request();
        return null;
   }
   public static Subject createProxy() {
        return (Subject) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(), new Class[]{Subject.class}, new SubjectHandler()
        );
    }
    public static void main(String[] args) {
        Subject proxy = SubjectHandler.createProxy();
        proxy.request();
    }
}

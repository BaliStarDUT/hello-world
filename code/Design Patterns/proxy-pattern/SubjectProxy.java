import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class SubjectProxy implements MethodInterceptor {
    private  Object target;

    /**
     * 创建代理对象
     *
     * @param target 目标对象
     * @return
     */
    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("向代理服务器发起请求");
        methodProxy.invokeSuper(o, objects);
        System.out.println("代理服务器响应请求");
        return null;
    }
}

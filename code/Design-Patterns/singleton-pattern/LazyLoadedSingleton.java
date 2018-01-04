/**
 * 单例模式示例：
 * - 延迟加载单例模式
 */
public class LazyLoadedSingleton{
    //other fields...

    //LazyLoadedSingleton类只有一个构造方法，被private修饰的， 客户对象无法创建该类实例。
    private LazyLoadedSingleton(){

    }

    private static class LazyHolder{
        //变量是私有的,外界无法访问
        //holds zhe singleton class
        private static LazyLoadedSingleton singleton = new LazyLoadedSingleton();

    }
    /**
     * 只有当第一次调用该方法时，JVM才会加载LazyHolder类，然后才初始化static的singleton
     * 这样即保证了线程安全，又实现了延迟加载
     */
    public static LazyLoadedSingleton getInstance(){
        return LazyHolder.singleton;
    }
    //other motheds...
}

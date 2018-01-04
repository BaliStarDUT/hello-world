/**
 * 单例模式示例：
 * - 延迟创建的单例模式
 */
public class UnThreadSafeSingleton{
    //变量是私有的,外界无法访问
    private static UnThreadSafeSingleton singleton = null;
    //other fields...

    //UnThreadSafeSingleton类只有一个构造方法，被private修饰的， 客户对象无法创建该类实例。
    private UnThreadSafeSingleton(){

    }

    //实现全局访问点
    public static UnThreadSafeSingleton getInstance(){
        //在这里实现延迟创建，但是下面的三行不是线程安全的，
        //在高并发环境下会生成多个不同的该类实例
        if(singleton ==null){
            singleton = new UnThreadSafeSingleton();
        }
        return singleton;
    }
    //other motheds...
}

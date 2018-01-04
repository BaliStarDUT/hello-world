/**
 * 单例模式示例：
 * - 简单单例模式
 */
public class Singleton{
    //变量是私有的,外界无法访问
    private static Singleton singleton = new Singleton();
    //other fields...

    //Singleton类只有一个构造方法，被private修饰的， 客户对象无法创建该类实例。
    private Singleton(){

    }

    //实现全局访问点
    public static Singleton getInstance(){
        return singleton;
    }
    //other motheds...
}

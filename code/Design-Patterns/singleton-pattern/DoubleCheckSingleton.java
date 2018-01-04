/**
 * 单例模式示例：
 * - 线程安全的延迟创建的单例模式
 */
public class DoubleCheckSingleton{
    //变量是私有的,外界无法访问
    //volatile具有synchronized的可见性特点，也就是说线程能够自动发现volatile变量的最新值。
    //这样，如果instatnce实例化成功，其他线程便能立即发现
    private volatile static DoubleCheckSingleton singleton = null;
    //other fields...

    //DoubleCheckSingleton类只有一个构造方法，被private修饰的， 客户对象无法创建该类实例。
    private DoubleCheckSingleton(){

    }

    //实现全局访问点
    public static DoubleCheckSingleton getInstance(){
        //两次检查是为了防止两个线程都进行到同步块的时候，可能会创建两个实例
        if(singleton ==null){
            synchronized (DoubleCheckSingleton.class){
                if(singleton ==null){ //这里如果不检查还是会生成两个实例
                    singleton = new DoubleCheckSingleton();
                }
        }
        return singleton;
    }
    //other motheds...
}

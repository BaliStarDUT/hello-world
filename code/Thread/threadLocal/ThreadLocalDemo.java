import java.util.Random;

public class ThreadLocalDemo implements Runnable{
    private final static ThreadLocal studentLocal = new ThreadLocal();
    public void run() {
        accessStudent();
    }
    public void accessStudent() {
        //获取当前线程的名字
        String currentThreadName = Thread.currentThread().getName();
        System.out.println(currentThreadName + " is running!");
        //产生一个随机数并打印
        Random random = new Random();
        int age = random.nextInt(100);
        System.out.println("thread " + currentThreadName + " set age to:" + age);
        //获取一个Student对象，并将随机数年龄插入到对象属性中
        Student student = getStudent();
        student.setAge(age);
        System.out.println("thread " + currentThreadName + " first read age is:" + student.getAge());
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("thread " + currentThreadName + " second read age is:" + student.getAge());
    }
    protected Student getStudent() {
        //获取本地线程变量并强制转换为Student类型
        Student student = (Student) studentLocal.get();
        //线程首次执行此方法的时候，studentLocal.get()肯定为null
        if (student == null) {
            //创建一个Student对象，并保存到本地线程变量studentLocal中
            student = new Student();
            studentLocal.set(student);
        }
        return student;
    }
    public static void main(String[] agrs) {
        ThreadLocalDemo td = new ThreadLocalDemo();
        Thread t1 = new Thread(td, "a");
        Thread t2 = new Thread(td, "b");
        t1.start();
        t2.start();
    }


}

 class Student {
    private int age = 0;   //年龄

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

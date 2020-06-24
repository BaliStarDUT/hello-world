package net.james.lambda;

/**
 * author: yang
 * datetime: 2020/6/16 11:45
 */

public class PrintThreadName extends ThreadGroup implements Runnable {

    public PrintThreadName(String name) {
        super(name);
    }

    public PrintThreadName(ThreadGroup parent, String name) {
        super(parent, name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

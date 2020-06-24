package net.james.lambda;

/**
 * author: yang
 * datetime: 2020/6/16 10:21
 */

public class LambdaTest {
    public static void main(String[] args) {
        new Thread(new PrintThreadName("1")).start();
        new Thread(new PrintThreadName("2")).start();
//        new Thread(PrintThreadName::new, "3").start();
//        new Thread(PrintThreadName::new, "4").start();
    }
}

class Bank1 {
     synchronized static void transferAccount() {
        System.out.println("开始转账：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("转账完毕");
    }
     synchronized static void debit() {
        System.out.println("开始扣款：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("扣款完毕");
    }
}
public class BankTest {
    public static void main(String[] args) {
        new Thread(Bank1::transferAccount, "北京银行").start();
        new Thread(Bank1::debit, "上海银行").start();
    }
}

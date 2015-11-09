package yang.mqtt;

import java.net.URISyntaxException;

/**
 *
 * @date 2015年10月15日 下午2:57:50
 * @author James Yang
 * @version 1.0
 * @since
 */
public class MainTest implements Runnable{
	private static int i = 0;
	public static void main(String[] args) throws InterruptedException {
		MainTest2 maint = new MainTest2();
		Thread listener = new Thread(maint);
		listener.start();
		Thread.sleep(1000);
//		for(int i=0;i<100;i++){
//			MainTest main = new MainTest();
//			Thread publisher = new Thread(main);
//			publisher.setName("Publisher"+i);
//			try {
//				Thread.sleep(10);
//			} catch (InterruptedException e) {
//				System.out.println("Thread error!");
//				e.printStackTrace();
//			}
//			publisher.start();
////			if(!publisher.isAlive()){
////				listener.stop();
////			}
		//}
		
	}

	@Override
	public void run() {
		
		System.out.println("Publisher Thread Run...");
		//TimerPublisher pub = new TimerPublisher();
		String name = "Publisher"+(i++);
		String host = "tcp://10.4.45.133:61613";//tcp://10.4.45.133:61613,tcp://10.4.44.210:8020
		String username = "admin";
		String password = "password";
		String publictopic = "Topics/htjs/serverToPhone";
		try {
			TimerPublisher pub = new TimerPublisher(name,host,username,password,publictopic,20,10);
		} catch (URISyntaxException e) {
			System.out.println("Publisher Run error!");
			e.printStackTrace();
		}
		
	}

}
class MainTest2 implements Runnable{

	@Override
	public void run() {
		System.out.println("Listener Thread Run....");
		//Listener list = new Listener();	
		String name = "Listener";
		String host = "tcp://101.200.1.101:8020";
		String username = "admin";
		String password = "password";
		String reseivetopic = "76061528";//Topics/htjs/serverToPhone
		try {
			Listener list = new Listener(name,host,username,password,reseivetopic);
		} catch (URISyntaxException e) {
			System.out.println("Listener Run Error!");
			e.printStackTrace();
		}

	}

}
//use join()
//Create multiple threads.
import java.lang.*;
class MyThread implements Runnable{
	Thread thrd;
	MyThread(String name){
		thrd=new Thread(this,name);
		thrd.start();
	}
	public void run(){
		System.out.println(thrd.getName()+" starting.");
		try{
			for(int count=0;count<10;count++){
				Thread.sleep(400);
				System.out.println("In "+thrd.getName()+", count is "+count);
			}
		}catch(InterruptedException exc){
			System.out.println(thrd.getName()+" interrupted.");
		}
		System.out.println(thrd.getName()+" terminating.");
		
	}
}
class JoinThreads{
	public static void main(String[] args){
		System.out.println("Main thread starting.");
		MyThread mt1=new MyThread("Child #1");
		MyThread mt2=new MyThread("Child #2");
		MyThread mt3=new MyThread("Child #3");
		
		try{
			mt1.thrd.join();
			System.out.println("Child #1 joined.");
			mt2.thrd.join();
			System.out.println("Child #2 joined.");
			mt3.thrd.join();
			System.out.println("Child #3 joined.");
		}catch(InterruptedException exc){
			System.out.println("Main Thread interrupted.");
		}
		
		//for(int i=0;i<50;i++){
		
		/*do{
		System.out.print(".");
		try{
			Thread.sleep(100);
		}catch(InterruptedException exc){
			System.out.println("Main thread Interrupted.");
		}
	}while(mt1.thrd.isAlive()||mt2.thrd.isAlive()||mt3.thrd.isAlive());*/
	System.out.println("Main thread ending.");
	}
}

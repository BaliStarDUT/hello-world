//create a thread by implementing Runnable.
class MyThread implements Runnable{
	String thrdName;
	MyThread(String name)
	{
		thrdName=name;
	}	
	//Entry point of thread 
	public void run(){
		System.out.println(thrdName+" starting.");
		try{
			for(int count=0;count<10;count++){
			Thread.sleep(400);
			System.out.println("In "+thrdName+", count is "+count );
			}
		}catch(InterruptedException exc){
		System.out.println(thrdName+" INTERRUPTED.");
		}
		System.out.println(thrdName+" terminating.");
	}
}
class UseThreads{
	public static void main(String[] args){
	System.out.println("Main thread starting.");
	MyThread mt=new MyThread("thread #1");
	Thread  newThrd=new Thread(mt);
	newThrd.start();
	for(int i=0;i<50;i++){
		System.out.print(".");
		try{
			Thread.sleep(100);
		}catch(InterruptedException exc){
			System.out.println("Main thread Interrupted.");
		}
	}
	System.out.println("Main thread ending.");
	}
}

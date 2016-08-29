//Suspending,resuming,and stopping a thread
class MyThread implements Runnable{
	Thread thrd;
	boolean suspended;
	boolean stopped;
	MyThread(String name){
		thrd=new Thread(this,name);
		suspended=false;
		stopped=false;
		thrd.start();
	}
	//This is the entry point for thread.
	public void run(){
		System.out.println(thrd.getName()+" Staring.");
		try{
			for(int i=1;i<1000;i++){
				System.out.print(i+" ");
				if(i%10==0){
					System.out.println();
					Thread.sleep(250);
				}
				//Use synchronized block to check suspended and stopped.
				synchronized(this){
					while(suspended){
						wait();
					}
					if(stopped) break;
				}
			}
		}catch(InterruptedException exc){
			System.out.println(thrd.getName()+" interrupted.");
		}
			System.out.println(thrd.getName()+" exiting.");	
	}
	//stop the thread
	synchronized void myStop(){
		stopped=true;
		//
		suspended =false;
		notify();
	}
	synchronized void mySuspend(){
		suspended=true;
		
	}
	synchronized void myResume(){
		suspended=false;
		notify();
	}
}	
class Suspend{
	public static void main(String[] args){
		MyThread ob1=new MyThread("My Thread");
		try{
			Thread.sleep(1000);
			ob1.mySuspend();
			System.out.println("Suspending thread.");
			Thread.sleep(1000);
			
			ob1.myResume();
			System.out.println("Resuming thread.");
			Thread.sleep(1000);
			
			ob1.mySuspend();
			System.out.println("Suspending thread.");
			Thread.sleep(1000);
			
			ob1.myResume();
			System.out.println("Resuming thread.");
			Thread.sleep(1000);
			
			ob1.mySuspend();
			System.out.println("Stopping thread.");
			ob1.myStop();
		}catch(InterruptedException e){
			System.out.println("Main thread interrupted.");
		}
		System.out.println("Main thread exiting.");
	}
}

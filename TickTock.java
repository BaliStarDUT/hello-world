//Use wait() and notify() to create a ticking clock
class TickTock{
	String state;
	synchronized void tick(boolean running){
		if(!running){
			state="ticked";
			notify();//notify any waiting threads;
			return;
		}
		System.out.print("Tick ");
		state="ticked";//set the current state to ticked
		notify();//let tock() run
		try{
			while(!state.equals("tocked"))
			wait(1000);//wait for tock() to complete
		}catch(InterruptedException exc){
			System.out.println("Thread interrupted.");
		}
	}
	
	synchronized void tock(boolean running){
		if(!running){
			state="tocked";
			notify();//notify any waiting threads;
			return;
		}
		System.out.println("Tock ");
		state="tocked";//set the current state to ticked
		notify();//let tick() run
		try{
			while(!state.equals("ticked"))
			wait(1000);//wait for tick() to complete
		}catch(InterruptedException exc){
			System.out.println("Thread interrupted.");
		}
	}
}

class MyThread implements Runnable{
	
	Thread thrd;
	TickTock ttob;
	
	MyThread(String name,TickTock tt){
		thrd=new Thread(this,name);
		ttob=tt;
		thrd.start();
	}
	public void run(){
		if(thrd.getName().compareTo("Tick")==0){
			for(int i=0;i<5;i++)
			ttob.tick(true);
			ttob.tick(false);
			
		}else{
			for(int i=0;i<5;i++)
			ttob.tock(true);
			ttob.tock(false);
		}
	}
}

class ThreadCom{
	public static void main(String args[]){
		TickTock tt=new TickTock();
		MyThread mt1=new MyThread("Tick",tt);
		MyThread mt2=new MyThread("Tock",tt);
		try{
			mt1.thrd.join();
			mt2.thrd.join();
		}catch(InterruptedException exc){
			System.out.println("Main Thread interrupted.");
		}
	}
	
}


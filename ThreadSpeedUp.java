//test the efficiency of threads
class ThreadSpeedUp{
	public static void main(String[] args){
		if(args.length!=2)
		{
			System.out.println("Usage: ThreadSpeedUp size numReps");
			return ;
		}
		
		//create all the data and the threads
		int[] data=new int[Integer.parseInt(args[0])];
		for(int i=0;i<data.length;i++){
			data[i]=data.length-i;
		}
		int numReps=Integer.parseInt(args[1]);
		Thread[] threads=new Thread[numReps];
		for(int i=0;i<numReps;i++){
			threads[i]=new CopyAndSortThread(data);
		}
		//now sort consecutively in one thread
		System.out.println("Starting sorting array of length "+args[0]+" in one thread "+numReps+"times.");
		for(int i=0;i<numReps;i++){
			copyAndSort(data);
		}
		System.out.println("Done sorting in one thread.");
		System.out.println("Starting sorting arrays of length "+args[0]+" using "+numReps+"threads.");
		
		//start all threads in parallel
		for(Thread thd: threads)
		thd.start();
		
		//wait for all threads to stop
		try{
			for(Thread thd: threads)
			thd.join();
		}catch(InterruptedException exc){
			System.out.println("Interrupted occurred.");
		}
		System.out.println("Done sorting using "+numReps+" threads.");
	}
	static void copyAndSort(int[] data){
		//copy data to a new array
		int[] nums=new int[data.length];
		for(int j=0;j<data.length;j++){
			nums[j]=data[j];
		}
		//now sort the new arrya using bubble sort
		for(int a=1;a<nums.length;a++)
			for(int b=nums.length-1;b>=a;b--){
				if(nums[b-1]>nums[b]){
					int t=nums[b-1];
					nums[b-1]=nums[b];
					nums[b]=t;
				}
			}
		for(int j=0;j<data.length;j++){
			System.out.print(nums[j]+" ");
		}
	}
}
class CopyAndSortThread extends Thread{
	int[] data;
	CopyAndSortThread(int[] d){
		data=d;
	}
	public void run(){
		ThreadSpeedUp.copyAndSort(data);
	}
}


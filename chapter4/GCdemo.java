//try this 4_2
//Demonstrate garbage collection and the finalize() method
class MyClass{
	int x;/
	MyClass(int i){
		x=i;
		//System.out.println("Generate: "+x);
	}
	//called when object is recycled.
	protected void finalize(){
		System.out.println("Finalizing "+x);
	}
	//genarates an object that is immediately abandoned
	void generate(int i){
		MyClass o= new MyClass(i);
	}
	
}
class GCdemo{
	public static void main(String[] args){
		MyClass ob=new MyClass(0);
		for(int count=1;count<1000000;count++)
		ob.generate(count);
	}
}

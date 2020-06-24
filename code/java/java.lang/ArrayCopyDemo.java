//Using arraycopy()
class ArrayCopyDemo{
	@Override
	public String toString(){
		return "ArrayCopyDemo";
	}
	@Override
	public int hashCode(){
		return 2;
	}
	@Override
	public boolean equals(Object object){
		return true;
	}
	static byte[] a={65,66,67,68,69,70,71,72,73,74};
	static byte[] b={77,77,77,77,77,77,77,77,77,77};
	public static void main(String[] args){
		System.out.println("a="+new String(a));
		System.out.println("b="+new String(b));
		System.arraycopy(a,0,b,0,a.length);
		System.out.println("a="+new String(a));
		System.out.println("b="+new String(b));
		System.arraycopy(a,0,a,1,a.length-1);
		System.arraycopy(b,1,b,0,b.length-1);
		System.out.println("a="+new String(a));
		System.out.println("b="+new String(b));
	try{
				ArrayCopyDemo demo = new ArrayCopyDemo();
				ArrayCopyDemo demo1 = new ArrayCopyDemo();
				MemoryDemo demo2 = new MemoryDemo();
				System.out.println("ArrayCopyDemo:"+demo.equals(b));
				System.out.println("MemoryDemo:"+demo2.equals(demo));
				System.out.println("MemoryDemo:"+(demo==demo1));
				// return;
			}finally{
				System.out.println("finally");
			}
			// 65+26
		for(int i =0;i<13;i++){
			byte[] a = {(new Integer(i+35).byteValue())};
			System.out.println(new String(a));
		}
	}
}

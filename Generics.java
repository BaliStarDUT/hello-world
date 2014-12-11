//A simple generic class .
class Generics<T>{
	T ob;
	Generics(T o){
		ob=o;
	}
	T getob(){
		return ob;
	}
	void showType(){
		System.out.println("Type of T is "+ob.getClass().getName());
	}
}
//Demonstrate the generic class
class GenDemo{
	public static void main(String[] args){
		Generics<Integer> iOb;
		iOb=new Generics<Integer>(88);
		iOb.showType();
		
		int v=iOb.getob();
		System.out.println("value: "+v);
		
		System.out.println();
		
		Generics<String> strOb=new Generics<String>("Generics Test");
		
		strOb.showType();
		
		String str=strOb.getob();
		System.out.println("Value:"+str);
		
	}
}

//A simple generic class with two type parameters:T and V.
class TwoGenerics<T,V>{
	T ob1;
	V ob2;
	TwoGenerics(T o1,V o2){
		ob1=o1;
		ob2=o2;
	}
	T getob1(){
		return ob1;
	}
	V getob2(){
		return ob2;
	}
	void showTypes(){
		System.out.println("Type of T is "+ob1.getClass().getName());
		System.out.println("Type of V is "+ob2.getClass().getName());
	}
}
//Demonstrate the generic class
class GenDemo{
	public static void main(String[] args){
		TwoGenerics<Integer,String> iOb;
		iOb=new TwoGenerics<Integer,String>(88,"Generics");
		
		iOb.showTypes();
		
		int v=iOb.getob1();
		System.out.println("value: "+v);
		String str=iOb.getob2();
		System.out.println("value: "+str);
		System.out.println();
		
	}
}

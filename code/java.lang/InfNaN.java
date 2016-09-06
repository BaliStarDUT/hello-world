//Demostrate isInfinite() and isNoN()
class InfNaN{
	public static void main(String[] args){
		Double d1=new Double(1/0.0);//如果改成0,会运行时错误
		Double d2=new Double(0/0.0);
		System.out.println("1/0.0 is "+d1+":"+d1.isInfinite()+","+d1.isNaN());
		System.out.println("0/0.0 is "+d1+":"+d2.isInfinite()+","+d2.isNaN());
	}
}

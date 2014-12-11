//In this version of NumericFns ,the type argument for T must be either Number ,or a class derived from Number.
class NumbericFns<T extends Number>{
	T num;
	NumbericFns(T n){
		num=n;
	}
	double reciprocal(){
		return 1/num.doubleValue();
	}
	double fraction(){
		return num.doubleValue()-num.intValue();
	}
}
//Demonstrate NumbericFns.
class BoundsDemo{
	public static void main(String[] args){
		NumbericFns<Integer> iOb= new NumbericFns<Integer>(5);
		System.out.println("Reciprocal of iOb is "+iOb.reciprocal());
		System.out.println("Fractional component of iOb is "+iOb.fraction());
		System.out.println();
		NumbericFns<Double> dOb=new NumbericFns<Double>(5.25);
		System.out.println("Reciprocal of dOb is "+dOb.reciprocal());
		System.out.println("Fractional component of dOb is "+dOb.fraction());
		} 
}

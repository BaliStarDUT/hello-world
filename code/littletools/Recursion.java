//A simple example of recursion
class Factorial{
		int factR(int n){
			int result;
			if(n==1) return 1;
			result=factR(n-1)*n;
			return result;
		}
		int factI(int n){
			int t,result;
			result=1;
			for(t=1;t<=n;t++)
				result*=t;
			return result;
		}

}
class Recursion{
	public static void main(String[] args){
		if(args.length!=1){
			System.out.println("Usage:java ShowBitsDemo number");
			return;
		}
		Factorial f=new Factorial();
		System.out.println("Factorial using recursion method: ");
		
		System.out.println("Factorial of "+Integer.parseInt(args[0])+" is "+f.factR(Integer.parseInt(args[0])));
		
		System.out.println("Factorial using iterative method: ");
		
		System.out.println("Factorial of "+Integer.parseInt(args[0])+" is "+f.factI(Integer.parseInt(args[0])));
	}
	
}

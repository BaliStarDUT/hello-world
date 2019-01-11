//showBits Display the sequence of bits
class BitOut{
	int numBits;
	BitOut(int n){
		if(n<1) n=1;
		if(n>64) n=64;
		numBits=n;
	}
	BitOut(String n){
		numBits=100;
	}
	void showBits(long val){
		long mask=1;

		mask <<= numBits-1;
		int spacer=8-(numBits%8);
		for(;mask!=0;mask>>>=1){
			if((val&mask)!=0)
				System.out.print("1");
			else
				System.out.print("0");
			spacer++;
			if((spacer%8)==0){
				System.out.print(" ");
				spacer=0;
			}
		}
		System.out.println();

	}
}
//Demo
class ShowBinaryDemo{
	public static void main(String[] args){
		if(args.length!=2){
			System.out.println("Usage:java ShowBitsDemo bits number");
			return;
		}
		Integer args0=new Integer(args[0]);
		Integer args1=new Integer(args[1]);
		System.out.println("args[0]= "+args0);
		BitOut b=new BitOut(Integer.parseInt(args[0]));

		System.out.println(args[1]+" in binary: ");
		b.showBits(Integer.parseInt(args[1]));

	}
}

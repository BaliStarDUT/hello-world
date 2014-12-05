//showBits Display the sequence of bits
class BitOut{
	int numBits;
	BitOut(int n){
		if(n<1) n=1;
		if(n>64) n=64;
		numBits=n;
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
class ShowBitsDemo{
	public static void main(String[] args){
		BitOut b=new BitOut(16);
		BitOut i=new BitOut(32);
		BitOut li=new BitOut(64);
		
		System.out.println("1024 in binary: ");
		b.showBits(1024);
		
		System.out.println("\n87987 in binary: ");
		i.showBits(87987);
		
		System.out.println("\n26656461 in binary: ");
		li.showBits(26656461);
	}
}

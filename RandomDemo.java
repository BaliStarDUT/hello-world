//Demonstrate random Gaussian values
import java.util.Random;
class RandomDemo{
	public static void main(String[] args){
		Random r=new Random();
		double val;
		double sum=0;
		int[] bell=new int[9];
		for(int i=0;i<100;i++){
			val=r.nextGaussian();
			System.out.println(val);
			sum+=val;
			double t=-2;
			for(int x=0;x<bell.length;x++,t+=0.5)
				if(val<t){
					bell[x]++;
					break;
				}
			}
			System.out.println("Average of values: "+(sum/100));
			
			for(int i=0;i<bell.length;i++){
				for(int x=bell[i];x>0;x--)
					System.out.print("*");
				System.out.println();
			
		}
	}
}

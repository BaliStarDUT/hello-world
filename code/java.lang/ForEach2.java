//Use for-reach style for on a two-dimensional array
class ForEach2{
public static void main(String[] args){
	int sum=0;
	int[][] nums=new int[9][9];
	
	for(int i=1;i<=9;i++){
		for(int j=1;j<=i;j++){
		nums[i-1][j-1]=(i)*(j);
		System.out.print(j+"*"+i+"="+nums[i-1][j-1]+" ");
		}
		System.out.println();
	}
	for(int[] x:nums){
		for(int y:x){
			//System.out.println("Value is:"+y);
			sum+=y;
		}
		}
		System.out.println("Summation:"+sum);
}
}

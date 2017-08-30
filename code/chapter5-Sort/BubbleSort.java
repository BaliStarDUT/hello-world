//冒泡排序 5-1
class BubbleSort{
	public static void main(String[] args){
		int[] nums={99,-10,100123,18,-987,5632,463,-9,284,49};
		int a,b,t;
		int size=10;
		System.out.println("Original array is: ");
		for(int i=0;i<size;i++){
			System.out.print(" "+nums[i]);
		}
		System.out.println();
		for(a=1;a<size;a++)
			for(b=size-1;b>=a;b--){
				if(nums[b-1]>nums[b]){
					t=nums[b-1];
					nums[b-1]=nums[b];
					nums[b]=t;
				}
			}
		System.out.println("Sorted array is : ");
		for(int i=0;i<size;i++){
			System.out.print(" "+nums[i]);
		}
	}
}

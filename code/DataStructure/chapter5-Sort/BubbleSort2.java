//冒泡排序 5-1
class BubbleSort2{
	public static void main(String[] args){
		int[] nums={99,-10,100123,18,-987,5632,463,-9,284,49};
		int a,b,t;
		int size=nums.length;
		System.out.println("Original array is: ");
		for(int i=0;i<size;i++){
			System.out.print(" "+nums[i]);
		}
		System.out.println();
		boolean needNextPass = true;
		for(a=1;a<size && needNextPass;a++)
			needNextPass = false;
			for(b=0;b<size-a;b++){
				if(nums[b]>nums[b+1]){
					t=nums[b+1];
					nums[b+1]=nums[b];
					nums[b]=t;
					needNextPass = true;
				}
			}
		System.out.println("Sorted array is : ");
		for(int i=0;i<size;i++){
			System.out.print(" "+nums[i]);
		}
		System.out.println();
	}
}

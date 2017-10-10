public class SelectionSort{
  public static void swapElements(int[] array,int i,int j){
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
  public static int indexLowest(int[] array,int start){
    int lowIndex = start;
    for(int i = start;i<array.length;i++){
      if(array[i]<array[lowIndex]){
        lowIndex = i;
      }
    }
    return lowIndex;
  }
  public static void selectionSort(int[] array){
    for(int i=0;i<array.length;i++){
      int j = indexLowest(array,i);
      swapElements(array,i,j);
    }
  }
  public static void main(String[] args) {
    int[] nums={99,-10,100123,18,-987,5632,463,-9,284,49};
    selectionSort(nums);
    System.out.println("Sorted array is : ");
		for(int i=0;i<nums.length;i++){
			System.out.print(" "+nums[i]);
		}
		System.out.println();
  }

}

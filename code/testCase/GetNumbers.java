/**
* 请写一段程序，输出所有能由0，1，2，3四个数字组成的无重复数字的三位整数，并统计个数。
*/
public class GetNumbers{
  private static  int[] ints = {0,1,2,3};
  private static int total = 0;
  public static void main(String[] args){
    for(int i=0;i<ints.length;i++){
      // System.out.print(ints[i]);
      if(ints[i]==0){
        continue; //去掉0开头的数字
      }
      int j = 0;
      while(j<ints.length){
        if(j!=i){
          // System.out.print(ints[j]);
          int k = 0;
          while(k<ints.length){
            if(k!=i&&k!=j){
              System.out.print(ints[i]);
              System.out.print(ints[j]);
              System.out.print(ints[k]);
              // System.out.println("-----");
              total++;
            }
            k++;
          }
        }
        j++;
      }
    }
    System.out.println("Total:"+total);
  }
}

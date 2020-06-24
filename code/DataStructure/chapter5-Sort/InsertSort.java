import java.util.*;

public class InsertSort{
  ArrayList list;
  public InsertSort(int num,int mod){
    list = new ArrayList(num);
    Random random = new Random();
    System.out.println("Before:");
    for(int i=0;i<num;i++){
      list.add(Math.abs(random.nextInt()%mod)+1);
      System.out.println(i+" = "+list.get(i));
    }
  }
  public void sortIt(){
    int tempInt;
    int MaxSize = 1;
    for(int i=1;i<list.size();i++){
      tempInt = (int)list.remove(i);
      if(tempInt >= (int)list.get(MaxSize-1)){
        list.add(MaxSize,tempInt);
        MaxSize++;
        System.out.println(list.toString());
      }else{
        for(int j= 0;j<MaxSize;j++){
          if((int)list.get(j)>=tempInt){
            list.add(j,tempInt);
            MaxSize++;
            System.out.println(list.toString());
            break;
          }
        }
      }
    }
    System.out.println("after:");
    for(int i=0;i<list.size();i++){
      System.out.println(i+" = "+list.get(i));
    }
  }
  public static void main(String[] args){
    InsertSort in = new InsertSort(10,100);
    in.sortIt();
  }
}

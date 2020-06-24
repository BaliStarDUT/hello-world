public class GenericMethodDemo{
  public static <E> void print(E[] list){
    for(int i=0;i<list.length;i++){
      System.out.println(list[i]+" ");
    }
    System.out.println();
  }
  public static void main(String[] args){
    Integer[] integer = {1,3,4,2,4};
    String[] string = {"a","b","c","d","e"};
    GenericMethodDemo.<Integer>print(integer);
    GenericMethodDemo.<String>print(string);
  }
}

public class StringTest{
    public static void main(String []args){
        int a = Long.valueOf(10001).compareTo(Long.valueOf(10008));
        System.out.println(a);

        String abc = "abcdefghij";
        if(abc.indexOf("abc")>0){
            System.out.println(abc.indexOf("abc"));
        }
        if(abc.indexOf("abc")>-1){
            System.out.println(abc.indexOf("abc"));
        }
        if(abc.indexOf("def")>0){
            System.out.println(abc.indexOf("def"));
        }
        if(abc.indexOf("def")>-1){
            System.out.println(abc.indexOf("def"));
        }

    }
}

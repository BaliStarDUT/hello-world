public class PropetiesTest{
  public static void main(String[] args){
    //系统属性类Properties类
    java.util.Properties properties = System.getProperties();
    properties.list(System.out);
  }
}

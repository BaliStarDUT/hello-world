import java.util.ResourceBundle;

class ResourceBundleTest{
  public static void main(String[] args){
    ResourceBundle res = ResourceBundle.getBundle("log4j");
    String flagFile = (String)res.getObject("log4j.rootLogger");
    String target = res.getString("log4j.appender.stdout.Target");
    System.out.println(flagFile);
    System.out.println(target);

  }
}

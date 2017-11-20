/**
* 代码演示了2点：1. 对象可以在GC时自我拯救，2. 这种自救的机会只有一次，因为一个对象的finalize（）方法最多只会被系统自动调用一次。
*/
public class FinalizeEscapeGC{
  public static FinalizeEscapeGC SAVE_HOOK = null;
  public void isAlive(){
    System.out.println("Yes, I'm still alive.");
  }
  @Override
  protected   void finalize() throws Throwable{
    super.finalize();
    System.out.println("finalize method executed!");
    FinalizeEscapeGC.SAVE_HOOK = this;
  }

  public static void main(String[] args) throws Throwable{
    SAVE_HOOK = new FinalizeEscapeGC();
    System.out.println(SAVE_HOOK.toString());
    SAVE_HOOK = null;
    System.out.println(SAVE_HOOK);
    System.gc();
    System.out.println(SAVE_HOOK);
    Thread.sleep(500);
    if(SAVE_HOOK!=null){
      System.out.println(SAVE_HOOK.toString());
      SAVE_HOOK.isAlive();
    }else{
      System.out.println(SAVE_HOOK.toString());
      System.out.println("No,I'm dead!");
    }

    SAVE_HOOK = null;
    System.out.println(SAVE_HOOK);
    System.gc();
    System.out.println(SAVE_HOOK);
    Thread.sleep(500);
    if(SAVE_HOOK!=null){
      System.out.println(SAVE_HOOK.toString());
      SAVE_HOOK.isAlive();
    }else{
      System.out.println(SAVE_HOOK);
      System.out.println("No,I'm dead!");
    }



  }

}

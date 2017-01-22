/**
*VM Args: -Xss2M
*/
public class JavaVMStackOOM{
    private void dontStop(){
        while(true){
 //           System.out.println("dontStop");
        }
    }
    public void stackLeakByThread(){
         while(true){
            Thread thread = new Thread(new Runnable(){
                @Override
                public void run(){
                    dontStop();
                }
            });
            thread.start();
         }
    }
    public static void main(String[] args){
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}

import java.util.Vector;

public class VectorTest{
  private static Vector<Integer> vector = new Vector<Integer>();
  public static void main(String[] args){
    while(true){
      for (int i=0;i<10;i++){
        vector.add(i);
      }
      Thread removeThread = new Thread(new Runnable(){
        @Override
        public void run(){
          for(int i=0;i<vector.size();i++){
            vector.remove(i);
          }
        }
      });
      Thread printThread  = new Thread(new Runnable(){
        @Override
        public void run(){
          for(int i=0;i<vector.size();i++){
            System.out.println(vector.get(i));
          }
        }
      });
      removeThread.start();
      printThread.start();
      while(Thread.activeCount()>20);
    }
  }
}

import java.io.*;
public class ClassLoaderTest{
  public static void main(String[] args) throws Exception{
    ClassLoader myLoader = new ClassLoader() {
      public Class<?>   loadClass(String name) throws ClassNotFoundException{
        try{
          String filename = name.substring(name.lastIndexOf(".")+1)+".class";
          InputStream is = getClass().getResourceAsStream(filename);
          if(is==null){
            return super.loadClass(name);
          }
          byte[] b = new byte[is.available()];
          is.read(b);
          return defineClass(name,b,0,b.length);
        }catch(IOException e){
          throw new ClassNotFoundException(name);
        }
      }
    };
    Object obj = myLoader.loadClass("DeadLoopClass");
    System.out.println(obj.getClass());
    System.out.println(obj instanceof DeadLoopClass);
    System.out.println(obj instanceof Class);
  }
}

import  java.io.InputStream;
import  java.io.BufferedReader;
import  java.io.InputStreamReader;

public class RunShellDemo0{
    public static void main(String[] args) {
           Runtime run = Runtime.getRuntime();
           try {
               // run.exec("cmd /k shutdown -s -t 3600");
               Process process = run.exec("nginx -t");

               // InputStream error = process.getErrorStream();
               // BufferedReader errorR = new BufferedReader(new InputStreamReader(error));
               // String linee = null;
               // while(null!=(linee = errorR.readLine())){
               //   System.out.println(linee);
               // }

               InputStream out = process.getOutputStream();
               BufferedReader outR = new BufferedReader(new InputStreamReader(out));
               String linee = null;
               while(null!=(linee = outR.readLine())){
                 System.out.println(linee);
               }

               // InputStream in = process.getInputStream();
               //
               // InputStreamReader reader = new InputStreamReader(in);
               // BufferedReader br = new BufferedReader(reader);
               // String line = null;
               // while(null!=(line = br.readLine())){
               //   System.out.println(br.readLine());
               // }
               // in.close();
               process.waitFor();
           } catch (Exception e) {
               e.printStackTrace();
           }
    }
}

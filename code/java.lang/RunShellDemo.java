import  java.io.InputStream;
import  java.io.BufferedReader;
import  java.io.InputStreamReader;

public class RunShellDemo{
    public static void main(String[] args) {
        String path = "C:\\Users\\yang\\Documents\\GitHub\\hello-world\\code\\java.lang\\RunShellDemo.java";
           Runtime run = Runtime.getRuntime();
           try {
               // run.exec("cmd /k shutdown -s -t 3600");
               Process process = run.exec("cmd.exe /k start " + path);
               InputStream in = process.getInputStream();
               InputStreamReader reader = new InputStreamReader(in);
               BufferedReader br = new BufferedReader(reader);
               String line = null;
               // while(null!=(line = br.readLine())){
                 System.out.println(br.readLine());
               // }
               in.close();
               process.waitFor();
           } catch (Exception e) {
               e.printStackTrace();
           }
    }
}

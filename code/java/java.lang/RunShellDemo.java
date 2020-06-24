import  java.io.InputStream;
import  java.io.BufferedReader;
import  java.io.InputStreamReader;

public class RunShellDemo{
    public static void main(String[] args) {
        String path = "C:\\Users\\yang\\Documents\\GitHub\\hello-world\\code\\java.lang\\RunShellDemo.java";
           Runtime run = Runtime.getRuntime();
           StringBuilder result = new StringBuilder();
           boolean flag = false;
           try {
               // run.exec("cmd /k shutdown -s -t 3600");
               Process process = run.exec(new String[]{"/bin/bash","./nginx/nginxT.sh"});
               BufferedReader errorR = new BufferedReader(new InputStreamReader(process.getErrorStream()));
               String linee = null;
               while(null!=(linee = errorR.readLine())){
                 System.out.println(linee);
                 result.append(linee);
                 if(linee.contains("test is successful")){
                     flag = true;
                 }
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
           if(flag == false){
               System.out.println(result);
           }
           return;
    }
}

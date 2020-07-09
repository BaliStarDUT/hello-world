package net.james.socket;

import java.util.TimerTask;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;

/**
 * author: yang
 * datetime: 2020/7/2 19:50
 */


public class ServerThread extends Thread{
    //和本线程相关的socket
    Socket socket = null;
    PrintWriter pw = null;
    OutputStream os = null;
    BufferedReader br = null;
    InputStreamReader isr = null;
    InputStream is = null;
    public ServerThread(Socket socket){
        this.socket = socket;
    }
    //线程执行的操作，响应客户端的请求
    public void run(){
        try{
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String info = null;
            while((info = br.readLine())!=null){
                System.out.println("I am the Server the client Syas:"+info);
            }
            socket.shutdownInput();
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("Welcome!");
            pw.flush();
        }catch(Exception e){
            System.out.println("something goes wrong!");
        }finally{
            try{
                //关闭资源
                if(pw!=null)//不判断的话会出错
                    pw.close();
                if(os!=null)
                    os.close();
                if(br!=null)
                    br.close();
                if(isr!=null)
                    isr.close();
                if(is!=null)
                    is.close();
                if(socket!=null)
                    socket.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}

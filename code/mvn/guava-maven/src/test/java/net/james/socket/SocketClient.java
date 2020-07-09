package net.james.socket;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.io.PrintWriter;

/**
 * author: yang
 * datetime: 2020/7/2 19:45
 */

public class SocketClient {

    public static void main(String[] args){
        try{
            //1.创建客户端Socket
            Socket socket = new Socket("localhost",8888);
            //2.获取输出流，向服务器发送信息
            OutputStream os = socket.getOutputStream();//字节输出流
            PrintWriter pw = new PrintWriter(os);//将输出流包装成打印流
            pw.write("user name:admin;password:yang");
            pw.flush();
            socket.shutdownOutput();
            //3.获取输出流
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String info = null;
            while((info = br.readLine())!=null){
                System.out.println("I am the client Server says:"+info);
            }
            //4.关闭资源
            br.close();
            is.close();
            pw.close();
            os.close();
            socket.close();
        }catch(Exception e){
            System.out.println("Something goes wrong!");
            e.printStackTrace();
        }
    }
}

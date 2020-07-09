package net.james.socket;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.util.Timer;

/**
 * author: yang
 * datetime: 2020/7/2 19:47
 */

public class SocketServer {

    public static void main(String[] args){
        try{
            //1.创建一个服务器端的Socket，指定绑定的端口
            ServerSocket serverSocket = new ServerSocket(8888);
            serverSocket.setSoTimeout(100*1000);//设置连接的超时时间10秒
            Socket socket = null;
            //记录客户端连接次数
            int count = 0;
            System.out.println("=======服务器即将启动，等待客户端的连接===========");
            while(true){
                //调用accept()方法开始监听，等待客户端的链接
                socket = serverSocket.accept();
                if(socket.isConnected()){
                    System.out.println("已经连接……");
                }
                //创建一个新的线程
                ServerThread serverThread = new ServerThread(socket);
                //启动线程
                serverThread.start();
                count++;
                System.out.println("客户端连接次数"+count);
                InetAddress address = socket.getInetAddress();
                System.out.println("当前客户端IP:"+address.getHostAddress());
            }
        }catch(SocketTimeoutException e){
            System.out.println("连接超时！");
            e.printStackTrace();
        }catch(Exception e){
            System.out.println("something goes wrong!");
            e.printStackTrace();
        }
    }
}

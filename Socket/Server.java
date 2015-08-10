//package com.yang.test;
import java.net.ServerSocket; 
import java.net.Socket;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
/*
基于TCP协议的Socket通信，服务器端，实现用户登录
*/
public class Server{
public static void main(String[] args){
		try{
		//1.创建一个服务器端的Socket，指定绑定的端口	
		ServerSocket serverSocket = new ServerSocket(8888);
		Socket socket = null;
		//记录客户端连接次数
		int count = 0;
		System.out.println("=======服务器即将启动，等待客户端的连接===========");
		while(true){
			//调用accept()方法开始监听，等待客户端的链接
			socket = serverSocket.accept();
			//创建一个新的线程
			ServerThread serverThread = new ServerThread(socket);
			//启动线程
			serverThread.start();
			count++;
			System.out.println("客户端连接次数"+count);
			InetAddress address = socket.getInetAddress();
			System.out.println("当前客户端IP:"+address.getHostAddress());
		}
		//serverSocket.close();
		}catch(Exception e){
			System.out.println("something goes wrong!");
			e.printStackTrace();
		}
	}
}
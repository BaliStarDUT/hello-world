//package com.yang.test;
import java.net.ServerSocket; 
import java.net.Socket;
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
		//2.调用accept()方法开始监听，等待客户端的链接
		System.out.println("=======服务器即将启动，等待客户端的连接===========");
		Socket socket = serverSocket.accept(); //处于阻塞状态，等待客户端连接，并返回一个socket对象
		//3.获取输入流，读取信息
		InputStream is = socket.getInputStream();//字节输入流
		InputStreamReader isr = new InputStreamReader(is);//将字节流包装为字符流
		BufferedReader br = new BufferedReader(isr);//为输入流添加缓冲
		String info =null;
		while((info = br.readLine())!=null){
			System.out.println("I am the server ,clietn says:"+info);
		}
		socket.shutdownInput();//关闭输入流
		//4.获取输出流，相应客户端的请求
		OutputStream os = socket.getOutputStream();
		PrintWriter pw = new PrintWriter(os);
		pw.write("Welcome!");
		pw.flush();
		//5.关闭资源
		pw.close();
		os.close();
		br.close();
		isr.close();
		is.close();
		socket.close();
		serverSocket.close();
		}catch(Exception e){
			System.out.println("something goes wrong!");
			e.printStackTrace();
		}
	}
}

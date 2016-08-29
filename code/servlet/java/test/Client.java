package yang.java.test;

import java.net.Socket;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.io.PrintWriter;
/*
基于TCP协议的Socket通信，客户端端，实现用户登录
*/
/**
 * @author yangzhene
 * @author yangzhene
 * {@code this is a class}
 * @
 */
public class Client{
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
		//3.获取输入流
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
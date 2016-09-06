package yang.java.test;

import java.net.Socket;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
/*
基于TCP协议的Socket通信，服务器端线程处理
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
			pw.close();
			os.close();
			br.close();
			isr.close();
			is.close();
			socket.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}

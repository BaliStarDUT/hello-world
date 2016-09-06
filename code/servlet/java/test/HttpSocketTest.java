package yang.java.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

/**
 * 模拟使用socket的HTTP客户端，用来发送一个HTTP请求，并显示返回的信息
* @author James Yang
* @date 2015年9月18日 下午5:17:49
* @version 1.0
* @since
*/
public class HttpSocketTest {
	public static void main(String[] args){
		HttpSocketTest ht = new HttpSocketTest();
		ht.httpSocket();
	}
	public void httpSocket(){
		String hostname = "www.imooc.com";
		String protocol = "http";
		try {
			Socket socket = new Socket(hostname,80);
			OutputStream os = socket.getOutputStream();
			boolean autoflush = true;
			PrintWriter out = new PrintWriter(os,autoflush);
			Charset charset = Charset.forName("utf-8");
			
			BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream(),charset));
			//发送关闭连接的请求到web服务器
			out.println("GET / HTTP/1.1");
			out.println("Host: "+hostname+":80");
			out.println("Connection: Close");
			out.println();
			
			//读取响应信息
			boolean loop = true;
			StringBuilder sb = new StringBuilder(8096);
			while(loop){
				if(bf.ready()){
					int i = 0;
					while(i!=-1){
						i = bf.read();
						sb.append((char)i);
					}
					loop = false;
				}
			}
			
			//将响应信息显示到控制台
			System.out.println(sb.toString());
			socket.close();
		} catch (UnknownHostException e) {
			System.out.println("UnknownHostException,Please check the hostname.");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

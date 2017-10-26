// package yang.java.test;

import java.net.Socket;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.io.PrintWriter;
/*
����TCPЭ����Socketͨ�ţ��ͻ��˶ˣ�ʵ���û���¼
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
		//1.�����ͻ���Socket
		Socket socket = new Socket("localhost",32773);
		//2.��ȡ����������������������Ϣ
		OutputStream os = socket.getOutputStream();//�ֽ�������
		PrintWriter pw = new PrintWriter(os);//����������װ�ɴ�ӡ��
		pw.write("user name:admin;password:yang");
		pw.flush();
		socket.shutdownOutput();
		//3.��ȡ������
		InputStream is = socket.getInputStream();
		System.out.println(is);
		System.out.println(socket.isConnected());

		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String info = null;
		while((info = br.readLine())!=null){
			System.out.println("I am the client Server says:"+info);
		}
		//4.�ر���Դ
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

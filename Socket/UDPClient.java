//package com.yang.test;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.UnknownHostException;
import java.net.SocketException;
import java.net.InetAddress;
import java.io.IOException;
/*
实现基于UDP的Socket通信,客户端
*/
public class UDPClient{
//发送数据
	public static void main(String[] args) throws UnknownHostException,SocketException{
		//1.定义服务器的地址，端口，以及数据
		InetAddress address = InetAddress.getByName("localhost");
		int port = 8800;
		byte[] data = "用户名：admin;密码：123".getBytes();
		//2.创建数据报
		DatagramPacket packet = new DatagramPacket(data,data.length,address,port);
		//3.创建DatagramSocket对象
		DatagramSocket socket = new DatagramSocket();
		//4. 向服务器端发送数据报
		try{
		socket.send(packet); 
		}catch(IOException e){
		System.out.println("Something goes Wrong!");
		}
//接收数据
		//1.
		byte[] data2 = new byte[1024];
		DatagramPacket packet2 = new DatagramPacket(data2,data2.length);
		
		try{//2.接收服务端数据
		socket.receive(packet2);
		}catch(Exception e){
			System.out.println("Someting gose wrong!");
		}
		//3.读取数据
		String reply = new String(data2,0,packet2.getLength());
		System.out.println("I am the client ,server says:"+reply);
		//4.关闭资源
		socket.close();
		
		}
}

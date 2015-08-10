//package com.yang.test;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.InetAddress;
/*
实现基于UDP的Socket通信,服务器端
*/
public class UDPServer{
	public static void main(String[] args) throws SocketException{
		//1.创建服务器端DatagramSocket,指定端口
		DatagramSocket socket = new DatagramSocket(8800);
		//2.创建数据报，用于接收客户端发送的数据
		byte[] data = new byte[1024];//创建字节数组，指定接收的数据包大小
		DatagramPacket packet = new DatagramPacket(data,data.length);
		try{
		//3.接收客户端发送的数据
		System.out.println("===========server has start:================");
		socket.receive(packet);//此方法在接收到数据报之前会一直阻塞
		//4.读取数据
		String info = new String(data,0,packet.getLength());
		System.out.println("I am the server ,Client says:"+info);
		}catch(Exception e){
			System.out.println("Something goes wrong!");
			e.printStackTrace();
		}
		/*
		向客户端响应数据
		*/
		//1.定义客户端的地址，端口号，数据
		InetAddress address = packet.getAddress();
		int port = packet.getPort();
		byte[] data2 = "Welcome!".getBytes();
		//2.创建数据报，包含响应的数据信息
		DatagramPacket packet2 = new DatagramPacket(data2,data2.length,address,port);
		try{
		//3.响应客户端
		socket.send(packet2);
		//4.关闭资源
		socket.close();
		}catch(Exception e){
			System.out.println("Someting gose wrong!");
		}
	}
}

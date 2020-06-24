//Demonstrate datagrams --client side
import java.net.*;
import java.io.*;
class DGClient{
	public static int clientPort=50000;
	public static int buffer_size=1024;
	public static DatagramSocket ds;
	public static void dgClient() throws IOException{
		String str;
		byte[] buffer=new byte[buffer_size];
		System.out.println("Receiving Data");
		for(;;){
			DatagramPacket p=new DatagramPacket(buffer,buffer.length);
			ds.receive(p);
			
			str=new String(p.getData(),0,p.getLength());
			
			System.out.println(str);
			
			if(str.equals("stop")){
				System.out.println("Client quits");
				break;
			}
		}
	}
	public static void main(String[] args){
		ds=null;
		try{
			ds=new DatagramSocket(clientPort);
			dgClient();
		}catch(IOException exc){
			System.out.println("CommunicationException"+exc);
		}finally{
			if(ds!=null){
				ds.close();
			}
		}
	}
}

//Demonstrate datagrams --server side.
import java.net.*;
import java.io.*;
class DGserver{
	public static int clientPort=50000;
	public static int serverPort=50001;
	
	public static DatagramSocket ds;
	public static void dgServer() throws IOException{
		byte[] buffer;
		String str;
		BufferedReader conin=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter characters, enter 'stop' to quit.");
		for(;;){
			str=conin.readLine();
			buffer=str.getBytes();
			ds.send(new DatagramPacket(buffer,buffer.length,InetAddress.getLocalHost(),clientPort));
			//quit when "stop"
			if(str.equals("stop")){
				System.out.println("Server quits");
				return;
			}
		}
	}
	public static void main(String[] args){
		ds=null;
		try{
			ds=new DatagramSocket(serverPort);
			dgServer();	
		}catch(IOException exc){
			System.out.println("CommunicationException"+exc);
		}finally{
			if(ds!=null){
				ds.close();
			}
		}
	}
}

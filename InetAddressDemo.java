//Demonstrate InetAddress 
import java.net.*;
class InetAddressDemo{
	public static void main(String[] args){
		try{
			InetAddress address=InetAddress.getByName("www.Google.com.hk");
			System.out.println("Host name: "+address.getHostName());
			System.out.println("Address: "+address.getHostAddress());
			
			System.out.println();
			
			
		}catch(UnknownHostException exc){
			System.out.println(exc);
		}
	}
}
 

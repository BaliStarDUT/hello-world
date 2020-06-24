//Demonstrate InetAddress 
import java.net.*;
class InetAddressDemo{
	public static void main(String[] args){
		if(args.length!=1){
			System.out.println("Usage: java InetAddressDemo URL");
			return;
		}
		try{
			InetAddress address=InetAddress.getByName("www.Google.com.hk");
			System.out.println("Host name: "+address.getHostName());
			System.out.println("Address: "+address.getHostAddress());
			
			System.out.println();
			
			InetAddress address2=InetAddress.getByName(args[0]);
			System.out.println("Host name: "+address2.getHostName());
			System.out.println("Address: "+address2.getHostAddress());
			
			
		}catch(UnknownHostException exc){
			System.out.println(exc);
		}
	}
}
 

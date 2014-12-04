//Demostrate URL
import java.net.*;
class URLDemo{
	public static void main(String[] args){
		try{
			URL url=new URL("http://www.baidu.com:80/index.html");
			System.out.println("Protocol: "+url.getProtocol());
			System.out.println("Port: "+url.getPort());
			System.out.println("Host: "+url.getHost());
			System.out.println("File: "+url.getFile());
			
		}catch(MalformedURLException exc){
			System.out.println("Invalid URL: "+exc);
		}
	}
}

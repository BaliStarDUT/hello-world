
import java.net.*;
import java.io.*;
import java.util.*;

class ForLoop {
  public static void main(String []args){
    for (int i=0;;i++){
      System.out.println(System.nanoTime()+"--"+i);
      httpRequest();
    }
  }
  private static void httpRequest(){
    try{
			URL url=new URL("http://localhost:5897");
			HttpURLConnection connection=(HttpURLConnection)url.openConnection();

			//display request method
			System.out.println("Request method is "+connection.getRequestMethod());
			//display response code
			System.out.println("Response code is "+connection.getResponseCode());
			//display response message
			System.out.println("Response message is "+connection.getResponseMessage());
			Map<String,List<String>> hdrMap=connection.getHeaderFields();
			Set<String> hdrKeys=hdrMap.keySet();
			System.out.println("\nHere is the header: ");
			for(String k:hdrKeys){
				System.out.println("Key: "+k+"Value: "+hdrMap.get(k));
			}

			}catch(IOException exc){
				System.out.println(exc);
			}
	}
}

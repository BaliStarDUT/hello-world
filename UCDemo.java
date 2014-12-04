//Demonstrate URLConnection
import java.net.*;
import java.io.*;
import java.util.*;
class UCDemo{
	public static void main(String[] args){
		InputStream in=null;
		URLConnection connection=null;
		try{
			URL url=new URL("http://weibo.com/u/3104305047/home?wvr=5&lf=reg");
			connection=url.openConnection();
			//getDate
			long d=connection.getDate();
			if(d==0)
				System.out.println("No date information.");
			else
				System.out.println("Date: "+new Date(d));
				
			//get content type
			System.out.println("Content-Type: "+connection.getContentType());
			//get expiration date
			d=connection.getExpiration();
			if(d==0){
				System.out.println("No expiration information.");
			}else
				System.out.println("Expires: "+new Date(d));
			//get last-modified date
			d=connection.getLastModified();
			if(d==0)
				System.out.println("No last-modified information");
			else
				System.out.println("last-modified: "+new Date(d));
			//get content length
			long len=connection.getContentLengthLong();
			if(len==-1)
				System.out.println("Content length unavailable.");
			else
				System.out.println("content-length: "+len);
			if(len!=0){
				System.out.println("==========content==========");
				in=connection.getInputStream();
				int ch;
				while(((ch=in.read())!=-1)){
					System.out.print((char)ch);
				}
			}else{
				System.out.println("No content available.");
			}
		}catch(IOException exc){
			System.out.println("connection error: "+exc);
		}finally{
			try{
				if(in!=null) in.close();
			}catch(IOException exc){
				System.out.println("Error closing socket: "+exc);
			}
		}
		
	}
}

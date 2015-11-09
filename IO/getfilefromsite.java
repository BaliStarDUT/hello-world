//Demonstrate URLConnection
//get file from site
import java.net.*;
import java.io.*;
import java.util.*;
class UCDemo{
	public static void main(String[] args){
		InputStream in=null;
		URLConnection connection=null;
		FileOutputStream fileout=null;
		if(args.length!=2){
			System.out.println("Usage:java GetFileFromSite url file");
			return;
		}
		try{
			URL url=new URL(args[0]);
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
				fileout=new FileOutputStream(args[1]);
				int ch;
				while(((ch=in.read())!=-1)){
					System.out.print((char)ch);
					fileout.write(ch);
				}
			}else{
				System.out.println("No content available.");
			}
		}catch(IOException exc){
			System.out.println("connection error: "+exc);
		}finally{
			try{
				if(in!=null) in.close();
				if(fileout!=null) fileout.close();
			}catch(IOException exc){
				System.out.println("Error closing socket and stream: "+exc);
			}
		}
		
	}
}

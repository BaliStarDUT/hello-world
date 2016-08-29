//Demonstrate Sockets.
//use automatic resource management to close a socket
import java.net.*;
import java.io.*;
class SocketDemo{
	public static void main(String[] args){
		int ch;
		//Socket socket=null;
		try(Socket socket =new Socket("localhost",80)){
		
		InputStream in = socket.getInputStream();
		OutputStream out=socket.getOutputStream();
		
		String str=(args.length==0?"index.html":args[0])+"\n";
		byte[] buf = str.getBytes();
		out.write(buf);
		while((ch=in.read())!=-1){
				System.out.print((char)ch);
		}
		}catch(IOException exc){
			System.out.println(exc);
		}
		/*finally{
			try{
				if(socket!=null) socket.close();
			}catch(IOException exc){
				System.out.println("Error closing socket: "+exc);
			}
		}*/
	}
}


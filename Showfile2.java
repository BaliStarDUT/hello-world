/*This variation wraps the code that opens and access the file within a single try block.
The file is closed by the finally block.*/
import java.io.*;
class Showfile{
	public static void main(String[] args){
		int i;
		FileInputStream fin=null;
		if(args.length!=1){
			System.out.println("Usage: java Showfile filename");
			return;
		}
		try{
			fin=new FileInputStream(args[0]);
			do{
				i=fin.read();
				if(i!=-1)
					System.out.print((char)i);
			}while(i!=-1);
			
		}catch(FileNotFoundException exc){
			System.out.println("File Not Found.");
		}catch(IOException exc){
			System.out.println("An I/O Error Occurred.");
		}finally{
			try{
				if(fin!=null)
					fin.close();
			}catch(IOException exc){
				System.out.println("Error Closing File.");
			}
		}
	}
}

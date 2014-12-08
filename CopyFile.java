/*Copy a text file.
	To use this program, specify the name of the source file and the destination file.
	For example,to copy a file called first.txt to a file called second.txt,use the following command line:
	java CopyFile first.txt second.txt
	*/
import java.io.*;
class CopyFile{
	public static void main(String[] args){
		int i;
		FileInputStream fin=null;
		FileOutputStream fout=null;
		if(args.length!=2){
			System.out.println("Usage: CopyFile from to");
			return;
		}
		try{
			fin=new FileInputStream(args[0]);
			fout=new FileOutputStream(args[1]);
				do{
					i=fin.read();
					if(i!=-1) fout.write(i);
				}while(i!=-1);
		}catch(IOException exc){
			System.out.println("I/O Error :"+exc);
		}finally{
			try{
				if(fin!=null) fin.close();
			}catch(IOException exc){
				System.out.println("Error Closing Output File.");
			}
		}
	}
}

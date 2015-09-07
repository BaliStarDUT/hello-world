import java.io.IOException;
import java.io.FileInputStream;
public class IOUtil{
	public static void main(String[] args) throws IOException{
		//IOUtil.printHex("/home/yangz/yangz.pub");
		IOUtil.printHexByByteArray("/home/yangz/yangz.pub");
	}
	//读取指定文件内容，按照16进制输出到控制台，并且每输出10个byte换行
	public static void printHex(String fileName) throws IOException{
		//把文件作为字节流进行读操作
		FileInputStream in = new FileInputStream(fileName);
		int b;
		int i =1;
		while((b = in.read())!=-1){
			System.out.print(Integer.toBinaryString(b)+" ");//以16进制输出
			System.out.print(Integer.toHexString(b)+" ");//以2进制输出
			if(i++%10 == 0){
				System.out.println();
			}
		}
		in.close();
	}
	public static void printHexByByteArray(String fileName) throws IOException{
		FileInputStream in = new FileInputStream(fileName);	
		byte[] buf = new byte[20*1024];
		//从in中批量读取字节，放入到buf中，从第0个开始放，最多放buf.length个，返回的是读到的字节个数
		int bytes = in.read(buf,0,buf.length);//一次性读完，说明字节数组足够大
		for(int i=0;i<bytes;i++){
			if(buf[i]<=0xf){
				System.out.print("0");
			}
			System.out.print(Integer.toHexString(buf[i])+" ");
			if(i++%10==0){
				System.out.println();
			}
		}
		
	}
}


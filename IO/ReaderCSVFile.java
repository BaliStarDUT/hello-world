//package yang.java.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * 解析简单CSV文件，可以识别逗号',',引号'"'，并在命令行输出解析结果
 * @author yangzhene
 *
 */
public class ReaderCSVFile {

	public static void main(String[] args){
		Path file = Paths.get("C:\\Users\\yangzhene\\Desktop\\original_data.csv");
		if(!Files.exists(file)){
			System.out.println(file+" does't exist.Terminating program!");
			System.exit(1);
		}
		try(BufferedReader fileIn = new BufferedReader(Files.newBufferedReader(file, Charset.forName("UTF-8")))){
			String saying = null;
			int totalRead = 0;
			while((saying = fileIn.readLine())!=null){
				//System.out.println(saying);
				readStr(saying); 
				//System.out.println();
								
				++totalRead;
			}
			System.out.format("%d sayings read.%n", totalRead);
		}catch(IOException e){
			System.out.println("Error reading file:"+file);
			e.printStackTrace();
		}
	}
	public static void readStr(String saying){
		int a1 = saying.indexOf(',');
		if(a1==-1){
			System.out.println(readWord(saying));
		}else{
			String b = saying.substring(0,a1);
			System.out.print(readWord(b)+" | ");
			String str1 = saying.substring(a1+1, saying.length());
			readStr(str1);
		}

	}
	public static String readWord(String str){
		int a = str.indexOf('"');
		int b = str.lastIndexOf('"');
		return str.substring(a+1, b);
	}
	
}

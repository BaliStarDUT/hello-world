package yang.java.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 利用StreamTokenizer来解析CSV文件
 * @date 2015年9月22日 上午11:31:42
 * @author James Yang
 * @version 1.0
 * @since
 */
public class ReadCSVFileUseToken {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		Path file = Paths.get("C:\\Users\\yangzhene\\Desktop\\original_data2.csv");
		if(!Files.exists(file)){
			System.out.println(file+" does't exist.Terminating program!");
			System.exit(1);
		}
		
		try(BufferedReader fileIn = new BufferedReader(Files.newBufferedReader(file, Charset.forName("UTF-8")))){
			readStr(fileIn);
			long endTime = System.currentTimeMillis();
			System.out.println("Use:"+(endTime-startTime)+"ms");
		} catch (IOException e) {
			System.out.println("Error reading file:"+file);
			e.printStackTrace();
		}
	}
	
	private static void readStr(BufferedReader buffreader) {
		StreamTokenizer tokenizer = new StreamTokenizer(buffreader);
		tokenizer.eolIsSignificant(true);
		int tokenType = 0;
		int totalRead = 0;
		try {
			while((tokenType = tokenizer.nextToken())!=tokenizer.TT_EOF){
				if(tokenType == StreamTokenizer.TT_NUMBER){
					System.out.print(tokenizer.nval+" | ");
				}else if(tokenType == StreamTokenizer.TT_WORD || tokenType == '\"' || tokenType == '\''){
					System.out.print(tokenizer.sval+" | ");
				}else if(tokenType == StreamTokenizer.TT_EOL){
					System.out.println();
					totalRead++;
				}
			}
			System.out.format("%d sayings read.\n", totalRead);
		} catch (IOException e) {
			System.out.println("Token errowr");
			e.printStackTrace();
		}
	}

}

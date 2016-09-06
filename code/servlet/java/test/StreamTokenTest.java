package yang.java.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 *
 * @date 2015年9月22日 上午9:49:46
 * @author James Yang
 * @version 1.0
 * @since
 */
public class StreamTokenTest {
	private int ttype; //store the token type code
	//Object to tokenize input from the standard input stream
	private StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	public static void main(String[] args) {
		StreamTokenTest st = new StreamTokenTest();
		for(int i=0;i<5;++i){
			try {
				System.out.println("Enter a integer:");
				System.out.println("Integer read:"+st.readInt());
				System.out.println("Enter a double value:");
				System.out.println("Double value read:"+st.readDouble());
				System.out.println("Enter a string:");
				System.out.println("string read:"+st.readString());
				} catch (InvalidUserInputException e) {
					System.out.println("InvalidUserInputException throw.\n"
							+e.getMessage());
					}
		}
	}
	/**
	 * 读取标记
	 * @return int
	 */
	private int readToken(){
		try {
			ttype = tokenizer.nextToken();
			return ttype;
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return 0;
	}
	public int readInt() throws InvalidUserInputException{
		//终止程序是不友好的，可以自定义异常抛出异常，让调用方法决定处理方法
		if(readToken() != StreamTokenizer.TT_NUMBER){
			throw new InvalidUserInputException("readInt() failed."
					+ "Input data not numeric");
		}
		//检查是否超出整型范围
		if(tokenizer.nval > (double)Integer.MAX_VALUE || tokenizer.nval < (double)Integer.MIN_VALUE){
			throw new InvalidUserInputException("readInt() failed."
					+ "Input outside range of type int");
		}
		//检查是否是整数，为了判断nval中的值是否是一个整数，将其转换为一个整型，再转换成double，看是否还是同一个值
		if(tokenizer.nval !=(double)(int)tokenizer.nval){
			throw new InvalidUserInputException("readInt() failed."
					+ "Input not an integer");
		}
		return (int)tokenizer.nval;
				
//		for(int i=0;i<5;i++){
//			if(readToken()==StreamTokenizer.TT_NUMBER){
//				System.out.println((int)tokenizer.nval); //Value is numeric,so return as int
//			}else{
//				System.out.println("Incorrect input:"+tokenizer.sval+" Re-enter an integer");
//				//Retry the read operation
//				continue;
//			}
//		}
//		System.out.println("Five failures reading an int value"+"- program terminated");
//		//End the program
//		System.exit(1);
//		return 0;
	}
	/**
	 * 读取double类型的值
	 * @return double 
	 * @throws InvalidUserInputException
	 */
	public double readDouble() throws InvalidUserInputException{
		if(readToken() != StreamTokenizer.TT_NUMBER){
			throw new InvalidUserInputException("readDouble() failed."
					+ "Input data not numeric");
		}
		return tokenizer.nval;
	}
	/**
	 * 读取字符串，将单引号或双引号中的识别为字符串
	 * @return String
	 * @throws InvalidUserInputException
	 */
	public String readString() throws InvalidUserInputException{
		if(readToken() == StreamTokenizer.TT_WORD || ttype =='\"' || ttype=='\''){
			return tokenizer.sval;
		}else{
			throw new InvalidUserInputException("readString() failed."
					+ "Input data is not a string");
		}
	}
	
}

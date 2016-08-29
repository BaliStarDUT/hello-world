package yang.java.test;
/**
 * 自定义的用户输入错误异常
 * @date 2015年9月22日 上午10:20:23
 * @author James Yang
 * @version 1.0
 * @since
 */
public class InvalidUserInputException extends Exception {
	private static final long serialVersionUID = 90001L;
	
	public InvalidUserInputException() {}

	public InvalidUserInputException(String message) {
		super(message);
	}
	
}

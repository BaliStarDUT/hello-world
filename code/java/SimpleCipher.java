//Use xor to encode and decode a message
class SimpleCipher{
	static String msg="This is a message!";
	public static void main(String[] args){
		String encMsg="";
		String decMsg="";
		int key=88;
		System.out.print("Original message: ");
		System.out.println(msg);
		
		for(int i=0;i<msg.length();i++){
			encMsg=encMsg+(char)(msg.charAt(i)^key);
		}
		System.out.print("Encoded message: ");
		System.out.println(encMsg);
		
		for(int i=0;i<msg.length();i++){
			decMsg=decMsg+(char)(encMsg.charAt(i)^key);
		}
		System.out.print("Decoded message: ");
		System.out.println(decMsg);
		
	}
}

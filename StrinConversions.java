//Convert an integer into binary,hexadecimal and octal.
class StrinConversions{
	public static void main(String[] args){
		if(args.length!=1){
			System.out.println("Usage:java StringConversions num");
			return;
		}
		int num=Integer.parseInt(args[0]);
		System.out.println(num+" in binary:"+Integer.toBinaryString(num));
		System.out.println(num+" in octal:"+Integer.toOctalString(num));
		System.out.println(num+" in hexadecimal:"+Integer.toHexString(num));
	}
}

class EncodeTest{
	public static void main(String[] args) throws Exception{
	String s = "慕课ABC";
	byte[] bytes1 = s.getBytes();//装换成字节序列，用的是默认编码gbk
	for(byte b:bytes1){
		//把字节（转换成int）以16进制方式显示
		System.out.print(Integer.toHexString(b & 0xff)+" ");
	}
	System.out.println();
	//gbk编码中文占2个字节英文占1个字节
	byte[] bytes2 = s.getBytes("gbk");
	for(byte b:bytes2){
		//把字节（转换成int）以16进制方式显示
		System.out.print(Integer.toHexString(b & 0xff)+" ");
	}
	System.out.println();
	byte[] bytes3 = s.getBytes("utf-8");
	//utf-8编码中文占用3个字节，英文占用1个字节
	for(byte b:bytes3){
		//把字节（转换成int）以16进制方式显示
		System.out.print(Integer.toHexString(b & 0xff)+" ");
	}
	System.out.println();
	
	//java是双字节字符，utf-16be编码
		byte[] bytes4 = s.getBytes("utf-16be");
	//utf-16be编码中文占用2个字节，英文占用2个字节
	for(byte b:bytes4){
		//把字节（转换成int）以16进制方式显示
		System.out.print(Integer.toHexString(b & 0xff)+" ");
	}
	System.out.println();
	//当字节序列是某种编码时，想把字节序列变成字符串，也需要用这种编码方式，否则乱码
	String str1 = new String(bytes4);//用项目默认编码方式gbk来读取字节序列
	System.out.println(str1);
	String str2 = new String(bytes4,"utf-16be");//用字符序列的编码格式构建字符串
	System.out.println(str2);
	
	//文本文件就是字节序列，可以是任意编码的字节序列
	//如果我们在中文机器上直接创建文本文件，该文件只认识ANSI编码
	
	}
}

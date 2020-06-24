//大小写转化
class UpCase{
	public static void main(String[] args){
		char ch;
		for(int i=0;i<10;i++){
			ch=(char)('a'+i);
			System.out.print(ch);
			//ch=(char)((int)ch&65503);
			ch=(char)(ch-32);
			System.out.print(ch+" ");
		}
		//System.out.println("Original message:");
		
	}
}

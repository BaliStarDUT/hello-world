package yang.java.test;

public class MoveTowle {

	public static void main(String[] args) {
		//move(4,'A','B','C');
		readStr("1,wo,shi,yi,ge,ren");
	}
	public static void move(int n,char a,char b,char c){
		if(n==1){
			System.out.println(a+"--->"+c);
		}else if(n>1){
			move(n-1,a,c,b);
			System.out.println(a+"--->"+c);
			move(n-1,b,a,c);
		}
	}
	public static void readStr(String saying){
		int a1 = saying.indexOf(',');
		if(a1==-1){
			System.out.println(saying);
		}else{
			System.out.print(saying.substring(0,a1)+" | ");
			String str1 = saying.substring(a1+1, saying.length());
			readStr(str1);
		}

	}

}

//第四章习题 13
 class Die{
	int sideUp;
	Die(){
	}
	int getSideUp(){
		return sideUp;
	}
	void roll(){
		sideUp=(int)(Math.random()*5+1.0);
	};
	
 }
 class DieDemo{
	public static void main(String[] args){
		Die die1=new Die();
		Die die2=new Die();
		for(int i=1;i<=10;i++){
		die1.roll();
		die2.roll();
		System.out.println("the result is "+die1.getSideUp()+" "+die2.getSideUp());
	}
	}
 }

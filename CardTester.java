//第四章 习题 14
class Card{
	int rank;
	char suit;
	Card(int a,char b){
		rank=a;
		suit=b;
	}
	int getRank(){
		return rank;
	}
	char getSuit(){
		return suit;
	}
}
class CardTester{
	public static void main(String[] args){
		Card card1=new Card(1,'A');
		System.out.println("card1 rank= "+card1.getRank()+" suit= "+card1.getSuit());
	}
}

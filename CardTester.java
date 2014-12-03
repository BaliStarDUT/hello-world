//第四章 习题 14,15
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
		Card card2=card1;
		card2.rank=2;
		card2.suit='B';
		System.out.println("card1 rank= "+card1.getRank()+" suit= "+card1.getSuit());
		System.out.println("card2 rank= "+card2.getRank()+" suit= "+card2.getSuit());

		
	}
}

//Demostrate rotateLeft() and rotateRight()
class Rotations{
	public static void main(String[] args){
		if(args.length!=1){
			System.out.println("Usage:java Rotations num");
			return;
		}
		int num=Integer.parseInt(args[0]);
		System.out.println(num+" in binary:"+Integer.toBinaryString(num));
		num = Integer.rotateLeft(num,4);
		System.out.println(num+" rotate left 4:"+Integer.toBinaryString(num));
		num= Integer.rotateRight(num,4);
		System.out.println(num+" rotate right 4:"+Integer.toBinaryString(num));
	}
}

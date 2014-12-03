//5-2 数据结构，一个简单的栈
class SimpleStack{
	char[] data;
	int tos;
	SimpleStack(int size){
		data=new char[size];
		tos=0;
	}
	void push(char ch){
		if(isFull()){
			System.out.println("-- Stack is full.");
			return;
		}
		data[tos]=ch;
		tos++;
	}
	char pop(){
		if(isEmpty()){
			System.out.println("-- Stack is empty.");
			return (char)0;
		}
		tos--;
		return data[tos];
	}
	boolean isFull(){
		return tos==data.length;
	}
	boolean isEmpty(){
		return tos==0;
	}
	
}

class SimpleStackDemo{
	public static void main(String[] args){
		int i;
		char ch;
		System.out.println("Demonstrate SimpleStack\n ");
		SimpleStack stack=new SimpleStack(10);
		
		System.out.println("Push 10 items onto a 10-element stack.");
		
		System.out.print("Pushing: ");
		for(ch='A';ch<'K';ch++){
			System.out.print(ch);
			stack.push(ch);
		}
		System.out.println("\nPop those 10 items from stack.");
		
		System.out.print("Poping:");
		for(i=0;i<10;i++){
			ch=stack.pop();
			System.out.print(ch);
		}
		System.out.println("\n\nNext,use isEmpty() and isFull() "+"to fill and empty the stack.");
		System.out.print("Pushing:");
		for(ch='A';!stack.isFull();ch++){
			System.out.print(ch);
			stack.push(ch);
		}
		System.out.println();
		
		System.out.print("Poping:");
		while(!stack.isEmpty()){
			ch=stack.pop();
			System.out.print(ch);
		}
		System.out.println("\n\n Now use a 4-element stack to generate "+" some errors.");
		SimpleStack smallStack =new SimpleStack(4);
		System.out.print("Pushing:");
		for(ch='1';ch<'6';ch++){
			System.out.print(ch);
			smallStack.push(ch);
		}
		System.out.print("Poping:");
		for(i=0;i<5;i++){
			ch=smallStack.pop();
			System.out.print(ch);
		}
	}
}

package yang.java.test;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class TestProgram {

	public static void main(String[] args) {
		System.out.println("This is a Test Program:");
		Calendar cal = Calendar.getInstance();	
		//cal.get(Calendar.YEAR);
		Date date = cal.getTime();
		System.out.println("The time is "+date.toGMTString());
		System.out.println("Please Select the number you want to run the program:");
		System.out.println("0.EXIT");
		System.out.println("1.Hello World!");
		
		Scanner sc= new Scanner(System.in);
		int select = sc.nextInt();
		switch(select){
		case 0:{
			System.out.println("Bye");
			return ;
		}
		case 1:{
			System.out.println("Hello world!");
		}
		}
	}

}

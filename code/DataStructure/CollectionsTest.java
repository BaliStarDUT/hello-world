//1.通过Collections.sort()方法，对Integer泛型的List进行排序
//2.对String泛型List进行排序
//3.对其他类型泛型的List进行排序
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
public class CollectionsTest{
	//1.通过Collections.sort()方法，对Integer泛型的List进行排序
	public void testSort1(){
		//创建一个Integer泛型的List，插入十个100以内的不重复随机整数
		List<Integer> integerList = new ArrayList<Integer>();
		Random random = new Random();
		Integer k;
		for(int i=0;i<10;i++){
			do{
				k = random.nextInt(100);//使之不重复
			}while(integerList.contains(k));
		integerList.add(k);
		System.out.println("成功添加整数类："+k);
		}	
		System.out.println("----------排序前--------------");
		for(Integer integer:integerList	){
			System.out.println("元素："+integer);
		}
		Collections.sort(integerList);
		System.out.println("----------排序后--------------");
		for(Integer integer:integerList	){
			System.out.println("元素："+integer);
		}
	}
	//2.对String泛型List进行排序
	public void testSort2(){
		//创建String泛型的List，添加三个乱序的String
		List<String> stringList = new ArrayList<String>();
		stringList.add("microsoft");
		stringList.add("apple");
		stringList.add("google");
		//调用sort方法
		System.out.println("----------排序前--------------");
		for(String string:stringList){
			System.out.println("元素："+string);
		}
		Collections.sort(stringList);
		System.out.println("----------排序后--------------");
		for(String string:stringList){
			System.out.println("元素："+string);
		}
	}
	//3.对其他类型泛型的List进行排序
	public void testSort3(){
		List<Student> studentList = new ArrayList<Student>();
		Random random = new Random();
		studentList.add(new Student(random.nextInt(1000)+"","zhen1"));
		studentList.add(new Student(random.nextInt(1000)+"","yang2"));
		studentList.add(new Student(random.nextInt(1000)+"","yang3"));
		studentList.add(new Student(random.nextInt(10000)+"","yang4"));		
		//调用按ID sort方法
		System.out.println("----------排序前--------------");
		for(Student string:studentList){
			System.out.println("学生："+string.id+":"+string.name);
		}
		Collections.sort(studentList);
		System.out.println("----------排序后--------------");
		for(Student string:studentList){
			System.out.println("学生："+string.id+":"+string.name);
		}
		
		Collections.sort(studentList,new StudentComparator());
		System.out.println("----------按姓名排序后--------------");
		for(Student string:studentList){
			System.out.println("学生："+string.id+":"+string.name);
		}
	}
	public static void main(String[] args){
		CollectionsTest ct = new CollectionsTest();
		ct.testSort3();
		
		
	}
}

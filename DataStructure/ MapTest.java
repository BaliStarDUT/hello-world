import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;
public class MapTest{
	//定义用来承装学生类型对象的Map
	public Map<String,Student> students;
	//在构造方法中初始化students属性
	public MapTest(){
		this.students = new HashMap<String,Student>();
	}
	//测试添加：输入学生ID，判断是否被占用
	//若未被占用，则输入姓名，创建新的学生对象，并且
	//添加到students中
	public void testPut(){
	//创建一个Scanner对象，用来获取输入的学生ID和姓名
	 Scanner console = new Scanner(System.in);
	 int i = 0;
	 while(i<3){
	 	System.out.println("Please input student ID:");
	 	String ID = console.next();
	 	//判断该ID是否被占用
	 	Student st = students.get(ID);
	 	if(st==null){
	 		//提示输入学生姓名
	 		System.out.println("Please input student name:");
	 		String name = console.next();
	 		//创建新的学生对象
	 		Student newStudent = new Student(ID,name);
	 		//通过调用students的put方法，添加映射关系
	 		students.put(ID,newStudent);
	 		System.out.println("成功添加学生:"+students.get(ID).name);
	 		i++;
	 	}else{
	 		System.out.println("This student is already exist!");
	 		continue;
	 	}
	 }	
	}
	//测试Map的keySet方法
	public void testKeySet(){
		//通过keySet方法，返回Map中的所有键的Set集合
		Set<String> keySet = students.keySet();
		//取得studetnsMap的容量
		System.out.println("There are:"+students.size()+" students");
		//遍历keySet，取得每一个键，再调用get方法取得每一个value
		for(String stuId:keySet){
			Student st = students.get(stuId);
			if(st!=null){
				System.out.println("student:"+st.name);
			}
		}
	}
	//通过entrySet方法来遍历Map
	public void testEntrySet(){
	//通过entrySet方法，返回Map中的所有键值对
	Set<Entry<String,Student>> entrySet = students.entrySet();
	for(Entry<String,Student> entry:entrySet){
		System.out.println("Get the key:"+entry.getKey());
		System.out.println("Get the value:"+entry.getValue().name);
	}
	}
	//测试删除Map中的映射
	public void testRemove(){
		//获取从键盘输入的ID
		Scanner sc = new Scanner(System.in);
		while(true){
			//提示输入待删除学生的ID
			System.out.println("Please input ID to remove:");
			String ID = sc.next();
			//判断ID是否有对应的值
		Student st = students.get(ID);
		if(st == null){
			//提示输入的ID不存在
			System.out.println("The ID does't exist!");
			continue;
		}else{
			students.remove(ID);
			System.out.println("Success remove "+st.name);
			break;
		}
		}
	}
	public static void main(String[] args){
		MapTest mt = new MapTest();
		mt.testPut();
		mt.testKeySet();
		mt.testRemove();
		mt.testEntrySet();		
	}
}

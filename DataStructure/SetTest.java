import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
public class SetTest{
	public List<CourseCellection> coursesToSelect;
	public Student student;
	private Scanner console;
	public SetTest(){
		coursesToSelect = new ArrayList<CourseCellection>();
		console =new Scanner(System.in);
	}
	public void testAdd(){
		//创建一个课程对象，并通过调用add方法，添加到备选课程List中
		CourseCellection cr1 = new CourseCellection("1","mathmatic");
		coursesToSelect.add(cr1);
		CourseCellection temp = (CourseCellection)coursesToSelect.get(0);
		System.out.println("添加课程 ："+temp.id+"："+temp.name);
		
		CourseCellection cr2 = new CourseCellection("2","C语言");
		coursesToSelect.add(0,cr2);
		CourseCellection temp2 = (CourseCellection)coursesToSelect.get(0);
		System.out.println("添加了课程："+temp2.id+":"+temp2.name);
		
/*		coursesToSelect.add(cr1);
		CourseCellection temp0 = (CourseCellection)coursesToSelect.get(0);
		System.out.println("添加了课程："+temp2.id+":"+temp2.name);*/
		
		//以下方法会抛出数组下标越界的异常
		//Course cr3 = new Course("3","test");
		//coursesToSelect.add(4,cr3);
		
		CourseCellection[] course = {new CourseCellection("3","离散数学"),new CourseCellection("4","汇编语言")};
		coursesToSelect.addAll(Arrays.asList(course));
		CourseCellection temp3 = (CourseCellection)coursesToSelect.get(2);
		CourseCellection temp4 = (CourseCellection)coursesToSelect.get(3);
		System.out.println("添加了两门课程："+temp3.id+":"+temp3.name+";"+temp4.id+":"+temp4.name);
		
		CourseCellection[] course2 = {new CourseCellection("5","高等数学"),new CourseCellection("6","大学英语")};
		coursesToSelect.addAll(2,Arrays.asList(course2));
		CourseCellection temp5 = (CourseCellection)coursesToSelect.get(2);
		CourseCellection temp6 = (CourseCellection)coursesToSelect.get(3);
		System.out.println("添加了两门课程："+temp5.id+":"+temp5.name+";"+temp6.id+":"+temp6.name);
		
	}

//通过for each 方法访问集合元素
	public void testForEach(){
		System.out.println("有如下课程(通过ForEach访问)：");
		for(Object obj:coursesToSelect)
		{
			CourseCellection cr = (CourseCellection)obj;
			System.out.println("课程："+cr.id+"："+cr.name);			
		}
	}
//测试List的contains方法
	public void testListContains(){
	//取得备选课程序列的第0个元素
	CourseCellection course = coursesToSelect.get(0);
	//打印输出是否包含该对象
	System.out.println("Get the Course:"+course.name);
	System.out.println("Contains this Course?"+course.name+","+coursesToSelect.contains(course));
	//提示输入课程名称
	System.out.println("请输入课程名称：");
	String name = console.next();
	CourseCellection course2 = new CourseCellection();
	course2.name =name;
	System.out.println("New Course:"+course2.name);
	System.out.println("Contains this Course?"+course2.name+","+coursesToSelect.contains(course2));
	//通过indexOf()方法来获得某元素索引的位置
	if(coursesToSelect.contains(course2))
		System.out.println("课程："+course2.name+"的索引位置为："+coursesToSelect.indexOf(course2));
}
//创建学生对象并选课 
	public void createStudentAndSelectCours(){
	//创建一学生对象
	student = new Student("1","Michal");
	System.out.println("欢迎学生："+student.name+"选课！");
	//创建一个Scanner对象，用来接收从键盘输入的ID
	Scanner console = new Scanner(System.in);
	for(int i=0;i<3;i++){
		System.out.println("请输入课程ID:");
		String courseId = console.next();
		for(CourseCellection cr:coursesToSelect){
			if(cr.id.equals(courseId)){
				student.courses.add(cr);
				
			}
		}
	}
		//st.testForEachForSet(student);
}
//测试Set的contains方法 
	public void testSetContains(){
		System.out.println("Please input the name of course:");
		String name = console.next();
		CourseCellection course = new CourseCellection();
		course.name = name;
		System.out.println("Create new course:"+course.name);
		System.out.println("备选课程中是否包含课程"+course.name+","+student.courses.contains(course));
	}
	public static void main(String[] args){
	SetTest st = new SetTest();
	st.testAdd();
	st.testForEach();
	st.testListContains();
	//st.createStudentAndSelectCours();
	//st.testSetContains();
}
	public void testForEachForSet(Student student){
		System.out.println("共选择了："+student.courses.size()+"门课程");
		for(CourseCellection cr:student.courses){
		System.out.println("选择了课程："+cr.id+":"+cr.name);
	}	
	}
	
	
}

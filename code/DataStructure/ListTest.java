import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

//备选课程类
public class ListTest{
	//用于存放备选课程的List
	public List coursesToSelect;
	public ListTest(){
		this.coursesToSelect = new ArrayList();
	}
	//用于往coursesToSelect中添加备选课程
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

		coursesToSelect.add(cr1);
		CourseCellection temp0 = (CourseCellection)coursesToSelect.get(0);
		System.out.println("添加了课程："+temp2.id+":"+temp2.name);

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
	//取得List中的元素的方法
	public void testGet(){
		int size = coursesToSelect.size();
		System.out.println("有如下课程：");
		for(int i=0;i<size;i++){
			CourseCellection cr = (CourseCellection)coursesToSelect.get(i);
			System.out.println("课程："+cr.id+"："+cr.name);
		}
	}
	//通过迭代器来遍历List
	public void testIterator(){
		Iterator it = coursesToSelect.iterator();
		System.out.println("有如下课程(通过迭代器访问)：");
		while(it.hasNext()){
			CourseCellection cr = (CourseCellection)it.next();
			System.out.println("课程："+cr.id+"："+cr.name);
		}
	}
	//通过for each 方法访问集合元素
	public void testForEach(){
		System.out.println("有如下课程(通过ForEach访问)：");
		if(coursesToSelect!=null && !coursesToSelect.isEmpty()){
			for(Object obj:coursesToSelect){
				CourseCellection cr = (CourseCellection)obj;
				System.out.println("课程："+cr.id+"："+cr.name);
			}
		}
	}
	//修改List中的元素
	public void testModify(){
		coursesToSelect.set(4,new CourseCellection("7","毛概"));
	}
	//删除LIst中的元素
	public void testRemove(){
		//CourseCellection cr = (CourseCellection)coursesToSelect.get(4);
		//System.out.println("我是课程："+cr.id+":"+cr.name+",我即将被删除");
		System.out.println("即将删除4和5位置上的课程！");
		CourseCellection[] courses = {(CourseCellection)coursesToSelect.get(4),(CourseCellection)coursesToSelect.get(5)};
		coursesToSelect.removeAll(Arrays.asList(courses));
		/*coursesToSelect.remove(4);*/
		System.out.println("成功删除课程！");
		testForEach();

	}
	//测试往list中添加其他类型的数据
	public void testType(){
		System.out.println("测试能否往List中添加一些奇怪的东西呢？");
		coursesToSelect.add("我不是课程，我是一个字符串！");
	}

	public static void main(String[] args){
		// ListTest lt = new ListTest();
		// lt.testAdd();
		// lt.testType();
		// lt.testForEach();
		/*lt.testGet();
		lt.testIterator();
		lt.testForEach();
		lt.testModify();
		lt.testForEach();
		lt.testRemove();*/
		test_list();
	}

	public static void test_list(){
		List<Map> param_data = new ArrayList<>(2);
		HashMap<String,Object> param2 = new HashMap<String,Object>();
		param2.put("a","1");
		param2.put("b","2");
		param_data.add(param2);
		param2.clear();
		param2.put("c","1");
		param2.put("d","2");
		param_data.add(param2);
		System.out.println(param_data);
	}
}

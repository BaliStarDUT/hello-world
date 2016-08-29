import java.util.List;
import java.util.ArrayList;
public class TestGeneric{
	//带有泛型的list类型属性
	public List<CourseCellection> courses;
	public TestGeneric(){
		this.courses = new ArrayList<CourseCellection>();
	}
	//测试添加
	public void testAdd(){
		CourseCellection cr1 = new CourseCellection("1","大学语文");
		courses.add(cr1);
		//泛型集合中，不能添加泛型归顶类性之外的东西（可以添加子类型），编译不通过
		//courses.add("看能否添加其他的？");
		CourseCellection cr2 = new CourseCellection("2","Java基础");
		courses.add(cr2);  
	}
	//测试循环遍历
	public void testForEach(){
		for(CourseCellection cr:courses){
			System.out.println(cr.id+":"+cr.name);
		}
	}
	//测试泛型集合可以添加泛型的子类型的对象实例
	public void testChild(){
		ChildCourse ccr = new ChildCourse();
		ccr.id = "3";
		ccr.name = "子类型的实例";
		courses.add(ccr);
	}
	//泛型不能使用基本类型
	public void testBasicType(){
		//编译报错，不能用基本数据类型
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		System.out.println("基本类型必须使用包装类型作为泛型！"+list.get(0));
	}
	public static void main(String[] args){
		TestGeneric tg = new TestGeneric();
		tg.testAdd();
		tg.testForEach();
		tg.testChild();
		tg.testForEach();
		tg.testBasicType();
	}
}

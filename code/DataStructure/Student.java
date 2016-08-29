//package com.yang.test;
/*学生类
*/
import java.util.Set;
import java.util.HashSet;
import java.lang.Comparable;
public class Student implements Comparable<Student>{
	public String id;
	public String name;
	public Set<CourseCellection> courses;
	public Student(String id,String name){
		this.id=id;
		this.name=name;
		this.courses = new HashSet<CourseCellection>();
	}
	@Override
	public int hashCode(){
		final int prime =31;
		int result = 1;
		result = prime * result +((name == null)?0:name.hashCode());
		return result;
	}
		@Override //equals()重写模板
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}
		if(obj == null)
			return false;
		if(!(obj instanceof Student))
			return false;
		Student course = (Student)obj;
		if(this.name == null){
			if(course.name == null){
				return true;
			}else{
				return false;
			}
		}else{
			if(this.name.equals(course.name)){
				return true;
			}else{
				return false;
			}
		}
	}
	@Override
	public int compareTo(Student o){
		return this.id.compareTo(o.id);
	}
	
}

//package com.yang.test;
/*课程类
*/
public class CourseCellection{
	public String id;
	public String name;
	public CourseCellection(){
		
	} 
	public CourseCellection(String id,String name){
		this.id=id;
		this.name=name;
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
		if(!(obj instanceof CourseCellection))
			return false;
		CourseCellection course = (CourseCellection)obj;
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
}


import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.ArrayList;

/*
* javac -cp /Users/aliyun/.m2/repository/org/slf4j/slf4j-api/1.7.7/slf4j-api-1.7.7.jar FileTest.java
* java -cp .:/Users/aliyun/.m2/repository/org/slf4j/slf4j-api/1.7.7/slf4j-api-1.7.7.jar FileTest
*/

//File类的基本用法
class FileTest{
	public static void main(String[] args) throws IOException{
		Logger logger = LoggerFactory.getLogger(FileTest.class);
		logger.info("hello {}",new Date());
		//构造函数
		File file = new File("/Users/air/Pictures/lol/image_champions/");
		System.out.println("This file "+"/Users/air/Pictures/lol/image_champions/"+"exists?:"+file.exists());
		if(!file.exists()){
			file.mkdir(); //file.mkdirs() 生成多级目录
		}else{
		//	file.delete();
		}
		//判断是否是一个目录
		System.out.println("This is a directory?:"+file.isDirectory());
		//判断是否是一个文件
		System.out.println("This is a file?:"+file.isFile());

		File file2 = new File("/home/yangz/yangz.pub");
		System.out.println(file);
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getName());
		System.out.println(file.getParent());
		FileTest.listDirectory(new File("/Users/air/Pictures/lol/image_champions/"));
	}
	//列出指定目录（包括其子目录）下所有文件
	public static List<String> listDirectory(File dir) throws IOException{
		if(!dir.exists()){
			throw new IllegalArgumentException("Directory:"+dir+" doesn`t exists!");
		}
		if(!dir.isDirectory()){
			throw new IllegalArgumentException(dir+" is now a directory!");
		}
		List<String> fileList = new ArrayList<String>();
		System.out.println(dir.list());
		String[] filenames = dir.list();
		for(String string:filenames){
			System.out.println(dir+"/"+string);
		}
		//返回直接子目录的File对象
		File[] files = dir.listFiles();
		if(files!=null && files.length>0){
			for(File file:files){
				if(file.isDirectory()){
					//递归操作
					System.out.println(file.getName()+"-");
					listDirectory(file);
				}else{
					System.out.println(file.getName());
					fileList.add(file.getName());
				}
			}
		}
		return fileList;
	}
}


import java.io.File;
import java.io.IOException;
// import FileTest;

public class FileTest2{
  public static void main(String[] args) throws IOException{
    // Filetest filetest = new FileTest();
    FileTest2.listDirectory(new File("/Users/aliyun/Downloads/image_champions"));
  }

  //列出指定目录（包括其子目录）下所有文件
	public static void listDirectory(File dir) throws IOException{
		if(!dir.exists()){
			throw new IllegalArgumentException("Directory:"+dir+" doesn`t exists!");
		}
		if(!dir.isDirectory()){
			throw new IllegalArgumentException(dir+" is now a directory!");
		}
		//System.out.println(dir.list());
		// String[] filenames = dir.list();
		// for(String string:filenames){
		// 	System.out.println(dir+"/"+string);
		// }
		//返回直接子目录的File对象
		File[] files = dir.listFiles();
		if(files!=null && files.length>0){
		for(File file:files){
        String filename = file.getName();
				if(file.isDirectory()){
					//递归操作
					System.out.println(filename+"-");
					listDirectory(file);
				}else if(FileTest2.filterFile(filename)){
					System.out.println(filename);
				}
			}
		}
	}
  private static boolean filterFile(String fileName){
    if(fileName.contains("Square")){
      return  true;
    }
    return false;
  }
}

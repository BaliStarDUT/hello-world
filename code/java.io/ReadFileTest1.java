import java.io.FileReader;
import java.io.BufferedReader;
import java.util.stream.Stream;

public class ReadFileTest1{
  public static void main(String[] args) throws Exception{
    ReadFileTest1 test= new ReadFileTest1();
    // test.fileReader();
    // test.fileReaderWithBuffer();
    test.fileReaderWithBuffer2();

  }
  private void fileReader() throws Exception{
    FileReader fr = new FileReader("IOUtil.java");
    char[] chars = new char[5];
    while(-1 !=fr.read(chars)){
      System.out.println(chars);
    }
  }
  private void fileReaderWithBuffer() throws Exception{
    FileReader fr = new FileReader("IOUtil.java");
    BufferedReader br = new BufferedReader(fr);
    String line = null;
    while(null!=(line = br.readLine())){
      System.out.println(line);
    }
  }
  private void fileReaderWithBuffer2() throws Exception{
    FileReader fr = new FileReader("IOUtil.java");
    BufferedReader br = new BufferedReader(fr);
    Stream<String> stream = br.lines();
    stream.forEach(line -> System.out.println(line));
    // String line = null;
    // while(null!=(line = br.readLine())){
    //   System.out.println(line);
    // }
  }
}

package serializable;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import reader.ReadLogFile;

class ObjSerializeAndDeserializeTest{
  public static void main(String[] args){
    // serializeObject();
    ReadLogFile object = deserializeObject();
    // object.main();
  }
  private static void serializeObject(){
    ReadLogFile object = new ReadLogFile();
     ObjectOutputStream outputStream = null;
    try {
            outputStream = new ObjectOutputStream(new FileOutputStream("./serialize.txt"));
            outputStream.writeObject(object);
            System.out.println("序列化成功。");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
  }
  private static ReadLogFile deserializeObject(){
    ReadLogFile object =null;
    ObjectInputStream inputStream=null;
    try {
        inputStream=new ObjectInputStream(new FileInputStream("./serialize.txt"));
        try {
            object = (ReadLogFile)inputStream.readObject();
            System.out.println("执行反序列化过程成功。"+object.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    return object;
  }
}

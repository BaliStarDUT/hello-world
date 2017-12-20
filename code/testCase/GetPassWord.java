/**
* 生成不少于6位数的随机密码，必须同时包含大写字母，小写字母，数字，符号的至少三种
*/

import java.util.Random;

public class GetPassWord{
  public static void main(String[] args){
      GetPassWord get = new GetPassWord();
      System.out.println(get.getpw(6));
  }
  private String getpw(int length){
    StringBuilder pw = new StringBuilder();
    Random randomType = new Random();
    for(int i=0;i<length;i++){
        int type = Math.abs(randomType.nextInt()%4)+1;
        // System.out.println((type));
        pw.append(getone(type));
    }
    // System.out.println(pw);
    return new String(pw);
  }
  private String getone(int type){
    Random randomType = new Random();
    switch(type){
      case 1:
        // 大写字母
        int inta = Math.abs(randomType.nextInt()%26)+65;
        byte[] a = {(new Integer(inta).byteValue())};
        return new String(a);
      case 2:
      // 小写字母
        int intb = Math.abs(randomType.nextInt()%26)+97;
        byte[] b = {(new Integer(intb).byteValue())};
        return new String(b);
      case 3:
      // 数字
        int intc = Math.abs(randomType.nextInt()%10);
        return new String(intc+"");
      case 4:
      // 符号
        int intd = Math.abs(randomType.nextInt()%13)+35;
        byte[] d = {(new Integer(intd).byteValue())};
        return new String(d);
    }
    return "";
  }
}

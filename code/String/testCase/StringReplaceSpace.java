/**
* 请实现一个函数，将一个字符串中的空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
* 参考：https://www.nowcoder.com/questionTerminal/4060ac7e3e404ad1a894ef3e17650423
*/

public class StringReplaceSpace{
  public static String replaceSpace(StringBuffer str) {
    StringBuffer result = new StringBuffer();
    char[] chars = new char[str.length()] ;
    str.getChars(0,str.length(),chars,0);
    for(int i=0;i<str.length();i++){
      if(chars[i] == ' '){
        result.append("%20");
        // System.out.println("%20");
      }else{
        result.append(chars[i]);
        // System.out.println(chars[i]);
      }
    }
    return new String(result);
  }

  public static String oneline(StringBuffer str){
     return str.toString().replaceAll(" " , "%20");
  }

  public static void main(String[] args){
      String line = new String("We Are Happy.");
      // line = replaceSpace(new StringBuffer(line));
      line = oneline(new StringBuffer(line));
      System.out.println(line);

      // line
  }
}

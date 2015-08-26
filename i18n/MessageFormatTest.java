//package yang.java.test;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;

/**
* @ClassName: MessageFormatTest
* @Description: MessageFormat类测试
* @author: yangzhen
* @date: 2014-8-29 下午10:29:19
*
*/ 
public class MessageFormatTest {

    public static void main(String[] args) {
        //模式字符串
        String pattern = "On {0}, a hurricance destroyed {1} houses and caused {2} of damage.";
        //实例化MessageFormat对象，并装载相应的模式字符串
        MessageFormat format = new MessageFormat(pattern, Locale.CHINA);
        MessageFormat format2 = new MessageFormat(pattern,Locale.CANADA_FRENCH);
        Object arr[] = {new Date(), 99, 100000000};
        //格式化模式字符串，参数数组中指定占位符相应的替换对象
        String result = format.format(arr);
        String result2 = format2.format(arr);
        System.out.println("以中文环境输出:"+result);
        System.out.println("以法语环境输出:"+result2);
    }
}

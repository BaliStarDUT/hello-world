import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class DubboProviderTest {
    public static  void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                new String[]{"/dubbo/dubbo-demo-provider.xml"}
        );
        applicationContext.start();
        System.in.read();
    }
}

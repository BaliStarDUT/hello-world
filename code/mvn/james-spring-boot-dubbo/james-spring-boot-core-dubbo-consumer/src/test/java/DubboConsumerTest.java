import com.james.springboot.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class DubboConsumerTest {
    public static  void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                new String[]{"/dubbo/dubbo-demo-consumer.xml"}
        );
        applicationContext.start();
        DemoService service = (DemoService) applicationContext.getBean("demoService");
        System.out.println(service.sayHello("James"));
        System.in.read();
    }
}

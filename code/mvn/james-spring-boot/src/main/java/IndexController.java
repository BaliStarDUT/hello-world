import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.awt.Desktop;
import java.net.URI;

@RestController
@EnableAutoConfiguration
@EnableScheduling
public class IndexController {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    @Scheduled(fixedDelay=5000)
    public void  scheduledTask() {
        System.out.println("Hello World!");
        System.out.println("163");
        try {
            if(Desktop.isDesktopSupported()){
                Desktop.getDesktop().browse(new URI("http://music.163.com/outchain/player?type=0&id=71889585&auto=0&height=430"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(IndexController.class, args);
    }

}

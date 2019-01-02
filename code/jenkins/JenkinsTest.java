import com.offbytwo.jenkins.JenkinsServer;
import java.net.URI;

public class JenkinsTest{
    public static void main(String[] args) throws URISyntaxException{
        JenkinsServer jenkinsServer = new JenkinsServer(new URI("http://192.168.26.19:30080","admin","Ab123456"));
        System.out.println(jenkinsServer.getVersion());
        System.out.println(jenkinsServer.isRunning());
    }
}

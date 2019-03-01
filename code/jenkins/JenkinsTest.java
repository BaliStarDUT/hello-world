import com.offbytwo.jenkins.JenkinsServer;
import java.net.URI;
import java.net.URISyntaxException;

// java -cp .:/d/Users/yangzhen/.m2/repository/com/offbytwo/jenkins/jenkins-client/O.3.7/jenkins-client-0.3.7.jar:/d/Users/yangzhen/./repository/org/apache/httpcomponents/httpclient/4.5.2/httpclient-4.5.2.jar:/d/Users/yangzhen/.m2/repository/com/googli ^guava/guava/19.0/guava-19.0.jar:/d/Users/yangzhen/.m2/repository/org/apache/httpcomponents/httpcore/4.4.4/httpcore-4.4.1 .jar:/d/Users/yangzhen/.m2/repository/org/slf4j/slf4j-api/1.7.21/slf4j-api-1.7.21.jar:/d/Users/yangzhen/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.6.5/jackson-databind-2.6.5.jar:/d/Users/yangzhen/.m2/repository/com/fastexml/jackson/core/jackson-core/2.6.5/jackson-core-2.6.5.jar:/d/Users/yangzhen/.m2/repository/com/fasterxml/jackson/core/jadl *n-annotations/2.6.0/jackson-annotations-2.6.0.jar:/d/Users/yangzhen/.m2/repository/commons-logging/commons-logging/1.2/C ^■Dns-logging-1.2.jar:/d/Users/yangzhen/.m2/repository/ch/qos/logback/logback-classic/1.1.7/logback-classic-l.1.7.jar: /t ^Users/yangzhen/.m2/repository/ch/qos/logback/logback-classic/l.1.7/logback-classic-l.1.7,jar:/d/Users/yangzhen/.m2/repository/ch/qos/loqback/logback-core/1.1.7/logback-core-l.1.7.jar JenkinsTest

public class JenkinsTest{
    public static void main(String[] args) throws URISyntaxException{
        JenkinsServer jenkinsServer = new JenkinsServer(new URI("http://192.168.26.19:30080","admin","Ab123456"));
        System.out.println(jenkinsServer.getVersion());
        System.out.println(jenkinsServer.isRunning());
    }
}

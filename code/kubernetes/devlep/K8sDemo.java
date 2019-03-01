import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.models.V1PodList;
import io.kubernetes.client.util.Config;

import java.io.IOException;

//$ java -cp .:/d/Users/yangzhen/.m2/repository/io/kubernetes/client-java-api/3.0.0/client-java-api-3.0.0.jar:/d/Users/yangzhen/.m2/repository/io/kubernetes/client-java/3.0.0/client-java-3.0.0.jar:/d/Users/yangzhen/.m2/repository/com/offbytwo/jenkins/jenkins-client/0.3.7/jenkins-client-0.3.7.jar:/d/Users/yangzhen/.m2/repository/org/apache/httpcomponents/httpclient/4.5.2/httpclient-4.5.2.jar:/d/Users/yangzhen/.m2/repository/com/google/guava/guava/19.0/guava-19.0.jar:/d/Users/yangzhen/.m2/repository/org/apache/httpcomponents/httpcore/4.4.4/httpcore-4.4.4.jar:/d/Users/yangzhen/.m2/repository/org/slf4j/slf4j-api/1.7.21/slf4j-api-1.7.21.jar:/d/Users/yangzhen/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.6.5/jackson-databind-2.6.5.jar:/d/Users/yangzhen/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.6.5/jackson-core-2.6.5.jar:/d/Users/yangzhen/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.6.0/jackson-annotations-2.6.0.jar:/d/Users/yangzhen/.m2/repository/commons-logging/commons-logging/1.2/commons-logging-1.2.jar:/d/Users/yangzhen/.m2/repository/ch/qos/logback/logback-classic/1.1.7/logback-classic-1.1.7.jar:/d/Users/yangzhen/.m2/repository/ch/qos/logback/logback-classic/1.1.7/logback-classic-1.1.7.jar:/d/Users/yangzhen/.m2/repository/ch/qos/logback/logback-core/1.1.7/logback-core-1.1.7.jar:/d/Users/yangzhen/.m2/repository/com/squareup/okio/okio/1.9.0/okio-1.9.0.jar:/d/Users/yangzhen/.m2/repository/com/squareup/okhttp/okhttp/2.7.5/okhttp-2.7.5.jar:/d/Users/yangzhen/.m2/repository/com/google/code/gson/gson/2.6.2/gson-2.6.2.jar:/d//Users/yangzhen/.m2/repository/joda-time/joda-time/2.9.4/joda-time-2.9.4.jar:/d//Users/yangzhen/.m2/repository/org/yaml/snakeyaml/1.18/snakeyaml-1.18.jar:/d/Users/yangzhen/.m2/repository/commons-codec/commons-codec/1.9/commons-codec-1.9.jar:/d/Users/yangzhen/.m2/repository/org/bouncycastle/bcprov-jdk15on/1.52/bcprov-jdk15on-1.52.jar  K8sDemo


public class K8sDemo {
    public static void main(String[] args) throws IOException, ApiException{
        ApiClient client = Config.defaultClient();
        Configuration.setDefaultApiClient(client);

        CoreV1Api api = new CoreV1Api();
        V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
        for (V1Pod item : list.getItems()) {
            System.out.println(item.getMetadata().getName());
        }
    }
}

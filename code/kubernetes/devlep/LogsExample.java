import com.google.common.io.ByteStreams;
import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.PodLogs;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.util.Config;
import java.io.IOException;
import java.io.InputStream;

/**
 * A simple example of how to use the Java API
 *
 * <p>Easiest way to run this: mvn exec:java
 * -Dexec.mainClass="io.kubernetes.client.examples.LogsExample"
 *
 * <p>From inside $REPO_DIR/examples
 */
public class LogsExample {
  public static void main(String[] args) throws IOException, ApiException, InterruptedException {
    ApiClient client = Config.defaultClient();
    Configuration.setDefaultApiClient(client);
    CoreV1Api coreApi = new CoreV1Api(client);

    PodLogs logs = new PodLogs();
    V1Pod pod =
        coreApi
            .listNamespacedPod("default", null, "false", null, null, null, null, null, null, null)
            .getItems()
            .get(0);

    InputStream is = logs.streamNamespacedPodLog(pod);
    ByteStreams.copy(is, System.out);
  }
}

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

// java -cp .:/Users/aliyun/.m2/repository/org/apache/httpcomponents/httpclient/4.5.2/httpclient-4.5.2.jar:/Users/aliyun/.m2/repository/org/apache/httpcomponents/httpcore/4.4.5/httpcore-4.4.5.jar:/Users/aliyun/.m2/repository/commons-logging/commons-logging/1.0.4/commons-logging-1.0.4.jar  HttpClientTest

public class HttpClientTest {
  public static void main(String[] args) throws IOException{
      String url = "http://www.google.cn/";
       System.out.println(url);
       System.out.println("Visit google using Apache HttpComponents Client 4.0：");
       List<BasicNameValuePair> data4 = new ArrayList<BasicNameValuePair>();
       data4.add(new BasicNameValuePair("username", "testuser"));
       data4.add(new BasicNameValuePair("password", "testpassword"));
       System.out.println(post4(url, data4));
  }
  /** 使用Apache HttpComponents Client 4.0，POST方法访问网页 */
    private static String post4(String url, List<? extends NameValuePair> data) throws IOException
             {
        HttpClient client = new DefaultHttpClient();
        HttpPost httpost = new HttpPost(url);
        httpost.setEntity(new UrlEncodedFormEntity(data, HTTP.UTF_8));
        try {
            HttpResponse response = client.execute(httpost);
            HttpEntity entity = response.getEntity();
            System.out.println("<< Response: " + response.getStatusLine());
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
            return null;
        } finally {
            client.getConnectionManager().shutdown();
        }
    }
}

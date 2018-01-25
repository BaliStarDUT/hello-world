import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.BlockingArrayQueue;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.http.HttpGenerator;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import java.util.Date;
// javac -cp ~/.m2/repository/org/eclipse/jetty/jetty-server/9.2.7.v20150116/jetty-server-9.2.7.v20150116.jar:~/.m2/repository/org/eclipse/jetty/jetty-util/9.2.7.v20150116/jetty-util-9.2.7.v20150116.jar:/Users/air/.m2/repository/org/eclipse/jetty/aggregate/jetty-all/9.1.5.v20140505/jetty-all-9.1.5.v20140505.jar JettyTest.java
// java -cp .:/Users/air/.m2/repository/org/eclipse/jetty/aggregate/jetty-all/9.1.5.v20140505/jetty-all-9.1.5.v20140505.jar:~/.m2/repository/javax/servlet/javax.servlet-api/3.1.0/javax.servlet-api-3.1.0.jar:/Users/air/.m2/repository/javax/servlet/javax.servlet-api/3.1.0/javax.servlet-api-3.1.0.jar JettyTest
public class JettyTest extends AbstractHandler{
    @Override
    public void handle( String target,
                        Request baseRequest,
                        HttpServletRequest request,
                        HttpServletResponse response ) throws IOException,
                                                      ServletException{
        // Declare response encoding and types
        response.setContentType("text/html; charset=utf-8");

        // Declare response status code
        response.setStatus(HttpServletResponse.SC_OK);

        // Write back response
        response.getWriter().println("<h1>Hello World"+new Date()+"</h1>");

        // Inform jetty that this request has now been handled
        baseRequest.setHandled(true);
    }

  public static void main(String[] args) throws Exception{
    QueuedThreadPool pool = new QueuedThreadPool(100, 10, 60,
                new BlockingArrayQueue<Runnable>(10,10, 50));
    Server server = new Server(pool);
    HttpConfiguration httpConfig = new HttpConfiguration();
    httpConfig.setSendServerVersion(true);
    httpConfig.setSendXPoweredBy(true);

    ServerConnector http = new ServerConnector(server, new HttpConnectionFactory(httpConfig));
    http.setIdleTimeout(3000);//设置连接超时时间
    http.setPort(8888);
    server.addConnector(http);
    server.setHandler(new JettyTest());

    // ServletContextHandler root = new ServletContextHandler(null,"/",ServletContextHandler.SESSIONS);
    // root.addServlet(new ServletHolder(),"/");
    // server.setHandler(root);

    // server.setServer()
    server.start();
    HttpGenerator.setJettyVersion("yang");//由于该值是启动前被写死的，只能启动后修改，可以有效果
    server.join();
  }
}

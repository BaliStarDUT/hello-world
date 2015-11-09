package yang.servlet.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fusesource.mqtt.client.MQTT;

import yang.mqtt.Listener;
import yang.mqtt.TimerPublisher;

/**
 * Servlet implementation class MqttServlet
 */
@WebServlet("/servlet/MqttServlet")
public class MqttServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MqttServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("我是doGet()方法！用来处理GET请求");
		response.setContentType("text/html;charset=GB2312");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<BODY>");
		//out.println("这是Servlet的例子");
		out.println("<h1 align=\"center\">" +"Using GET Method to Read Form Data" + "</h1>\n" +
                "<ul>\n" +
                "  <li><b>Clientname</b>："
                + request.getParameter("Clientname") + "\n" +
                "  <li><b>Publictopic</b>："
                + request.getParameter("Publictopic") + "\n" +
                "  <li><b>Size</b>："
                + request.getParameter("Size") + "\n" +
                "  <li><b>Times</b>："
                + request.getParameter("Times") + "\n" +
                "  <li><b>Host</b>："
                + request.getParameter("Host") + "\n" +
                "  <li><b>Username</b>："
                + request.getParameter("Username") + "\n" +
                "  <li><b>Password</b>："
                + request.getParameter("Password") + "\n" +
                
                "</ul>\n" );
		
		

		System.out.println("Publisher Thread Run...");
		//TimerPublisher pub = new TimerPublisher();
		String name = request.getParameter("Clientname");
		String host = request.getParameter("Host");//"tcp://10.4.45.133:61613";//tcp://10.4.45.133:61613,tcp://10.4.44.210:8020
		String username = request.getParameter("Username");//"admin";
		String password = request.getParameter("Password");//"password";
		String publictopic = request.getParameter("Publictopic");//"Topics/htjs/serverToPhone";
		try {
			Calendar a = Calendar.getInstance();
			System.out.println(a.getTimeInMillis());
			//MQTT mqtt = new MQTT();
			//Listener list = new Listener(name,host,username,password,publictopic);

			TimerPublisher pub = new TimerPublisher(name,host,username,password,publictopic,5,1);
		} catch (Exception e) {
			System.out.println("Publisher Run error!");
			e.printStackTrace();
		}
		out.println("<p>运行timerPublisher结束</p>");
		out.println("</BODY>");
		out.println("</HTML>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

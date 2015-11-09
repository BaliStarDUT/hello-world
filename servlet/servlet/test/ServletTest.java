package yang.servlet.test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletTest
 */
@WebServlet("/ServletTest")
public class ServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*
	 * 
	 
	private ServletConfig config;
	public void init(ServletConfig config){
		this.config=config;
	}*/
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String paramVal = this.config.getInitParameter("name");
		//response.getWriter().println(paramVal);//获取web应用的初始化参数
		/*
		 * 设置refresh响应头，让浏览器每1秒刷新一次
		 */
		response.setHeader("refresh", "1");
		
		response.getWriter().print("<hr />");
		
		/*Enumeration<String> e = config.getInitParameterNames();
		while(e.hasMoreElements()){
			String name = e.nextElement();
			String value = config.getInitParameter(name);
			response.getWriter().print(name+"="+value+"<br />");
		}*/
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method<br />");
		out.println("Today is ");
		Date date= new Date();
		DateFormat dateForm = DateFormat.getDateTimeInstance();
		out.println(dateForm.format(date));
		
		out.println("<br />another time  is: ");
		
		Date date2 = new Date();
		Calendar cl= Calendar.getInstance();
		cl.setTime(date);
		cl.add(Calendar.HOUR_OF_DAY, -50);
		out.println(cl.getTime()+"<br />");
		long a = (date.getTime()-cl.getTimeInMillis())/1000;
		float b = (float)(a/3600);
		if(b>24)
		{
			int c = (int)b/24;
			out.println("day:"+c+"<br />");
		}else if(b<1){
			out.println("minutes"+b*60);
		}else{
			int d = (int)b;
			out.println("hours"+d+"<br />");
		}
		//out.println(date2.toString()+"<br />");
		date2.setMinutes(40);
		date2.setHours(13);
		out.println(date2.toString()+"<br />");
		out.println("day:"+date.getDate());
		out.println("hour :"+date.getHours()+"<br />");
		out.println("day:"+date2.getDate());
		out.println("hour :"+date2.getHours()+"<br />");
		int day= date.getDate()-date2.getDate();
		if(day>0)
		{
			out.println(day+"days before");
		}else{
			int hour= date.getHours()-date2.getHours();
			if(hour>0){
			out.println(hour+"hours before");
			}else{
			int minute = date.getMinutes()-date2.getMinutes();
			out.println(minute+"minutes before");
			}
		}
		long timebetween = date.getTime()-date2.getTime();
		out.println(timebetween+"<br />");
		date2.setTime(timebetween);
		out.println(date2.toString()+"<br />");
		out.println(date2.getMinutes()+"<br />");
		
		
		
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
		
		try {
			//outputChineseByOutputStream(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//使用OutputStream流输出中文
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
		//doGet(request, response);
	}
	/*
	 * 使用outpurstream流输出中文
	 */
	public void outputChineseByOutputStream(HttpServletResponse response) throws Exception{
		 /**
		  * 用OutputStream输出中文注意问题：
		          * 在服务器端，数据是以哪个码表输出的，那么就要控制客户端浏览器以相应的码表打开，
		28          * 比如：outputStream.write("中国".getBytes("UTF-8"));//使用OutputStream流向客户端浏览器输出中文，以UTF-8的编码进行输出
		29          * 此时就要控制客户端浏览器以UTF-8的编码打开，否则显示的时候就会出现中文乱码，那么在服务器端如何控制客户端浏览器以以UTF-8的编码显示数据呢？
		30          * 可以通过设置响应头控制浏览器的行为，例如：
		31          * response.setHeader("content-type", "text/html;charset=UTF-8");//通过设置响应头控制浏览器以UTF-8的编码显示数据
		32          */
		String data = "中国";
		OutputStream outputStream = response.getOutputStream();//获取outputstream输出流
		response.setHeader("content-type", "text/html;charset=UTF-8");//通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
		
		
		/**
		37          * data.getBytes()是一个将字符转换成字节数组的过程，这个过程中一定会去查码表，
		38          * 如果是中文的操作系统环境，默认就是查找查GB2312的码表，
		39          * 将字符转换成字节数组的过程就是将中文字符转换成GB2312的码表上对应的数字
		40          * 比如： "中"在GB2312的码表上对应的数字是98
		41          *         "国"在GB2312的码表上对应的数字是99
		42          */
	       /**
		44          * getBytes()方法如果不带参数，那么就会根据操作系统的语言环境来选择转换码表，如果是中文操作系统，那么就使用GB2312的码表
		45          */
		
		
		byte[] dataByteArr = data.getBytes("UTF-8");//将字符转换成字节数组，指定以UTF-8编码进行转换
		outputStream.write(dataByteArr);
	}

}

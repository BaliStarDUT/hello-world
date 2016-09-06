package yang.servlet.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;


/**
 * Servlet implementation class MyServlet
 */
@WebServlet(name = "MyServlet",urlPatterns= {"/my"})
public class MyServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private transient ServletConfig servletConfig;
	

	public void init(ServletConfig config) throws ServletException {
			this.servletConfig = config;
		}


	public ServletConfig getServletConfig() {
		return servletConfig;
	}

	public String getServletInfo() {
		return "My Servlet"; 
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		String servletName = servletConfig.getServletName();
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print("<html><head></head><body>Hello from "+servletName+"</body></html>");
	}

	@Override
	public void destroy() {
	}
}

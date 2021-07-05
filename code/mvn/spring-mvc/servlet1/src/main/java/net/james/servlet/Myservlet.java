package net.james.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * author: yang
 * datetime: 2021/6/23 11:51
 */

public class Myservlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(Myservlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        String requestURI = req.getRequestURI();
        String servletPath = req.getServletPath();
        log(method+"-"+requestURI+"-"+servletPath);
        if(method.equals("add")){
            req.getSession().setAttribute("msg","执行了add方法");
        }
        if(method.equals("delete")){
            req.getSession().setAttribute("msg","执行了delete方法");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req,resp);

//        resp.sendRedirect("/");
    }
}

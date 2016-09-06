package yang.servlet.test;
import java.io.IOException;
import java.text.MessageFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class CheckServlet
 */
@WebServlet("/DoFormServlet")
public class DoFormServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //客户端是以UTF-8编码提交表单数据的，所以需要设置服务器端以UTF-8的编码进行接收，否则对于中文数据就会产生乱码
        request.setCharacterEncoding("UTF-8");
        /**
         * 编&nbsp;&nbsp;号(文本框)：
           <input type="text" name="userid" value="NO." size="2" maxlength="2">
         */
        String userid = request.getParameter("userid");//获取填写的编号，userid是文本框的名字，<input type="text" name="userid">
        /**
         * 用户名(文本框)：<input type="text" name="username" value="请输入用户名">
         */
        String username = request.getParameter("username");//获取填写的用户名
        /**
         * 密&nbsp;&nbsp;码(密码框)：<input type="password" name="userpass" value="请输入密码">
         */
        String userpass = request.getParameter("userpass");//获取填写的密码
        String sex = request.getParameter("sex");//获取选中的性别
        String dept = request.getParameter("dept");//获取选中的部门
        //获取选中的兴趣，因为可以选中多个值，所以获取到的值是一个字符串数组，因此需要使用getParameterValues方法来获取
        String[] insts = request.getParameterValues("inst");
        String note = request.getParameter("note");//获取填写的说明信息
        String hiddenField = request.getParameter("hiddenField");//获取隐藏域的内容
        
        String instStr="";
        /**
         * 获取数组数据的技巧，可以避免insts数组为null时引发的空指针异常错误！
         */
        for (int i = 0; insts!=null && i < insts.length; i++) {
            if (i == insts.length-1) {
                instStr+=insts[i];
            }else {
                instStr+=insts[i]+",";
            }
        }
        
        String htmlStr = "<table>" +
                            "<tr><td>填写的编号：</td><td>{0}</td></tr>" +
                            "<tr><td>填写的用户名：</td><td>{1}</td></tr>" +
                            "<tr><td>填写的密码：</td><td>{2}</td></tr>" +
                            "<tr><td>选中的性别：</td><td>{3}</td></tr>" +
                            "<tr><td>选中的部门：</td><td>{4}</td></tr>" +
                            "<tr><td>选中的兴趣：</td><td>{5}</td></tr>" +
                            "<tr><td>填写的说明：</td><td>{6}</td></tr>" +
                            "<tr><td>隐藏域的内容：</td><td>{7}</td></tr>" +
                        "</table>";
        htmlStr = MessageFormat.format(htmlStr, userid,username,userpass,sex,dept,instStr,note,hiddenField);
        
        response.setCharacterEncoding("UTF-8");//设置服务器端以UTF-8编码输出数据到客户端
        response.setContentType("text/html;charset=UTF-8");//设置客户端浏览器以UTF-8编码解析数据
        response.getWriter().write(htmlStr);//输出htmlStr里面的内容到客户端浏览器显示
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
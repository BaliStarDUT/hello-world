package demo.freemarker.servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import demo.freemarker.entity.Dog;
import demo.freemarker.entity.Store;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FreeMarkerServlet extends HttpServlet{

    /**
     *
     */
    private static final long serialVersionUID = -7620561776035469936L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        /*数据模型data-model */
        Dog d1 = new Dog("d1", "小花狗", "德国牧羊犬");
        Dog d2 = new Dog("d2", "大黄狗", "哈士奇");
        Dog d3 = new Dog("d3", "小咖啡", "泰迪");
        List<Dog> dogs = new ArrayList<Dog>();
        dogs.add(d1);
        dogs.add(d2);
        dogs.add(d3);
        Store store = new Store("st1", "宠物专卖北京店", "北京市海淀区",dogs);

        try {
            /*配置信息*/
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
            cfg.setDirectoryForTemplateLoading(new File(req.getSession().getServletContext()
                    .getRealPath("/freemarker")));//文件目录路径
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            /*模板*/
            Template temp = cfg.getTemplate("storetemp.ftl");
            /*数据模型与模板组合*/
            Writer out = new OutputStreamWriter(System.out);
            temp.process(store, out);
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TemplateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doPost(req, resp);
    }
}

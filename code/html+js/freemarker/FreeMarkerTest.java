/**
 * @author air
 * @date 6/21/18 10:35
 */

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.b3log.latke.user.GeneralUser;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreeMarkerTest {
    public static void main(String[] args){
        Configuration config = new Configuration();
//        config.setTemplateLoader();
        String path = new File("/Users/air/Documents/GitHub/solo/src/test/java/").getAbsolutePath();
        System.out.println(path);
        try {
            config.setDirectoryForTemplateLoading(new File(path));
            config.setObjectWrapper(new DefaultObjectWrapper());
            Template template = config.getTemplate("test.ftl","UTF-8");
            Map root = new HashMap();
            List<GeneralUser> users = new ArrayList<GeneralUser>();
            GeneralUser u1 = new GeneralUser();
            u1.setId("1");
            u1.setNickname("Eric");
            users.add(u1);
            GeneralUser u2 = new GeneralUser();
            u2.setId("2");
            u2.setNickname("Rex");
            GeneralUser u3 = new GeneralUser();
            u3.setId("3");
            u3.setNickname("Martin");
            users.add(u2);
            users.add(u3);
            root.put("userList",users);
            Map product = new HashMap();
            root.put("lastProduct",product);
            product.put("url","http://www.google.com");
            product.put("name", "green hose");
            File file = new File(path +File.separator+ "test.html");
            if(!file.exists()){
                file.createNewFile();
            }
            Writer out = new BufferedWriter(new FileWriter(file));
            template.process(root,out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}

package top.hunaner;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Constants;
import com.jfinal.config.Routes;
import com.jfinal.config.Plugins;
import com.jfinal.config.Interceptors;
import com.jfinal.config.Handlers;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import top.hunaner.interceptor.LogInterceptor;
import top.hunaner.controller.HelloController;

public class JfinalConfig extends JFinalConfig{

    public void configConstant(Constants me){
        me.setDevMode(true);
        me.setViewType(ViewType.JSP);
    }
    public void configRoute(Routes me){
        me.add("/hello", HelloController.class);
    }
	public void configPlugin(Plugins me) {

    }
	public void configInterceptor(Interceptors me) {
        me.add(new LogInterceptor());
    }
	public void configHandler(Handlers me) {

    }
    public void configEngine(Engine engine){

    }
    public void afterJFinalStart(){
        System.out.println("afterJFinalStart--------------");
    }
    public void beforeJFinalStop(){
        System.out.println("beforeJFinalStop---------------");
    }

}

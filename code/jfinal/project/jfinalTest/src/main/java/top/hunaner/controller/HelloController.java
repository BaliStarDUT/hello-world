package top.hunaner.controller;

import java.util.Date;
import com.jfinal.core.Controller;

public class HelloController extends Controller {
	public void index() {
		System.out.println("index");
        renderText("此方法是一个action");
	}
	public void test() {
        renderHtml("renderHtml"+new Date());
	}
}

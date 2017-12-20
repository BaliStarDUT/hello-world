package top.hunaner.controller;

import com.jfinal.core.Controller;

public class HelloController extends Controller {
	public void index() {
        renderText("此方法是一个action");
	}
	public void test() {
        renderText("此方法是一个action");
	}
}

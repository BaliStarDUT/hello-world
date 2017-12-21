package top.hunaner.controller;

import com.jfinal.core.Controller;
import com.jfinal.core.ActionKey;

public class LoginController extends Controller{
    @ActionKey("/login")
    public void login(){
        render("login.html");
    }
}

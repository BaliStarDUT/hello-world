package net.james.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/index")
public class IndexController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.addObject("me","index");
        modelAndView.setViewName("index");
        return modelAndView;
    }
}

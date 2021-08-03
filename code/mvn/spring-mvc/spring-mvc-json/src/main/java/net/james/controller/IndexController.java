package net.james.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/index")
public class IndexController  {
//implements ApplicationContextAware
    @Autowired
    ApplicationContext applicationContext;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    @ResponseBody
    public String index() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String string = mapper.writeValueAsString(this.getClass());
        return string;
    }

//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }
}


package com.james.springboot.controller;

import com.james.springboot.dao.bean.LolHeros;
import com.james.springboot.service.LolHerosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    LolHerosService lolHerosService;

    @RequestMapping("/hello")
    String home() {
        return "Hello World!"+"你好世界";
    }

    @ResponseBody
    @RequestMapping(value = "/",method = RequestMethod.GET)
    List<LolHeros> getlolHeros(@RequestParam(value = "name_cn") String name_cn,
                               @RequestParam(value = "name_en") String name_en) {
        return lolHerosService.getHeros(name_cn,name_en);
    }

    @ResponseBody
    @RequestMapping(value = "/",method = RequestMethod.POST)
    int createlolHeros(@ModelAttribute LolHeros lolHero) {
        return lolHerosService.insertLolHero(lolHero);
    }

}

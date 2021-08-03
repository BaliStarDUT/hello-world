package net.james.boot.controller;

import net.james.boot.dao.bean.LolHeros;
import net.james.boot.service.LolHerosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(value = "/hero")
public class HeroController {

    @Autowired
    LolHerosService lolHerosService;

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

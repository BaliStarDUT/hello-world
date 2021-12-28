package com.james.springboot.web;

import com.james.springboot.dao.bean.LolHeros;
import com.james.springboot.service.LolHerosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/heros_view",method = RequestMethod.GET)
    ModelAndView getlolHeros_view(ModelAndView modelAndView) {
        List<LolHeros>  heros = lolHerosService.getHeros();
        modelAndView.addObject("heros",heros);
        modelAndView.addObject("lolheroForm",new LolHeros());
        modelAndView.addObject("title","Legue of Legent");
        modelAndView.setViewName("heros");

        return modelAndView;
    }

    @RequestMapping(value = "/heros_view2",method = RequestMethod.GET)
    String getlolHeros_view2(Model model) {
        List<LolHeros>  heros = lolHerosService.getHeros();
        model.addAttribute("heros",heros);
        model.addAttribute("lolHero",new LolHeros());
        model.addAttribute("title","Legue of Legent");
        return "heros";
    }

    @ResponseBody
    @RequestMapping(value = "/hero",method = RequestMethod.POST)
    int createlolHeros(@ModelAttribute("lolHero") LolHeros lolHero) {
        return lolHerosService.insertLolHero(lolHero);
    }


    @RequestMapping(value="/lolheros/new", method=RequestMethod.POST)
    public String saveHeroInfo(@ModelAttribute(name = "lolHero") LolHeros lolHero,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "heros";
        }else{
            int count = this.lolHerosService.insertLolHero(lolHero);
        }
        List<LolHeros> herosList = (List<LolHeros>) this.lolHerosService.getHeros();
        model.addAttribute("herosList",herosList);
        model.addAttribute("msg","获取成功");
        return "heros";
    }
}

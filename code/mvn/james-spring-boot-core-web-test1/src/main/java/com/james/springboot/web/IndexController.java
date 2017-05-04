package com.james.springboot.web;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.net.URI;

@Controller
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value="根目录", notes="网站根目录")
    @ApiImplicitParam(name = "map", value = "传递到响应的模型", required = true, dataType = "ModelMap")
    @RequestMapping(value = "/",method= RequestMethod.GET)
    public String index(ModelMap map) {
        map.addAttribute("title","Legue Of Legends");
        return "index";
    }
    @Scheduled(fixedDelay=50000)
    public void  scheduledTask() {
        logger.debug("Logger Level ：DEBUG "+"Hello World!");
        logger.info("Logger Level ：INFO "+"163");
        try {
            if(Desktop.isDesktopSupported()){
                logger.warn("Logger Level ：WARN "+"Open browser");
                Desktop.getDesktop().browse(new URI("http://music.163.com/outchain/player?type=0&id=71889585&auto=0&height=430"));
            }
        } catch (Exception e) {
            logger.error("Logger Level ：ERROR "+e.getMessage());
            e.printStackTrace();
        }
    }
}

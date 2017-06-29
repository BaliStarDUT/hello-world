package com.james.cloud.web;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller(value = "index1Controller")
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value="根目录", notes="网站根目录")
    @ApiImplicitParam(name = "map", value = "传递到响应的模型", required = true, dataType = "ModelMap")
    @RequestMapping(value = "/hahaha",method= RequestMethod.GET)
    public String indexx(ModelMap map) {
        map.addAttribute("title","Legue Of Legends");
        return "index";
    }
}

package com.james.springboot.controller;

import com.james.springboot.dao.bean.Busi;
import com.james.springboot.service.OutSideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * author: yang
 * datetime: 2020/11/20 14:26
 */

@RestController
@RequestMapping("/outside")
public class OutsideController {

    private static Logger logger = LoggerFactory.getLogger(OutsideController.class);

    @Autowired
    OutSideService outSideService;

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    String addToOutside(){
        outSideService.addtoABC();
        return new Date().toString();
    }

    @RequestMapping(value = "/abc/busi",method = RequestMethod.GET)
    List<Busi> get_busi(){
        List<Busi> busi = outSideService.getBusi();
        return busi;
    }


    @RequestMapping(value = "/abc/mysql",method = RequestMethod.GET)
    String get_mysql_cluster(){
        return "mysql_list";
    }
}

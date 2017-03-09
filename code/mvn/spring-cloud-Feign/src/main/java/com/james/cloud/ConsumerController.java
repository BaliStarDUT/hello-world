package com.james.cloud;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2017/3/9 0009.
 */
@RestController
public class ConsumerController  {
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    ComputeClient computeClient;

    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public Integer  add(@RequestParam Integer a, @RequestParam Integer b) {
        return computeClient.add(a, b);
    }
}

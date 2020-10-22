package com.james.springboot.service.impl;

import com.james.springboot.dao.LolHeros;
import com.james.springboot.dao.LolHerosExample;
import com.james.springboot.dao.LolHerosMapper;
import com.james.springboot.service.LolHerosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author: yang
 * datetime: 2020/10/21 17:39
 */
@Service
public class LolHerosServiceImpl implements LolHerosService {

    private static Logger logger = LoggerFactory.getLogger(LolHerosServiceImpl.class);

    @Autowired
    LolHerosMapper lolHerosMapper;

    @Override
    public List<LolHeros>  getHeros(String name_cn,String name_en) {
        LolHerosExample example = new LolHerosExample();
        LolHerosExample.Criteria criteria = example.createCriteria();
        criteria.andNameCnEqualTo(name_cn);
        criteria.andNameCnEqualTo(name_en);
        List<LolHeros> lolHeros = lolHerosMapper.selectByExample(example);
        return lolHeros;
    }

    @Override
    public int insertLolHero(LolHeros hero) {
       return lolHerosMapper.insert(hero);
    }
}

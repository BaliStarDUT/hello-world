package net.james.service.impl;

import com.github.pagehelper.PageHelper;
import net.james.dao.LolHeros;
import net.james.dao.LolHerosExample;
import net.james.dao.LolHerosMapper;
import net.james.service.LolHerosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public List<LolHeros>  getHeros(String name_cn, String name_en) {
        LolHerosExample example = new LolHerosExample();
        LolHerosExample.Criteria criteria = example.createCriteria();
        criteria.andNameCnEqualTo(name_cn);
        criteria.andNameCnEqualTo(name_en);
        List<LolHeros> lolHeros = lolHerosMapper.selectByExample(example);
        return lolHeros;
    }

    @Override
    public List<LolHeros>  getHeros() {
        LolHerosExample example = new LolHerosExample();
        LolHerosExample.Criteria criteria = example.createCriteria();

//        PageHelper.startPage(1, 2);
        List<LolHeros> lolHeros = lolHerosMapper.selectByExample(example);
        return lolHeros;
    }

    @Transactional
    @Override
    public int insertLolHero(LolHeros hero) {
       return lolHerosMapper.insert(hero);
    }
}

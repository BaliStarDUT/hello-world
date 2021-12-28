package com.james.springboot.service;


import com.james.springboot.dao.bean.LolHeros;

import java.util.List;

/**
 * author: yang
 * datetime: 2020/10/21 17:38
 */

public interface LolHerosService {

    List<LolHeros> getHeros(String name_cn, String name_en);

    List<LolHeros>  getHeros();

    int insertLolHero(LolHeros hero);
}

package net.james.boot.service;

import net.james.boot.dao.bean.LolHeros;

import java.util.List;

/**
 * author: yang
 * datetime: 2020/10/21 17:38
 */

public interface LolHerosService {

    List<LolHeros> getHeros(String name_cn, String name_en);

    int insertLolHero(LolHeros hero);
}

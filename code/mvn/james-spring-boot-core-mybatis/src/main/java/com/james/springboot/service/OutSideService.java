package com.james.springboot.service;

import com.james.springboot.dao.bean.Busi;

import java.util.List;

/**
 * author: yang
 * datetime: 2020/11/20 14:29
 */

public interface OutSideService {

    public String addtoABC();

    List<Busi> getBusi();

}

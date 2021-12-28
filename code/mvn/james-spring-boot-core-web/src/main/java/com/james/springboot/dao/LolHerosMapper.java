package com.james.springboot.dao;

import com.james.springboot.dao.bean.LolHeros;
import com.james.springboot.dao.bean.LolHerosExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LolHerosMapper {
    long countByExample(LolHerosExample example);

    int deleteByExample(LolHerosExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LolHeros record);

    int insertSelective(LolHeros record);

    List<LolHeros> selectByExampleWithBLOBs(LolHerosExample example);

    List<LolHeros> selectByExample(LolHerosExample example);

    LolHeros selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LolHeros record, @Param("example") LolHerosExample example);

    int updateByExampleWithBLOBs(@Param("record") LolHeros record, @Param("example") LolHerosExample example);

    int updateByExample(@Param("record") LolHeros record, @Param("example") LolHerosExample example);

    int updateByPrimaryKeySelective(LolHeros record);

    int updateByPrimaryKeyWithBLOBs(LolHeros record);

    int updateByPrimaryKey(LolHeros record);
}
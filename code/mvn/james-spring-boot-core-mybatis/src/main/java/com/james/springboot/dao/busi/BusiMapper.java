package com.james.springboot.dao.busi;

import com.james.springboot.dao.bean.Busi;
import com.james.springboot.dao.bean.BusiExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusiMapper {
    long countByExample(BusiExample example);

    int deleteByExample(BusiExample example);

    int deleteByPrimaryKey(String busiId);

    int insert(Busi record);

    int insertSelective(Busi record);

    List<Busi> selectByExample(BusiExample example);

    Busi selectByPrimaryKey(String busiId);

    int updateByExampleSelective(@Param("record") Busi record, @Param("example") BusiExample example);

    int updateByExample(@Param("record") Busi record, @Param("example") BusiExample example);

    int updateByPrimaryKeySelective(Busi record);

    int updateByPrimaryKey(Busi record);
}
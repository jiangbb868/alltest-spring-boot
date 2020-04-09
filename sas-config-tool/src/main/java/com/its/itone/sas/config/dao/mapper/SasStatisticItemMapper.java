package com.its.itone.sas.config.dao.mapper;

import com.its.itone.sas.config.dao.pojo.SasStatisticItem;
import com.its.itone.sas.config.dao.pojo.SasStatisticItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SasStatisticItemMapper {
    int countByExample(SasStatisticItemExample example);

    int deleteByExample(SasStatisticItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(SasStatisticItem record);

    int insertSelective(SasStatisticItem record);

    List<SasStatisticItem> selectByExample(SasStatisticItemExample example);

    SasStatisticItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SasStatisticItem record, @Param("example") SasStatisticItemExample example);

    int updateByExample(@Param("record") SasStatisticItem record, @Param("example") SasStatisticItemExample example);

    int updateByPrimaryKeySelective(SasStatisticItem record);

    int updateByPrimaryKey(SasStatisticItem record);
}
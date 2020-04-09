package com.its.itone.sas.config.dao.mapper;

import com.its.itone.sas.config.dao.pojo.SasStatisticIndicator;
import com.its.itone.sas.config.dao.pojo.SasStatisticIndicatorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SasStatisticIndicatorMapper {
    int countByExample(SasStatisticIndicatorExample example);

    int deleteByExample(SasStatisticIndicatorExample example);

    int deleteByPrimaryKey(String id);

    int insert(SasStatisticIndicator record);

    int insertSelective(SasStatisticIndicator record);

    List<SasStatisticIndicator> selectByExample(SasStatisticIndicatorExample example);

    SasStatisticIndicator selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SasStatisticIndicator record, @Param("example") SasStatisticIndicatorExample example);

    int updateByExample(@Param("record") SasStatisticIndicator record, @Param("example") SasStatisticIndicatorExample example);

    int updateByPrimaryKeySelective(SasStatisticIndicator record);

    int updateByPrimaryKey(SasStatisticIndicator record);
}
package com.its.itone.sas.config.dao.mapper;

import com.its.itone.sas.config.dao.pojo.SasStatisticItemTime;
import com.its.itone.sas.config.dao.pojo.SasStatisticItemTimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SasStatisticItemTimeMapper {
    int countByExample(SasStatisticItemTimeExample example);

    int deleteByExample(SasStatisticItemTimeExample example);

    int deleteByPrimaryKey(String id);

    int insert(SasStatisticItemTime record);

    int insertSelective(SasStatisticItemTime record);

    List<SasStatisticItemTime> selectByExample(SasStatisticItemTimeExample example);

    SasStatisticItemTime selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SasStatisticItemTime record, @Param("example") SasStatisticItemTimeExample example);

    int updateByExample(@Param("record") SasStatisticItemTime record, @Param("example") SasStatisticItemTimeExample example);

    int updateByPrimaryKeySelective(SasStatisticItemTime record);

    int updateByPrimaryKey(SasStatisticItemTime record);
}
package com.its.itone.sas.config.dao.mapper;

import com.its.itone.sas.config.dao.pojo.SasStatisticObject;
import com.its.itone.sas.config.dao.pojo.SasStatisticObjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SasStatisticObjectMapper {
    int countByExample(SasStatisticObjectExample example);

    int deleteByExample(SasStatisticObjectExample example);

    int deleteByPrimaryKey(String id);

    int insert(SasStatisticObject record);

    int insertSelective(SasStatisticObject record);

    List<SasStatisticObject> selectByExample(SasStatisticObjectExample example);

    SasStatisticObject selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SasStatisticObject record, @Param("example") SasStatisticObjectExample example);

    int updateByExample(@Param("record") SasStatisticObject record, @Param("example") SasStatisticObjectExample example);

    int updateByPrimaryKeySelective(SasStatisticObject record);

    int updateByPrimaryKey(SasStatisticObject record);
}
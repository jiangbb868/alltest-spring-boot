package com.its.itone.sas.config.dao.mapper;

import com.its.itone.sas.config.dao.pojo.SasStatisticObjDimRelation;
import com.its.itone.sas.config.dao.pojo.SasStatisticObjDimRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SasStatisticObjDimRelationMapper {
    int countByExample(SasStatisticObjDimRelationExample example);

    int deleteByExample(SasStatisticObjDimRelationExample example);

    int deleteByPrimaryKey(String id);

    int insert(SasStatisticObjDimRelation record);

    int insertSelective(SasStatisticObjDimRelation record);

    List<SasStatisticObjDimRelation> selectByExample(SasStatisticObjDimRelationExample example);

    SasStatisticObjDimRelation selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SasStatisticObjDimRelation record, @Param("example") SasStatisticObjDimRelationExample example);

    int updateByExample(@Param("record") SasStatisticObjDimRelation record, @Param("example") SasStatisticObjDimRelationExample example);

    int updateByPrimaryKeySelective(SasStatisticObjDimRelation record);

    int updateByPrimaryKey(SasStatisticObjDimRelation record);
}
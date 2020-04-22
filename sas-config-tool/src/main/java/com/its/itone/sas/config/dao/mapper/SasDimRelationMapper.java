package com.its.itone.sas.config.dao.mapper;

import com.its.itone.sas.config.dao.pojo.SasDimRelation;
import com.its.itone.sas.config.dao.pojo.SasDimRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SasDimRelationMapper {
    int countByExample(SasDimRelationExample example);

    int deleteByExample(SasDimRelationExample example);

    int deleteByPrimaryKey(String id);

    int insert(SasDimRelation record);

    int insertSelective(SasDimRelation record);

    List<SasDimRelation> selectByExample(SasDimRelationExample example);

    SasDimRelation selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SasDimRelation record, @Param("example") SasDimRelationExample example);

    int updateByExample(@Param("record") SasDimRelation record, @Param("example") SasDimRelationExample example);

    int updateByPrimaryKeySelective(SasDimRelation record);

    int updateByPrimaryKey(SasDimRelation record);
}
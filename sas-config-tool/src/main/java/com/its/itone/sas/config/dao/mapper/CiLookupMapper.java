package com.its.itone.sas.config.dao.mapper;

import com.its.itone.sas.config.dao.pojo.CiLookup;
import com.its.itone.sas.config.dao.pojo.CiLookupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CiLookupMapper {
    int countByExample(CiLookupExample example);

    int deleteByExample(CiLookupExample example);

    int deleteByPrimaryKey(String id);

    int insert(CiLookup record);

    int insertSelective(CiLookup record);

    List<CiLookup> selectByExample(CiLookupExample example);

    CiLookup selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CiLookup record, @Param("example") CiLookupExample example);

    int updateByExample(@Param("record") CiLookup record, @Param("example") CiLookupExample example);

    int updateByPrimaryKeySelective(CiLookup record);

    int updateByPrimaryKey(CiLookup record);
}
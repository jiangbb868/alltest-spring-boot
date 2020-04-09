package com.its.itone.sas.config.dao.mapper;

import com.its.itone.sas.config.dao.pojo.CiLookupType;
import com.its.itone.sas.config.dao.pojo.CiLookupTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CiLookupTypeMapper {
    int countByExample(CiLookupTypeExample example);

    int deleteByExample(CiLookupTypeExample example);

    int deleteByPrimaryKey(String id);

    int insert(CiLookupType record);

    int insertSelective(CiLookupType record);

    List<CiLookupType> selectByExample(CiLookupTypeExample example);

    CiLookupType selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CiLookupType record, @Param("example") CiLookupTypeExample example);

    int updateByExample(@Param("record") CiLookupType record, @Param("example") CiLookupTypeExample example);

    int updateByPrimaryKeySelective(CiLookupType record);

    int updateByPrimaryKey(CiLookupType record);
}
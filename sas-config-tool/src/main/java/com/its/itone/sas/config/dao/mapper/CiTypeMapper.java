package com.its.itone.sas.config.dao.mapper;

import com.its.itone.sas.config.dao.pojo.CiType;
import com.its.itone.sas.config.dao.pojo.CiTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CiTypeMapper {
    int countByExample(CiTypeExample example);

    int deleteByExample(CiTypeExample example);

    int deleteByPrimaryKey(String id);

    int insert(CiType record);

    int insertSelective(CiType record);

    List<CiType> selectByExample(CiTypeExample example);

    CiType selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CiType record, @Param("example") CiTypeExample example);

    int updateByExample(@Param("record") CiType record, @Param("example") CiTypeExample example);

    int updateByPrimaryKeySelective(CiType record);

    int updateByPrimaryKey(CiType record);
}
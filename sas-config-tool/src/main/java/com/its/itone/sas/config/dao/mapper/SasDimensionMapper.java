package com.its.itone.sas.config.dao.mapper;

import com.its.itone.sas.config.dao.pojo.SasDimension;
import com.its.itone.sas.config.dao.pojo.SasDimensionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SasDimensionMapper {
    int countByExample(SasDimensionExample example);

    int deleteByExample(SasDimensionExample example);

    int deleteByPrimaryKey(String id);

    int insert(SasDimension record);

    int insertSelective(SasDimension record);

    List<SasDimension> selectByExample(SasDimensionExample example);

    SasDimension selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SasDimension record, @Param("example") SasDimensionExample example);

    int updateByExample(@Param("record") SasDimension record, @Param("example") SasDimensionExample example);

    int updateByPrimaryKeySelective(SasDimension record);

    int updateByPrimaryKey(SasDimension record);
}
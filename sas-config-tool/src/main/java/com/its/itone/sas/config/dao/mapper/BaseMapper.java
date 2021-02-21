package com.its.itone.sas.config.dao.mapper;

import com.its.itone.sas.config.dao.pojo.CiObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseMapper<Pojo extends CiObject, Example> {
    int countByExample(Example example);

    int deleteByExample(Example example);

    int deleteByPrimaryKey(String id);

    int insert(Pojo record);

    int insertSelective(Pojo record);

    List<Pojo> selectByExample(Example example);

    CiObject selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Pojo record, @Param("example") Example example);

    int updateByExample(@Param("record") Pojo record, @Param("example") Example example);

    int updateByPrimaryKeySelective(Pojo record);

    int updateByPrimaryKey(Pojo record);
}

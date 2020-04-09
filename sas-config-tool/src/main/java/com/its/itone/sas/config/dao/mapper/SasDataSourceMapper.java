package com.its.itone.sas.config.dao.mapper;

import com.its.itone.sas.config.dao.pojo.SasDataSource;
import com.its.itone.sas.config.dao.pojo.SasDataSourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SasDataSourceMapper {
    int countByExample(SasDataSourceExample example);

    int deleteByExample(SasDataSourceExample example);

    int deleteByPrimaryKey(String id);

    int insert(SasDataSource record);

    int insertSelective(SasDataSource record);

    List<SasDataSource> selectByExample(SasDataSourceExample example);

    SasDataSource selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SasDataSource record, @Param("example") SasDataSourceExample example);

    int updateByExample(@Param("record") SasDataSource record, @Param("example") SasDataSourceExample example);

    int updateByPrimaryKeySelective(SasDataSource record);

    int updateByPrimaryKey(SasDataSource record);
}
package com.its.itone.sas.config.service;

import com.its.itone.sas.config.dao.mapper.SasDataSourceMapper;
import com.its.itone.sas.config.dao.pojo.SasDataSource;
import com.its.itone.sas.config.dao.pojo.SasDataSourceExample;
import com.its.itone.sas.config.util.CiTypeUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class SasDataSourceService {

    @Autowired
    private SasDataSourceMapper mapper;

    public List<SasDataSource> list() {
        SasDataSourceExample example = new SasDataSourceExample();
        List<SasDataSource> dataSource = mapper.selectByExample(example);
        return dataSource;
    }


    public SasDataSource get(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    public void update(SasDataSource dataSource) {
        if (dataSource.getLoader() == null) {
            dataSource.setLoader("");
        }
        mapper.updateByPrimaryKeySelective(dataSource);
    }

    public void delete(String id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void add(SasDataSource dataSource) {
        dataSource.setId(UUID.randomUUID().toString());
        dataSource.setType(CiTypeUtils.CITYPE_DATA_SOURCE);
        if (dataSource.getLoader() == null) {
            dataSource.setLoader("");
        }
        mapper.insertSelective(dataSource);
    }
}

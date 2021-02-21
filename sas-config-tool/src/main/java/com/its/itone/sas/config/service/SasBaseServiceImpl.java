package com.its.itone.sas.config.service;

import com.its.itone.sas.config.dao.mapper.BaseMapper;
import com.its.itone.sas.config.dao.pojo.CiObject;
import com.its.itone.sas.config.util.CiTypeUtils;

import java.util.List;
import java.util.UUID;

public class SasBaseServiceImpl<Mapper extends BaseMapper,Pojo extends CiObject> {

    private Mapper mapper;

    public List<Pojo> list() {
        Object example = ExampleFactory.buildExample(this.getClass());
        List<Pojo> dataSource = mapper.selectByExample(example);
        return dataSource;
    }

    public CiObject get(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    public void update(Pojo pojo) {
        mapper.updateByPrimaryKeySelective(pojo);
    }

    public void delete(String id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void add(Pojo pojo) {
        pojo.setId(UUID.randomUUID().toString());
        pojo.setType(CiTypeUtils.CITYPE_DATA_SOURCE);
        mapper.insertSelective(pojo);
    }
}

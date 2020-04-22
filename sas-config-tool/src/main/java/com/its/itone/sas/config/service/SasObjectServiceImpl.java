package com.its.itone.sas.config.service;

import com.its.itone.sas.config.api.SasObjectService;
import com.its.itone.sas.config.dao.mapper.SasStatisticObjectMapper;
import com.its.itone.sas.config.dao.pojo.SasStatisticObject;
import com.its.itone.sas.config.dao.pojo.SasStatisticObjectExample;
import com.its.itone.sas.config.util.CiTypeUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("sasObjectService")
public class SasObjectServiceImpl implements SasObjectService {

    @Autowired
    private SasStatisticObjectMapper mapper;

    public List<SasStatisticObject> list() {
        SasStatisticObjectExample example = new SasStatisticObjectExample();
        List<SasStatisticObject> statisticObjects = mapper.selectByExample(example);
        return statisticObjects;
    }

    public SasStatisticObject get(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    public void update(SasStatisticObject statisticObject) {
        mapper.updateByPrimaryKeySelective(statisticObject);
    }

    public void delete(String id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void add(SasStatisticObject statisticObject) {
        statisticObject.setId(UUID.randomUUID().toString());
        statisticObject.setType(CiTypeUtils.CITYPE_STATISTIC_OBJECT);
        mapper.insertSelective(statisticObject);
    }
}

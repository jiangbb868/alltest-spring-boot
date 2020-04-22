package com.its.itone.sas.config.service;

import com.its.itone.sas.config.api.SasIndicatorService;
import com.its.itone.sas.config.dao.mapper.SasStatisticIndicatorMapper;
import com.its.itone.sas.config.dao.pojo.SasStatisticIndicator;
import com.its.itone.sas.config.dao.pojo.SasStatisticIndicatorExample;
import com.its.itone.sas.config.util.CiTypeUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class SasIndicatorServiceImpl implements SasIndicatorService{

    @Autowired
    private SasStatisticIndicatorMapper mapper;

    public List<SasStatisticIndicator> list() {
        SasStatisticIndicatorExample example = new SasStatisticIndicatorExample();
        List<SasStatisticIndicator> indicator = mapper.selectByExample(example);
        return indicator;
    }


    public SasStatisticIndicator get(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    public void update(SasStatisticIndicator indicator) {
        mapper.updateByPrimaryKeySelective(indicator);
    }

    public void delete(String id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void add(SasStatisticIndicator indicator) {
        indicator.setId(UUID.randomUUID().toString());
        indicator.setType(CiTypeUtils.CITYPE_STATISTIC_INDICATOR);
        mapper.insertSelective(indicator);
    }
}

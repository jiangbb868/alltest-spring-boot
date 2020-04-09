package com.its.itone.sas.config.service;

import com.its.itone.sas.config.api.CiLookupService;
import com.its.itone.sas.config.dao.mapper.CiLookupMapper;
import com.its.itone.sas.config.dao.pojo.CiLookup;
import com.its.itone.sas.config.dao.pojo.CiLookupExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ciLookupService")
public class CiLookupServiceImpl implements CiLookupService{

    @Autowired
    private CiLookupMapper mapper;

    @Override
    public List<CiLookup> listByCiLookupType(String lookupType) {
        CiLookupExample example = new CiLookupExample();
        CiLookupExample.Criteria criteria = example.createCriteria();
        criteria.andLookupTypeEqualTo(lookupType);
        return mapper.selectByExample(example);
    }
}

package com.its.itone.sas.config.service;

import com.its.itone.sas.config.api.SasDimRelationService;
import com.its.itone.sas.config.dao.mapper.SasDimRelationMapper;
import com.its.itone.sas.config.dao.pojo.SasDimRelation;
import com.its.itone.sas.config.dao.pojo.SasDimRelationExample;
import com.its.itone.sas.config.util.CiTypeUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("sasDimRelationService")
public class SasDimRelationServiceImpl implements SasDimRelationService{

    @Autowired
    private SasDimRelationMapper mapper;

    public List<SasDimRelation> list() {
        SasDimRelationExample example = new SasDimRelationExample();
        List<SasDimRelation> DimRelations = mapper.selectByExample(example);
        return DimRelations;
    }

    public SasDimRelation get(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    public void update(SasDimRelation dimRelation) {
        mapper.updateByPrimaryKeySelective(dimRelation);
    }

    public void delete(String id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void add(SasDimRelation dimRelation) {
        dimRelation.setId(UUID.randomUUID().toString());
        dimRelation.setType(CiTypeUtils.CITYPE_STATISTIC_DIM_RELATION);
        mapper.insertSelective(dimRelation);
    }
}

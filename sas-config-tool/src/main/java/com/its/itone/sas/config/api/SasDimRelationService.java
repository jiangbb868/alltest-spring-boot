package com.its.itone.sas.config.api;

import com.its.itone.sas.config.dao.pojo.SasDimRelation;

import java.util.List;

public interface SasDimRelationService {

    List<SasDimRelation> list();

    SasDimRelation get(String id);

    void update(SasDimRelation statisticObject);

    void delete(String id) ;

    void add(SasDimRelation statisticObject);
}

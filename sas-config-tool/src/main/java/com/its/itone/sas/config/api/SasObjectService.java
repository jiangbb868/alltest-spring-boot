package com.its.itone.sas.config.api;

import com.its.itone.sas.config.dao.pojo.SasStatisticObject;

import java.util.List;

public interface SasObjectService {
    
    List<SasStatisticObject> list();

    SasStatisticObject get(String id);

    void update(SasStatisticObject statisticObject);

    void delete(String id) ;

    void add(SasStatisticObject statisticObject);
    
}

package com.its.itone.sas.config.api;

import com.its.itone.sas.config.dao.pojo.SasStatisticIndicator;

import java.util.List;

public interface SasIndicatorService {
    
    List<SasStatisticIndicator> list();

    SasStatisticIndicator get(String id);

    void update(SasStatisticIndicator indicator);

    void delete(String id) ;

    void add(SasStatisticIndicator indicator);
}

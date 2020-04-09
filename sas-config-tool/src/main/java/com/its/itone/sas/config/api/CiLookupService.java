package com.its.itone.sas.config.api;

import com.its.itone.sas.config.dao.pojo.CiLookup;

import java.util.List;

public interface CiLookupService {

    List<CiLookup> listByCiLookupType(String lookupType);
}

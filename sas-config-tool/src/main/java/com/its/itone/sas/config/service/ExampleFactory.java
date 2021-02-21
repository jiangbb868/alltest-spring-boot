package com.its.itone.sas.config.service;

import com.its.itone.sas.config.dao.pojo.SasDataSourceExample;

public class ExampleFactory {

    public static Object buildExample(Class clazz) {
        if (clazz.equals(SasDataSourceService.class)) {
            return new SasDataSourceExample();
        }
        return null;
    }
}

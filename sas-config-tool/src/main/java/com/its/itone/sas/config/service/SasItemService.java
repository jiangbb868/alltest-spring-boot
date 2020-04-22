package com.its.itone.sas.config.service;

import com.its.itone.sas.config.dao.mapper.SasStatisticItemMapper;
import com.its.itone.sas.config.dao.pojo.SasStatisticItem;
import com.its.itone.sas.config.dao.pojo.SasStatisticItemExample;
import com.its.itone.sas.config.util.CiTypeUtils;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class SasItemService {

    @Autowired
    private SasStatisticItemMapper mapper;

    public List<SasStatisticItem> list() {
        SasStatisticItemExample example = new SasStatisticItemExample();
        List<SasStatisticItem> statisticItems = mapper.selectByExample(example);
        return statisticItems;
    }

    public SasStatisticItem get(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    public void update(SasStatisticItem statisticItem) {
        mapper.updateByPrimaryKeySelective(statisticItem);
    }

    public void delete(String id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void add(SasStatisticItem statisticItem) {
        statisticItem.setId(UUID.randomUUID().toString());
        statisticItem.setType(CiTypeUtils.CITYPE_STATISTIC_ITEM);
        mapper.insertSelective(statisticItem);
    }
}

package com.its.itone.sas.config.controller;

import com.its.itone.sas.config.dao.pojo.SasStatisticItem;
import com.its.itone.sas.config.service.SasItemService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/sas/item")
@RestController
public class SasItemController {

    @Autowired
    private SasItemService sasItemService;

    @GetMapping(value="/list")
    public List<SasStatisticItem> list() throws Exception {
        return sasItemService.list();
    }

    @GetMapping(value="/{id}")
    public SasStatisticItem get(@PathVariable(value = "id") String id) throws Exception {
        return sasItemService.get(id);
    }

    @PostMapping(value="/update")
    public void update(@RequestParam(value="item") SasStatisticItem statisticItem) throws Exception {
        sasItemService.update(statisticItem);
    }

    @PostMapping(value="/delete")
    public void delete(@RequestParam(value="id") String id) throws Exception {
        sasItemService.delete(id);
    }

    @PostMapping(value="/add")
    public String add(@RequestParam(value="item") SasStatisticItem statisticItem) {
        sasItemService.add(statisticItem);
        return statisticItem.getId();
    }
}

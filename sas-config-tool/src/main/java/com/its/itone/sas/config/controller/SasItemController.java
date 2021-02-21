package com.its.itone.sas.config.controller;

import com.its.itone.sas.config.SasConfigApplication;
import com.its.itone.sas.config.dao.pojo.SasStatisticItem;
import com.its.itone.sas.config.service.SasItemService;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public String list() throws Exception {
        List<SasStatisticItem> statisticItems = sasItemService.list();
        JSONObject result = new JSONObject();
        result.put("rows", statisticItems);
        result.put("total", statisticItems.size());
        return result.toJSONString();
    }

    @GetMapping(value="/{id}")
    public SasStatisticItem get(@PathVariable(value = "id") String id) throws Exception {
        return sasItemService.get(id);
    }

    @PostMapping(value="/update")
    public String update(@RequestBody SasStatisticItem statisticItem) throws Exception {
        sasItemService.update(statisticItem);
        return "success";
    }

    @PostMapping(value="/delete")
    public String delete(@RequestParam(value="id") String id) throws Exception {
        sasItemService.delete(id);
        return "success";
    }

    @PostMapping(value="/add")
    public String add(@RequestBody SasStatisticItem statisticItem) {
        sasItemService.add(statisticItem);
        return statisticItem.getId();
    }
}

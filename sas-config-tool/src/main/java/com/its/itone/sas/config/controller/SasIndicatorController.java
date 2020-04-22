package com.its.itone.sas.config.controller;

import com.its.itone.sas.config.api.SasIndicatorService;
import com.its.itone.sas.config.dao.pojo.SasStatisticIndicator;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/sas/indicator")
@RestController
public class SasIndicatorController {

    @Autowired
    private SasIndicatorService sasIndicatorService;

    @GetMapping(value="/list")
    public String list() throws Exception {
        List<SasStatisticIndicator> indicators =  sasIndicatorService.list();
        JSONObject result = new JSONObject();
        result.put("rows", indicators);
        result.put("total", indicators.size());
        return result.toJSONString();
    }

    @PostMapping(value="/add")
    public String add(@RequestBody SasStatisticIndicator indicator) throws Exception {
        sasIndicatorService.add(indicator);
        return "sucess";
    }

    @PostMapping(value="/update")
    public String update(@RequestBody SasStatisticIndicator indicator) throws Exception {
        sasIndicatorService.update(indicator);
        return "sucess";
    }

    @PostMapping(value="/delete")
    public String delete(@RequestParam(value="id") String id) throws Exception {
        sasIndicatorService.delete(id);
        return "sucess";
    }

    @GetMapping(value="/get")
    public String get(@RequestParam(value="id") String id) throws Exception {
        sasIndicatorService.get(id);
        return "sucess";
    }
}

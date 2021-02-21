package com.its.itone.sas.config.controller;

import com.its.itone.sas.config.api.SasObjectService;
import com.its.itone.sas.config.dao.pojo.SasStatisticObject;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/sas/object")
@RestController
public class SasObjectController {

    @Autowired
    private SasObjectService sasObjectService;

    @GetMapping(value="/list")
    public String list() throws Exception {
        List<SasStatisticObject> statisticObjects =  sasObjectService.list();
        JSONObject result = new JSONObject();
        result.put("rows", statisticObjects);
        result.put("total", statisticObjects.size());
        return result.toJSONString();
    }

    @PostMapping(value="/add")
    public String add(@RequestBody SasStatisticObject statisticObject) throws Exception {
        sasObjectService.add(statisticObject);
        return "success";
    }

    @PostMapping(value="/update")
    public String update(@RequestBody SasStatisticObject statisticObject) throws Exception {
        sasObjectService.update(statisticObject);
        return "success";
    }

    @PostMapping(value="/delete")
    public String delete(@RequestParam(value="id") String id) throws Exception {
        sasObjectService.delete(id);
        return "success";
    }

    @GetMapping(value="/get")
    public String get(@RequestParam(value="id") String id) throws Exception {
        sasObjectService.get(id);
        return "success";
    }
}

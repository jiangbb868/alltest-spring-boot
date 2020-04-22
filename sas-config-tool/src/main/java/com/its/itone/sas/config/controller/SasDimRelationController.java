package com.its.itone.sas.config.controller;


import com.its.itone.sas.config.api.SasDimRelationService;
import com.its.itone.sas.config.dao.pojo.SasDimRelation;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/sas/dimRelation")
@RestController
public class SasDimRelationController {

    @Autowired
    private SasDimRelationService sasDimRelationService;

    @GetMapping(value="/list")
    public String list() throws Exception {
        List<SasDimRelation> DimRelations =  sasDimRelationService.list();
        JSONObject result = new JSONObject();
        result.put("rows", DimRelations);
        result.put("total", DimRelations.size());
        return result.toJSONString();
    }

    @PostMapping(value="/add")
    public String add(@RequestBody SasDimRelation DimRelation) throws Exception {
        sasDimRelationService.add(DimRelation);
        return "sucess";
    }

    @PostMapping(value="/update")
    public String update(@RequestBody SasDimRelation DimRelation) throws Exception {
        sasDimRelationService.update(DimRelation);
        return "sucess";
    }

    @PostMapping(value="/delete")
    public String delete(@RequestParam(value="id") String id) throws Exception {
        sasDimRelationService.delete(id);
        return "sucess";
    }

    @GetMapping(value="/get")
    public String get(@RequestParam(value="id") String id) throws Exception {
        sasDimRelationService.get(id);
        return "sucess";
    }
}

package com.its.itone.sas.config.controller;

import com.its.itone.sas.config.dao.pojo.SasDataSource;
import com.its.itone.sas.config.service.SasDataSourceService;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/sas/datasource")
@RestController
public class SasDataSourceController {

    @Autowired
    private SasDataSourceService sasDataSourceService;

    @GetMapping(value="/list")
    public String list() throws Exception {
        List<SasDataSource> DataSources =  sasDataSourceService.list();
        JSONObject result = new JSONObject();
        result.put("rows", DataSources);
        result.put("total", DataSources.size());
        return result.toJSONString();
    }

    @PostMapping(value="/add")
    public String add(@RequestBody SasDataSource dataSource) throws Exception {
        sasDataSourceService.add(dataSource);
        return "success";
    }

    @PostMapping(value="/update")
    public String update(@RequestBody SasDataSource dataSource) throws Exception {
        sasDataSourceService.update(dataSource);
        return "success";
    }

    @PostMapping(value="/delete")
    public String delete(@RequestParam(value="id") String id) throws Exception {
        sasDataSourceService.delete(id);
        return "success";
    }

    @GetMapping(value="/get")
    public String get(@RequestParam(value="id") String id) throws Exception {
        sasDataSourceService.get(id);
        return "success";
    }

}

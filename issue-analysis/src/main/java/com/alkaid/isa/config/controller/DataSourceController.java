package com.alkaid.isa.config.controller;

import com.alibaba.fastjson.JSONObject;
import com.alkaid.isa.config.api.IDataSourceService;
import com.alkaid.isa.config.pojo.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/isa/datasource")
@RestController
public class DataSourceController {

    @Autowired
    private IDataSourceService dataSourceService;

    @GetMapping(value="/list")
    public String list() throws Exception {
        List<DataSource> DataSources =  dataSourceService.list();
        JSONObject result = new JSONObject();
        result.put("rows", DataSources);
        result.put("total", DataSources.size());
        return result.toJSONString();
    }

    @PostMapping(value="/add")
    public String add(@RequestBody DataSource dataSource) throws Exception {
        dataSourceService.save(dataSource);
        return "sucess";
    }

    @PostMapping(value="/update")
    public String update(@RequestBody DataSource dataSource) throws Exception {
        dataSourceService.saveOrUpdate(dataSource);
        return "sucess";
    }

    @PostMapping(value="/delete")
    public String delete(@RequestParam(value="id") long id) throws Exception {
        dataSourceService.removeById(id);
        return "sucess";
    }

    @GetMapping(value="/get")
    public String get(@RequestParam(value="id") long id) throws Exception {
        dataSourceService.getById(id);
        return "sucess";
    }
}

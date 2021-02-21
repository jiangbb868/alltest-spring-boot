package com.alkaid.isa.config.controller;

import com.alibaba.fastjson.JSONObject;
import com.alkaid.isa.config.api.IAnalysisProcessService;
import com.alkaid.isa.config.pojo.AnalysisProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/isa/process")
@RestController
public class AnalysisProcessController {
    @Autowired
    private IAnalysisProcessService analysisProcessService;

    @GetMapping(value="/list")
    public String list() throws Exception {
        List<AnalysisProcess> AnalysisProcesss =  analysisProcessService.list();
        JSONObject result = new JSONObject();
        result.put("rows", AnalysisProcesss);
        result.put("total", AnalysisProcesss.size());
        return result.toJSONString();
    }

    @PostMapping(value="/add")
    public String add(@RequestBody AnalysisProcess analysisProcess) throws Exception {
        analysisProcessService.save(analysisProcess);
        return "sucess";
    }

    @PostMapping(value="/update")
    public String update(@RequestBody AnalysisProcess analysisProcess) throws Exception {
        analysisProcessService.saveOrUpdate(analysisProcess);
        return "sucess";
    }

    @PostMapping(value="/delete")
    public String delete(@RequestParam(value="id") long id) throws Exception {
        analysisProcessService.removeById(id);
        return "sucess";
    }

    @GetMapping(value="/get")
    public String get(@RequestParam(value="id") long id) throws Exception {
        analysisProcessService.getById(id);
        return "sucess";
    }
}

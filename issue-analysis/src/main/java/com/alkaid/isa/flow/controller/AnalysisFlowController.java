package com.alkaid.isa.flow.controller;

import com.alibaba.fastjson.JSONObject;
import com.alkaid.isa.flow.api.IAnalysisFlowService;
import com.alkaid.isa.flow.pojo.AnalysisFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/isa/flow")
@RestController
public class AnalysisFlowController {

    @Autowired
    private IAnalysisFlowService analysisFlowService;

    @GetMapping(value="/list")
    public String list() throws Exception {
        List<AnalysisFlow> AnalysisFlows =  analysisFlowService.list();
        JSONObject result = new JSONObject();
        result.put("rows", AnalysisFlows);
        result.put("total", AnalysisFlows.size());
        return result.toJSONString();
    }

    @PostMapping(value="/add")
    public String add(@RequestBody AnalysisFlow analysisFlow) throws Exception {
        analysisFlowService.save(analysisFlow);
        return "sucess";
    }

    @PostMapping(value="/update")
    public String update(@RequestBody AnalysisFlow analysisFlow) throws Exception {
        analysisFlowService.saveOrUpdate(analysisFlow);
        return "sucess";
    }

    @PostMapping(value="/delete")
    public String delete(@RequestParam(value="id") long id) throws Exception {
        analysisFlowService.removeById(id);
        return "sucess";
    }

    @GetMapping(value="/get")
    public String get(@RequestParam(value="id") long id) throws Exception {
        analysisFlowService.getById(id);
        return "sucess";
    }
}

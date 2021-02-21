package com.alkaid.isa.flow.controller;

import com.alibaba.fastjson.JSONObject;
import com.alkaid.isa.flow.api.IAnalysisSequenceService;
import com.alkaid.isa.flow.pojo.AnalysisSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/isa/sequence")
@RestController
public class AnalysisSequenceController {

    @Autowired
    private IAnalysisSequenceService analysisSequenceService;

    @GetMapping(value="/list")
    public String list() throws Exception {
        List<AnalysisSequence> AnalysisSequences =  analysisSequenceService.list();
        JSONObject result = new JSONObject();
        result.put("rows", AnalysisSequences);
        result.put("total", AnalysisSequences.size());
        return result.toJSONString();
    }

    @PostMapping(value="/add")
    public String add(@RequestBody AnalysisSequence analysisSequence) throws Exception {
        analysisSequenceService.save(analysisSequence);
        return "sucess";
    }

    @PostMapping(value="/update")
    public String update(@RequestBody AnalysisSequence analysisSequence) throws Exception {
        analysisSequenceService.saveOrUpdate(analysisSequence);
        return "sucess";
    }

    @PostMapping(value="/delete")
    public String delete(@RequestParam(value="id") long id) throws Exception {
        analysisSequenceService.removeById(id);
        return "sucess";
    }

    @GetMapping(value="/get")
    public String get(@RequestParam(value="id") long id) throws Exception {
        analysisSequenceService.getById(id);
        return "sucess";
    }
}

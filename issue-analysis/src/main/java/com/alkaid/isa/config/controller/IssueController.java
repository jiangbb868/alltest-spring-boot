package com.alkaid.isa.config.controller;

import com.alibaba.fastjson.JSONObject;
import com.alkaid.isa.config.api.IIssueService;
import com.alkaid.isa.config.pojo.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/isa/issue")
@RestController
public class IssueController {

    @Autowired
    private IIssueService issueService;

    @GetMapping(value="/list")
    public String list() throws Exception {
        List<Issue> Issues =  issueService.list();
        JSONObject result = new JSONObject();
        result.put("rows", Issues);
        result.put("total", Issues.size());
        return result.toJSONString();
    }

    @PostMapping(value="/add")
    public String add(@RequestBody Issue issue) throws Exception {
        issueService.save(issue);
        return "sucess";
    }

    @PostMapping(value="/update")
    public String update(@RequestBody Issue issue) throws Exception {
        issueService.saveOrUpdate(issue);
        return "sucess";
    }

    @PostMapping(value="/delete")
    public String delete(@RequestParam(value="id") long id) throws Exception {
        issueService.removeById(id);
        return "sucess";
    }

    @GetMapping(value="/get")
    public String get(@RequestParam(value="id") long id) throws Exception {
        issueService.getById(id);
        return "sucess";
    }
}

package com.alkaid.clickhouse.controller;

import com.alkaid.clickhouse.entity.Visits;
import com.alkaid.clickhouse.service.IVisitsService;
import com.alkaid.clickhouse.util.ClickhouseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class VisitController {

    @Autowired
    private IVisitsService visitsService;

    @GetMapping("/list")
    @ResponseBody
    public List<Visits> list() {
        List result = ClickhouseUtil.sqlQuery("select * from visits");
        return result;
    }

    @GetMapping("/query/{sql}")
    @ResponseBody
    public List<Visits> list(@PathVariable("sql") String sql) {
        List result = ClickhouseUtil.sqlQuery(sql);
        return result;
    }
}

package com.alkaid.isa.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

@Controller
public class IsaController {

    @GetMapping(value="/index")
    public String index(HashMap<String, Object> map) {
        map.put("message", "欢迎进入问题分析工具");
        return "index";
    }
}

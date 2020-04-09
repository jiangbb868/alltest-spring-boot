package com.its.itone.sas.config.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;

@Controller
public class SasController {

    @GetMapping(value="/list")
    public List<String> list() throws Exception {

        return null;
    }

    @GetMapping(value="/index")
    public String index(HashMap<String, Object> map) {
        map.put("message", "欢迎进入sas配置页面");
        return "index";
    }

    @GetMapping(value="/home")
    public String home(HashMap<String, Object> map) {
        map.put("message", "欢迎进入sas配置页面");
        return "home1";
    }

    @GetMapping(value="/menu")
    public String menu(HashMap<String, Object> map) {
        map.put("message", "欢迎进入sas配置页面");
        return "menu";
    }

    @GetMapping(value="/leftmenu")
    public String leftmenu(HashMap<String, Object> map) {
        map.put("message", "欢迎进入sas配置页面");
        return "leftmenu";
    }
}

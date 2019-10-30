package com.alkaid.shiroredis.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/user")
@Controller
public class UserController {
    @RequiresPermissions("user:show")
    @ResponseBody
    @RequestMapping(value="/show", method = RequestMethod.GET)
    public String showUser() {
        return "显示用户";
    }

    @RequiresPermissions("user:list")
    @ResponseBody
    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String listUser() {
        return "用户列表";
    }
}

package com.alkaid.helm.controller;

import com.alkaid.helm.dao.pojo.TblUser;
import com.alkaid.helm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String list() {
        return userService.list().toString();
    }
}

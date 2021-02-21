package com.alkaid.mock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class MockController {

    @GetMapping("/users")
    public String users() {
        return "{\n" +
                "\t\"code\": \"200\",\n" +
                "\t\"users\": [{\n" +
                "\t\t\t\"id\": \"884d18e0-37f0-47f4-a010-52a720d83cfc\",\n" +
                "\t\t\t\"name\": \"zhangsan\",\n" +
                "\t\t\t\"display_name\": \"张三\",\n" +
                "\t\t\t\"attributes\": \"\",\n" +
                "\t\t\t\"create_user\": \"\",\n" +
                "\t\t\t\"create_time\": \"2020/9/5 23:13:43\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": \"884d18e0-37f0-47f4-a010-52a720d83cfc\",\n" +
                "\t\t\t\"name\": \"zhangsan\",\n" +
                "\t\t\t\"display_name\": \"张三\",\n" +
                "\t\t\t\"attributes\": \"\",\n" +
                "\t\t\t\"create_user\": \"\",\n" +
                "\t\t\t\"create_time\": \"2020/9/5 23:13:43\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
    }

    @GetMapping("/users/{id}")
    public String getById(@PathVariable(value = "id") Long id) {
        return "{\n" +
                "\t\"code\": \"200\",\n" +
                "\t\"users\": [{\n" +
                "\t\t\t\"id\": \"884d18e0-37f0-47f4-a010-52a720d83cfc\",\n" +
                "\t\t\t\"name\": \"zhangsan\",\n" +
                "\t\t\t\"display_name\": \"张三\",\n" +
                "\t\t\t\"attributes\": \"\",\n" +
                "\t\t\t\"create_user\": \"\",\n" +
                "\t\t\t\"create_time\": \"2020/9/5 23:13:43\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": \"884d18e0-37f0-47f4-a010-52a720d83cfc\",\n" +
                "\t\t\t\"name\": \"zhangsan\",\n" +
                "\t\t\t\"display_name\": \"张三\",\n" +
                "\t\t\t\"attributes\": \"\",\n" +
                "\t\t\t\"create_user\": \"\",\n" +
                "\t\t\t\"create_time\": \"2020/9/5 23:13:43\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
    }

    @PostMapping("/user")
    public String create(@RequestBody HashMap<String, String> user) {
        System.out.println("name:" + user.get("name"));
        return "处理成功";
    }
}

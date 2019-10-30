package com.alkaid.shiroredis.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Resource {

    @PostConstruct
    public void init(){
        resource = this;
    }

    public static Resource resource;

    @Autowired
    public static RedisTemplate redisTemplate;
}

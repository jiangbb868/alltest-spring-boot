package com.alkaid.shiroredis.initialize;

import com.alkaid.shiroredis.common.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class ThisApplicationRunner implements ApplicationRunner {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Resource.redisTemplate = redisTemplate;
    }
}

package com.alkaid.alltest.quartz.initialize;

import com.alkaid.alltest.quartz.common.Resource;
import com.alkaid.alltest.quartz.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ThisApplicationRunner implements ApplicationRunner {

    private static SchedulerManager schedulerManager;

    public static SchedulerManager getSchedulerManager() {
        return schedulerManager;
    }

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        schedulerManager = new SchedulerManager();
        schedulerManager.start();

        redisUtil.clearAll("*");
        Resource.redisUtil = redisUtil;
    }
}

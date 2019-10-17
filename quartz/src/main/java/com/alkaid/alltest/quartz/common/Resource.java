package com.alkaid.alltest.quartz.common;

import com.alkaid.alltest.quartz.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Resource {

    public static String TOTAL_COUNT_KEY = "totalCount";

    public static String JOB_INDEX = "jobIndex";

    public static RedisUtil redisUtil;

    private static Map<String, Integer> jobMap = new ConcurrentHashMap<>();

    private static AtomicInteger jogCount = new AtomicInteger(0);

    public static Map<String, Integer> getJobMap() {
        return jobMap;
    }

    public static AtomicInteger getJogCount() {
        return jogCount;
    }
}

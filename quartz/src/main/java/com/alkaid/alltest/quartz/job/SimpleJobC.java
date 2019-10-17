package com.alkaid.alltest.quartz.job;

import com.alkaid.alltest.quartz.common.Resource;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleJobC implements Job {

    Logger logger = LoggerFactory.getLogger(SimpleJobC.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        AtomicInteger totalCount = Resource.getJogCount();
        logger.info("Time: {}, Simple Job B begin >>> index = {} ", System.currentTimeMillis(), totalCount.incrementAndGet());
        // 大量的计算任务
        for (long i =  0; i < 1000000000L; i ++) {
            double a = 234234.34235354365f;
            double b = 3432423.21412342432;
            double c = 3242433454.24235235235;
            double d = 3232312342412431.243234234;
            double f = a * b + a / b * c * d;
        }

        logger.info("Time: {}, Simple Job B end <<< remain = {}", System.currentTimeMillis(), totalCount.decrementAndGet());
    }
}

package com.alkaid.alltest.quartz.job;

import com.alkaid.alltest.quartz.common.Resource;
import com.alkaid.alltest.quartz.redis.RedisUtil;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


@DisallowConcurrentExecution
public class SimpleJobB implements Job {

    Logger logger = LoggerFactory.getLogger(SimpleJobB.class);

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String jobKey = context.getJobDetail().getKey().getName();
        String group = context.getJobDetail().getKey().getGroup();

        long jobCount = Resource.redisUtil.incr(jobKey, 1);
        long totalCount = Resource.redisUtil.incr(Resource.TOTAL_COUNT_KEY, 1);
        logger.info("Time: {}, {}[{}][{}] total count = {} begin >>>>>>>>>>>>>>>>>>>>>>>> ",
                    System.currentTimeMillis(), jobKey, group, jobCount, totalCount);
        try {
            Thread.sleep(10 * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("Time: {}, {}[{}][{}] end <<<<<<<<<<<<<<<<<<<<< ",
                    System.currentTimeMillis(), jobKey, group, jobCount);
    }
}

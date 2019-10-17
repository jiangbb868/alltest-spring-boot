package com.alkaid.alltest.quartz.job;

import com.alkaid.alltest.quartz.common.Resource;
import com.alkaid.alltest.quartz.redis.RedisUtil;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DisallowConcurrentExecution
public class SimpleJobA implements Job {

    Logger logger = LoggerFactory.getLogger(SimpleJobA.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // Map jobMap = Resource.getJobMap();
        //AtomicInteger jobCount = Resource.getJogCount();
        String jobKey = context.getJobDetail().getKey().getName();
        String group = context.getJobDetail().getKey().getGroup();

        long jobCount = Resource.redisUtil.incr(jobKey, 1);
        long totalCount = Resource.redisUtil.incr(Resource.TOTAL_COUNT_KEY, 1);
        logger.info("Time: {}, {}[{}][{}] total count = {} begin >>>>>>>>>>>>>>>>>>>>>>>> ",
                    System.currentTimeMillis(), jobKey, group, jobCount, totalCount);
        try {
            Thread.sleep(60 * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("Time: {}, {}[{}][{}] end <<<<<<<<<<<<<<<<<<<<< ",
                    System.currentTimeMillis(), jobKey, group, jobCount);
    }
}

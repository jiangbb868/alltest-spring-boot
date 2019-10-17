package com.alkaid.alltest.quartz.job;

import com.alkaid.alltest.quartz.common.Resource;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleJobE implements InterruptableJob {

    Logger logger = LoggerFactory.getLogger(SimpleJobE.class);
    @Override
    public void interrupt() throws UnableToInterruptJobException {

    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String jobKey = context.getJobDetail().getKey().getName();
        String group = context.getJobDetail().getKey().getGroup();

        long jobCount = Resource.redisUtil.incr(jobKey, 1);
        long totalCount = Resource.redisUtil.incr(Resource.TOTAL_COUNT_KEY, 1);
        logger.info("Time: {}, InterruptableJob {}[{}][{}] total count = {} begin >>>>>>>>>>>>>>>>>>>>>>>> ",
                    System.currentTimeMillis(), jobKey, group, jobCount, totalCount);
        try {
            Thread.sleep(60 * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("Time: {}, InterruptableJob {}[{}][{}] end <<<<<<<<<<<<<<<<<<<<< ",
                    System.currentTimeMillis(), jobKey, group, jobCount);
    }
}

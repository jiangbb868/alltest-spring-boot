package com.alkaid.alltest.quartz.job;

import com.alkaid.alltest.quartz.common.Resource;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleJobD implements Job {
    Logger logger = LoggerFactory.getLogger(SimpleJobD.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String jobKey = context.getJobDetail().getKey().getName();
        String group = context.getJobDetail().getKey().getGroup();

        long jobCount = Resource.redisUtil.incr(jobKey, 1);
        long totalCount = Resource.redisUtil.incr(Resource.TOTAL_COUNT_KEY, 1);
        logger.info("Time: {}, {}[{}][{}] total count = {} begin >>>>>>>>>>>>>>>>>>>>>>>> ",
                    System.currentTimeMillis(), jobKey, group, jobCount, totalCount);

        for (long i =  0; i < 100L; i ++) {
            double a = 234234.34235354365f;
            double b = 3432423.21412342432;
            double c = 3242433454.24235235235;
            double f = a * b + a / b * c;
        }
        if (jobCount == 1) {
            throw new JobExecutionException();
        }
        logger.info("Time: {}, {}[{}][{}] end <<<<<<<<<<<<<<<<<<<<< ",
                    System.currentTimeMillis(), jobKey, group, jobCount);
    }
}

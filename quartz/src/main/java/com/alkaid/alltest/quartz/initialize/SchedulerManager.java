package com.alkaid.alltest.quartz.initialize;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchedulerManager {

    Logger logger = LoggerFactory.getLogger(SchedulerManager.class);
    private Scheduler scheduler;
    private String schedulerId;

    public void start() throws SchedulerException {
        SchedulerFactory sf = new StdSchedulerFactory();
        scheduler = sf.getScheduler();
        scheduler.clear();
        scheduler.start();
        schedulerId = scheduler.getSchedulerInstanceId();
    }

    public void shutdown() throws SchedulerException {
        if (scheduler != null && scheduler.isStarted()) {
            scheduler.shutdown();
        }
    }

    public void addJob(JobDetail jobDetail, Trigger trigger) throws SchedulerException {
        if (scheduler != null && scheduler.isStarted()) {
            if (trigger instanceof  SimpleTrigger) {
                SimpleTrigger simpleTrigger = (SimpleTrigger)trigger;
                logger.info(jobDetail.getKey() + " will run at: " + trigger.getNextFireTime()
                            + " and repeat: "
                            + simpleTrigger.getRepeatCount() + " times, every "
                            + simpleTrigger.getRepeatInterval() / 1000 + " seconds");
            } else {
                logger.info(jobDetail.getKey() + " will run at: " + trigger.getNextFireTime());
            }
            scheduler.scheduleJob(jobDetail, trigger);
        }

    }

    public void removeJob(JobKey jobKey) throws SchedulerException {
        if (scheduler != null && scheduler.checkExists(jobKey)) {
            scheduler.deleteJob(jobKey);
        }
    }

    public String getSchedulerId() {
        return schedulerId;
    }

    public void clearAllJob() throws SchedulerException {
        scheduler.clear();
    }

    public void triggerJob(String jobName, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(jobName, group);
        scheduler.triggerJob(jobKey);
    }
}

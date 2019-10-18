package com.alkaid.alltest.quartz.controller;

import static org.quartz.DateBuilder.futureDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import com.alkaid.alltest.quartz.common.Resource;
import com.alkaid.alltest.quartz.initialize.ThisApplicationRunner;
import com.alkaid.alltest.quartz.initialize.SchedulerManager;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    @RequestMapping(value = "/addJob/{group}/{prefix}/{count}/{interval}", method = RequestMethod.GET)
    public String addJob(@PathVariable(value="prefix") String prefix,
                         @PathVariable(value="group") String group,
                         @PathVariable(value="count") int count,
                         @PathVariable(value="interval") String interval)
            throws SchedulerException, ClassNotFoundException {

        if (prefix == null || prefix.isEmpty() || count <= 0) {
            return "bad request";
        }
        Class<? extends Job> clazz = getJobClass(prefix);

        long startIndex = Resource.redisUtil.incr(Resource.JOB_INDEX, 1);
        long endIndex = startIndex + count;
        for (long i = startIndex; i < endIndex; i ++) {
            String jobIdentity = "job_" + prefix + "_" + i;
            String triggerIdentity = "trigger_" + prefix + "_" + i;

            SchedulerManager schedulerManager = ThisApplicationRunner.getSchedulerManager();
            JobDetail jobDetail = buildJob(clazz, jobIdentity, group);
            Trigger trigger = buildTrigger(interval, triggerIdentity, group);
            schedulerManager.addJob(jobDetail, trigger);
        }
        return "add job succeed ! prefix: " + prefix + " count: " + count;
    }

    private JobDetail buildJob(Class<? extends Job> clazz, String jobIdentity, String group) {
        return newJob(clazz).withIdentity(jobIdentity, group).requestRecovery().build();
    }

    private Class<? extends Job> getJobClass(String prefix) throws ClassNotFoundException {
        return (Class<? extends Job>)Class.forName("com.alkaid.alltest.quartz.job.SimpleJob"+prefix);
    }

    private Trigger buildTrigger(String interval, String triggerIdentity, String group) {
        if (interval.contains("-")) {
            String[] args = interval.split("-");
            // 一次性任务
            return buildSimpleTrigger(triggerIdentity, group,
                                                 Integer.valueOf(args[0]), Integer.valueOf(args[1]));
        } else {
            // 周期性任务
            String cron;
            int n = Integer.valueOf(interval);
            if (n < 60) {
                cron = "0/" + interval + " * * * * ?";
            } else if (n < 3600){
                cron = "0 0/" + n/60 + " * * * ?";
            } else {
                cron = "0 0 0/" + n/3600 + " * * ?";
            }
            return builderCronTrigger(triggerIdentity, group, cron);
        }
    }

    private Trigger buildSimpleTrigger(String triggerIdentity, String group,
                                       int repeatCount, int interval) {
        return  TriggerBuilder.newTrigger()
                        .withIdentity(triggerIdentity, group)
                        .startAt(futureDate(1, DateBuilder.IntervalUnit.SECOND))
                        .withSchedule(
                                simpleSchedule().withRepeatCount(repeatCount).withIntervalInSeconds(interval))
                        .build();
    }

    private Trigger builderCronTrigger(String triggerIdentity, String group, String cron) {
        CronTriggerImpl trigger =  (CronTriggerImpl)TriggerBuilder.newTrigger()
                .withIdentity(triggerIdentity, group)
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .build();
        trigger.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
        return trigger;
    }

    @RequestMapping(value = "/removeJob/{jobName}/{group}", method = RequestMethod.GET)
    public String removeJob(@PathVariable(value="jobName") String jobName,
                            @PathVariable(value="group") String group)
            throws SchedulerException {
        SchedulerManager schedulerManager = ThisApplicationRunner.getSchedulerManager();
        JobKey jobKey = new JobKey(jobName, group);
        schedulerManager.removeJob(jobKey);
        return "removce job succeed !";
    }

    @RequestMapping(value = "/clearAllJob", method = RequestMethod.GET)
    public String clearAll() throws SchedulerException {
        SchedulerManager schedulerManager = ThisApplicationRunner.getSchedulerManager();
        schedulerManager.clearAllJob();
        return "clear all job succeed !";
    }

    @RequestMapping(value = "/triggerJob/{jobName}", method = RequestMethod.GET)
    public String triggerJob(@PathVariable(value="jobName") String jobName) throws SchedulerException {
        SchedulerManager schedulerManager = ThisApplicationRunner.getSchedulerManager();
        schedulerManager.triggerJob(jobName, schedulerManager.getSchedulerId());
        return "trigger job succeed !";
    }
}

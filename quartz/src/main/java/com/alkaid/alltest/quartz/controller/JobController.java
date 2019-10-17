package com.alkaid.alltest.quartz.controller;

import static org.quartz.DateBuilder.futureDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import com.alkaid.alltest.quartz.common.Resource;
import com.alkaid.alltest.quartz.job.SimpleJobA;
import com.alkaid.alltest.quartz.job.SimpleJobB;
import com.alkaid.alltest.quartz.job.SimpleJobC;
import com.alkaid.alltest.quartz.initialize.ThisApplicationRunner;
import com.alkaid.alltest.quartz.initialize.SchedulerManager;
import com.alkaid.alltest.quartz.job.SimpleJobD;
import com.alkaid.alltest.quartz.job.SimpleJobE;
import com.alkaid.alltest.quartz.job.SimpleJobF;
import org.quartz.CronScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    @RequestMapping(value = "/addJob/{prefix}/{count}/{interval}", method = RequestMethod.GET)
    public String addJob(@PathVariable(value="prefix") String prefix,
                         @PathVariable(value="count") int count,
                         @PathVariable(value="interval") String interval) throws SchedulerException {

        if (prefix == null || prefix.isEmpty() || count <= 0) {
            return "bad request";
        }
        Class<? extends Job> clazz = SimpleJobA.class;
        if (prefix.equals("B")) {
            clazz = SimpleJobB.class;
        } else if (prefix.equals("C")) {
            clazz = SimpleJobC.class;
        } else if (prefix.equals("D")) {
            clazz = SimpleJobD.class;
        } else if (prefix.equals("E")) {
            clazz = SimpleJobE.class;
        } else if (prefix.equals("F")) {
            clazz = SimpleJobF.class;
        }
        long startIndex = Resource.redisUtil.incr(Resource.JOB_INDEX, 1);
        long endIndex = startIndex + count;
        for (long i = startIndex; i < endIndex; i ++) {
            String jobIdentity = "job_" + prefix + "_" + i;
            String triggerIdentity = "trigger_" + prefix + "_" + i;

            SchedulerManager schedulerManager = ThisApplicationRunner.getSchedulerManager();
            JobDetail jobDetail = newJob(clazz)
                    .withIdentity(jobIdentity, schedulerManager.getSchedulerId())
                    .requestRecovery()
                    .build();

            if (interval.contains("-")) {
                String[] args = interval.split("-");
                // 一次性任务
                Trigger trigger = buildSimpleTrigger(triggerIdentity, schedulerManager,
                                                     Integer.valueOf(args[0]), Integer.valueOf(args[1]));
                schedulerManager.addJob(jobDetail, trigger);
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
                Trigger trigger = builderCronTrigger(triggerIdentity, schedulerManager, cron);
                schedulerManager.addJob(jobDetail, trigger);
            }
        }
        return "add job succeed ! prefix: " + prefix + " count: " + count;
    }

    private Trigger buildSimpleTrigger(String triggerIdentity, SchedulerManager schedulerManager,
                                       int repeatCount, int interval) {
        return  TriggerBuilder.newTrigger()
                        .withIdentity(triggerIdentity, schedulerManager.getSchedulerId())
                        .startAt(futureDate(1, DateBuilder.IntervalUnit.SECOND))
                        .withSchedule(
                                simpleSchedule().withRepeatCount(repeatCount).withIntervalInSeconds(interval))
                        .build();
    }

    private Trigger builderCronTrigger(String triggerIdentity, SchedulerManager schedulerManager, String cron) {
        return TriggerBuilder.newTrigger()
                .withIdentity(triggerIdentity, schedulerManager.getSchedulerId())
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .build();
    }

    @RequestMapping(value = "/removeJob/{jobName}", method = RequestMethod.GET)
    public String removeJob(@PathVariable(value="jobName") String jobName)
            throws SchedulerException {
        SchedulerManager schedulerManager = ThisApplicationRunner.getSchedulerManager();
        JobKey jobKey = new JobKey(jobName);
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
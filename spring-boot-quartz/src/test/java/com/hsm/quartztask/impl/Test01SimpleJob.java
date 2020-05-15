package com.hsm.quartztask.impl;

import com.hsm.quartztask.job.SimpleJob;
import org.junit.Before;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/5/13 11:40
 */
public class Test01SimpleJob {
    private static Scheduler scheduler;
    @Before
    public void init() throws Exception{
        StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
        scheduler = schedulerFactory.getScheduler();
        scheduler.start();
    }


    @Test
    public void simpleJob() throws Exception{
        // 构建Job信息
        JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class)
                .withIdentity("任务名称", "任务组")
                .withDescription("这是任务描述")
                .build();

        //构建触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("触发器名称", "触发器组")
                .startNow()
                .build();

        //任务调度器调度任务
        scheduler.scheduleJob(jobDetail, trigger);
        //睡眠防止程序立马停止
        TimeUnit.MINUTES.sleep(10);
    }
}
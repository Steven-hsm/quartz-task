package com.hsm.quartztask.service.impl;

import com.hsm.quartztask.job.SimpleJobWithData;
import org.junit.Before;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/5/13 14:37
 */
public class Test03JobBuilder {
    private static Scheduler scheduler;
    @Before
    public void init() throws Exception{
        StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
        scheduler = schedulerFactory.getScheduler();
        scheduler.start();
    }


    @Test
    public void jobData() throws Exception {

        //获取需要插入的数据
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("data", "需要使用的数据");

        JobKey JobKey = new JobKey("任务名", "任务组");
        // 构建Job信息
        JobDetail jobDetail = JobBuilder.newJob()
                .ofType(SimpleJobWithData.class)
                .withIdentity(JobKey)
                .withDescription("这是任务描述")
                .setJobData(jobDataMap)
                .usingJobData("data2", "第二种方式插入数据")
                .storeDurably(false)
                .requestRecovery(false)
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

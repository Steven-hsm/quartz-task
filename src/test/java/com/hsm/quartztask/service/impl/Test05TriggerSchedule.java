package com.hsm.quartztask.service.impl;

import com.hsm.quartztask.job.SimpleJobWithData;
import org.assertj.core.util.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/5/13 15:01
 */
public class Test05TriggerSchedule {
    private static Scheduler scheduler;
    @Before
    public void init() throws Exception{
        StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
        scheduler = schedulerFactory.getScheduler();
        scheduler.start();
    }


    @Test
    public void withSchedule() throws Exception {

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
        TriggerKey triggerKey = new TriggerKey("触发器名称", "触发器组");
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerKey)
                .withDescription("触发器描述")
                .startAt(new Date())
                .endAt(DateUtil.tomorrow())
                .withPriority(1)
                //.modifiedByCalendar("日程名称")
                .forJob(JobKey)
                .usingJobData("data3", "第二种方式插入数据")
                .withSchedule(
                        //设置调度的属性
                        SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).withRepeatCount(3)
                )
                .startNow()
                .build();

        //任务调度器调度任务
        scheduler.scheduleJob(jobDetail, trigger);
        //睡眠防止程序立马停止
        TimeUnit.MINUTES.sleep(10);
    }
}

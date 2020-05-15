package com.hsm.quartz.job.simple;

import com.hsm.quartz.job.InvoiceJob;
import com.hsm.quartz.job.util.DateUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/5/15 10:01
 */
public class SimpleTrigger {
    public static void main(String[] args) throws Exception{
        StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();

        //获取需要插入的数据
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("data", "需要使用的数据");

        JobKey JobKey = new JobKey("job1", "group1");
        // 构建Job信息
        JobDetail jobDetail = JobBuilder.newJob()
                .ofType(InvoiceJob.class)
                .withIdentity(JobKey)
                .withDescription("这是任务描述")
                .setJobData(jobDataMap)
                .usingJobData("data2", "第二种方式插入数据")
                .storeDurably(false)
                .requestRecovery(false)
                .build();

        //构建触发器
        TriggerKey triggerKey = new TriggerKey("simple", "simpleGroup");
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerKey)
                .withDescription("触发器描述")
                .startAt(new Date())
                .endAt(DateUtils.tomorrow())
                .withPriority(1)
                //这里如果要使用需要在调度器中指定
                //.modifiedByCalendar("日程名称")
                .forJob(JobKey)
                .usingJobData("data3", "第二种方式插入数据")
                .withSchedule(
                        //配置简单的触发器
                        SimpleScheduleBuilder
                                .simpleSchedule()
                                .withIntervalInSeconds(1)
                                .repeatForever())
                .startNow()
                .build();

        //任务调度器调度任务
        scheduler.scheduleJob(jobDetail, trigger);
        System.out.println("任务调度完毕");
    }
}

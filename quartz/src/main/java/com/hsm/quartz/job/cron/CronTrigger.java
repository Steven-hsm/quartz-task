package com.hsm.quartz.job.cron;

import com.hsm.quartz.job.InvoiceJob;
import com.hsm.quartz.job.util.DateUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/5/15 10:02
 */
public class CronTrigger {
    public static void main(String[] args) throws Exception{
        StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();

        //获取需要插入的数据
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("data", "需要使用的数据");

        JobKey JobKey = new JobKey("job2", "group2");
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
        TriggerKey triggerKey = new TriggerKey("cron", "cronGroup");
        // Cron表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder cron = CronScheduleBuilder.cronSchedule("*/5 * * * * ? 2020");

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerKey)
                .withDescription("触发器描述")
                .startAt(new Date())
                .endAt(DateUtils.tomorrow())
                .withPriority(1)
                //.modifiedByCalendar("日程名称")
                .forJob(JobKey)
                .usingJobData("data3", "第二种方式插入数据")
                .withSchedule(cron)
                .startNow()
                .build();

        //任务调度器调度任务
        scheduler.scheduleJob(jobDetail, trigger);
        //睡眠防止程序立马停止
        TimeUnit.MINUTES.sleep(10);
    }
}

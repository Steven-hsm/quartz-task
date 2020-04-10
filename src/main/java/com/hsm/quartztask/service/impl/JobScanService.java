package com.hsm.quartztask.service.impl;

import com.alibaba.fastjson.JSON;
import com.hsm.quartztask.common.BaseJob;
import com.hsm.quartztask.common.JobClassSet;
import com.hsm.quartztask.common.TaskPropertyRead;
import com.hsm.quartztask.common.enums.TriggerStateEnum;
import com.hsm.quartztask.entity.po.JobAndTriggerPO;
import com.hsm.quartztask.mapper.JobAndTriggerMapper;
import com.hsm.quartztask.service.IJobScanService;
import com.hsm.quartztask.util.ClassUtils;
import com.hsm.quartztask.util.JobUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/4/9 18:21
 */
@Service
@Slf4j
public class JobScanService implements IJobScanService {
    @Autowired
    private JobClassSet jobClassSet;
    @Autowired
    private TaskPropertyRead taskPropertyRead;
    @Autowired
    private JobAndTriggerMapper jobAndTriggerMapper;
    @Autowired
    private Scheduler scheduler;

    @Override
    public void scanJobClass() {
        String scanPackage = taskPropertyRead.getScanPackage();
        if ("com.hsm.quartztask.job".equals(scanPackage)) {
            log.error("没有配置spring.quartz.jobspring.package,你的任务可能无法启动");
        }
        //获取jar下所有的java文件
        List<Class<?>> classes = ClassUtils.getClasses(scanPackage);
        //剔除不是BaseJob的文件
        classes.removeIf(clazz -> !BaseJob.class.isAssignableFrom(clazz));
        //将所有扫描的类放入到set集合中
        classes.forEach(clazz -> {
            jobClassSet.getJobClasses().add(clazz.getName());
        });
        //获取所有不是删除状态的任务,即需要执行
        List<JobAndTriggerPO> tableList = jobAndTriggerMapper.selectScheduledJob(TriggerStateEnum.DELETE.getState());
        tableList.forEach(jobAndTrigger -> {
            //表中存在就直接调度任务启动
            if (jobClassSet.getJobClasses().contains(jobAndTrigger.getJobClass())) {
// 构建Job信息
                jobClassSet.getScheduledClass().add(jobAndTrigger.getJobClass());
                try {
                    JobDetail jobDetail = JobBuilder.newJob(JobUtil.getClass(jobAndTrigger.getJobClass()).getClass()).withIdentity(jobAndTrigger.getJobName(), jobAndTrigger.getJobGroup()).withDescription(jobAndTrigger.getDescription()).build();

                    // Cron表达式调度构建器(即任务执行的时间)
                    CronScheduleBuilder cron = CronScheduleBuilder.cronSchedule(jobAndTrigger.getCronExpression());

                    //根据Cron表达式构建一个Trigger
                    CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobAndTrigger.getJobName(), jobAndTrigger.getJobGroup()).withSchedule(cron).build();
                    if (TriggerStateEnum.NORMAL.getState().equals(jobAndTrigger.getTriggerState())) {
                        scheduler.scheduleJob(jobDetail, trigger);
                    }else if(TriggerStateEnum.PAUSED.getState().equals(jobAndTrigger.getTriggerState())){
                        scheduler.scheduleJob(jobDetail, trigger);
                        scheduler.pauseJob(JobKey.jobKey(jobAndTrigger.getJobName(), jobAndTrigger.getJobGroup()));
                    }
                } catch (Exception e) {
                    log.error("触发{}任务异常", JSON.toJSON(jobAndTrigger), e);
                }
            }
            jobClassSet.getToAddClass().addAll(jobClassSet.getJobClasses());
            jobClassSet.getToAddClass().removeAll(jobClassSet.getScheduledClass());
        });


    }
}

package com.hsm.quartztask.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsm.quartztask.common.*;
import com.hsm.quartztask.common.enums.TriggerStateEnum;
import com.hsm.quartztask.entity.bo.JobInfoBO;
import com.hsm.quartztask.entity.po.JobAndTriggerPO;
import com.hsm.quartztask.entity.vo.JobCronVO;
import com.hsm.quartztask.entity.vo.JobFormVO;
import com.hsm.quartztask.entity.vo.JobKeyVO;
import com.hsm.quartztask.mapper.JobAndTriggerMapper;
import com.hsm.quartztask.service.IJobService;
import com.hsm.quartztask.util.JobUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/4/9 12:01
 */
@Service
@Slf4j
public class JobService implements IJobService {

    @Autowired
    private Scheduler scheduler;
    @Autowired
    private JobAndTriggerMapper jobAndTriggerMapper;
    @Autowired
    private JobClassSet jobClassSet;

    @Override
    public ResponseBO addJob(JobFormVO jobFormVO) throws Exception {

        // 启动调度器
        scheduler.start();
        JobAndTriggerPO jobAndTrigger = jobAndTriggerMapper.getByKey(jobFormVO.getJobName(), jobFormVO.getJobGroup());
        if(jobAndTrigger == null){
            jobAndTrigger = new JobAndTriggerPO();
            BeanUtils.copyProperties(jobFormVO, jobAndTrigger);
            jobAndTrigger.setTriggerName(jobFormVO.getJobName());
            jobAndTrigger.setTriggerGroup(jobFormVO.getJobGroup());
            jobAndTrigger.setTriggerState(TriggerStateEnum.NORMAL.getState());
            jobAndTrigger.setCtime(System.currentTimeMillis());
            jobAndTriggerMapper.insert(jobAndTrigger);
        }else{
            jobAndTriggerMapper.updateState(jobFormVO.getJobName(), jobFormVO.getJobGroup(), TriggerStateEnum.NORMAL.getState());
        }

        // 构建Job信息
        JobDetail jobDetail = JobBuilder.newJob(JobUtil.getClass(jobFormVO.getJobClass()).getClass()).withIdentity(jobFormVO.getJobName(), jobFormVO.getJobGroup()).withDescription(jobFormVO.getDescription()).build();

        // Cron表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder cron = CronScheduleBuilder.cronSchedule(jobFormVO.getCronExpression());

        //根据Cron表达式构建一个Trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobFormVO.getJobName(), jobFormVO.getJobGroup()).withSchedule(cron).build();
        try {

            scheduler.scheduleJob(jobDetail, trigger);
            jobClassSet.getToAddClass().remove(jobFormVO.getJobClass());
        } catch (SchedulerException e) {
            log.error("【定时任务】创建失败！", e);
            throw new Exception("【定时任务】创建失败！");
        }
        return ResponseBO.success("添加成功");
    }

    @Override
    public ResponseBO deleteJob(JobKeyVO jobKeyVO) throws  Exception{
        JobAndTriggerPO jobAndTrigger = jobAndTriggerMapper.getByKey(jobKeyVO.getJobName(), jobKeyVO.getJobGroup());
        if(jobAndTrigger == null){
            return ResponseBO.failure("数据库存储异常");
        }
        jobAndTriggerMapper.updateState(jobKeyVO.getJobName(),jobKeyVO.getJobGroup(),TriggerStateEnum.DELETE.getState());
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobKeyVO.getJobName(), jobKeyVO.getJobGroup()));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobKeyVO.getJobName(), jobKeyVO.getJobGroup()));
        scheduler.deleteJob(JobKey.jobKey(jobKeyVO.getJobName(), jobKeyVO.getJobGroup()));
        jobClassSet.getToAddClass().add(jobAndTrigger.getJobClass());
        return ResponseBO.success("删除成功");
    }

    @Override
    public ResponseBO pauseJob(JobKeyVO jobKeyVO) throws Exception {
        jobAndTriggerMapper.updateState(jobKeyVO.getJobName(),jobKeyVO.getJobGroup(),TriggerStateEnum.PAUSED.getState());
        scheduler.pauseJob(JobKey.jobKey(jobKeyVO.getJobName(), jobKeyVO.getJobGroup()));
        return ResponseBO.success("暂停成功");
    }

    @Override
    public ResponseBO resumeJob(JobKeyVO jobKeyVO) throws Exception {
        jobAndTriggerMapper.updateState(jobKeyVO.getJobName(),jobKeyVO.getJobGroup(),TriggerStateEnum.NORMAL.getState());
        scheduler.resumeJob(JobKey.jobKey(jobKeyVO.getJobName(), jobKeyVO.getJobGroup()));
        return ResponseBO.success("重启成功");
    }

    @Override
    public ResponseBO cronJob(JobCronVO jobCronVO) throws Exception {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobCronVO.getJobName(), jobCronVO.getJobGroup());
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobCronVO.getCronExpression());

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 根据Cron表达式构建一个Trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            jobAndTriggerMapper.updateCron(jobCronVO.getJobName(),jobCronVO.getJobGroup(),jobCronVO.getCronExpression());
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.error("【定时任务】更新失败！", e);
            throw new Exception("【定时任务】创建失败！");
        }
        return ResponseBO.success("编辑成功");
    }

    @Override
    public ResponseBO<PageBO<JobInfoBO>> listJob(QueryVO queryVO)  throws Exception {
        List<JobInfoBO> list = new ArrayList<>();
        try {
            List<String> triggerGroupNames = scheduler.getTriggerGroupNames();
            for (String triggerGroup : triggerGroupNames) {
                Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(GroupMatcher.groupEquals(triggerGroup));
                for (TriggerKey triggerKey : triggerKeys) {
                    Trigger trigger = scheduler.getTrigger(triggerKey);

                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    JobKey jobKey = trigger.getJobKey();
                    JobDetail jobDetail = scheduler.getJobDetail(jobKey);

                    JobInfoBO info = new JobInfoBO();
                    info.setTriggerName(triggerKey.getName());
                    info.setTriggerGroup(triggerKey.getGroup());

                    info.setJobName(jobKey.getName());
                    info.setJobGroup(jobKey.getGroup());
                    info.setJobClass(jobDetail.getJobClass().getName());
                    info.setDescription(jobDetail.getDescription());
                    info.setTriggerState(triggerState.name());
                    if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger = (CronTrigger) trigger;
                        info.setCronExpression(cronTrigger.getCronExpression());
                    } else {
                        info.setCronExpression("not cron");
                    }
                    info.setCreateTime(trigger.getStartTime());
                    info.setPreviousFireTime(trigger.getPreviousFireTime());
                    info.setNextFireTime(trigger.getNextFireTime());
                    list.add(info);
                }
            }
        } catch (SchedulerException e) {
            log.error("获取定时任务异常", e);
            throw  e;
        }
        return ResponseBO.success(PageUtils.transferToPage(list, queryVO));
    }

    @Override
    public ResponseBO executeJob(JobKeyVO jobKeyVO) throws Exception{
        try {
            log.info("触发jobName={},jobGroup={}", jobKeyVO.getJobName(), jobKeyVO.getJobGroup());
            JobKey jobKey = new JobKey(jobKeyVO.getJobName(), jobKeyVO.getJobGroup());
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            log.error("触发job失败, jobName={},jobGroup={},e={}", jobKeyVO.getJobName(), jobKeyVO.getJobGroup(), e);
            throw new Exception("任务执行失败");
        }
        return ResponseBO.success("任务执行成功");
    }

    @Override
    public ResponseBO<PageBO<JobAndTriggerPO>> listJob2(QueryVO queryVO) {
        IPage<JobAndTriggerPO> page = jobAndTriggerMapper.list(new Page(queryVO.getCurrentPage(),queryVO.getPageSize()));
        return ResponseBO.success(PageUtils.transferToPage(page.getRecords(), queryVO));
    }
}

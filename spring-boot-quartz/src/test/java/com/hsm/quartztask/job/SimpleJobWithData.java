package com.hsm.quartztask.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/5/13 13:55
 */
@Slf4j
public class SimpleJobWithData implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //获取传入的数据
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        jobDataMap.forEach((key, value) -> {
            log.info("{}\t{}", key, value);
        });


    }
}

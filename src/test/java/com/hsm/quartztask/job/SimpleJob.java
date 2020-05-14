package com.hsm.quartztask.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/5/13 13:55
 */
@Slf4j
public class SimpleJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("一个简单的任务正在执行中");
    }
}

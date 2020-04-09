package com.hsm.quartztask.common;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/4/9 13:29
 */
public interface BaseJob extends Job {
    @Override
    void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException;
}

package com.hsm.quartztask.job;

import com.hsm.quartztask.common.BaseJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/4/9 22:20
 */
@Slf4j
public class CllJob implements BaseJob {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("开始处理任务:{}" ,new Date());
    }
}

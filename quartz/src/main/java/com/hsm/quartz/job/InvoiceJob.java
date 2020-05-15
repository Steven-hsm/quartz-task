package com.hsm.quartz.job;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/5/15 09:57
 */
@Slf4j
@Data
public class InvoiceJob implements Job {
    private String trigger;
    private String triggerGroup;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("{},{}任务正在执行:", this.trigger, this, trigger);
    }
}

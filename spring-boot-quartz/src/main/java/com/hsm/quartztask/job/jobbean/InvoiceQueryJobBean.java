package com.hsm.quartztask.job.jobbean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author huangsenming
 * @Description: 继承spring-quartz
 * @date 2020/5/14 11:45
 */
@Data
@Slf4j
public class InvoiceQueryJobBean extends QuartzJobBean {
    /**
     * QuartzJobBean 和Job的区别就是QuartzJobBean将job的datamap数据直接放到实例对象的属性中
     */
    private String name;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        //TODO 发票查询
        log.info("从dataMap中获取的数据:{}", this.getName());
    }
}

package com.hsm.quartztask.config.springquartz;


import com.hsm.quartztask.config.springquartz.jobfactory.QuartzJobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;


/**
 * @author huangsenming
 * @Description:
 * @date 2020/5/14 11:55
 */

@Configuration
@EnableAutoConfiguration
public class ScheduleBean {
    @Autowired
    private QuartzJobFactory quartzJobFactory;
    @Autowired
    private CronTriggerFactoryBean invoiceQueryTrigger;

    @Bean
    public SchedulerFactoryBean scheduler(@Qualifier("dataSource") DataSource dataSource){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(quartzJobFactory);
        schedulerFactoryBean.setDataSource(dataSource);
        //schedulerFactoryBean.setTriggers(invoiceQueryTrigger.getObject());
        return schedulerFactoryBean;
    }
}

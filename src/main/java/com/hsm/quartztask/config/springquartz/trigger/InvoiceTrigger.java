package com.hsm.quartztask.config.springquartz.trigger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/5/14 11:56
 */
@Component
public class InvoiceTrigger {

    @Autowired
    private JobDetailFactoryBean invoiceQueryJobDetail;

    @Bean("invoiceQueryTrigger")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public CronTriggerFactoryBean cronTriggerFactoryBean() {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setName("触发器名称");
        cronTriggerFactoryBean.setGroup("触发器组名称");
        cronTriggerFactoryBean.setJobDetail(invoiceQueryJobDetail.getObject());
        cronTriggerFactoryBean.setCronExpression("*/5 * * * * ?");
        return cronTriggerFactoryBean;
    }
}

package com.hsm.quartztask.config.springquartz.jobfactory;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/5/14 12:01
 */
@Component
public class QuartzJobFactory extends AdaptableJobFactory {
    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);
        //这里将属性直接注入,那么就可以在job中使用spring中的bean了
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}

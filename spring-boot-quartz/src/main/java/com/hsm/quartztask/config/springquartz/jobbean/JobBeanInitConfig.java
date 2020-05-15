package com.hsm.quartztask.config.springquartz.jobbean;

import com.hsm.quartztask.job.jobbean.InvoiceQueryJobBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/5/14 11:55
 */
@Component
public class JobBeanInitConfig {

    @Bean("invoiceQueryJobDetail")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public JobDetailFactoryBean jobDetailFactoryBean() {
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("name", "开票查询");
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setName("任务名");
        jobDetailFactoryBean.setGroup("任务组");
        jobDetailFactoryBean.setJobClass(InvoiceQueryJobBean.class);
        jobDetailFactoryBean.setJobDataAsMap(dataMap);
        // 必须设置为true，如果为false，当没有活动的触发器与之关联时会在调度器中会删除该任务
        jobDetailFactoryBean.setDurability(true);
        return jobDetailFactoryBean;
    }
}

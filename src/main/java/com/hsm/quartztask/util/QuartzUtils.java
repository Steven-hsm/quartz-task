package com.hsm.quartztask.util;

import lombok.extern.slf4j.Slf4j;
import org.quartz.CronExpression;
import org.quartz.Job;
import org.quartz.Trigger;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: huangsm
 * @Date: 2019/5/28 17:30
 * @Description: 定时任务初始化工具类:目的就是为了抽出模板代码
 */
@Slf4j
public final class QuartzUtils {
    private QuartzUtils() {
    }
    /**
     * @Author: huangsm on 2019/5/28 17:58
     * @param: [cronExpression] 0 0/5 * * * ?
     * @return: boolean
     * @Description: 检查cron表达式是否正确
     */
    public static boolean checkCron(String cronExpression){
        return CronExpression.isValidExpression(cronExpression);
    }
    /**
     * @Author: huangsm on 2019/5/28 18:46
     * @param: [cronExpression]
     * @return: void
     * @Description: 每隔五秒打印一次下次执行时间
     */
    public static void printExeTimeWith5Senconds(String cronExpression){
        CronExpression cron ;
        Date now = new Date();
        try {
            cron = new CronExpression(cronExpression);
            //上次执行时间
            Date lastDate = now;
            while(true){
                log.info("执行时间：{}", lastDate);
                lastDate = cron.getNextValidTimeAfter(lastDate);

                Thread.sleep(5000L);
            }
        } catch (ParseException e) {
            log.error("cron表达式解析异常", e);
        }catch (Exception e){
            log.error("打印cron表达式执行时间异常", e);
        }
    }

}

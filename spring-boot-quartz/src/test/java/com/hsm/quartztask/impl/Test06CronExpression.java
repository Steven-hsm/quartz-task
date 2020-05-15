package com.hsm.quartztask.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.spi.MutableTrigger;

import java.util.Date;

/**
 * @author huangsenming
 * @Description: cron表达式
 * seconds 秒 (, - * /)
 * minutes 分 (, - * /)
 * hours 小时 (, - * /)
 * daysOfMonth 月份的第几天 1-31 (,- * ? / L W C )
 * months 月份 0-11 (, - * /)
 * daysOfWeek 每一周的第几天 1-7 分别表示周日到周六 , (- * ? / L C #)
 * years 年 最大为当前年份+100年 (, - * /)
 *
 * * 表示匹配任意值
 * ? daysOfMonth 和daysOfWeek 必须有一个为 ? 号,其他地方都不可用
 * - 表示范围如 1-7
 * a/b 第a开始,没过b触发一次
 * , 表示多值 a,b 表示在第a和第b都执行
 * L 表示最后
 * W 表示有效工作日,只能用在daysOfMonth
 * LW连用只能用在daysOfMonth,表示这个月份的最后一个工作日
 * # 只能用在 只能用在daysOfWeek 例如 4#2 表示某月的第二个周三
 * @date 2020/5/13 16:05
 */
@Slf4j
public class Test06CronExpression {

    @Test
    public void cronExpression() throws Exception{
        CronExpression cronExpression = new CronExpression("*/5 * * * * ? 2020");
        log.info(cronExpression.getTimeAfter(new Date()).toString());
    }

    @Test
    public void cronBuild(){
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("*/5 * * * * ? 2020");
        MutableTrigger build = cronScheduleBuilder.build();
    }

    @Test
    public void cornVerify(){
        Assert.assertTrue(CronExpression.isValidExpression("*/5 * * * * ? 2020"));
    }

    @Test
    public void nextTime() throws Exception{
        CronExpression cronExpression = new CronExpression("*/5 * * * * ? 2020");
        //上次执行时间
        Date lastDate = new Date();
        while(true){
            log.info("执行时间：{}", lastDate);
            lastDate = cronExpression.getNextValidTimeAfter(lastDate);
            Thread.sleep(5000L);
        }
    }


}

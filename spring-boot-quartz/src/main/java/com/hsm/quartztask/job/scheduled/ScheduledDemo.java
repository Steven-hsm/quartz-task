package com.hsm.quartztask.job.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/5/14 14:55
 */
@Slf4j
@Component
public class ScheduledDemo {
    @Scheduled(cron = "*/5 * * * * ? ")
    void init() {
        log.info("spring 自己的简单定时任务");
    }
}

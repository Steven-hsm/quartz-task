package com.hsm.quartztask;


import com.hsm.quartztask.service.IJobScanService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 启动成功事件
 */
@Component
@Slf4j
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private IJobScanService jobScanService;
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        log.info("=============springboot启动成功===============");
        //扫码job包
        jobScanService.scanJobClass();

    }
}
package com.hsm.quartztask;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.hsm.quartztask.mapper")
public class QuartzTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzTaskApplication.class, args);
    }

}

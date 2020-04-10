package com.hsm.quartztask;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hsm.quartztask.mapper")
public class QuartzTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzTaskApplication.class, args);
    }

}

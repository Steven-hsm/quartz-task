package com.hsm.quartztask.service.impl;

import com.alibaba.fastjson.JSON;
import com.hsm.quartztask.common.PageBO;
import com.hsm.quartztask.common.QueryVO;
import com.hsm.quartztask.common.ResponseBO;
import com.hsm.quartztask.entity.po.JobAndTriggerPO;
import com.hsm.quartztask.service.IJobService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/4/10 15:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class JobServiceTest {
    @Autowired
    private IJobService jobService;

    @Test
    void listJob2() {
        ResponseBO<PageBO<JobAndTriggerPO>> pageBOResponseBO = jobService.listJob2(new QueryVO(1, 10));
        System.out.println(JSON.toJSONString(pageBOResponseBO));
    }

    @Test
    void list() throws IOException {
        InputStream in = JobServiceTest.class.getClassLoader().getResourceAsStream("config/config.properties");
        Properties properties = new Properties();
        properties.load(in);

        properties.forEach((key,value) ->{
            System.out.println("key:" + key );
            System.out.println("value:" + value );
            System.out.println();
        });
    }
}
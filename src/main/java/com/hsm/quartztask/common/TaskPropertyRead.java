package com.hsm.quartztask.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/4/9 18:31
 */
@Data
@Component
public class TaskPropertyRead {
    @Value("${spring.quartz.job.package}")
    private String scanPackage;
}

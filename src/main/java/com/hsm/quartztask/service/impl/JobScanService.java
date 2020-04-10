package com.hsm.quartztask.service.impl;

import com.hsm.quartztask.common.BaseJob;
import com.hsm.quartztask.common.JobClassSet;
import com.hsm.quartztask.common.TaskPropertyRead;
import com.hsm.quartztask.service.IJobScanService;
import com.hsm.quartztask.util.ClassUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/4/9 18:21
 */
@Service
@Slf4j
public class JobScanService implements IJobScanService {
    @Autowired
    private JobClassSet jobClassSet;
    @Autowired
    private TaskPropertyRead taskPropertyRead;
    
    @Override
    public void scanJobClass() {
        String scanPackage = taskPropertyRead.getScanPackage();
        if("com.hsm.quartztask.job".equals(scanPackage)){
            log.error("没有配置spring.quartz.jobspring.package,你的任务可能无法启动" );
        }
        //获取jar下所有的java文件
        List<Class<?>> classes = ClassUtils.getClasses(scanPackage);
        //剔除不是BaseJob的文件
        classes.removeIf(clazz -> !BaseJob.class.isAssignableFrom(clazz));

        classes.forEach(clazz -> {
            jobClassSet.getJobClasses().add(clazz.getName());
        });
    }
}

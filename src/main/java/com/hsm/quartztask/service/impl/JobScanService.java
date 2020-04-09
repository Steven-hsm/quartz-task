package com.hsm.quartztask.service.impl;

import com.hsm.quartztask.common.JobClassSet;
import com.hsm.quartztask.service.IJobScanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void scanJobClass() {


    }
}

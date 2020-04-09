package com.hsm.quartztask.controller.scan;

import com.hsm.quartztask.common.JobClassSet;
import com.hsm.quartztask.common.ResponseBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/4/9 22:51
 */
@RestController
@Slf4j
@RequestMapping("/job/scan")
@Api(value = "任务扫码结果",tags = "任务扫码结果")
public class ScanResultController {
    @Autowired
    private JobClassSet jobClassSet;
    @ApiOperation(value = "列表查询",httpMethod = "GET")
    @GetMapping("/list")
    public ResponseBO list(){
        return ResponseBO.success(jobClassSet);
    }
}

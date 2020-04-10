package com.hsm.quartztask.controller.job;

import com.hsm.quartztask.common.PageBO;
import com.hsm.quartztask.common.QueryVO;
import com.hsm.quartztask.common.ResponseBO;
import com.hsm.quartztask.entity.bo.JobInfoBO;
import com.hsm.quartztask.entity.po.JobAndTriggerPO;
import com.hsm.quartztask.entity.vo.JobCronVO;
import com.hsm.quartztask.entity.vo.JobFormVO;
import com.hsm.quartztask.entity.vo.JobKeyVO;
import com.hsm.quartztask.service.IJobService;
import com.hsm.quartztask.util.QuartzUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author huangsenming
 * @Description: 任务控制器
 * @date 2020/4/9 11:56
 */
@RestController
@Slf4j
@RequestMapping("/job")
@Api(value = "任务控制",tags = "任务控制")
public class JobController {

    @Autowired
    private IJobService jobService;

    @ApiOperation(value = "添加任务",httpMethod = "POST")
    @PostMapping
    public ResponseBO addJob(@RequestBody @Validated JobFormVO JobFormVO){
        try{
            return jobService.addJob(JobFormVO);
        }catch (Exception e){
            log.error("服务处理异常,{}", e);
            return ResponseBO.failure("添加失败");
        }
    }

    @ApiOperation(value = "删除任务",httpMethod = "DELETE")
    @DeleteMapping
    public ResponseBO deleteJob(@RequestBody @Validated JobKeyVO jobKeyVO){
        try{
            return jobService.deleteJob(jobKeyVO);
        }catch (Exception e){
            log.error("服务处理异常,{}", e);
            return ResponseBO.failure("删除失败");
        }
    }

    @ApiOperation(value = "暂停任务",httpMethod = "PUT")
    @PutMapping("/pause")
    public ResponseBO pauseJob(@RequestBody @Validated JobKeyVO jobKeyVO){
        try{
            return jobService.pauseJob(jobKeyVO);
        }catch (Exception e){
            log.error("服务处理异常,{}", e);
            return ResponseBO.failure("删除失败");
        }
    }

    @ApiOperation(value = "重启任务",httpMethod = "PUT")
    @PutMapping("/resume")
    public ResponseBO resumeJob(@RequestBody @Validated JobKeyVO jobKeyVO){
        try{
            return jobService.resumeJob(jobKeyVO);
        }catch (Exception e){
            log.error("服务处理异常,{}", e);
            return ResponseBO.failure("重启失败");
        }
    }

    @ApiOperation(value = "修改cron表达式",httpMethod = "PUT")
    @PutMapping("/cron")
    public ResponseBO cronJob(@RequestBody @Validated JobCronVO jobCronVO){
        if(!QuartzUtils.checkCron(jobCronVO.getCronExpression())){
            return ResponseBO.failure("cron表达式输入有误,请仔细检查");
        }
        try{
            return jobService.cronJob(jobCronVO);
        }catch (Exception e){
            log.error("服务处理异常,{}", e);
            return ResponseBO.failure("重启失败");
        }
    }

    @ApiOperation(value = "获取任务列表",httpMethod = "GET")
    @GetMapping("/list")
    public ResponseBO<PageBO<JobInfoBO>> listJob(QueryVO queryVO){
        try{
            return jobService.listJob(queryVO);
        }catch (Exception e){
            log.error("服务处理异常,{}", e);
            return ResponseBO.failure("列表获取失败");
        }
    }

    @ApiOperation(value = "获取任务列表",httpMethod = "GET")
    @GetMapping("/list2")
    public ResponseBO<PageBO<JobAndTriggerPO>> listJob2(QueryVO queryVO){
        try{
            return jobService.listJob2(queryVO);
        }catch (Exception e){
            log.error("服务处理异常,{}", e);
            return ResponseBO.failure("列表获取失败");
        }
    }




}

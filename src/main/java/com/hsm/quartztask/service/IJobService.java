package com.hsm.quartztask.service;

import com.hsm.quartztask.common.PageBO;
import com.hsm.quartztask.common.QueryVO;
import com.hsm.quartztask.common.ResponseBO;
import com.hsm.quartztask.entity.bo.JobInfoBO;
import com.hsm.quartztask.entity.vo.JobCronVO;
import com.hsm.quartztask.entity.vo.JobFormVO;
import com.hsm.quartztask.entity.vo.JobKeyVO;

import java.util.List;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/4/9 12:01
 */
public interface IJobService {
    /**
     * 添加任务
     * @param jobFormVO
     * @return
     */
    ResponseBO addJob(JobFormVO jobFormVO) throws Exception;

    /**
     * 删除任务
     * @param jobKeyVO
     * @return
     */
    ResponseBO deleteJob(JobKeyVO jobKeyVO) throws  Exception;

    /**
     * 暂停任务
     * @param jobKeyVO
     * @return
     */
    ResponseBO pauseJob(JobKeyVO jobKeyVO)  throws  Exception;

    /**
     * 重启任务
     * @param jobKeyVO
     * @return
     */
    ResponseBO resumeJob(JobKeyVO jobKeyVO) throws Exception ;

    /**
     * 修改任务定时器
     * @param jobCronVO
     * @return
     */
    ResponseBO cronJob(JobCronVO jobCronVO) throws Exception ;

    /**
     * 获取任务列表
     * @param queryVO
     * @return
     */
    ResponseBO<PageBO<JobInfoBO>> listJob(QueryVO queryVO)  throws Exception ;
}

package com.hsm.quartztask.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsm.quartztask.entity.po.JobAndTriggerPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/4/10 15:11
 */
@Mapper
public interface JobAndTriggerMapper extends BaseMapper<JobAndTriggerPO> {

    List<JobAndTriggerPO> list();

    IPage<JobAndTriggerPO> list(Page tPage);

    /**
     * 更新定时器状态
     * @param jobName
     * @param jobGroup
     * @param triggerState
     * @return
     */
    @Update("UPDATE job_trigger SET trigger_state = #{triggerState} " +
            "WHERE job_name=#{jobName} AND job_group = #{jobGroup}")
    Integer updateState(@Param("jobName")String jobName, @Param("jobGroup")String jobGroup, @Param("triggerState")String triggerState);

    /**
     * 根据唯一索引查询数据
     * @param jobName
     * @param jobGroup
     * @return
     */
    @Select("SELECT * FROM job_trigger " +
            "WHERE job_name=#{jobName} AND job_group = #{jobGroup} ")
    JobAndTriggerPO getByKey(@Param("jobName")String jobName, @Param("jobGroup")String jobGroup);

    /**
     * 更新任务的cron表达式
     * @param jobName
     * @param jobGroup
     * @param cronExpression
     * @return
     */
    @Update("UPDATE job_trigger SET cron_expression = #{cron_expression} " +
            "WHERE job_name=#{jobName} AND job_group = #{jobGroup}")
    Integer updateCron(String jobName, String jobGroup, String cronExpression);
}

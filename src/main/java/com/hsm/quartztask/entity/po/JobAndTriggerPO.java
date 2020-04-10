package com.hsm.quartztask.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigInteger;

/**
 * <p>
 * 实体类
 * </p>
 *
 * @package: com.xkcoding.task.quartz.entity.domain
 * @description: 实体类
 * @author: yangkai.shen
 * @date: Created in 2018-11-26 15:05
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
@TableName("job_trigger")
public class JobAndTriggerPO {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    /**
     * 定时任务名称
     */
    private String jobName;
    /**
     * 定时任务组
     */
    private String jobGroup;
    /**
     * 定时任务全类名
     */
    private String jobClass;
    /**
     * 触发器名称
     */
    private String triggerName;
    /**
     * 触发器组
     */
    private String triggerGroup;
    /**
     * cron 表达式
     */
    private String cronExpression;
    /**
     * 定时任务状态
     */
    private String triggerState;
    /**
     * 添加时间
     */
    private Long ctime;
}
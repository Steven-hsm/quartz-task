package com.hsm.quartztask.entity.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: huangsenming
 * @Date: 2019/5/28 20:25
 * @Description:
 */
@Data
@ApiModel("cron表达式参数")
public class JobInfoBO {
    @ApiModelProperty("触发器名称")
    private String triggerName;
    @ApiModelProperty("触发器组")
    private String triggerGroup;
    @ApiModelProperty("任务名")
    private String jobName;
    @ApiModelProperty("任务组")
    private String jobGroup;
    @ApiModelProperty("任务描述")
    private String description;
    @ApiModelProperty("任务描述")
    private String triggerState;
    @ApiModelProperty("cron表达式")
    private String cronExpression;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("上次执行时间")
    private Date previousFireTime;
    @ApiModelProperty("下次执行时间")
    private Date nextFireTime;
    @ApiModelProperty("任务类")
    private String jobClass;
}

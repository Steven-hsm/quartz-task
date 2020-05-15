package com.hsm.quartztask.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/4/9 14:30
 */
@Data
@ApiModel("cron表达式参数")
public class JobCronVO {
    @ApiModelProperty("cron表达式")
    @NotEmpty(message = "cron表达式不能为空")
    private String cronExpression;

    @ApiModelProperty("任务名称")
    @NotEmpty(message = "任务名称不能为空")
    private String jobName;

    @ApiModelProperty("任务组名称")
    @NotEmpty(message = "任务组名称不能为空")
    private String jobGroup;
}

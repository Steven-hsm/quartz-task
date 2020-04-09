package com.hsm.quartztask.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/4/9 12:04
 */
@ApiModel("任务表单参数")
@Data
public class JobFormVO {
    @ApiModelProperty("任务名称")
    @NotEmpty(message = "任务名称不能为空")
    private String jobName;

    @ApiModelProperty("任务组名称")
    @NotEmpty(message = "任务组名称不能为空")
    private String jobGroup;

    @ApiModelProperty("任务类")
    @NotEmpty(message = "任务类不能为空")
    private String jobClass;

    @ApiModelProperty("任务描述")
    private String description;

    @ApiModelProperty("cron表达式")
    @NotEmpty
    @NotEmpty(message = "cron表达式不能为空")
    private String cronExpression;
}

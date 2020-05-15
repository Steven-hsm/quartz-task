package com.hsm.quartztask.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/4/10 09:40
 */
@ApiModel("分页反参")
@Data
public class PageBO<T> {
    @ApiModelProperty("详细数据")
    private List<T> data;
    @ApiModelProperty("总数据量")
    private int totalCount;
    @ApiModelProperty("开始页")
    private int start;
    @ApiModelProperty("结束页")
    private int end;

    public PageBO() {
        this.data = new ArrayList<>();
        this.totalCount = 0;
        this.start = 0;
        this.end = 0;
    }
}

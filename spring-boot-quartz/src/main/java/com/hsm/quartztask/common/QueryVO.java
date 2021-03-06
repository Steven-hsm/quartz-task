package com.hsm.quartztask.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/4/10 09:32
 */
@Data
@ApiModel("分页入参")
public class QueryVO {
    @ApiModelProperty("分页入参")
    private int page;
    @ApiModelProperty("分页大小")
    private int perSize;

    public QueryVO() {
    }

    public QueryVO(int currentPage, int pageSize) {
        this.page = page;
        this.perSize = perSize;
    }
}

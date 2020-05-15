package com.hsm.quartztask.common.enums;

import lombok.Getter;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/4/10 16:06
 */
public enum TriggerStateEnum {
    NORMAL("NORMAL","运行"),
    DELETE("DELETE","删除"),
    PAUSED("PAUSED","停止"),
    ;

    @Getter
    private String state;
    @Getter
    private String desc;
    TriggerStateEnum(String state, String desc) {
        this.state = state;
        this.desc = desc;
    }

}

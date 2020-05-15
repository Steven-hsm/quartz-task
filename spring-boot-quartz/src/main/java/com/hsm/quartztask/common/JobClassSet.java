package com.hsm.quartztask.common;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author huangsenming
 * @Description: 任务类集合
 * @date 2020/4/9 18:27
 */
@Component
@Data
public class JobClassSet {
    /**
     * 扫描的所有任务类
     */
    private Set<String> jobClasses = new HashSet<>(32);
    /**
     * 正在被调度的任务类,包含正常,以及停止的任务
     */
    private Set<String> scheduledClass = new HashSet<>(32);
    /**
     * 可以添加的任务类
     */
    private Set<String> toAddClass = new HashSet<>(32);


}

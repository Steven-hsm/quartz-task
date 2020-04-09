package com.hsm.quartztask.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/4/9 18:27
 */
@Component
public class JobClassSet {
    @Getter
    @Setter
    private Set<String> jobClasses = new HashSet<>(32);
}

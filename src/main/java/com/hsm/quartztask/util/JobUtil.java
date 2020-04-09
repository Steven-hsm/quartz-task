package com.hsm.quartztask.util;

import com.hsm.quartztask.common.BaseJob;

/**
 * <p>
 * 定时任务反射工具类
 * </p>
 *
 * @package: com.xkcoding.task.quartz.util
 * @description: 定时任务反射工具类
 * @author: yangkai.shen
 * @date: Created in 2018-11-26 13:33
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class JobUtil {

    public static BaseJob getClass(String classname) throws Exception {
        Class<?> clazz = Class.forName(classname);
        return (BaseJob) clazz.newInstance();
    }
}

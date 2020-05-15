package com.hsm.quartz.job.util;

import lombok.extern.slf4j.Slf4j;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 获取或转换时间的工具类
 *
 * 2011-11-1上午09:42:00
 *
 */
@Slf4j
public class DateUtils {

	public final static String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public final static String DEFAULT_DAY_FORMAT = "yyyy-MM-dd";
	public final static String HOUR_MINUTE_FORMAT = "HH:mm";
	public final static String SIMPLE_DAY_FORMAT = "yyyyMMdd";
	public final static String SIMPLE_DAYTIME_FORMAT = "yyyyMMddHHmmss";
	public final static String  SIMPLE_CHINA_DATE_HHmm = "yyyy年MM月dd日 HH:mm";


	public static Date tomorrow() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
}

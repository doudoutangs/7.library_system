package com.library.core.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * Created by sugar on 2023/9/13.
 */
public class DateTool {
    public static int[] getNowYearMonth() {
        String date = DateUtil.format(new Date(), DatePattern.NORM_DATE_PATTERN);
        String[] dateTime = date.split("-");
        int year = Integer.parseInt(dateTime[0]);
        int month = Integer.parseInt(dateTime[1]);
        return new int[]{year, month};
    }
}

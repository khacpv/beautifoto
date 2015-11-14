package com.oicmap.beautifoto.common.util;

import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static final int MILLIS_SECONDS_PER_DAY = 24 * 60 * 60 * 1000;

    private static final int MILLIS_SECONDS_PER_HOUR = 60 * 60 * 1000;

    private static final int MILLIS_SECONDS_PER_MINUTE = 60 * 1000;

    public static long convertDateToTimestamp(Date inputDate) {
        try {
            return inputDate.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Date convertLongToDate(Long time) {
        if (null == time) return null;
        try {
            return new Date(time);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int subtractDate(long nearDay, long beforeDay) {
        int result = (int) nearDay - (int) beforeDay;
        return result;
    }

    private static Locale getLocale() {
        return Locale.getDefault();
    }
}
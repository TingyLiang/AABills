package com.sddy.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {
    private static final String PATTERN_TIME = "yyyy-MM-dd HH:mm:ss";
    private static final String PATTERN_DATE = "yyyy-MM-dd";

    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_TIME, Locale.getDefault());
        return sdf.format(new Date());
    }
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE, Locale.getDefault());
        return sdf.format(new Date());
    }

    public static String getDateString(long timestamp, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }

    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public static int getDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }
}

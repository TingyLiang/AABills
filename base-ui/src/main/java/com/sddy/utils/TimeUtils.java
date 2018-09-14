package com.sddy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN, Locale.getDefault());
        return sdf.format(new Date());
    }
}

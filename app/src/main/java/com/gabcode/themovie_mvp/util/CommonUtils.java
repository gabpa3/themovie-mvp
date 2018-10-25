package com.gabcode.themovie_mvp.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CommonUtils {

    private static final String FORMAT_DATE = "yyyy-MM-dd";

    public static String getFirstDateOfYear() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(calendar.get(Calendar.YEAR), 0, 1);
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
        return formatter.format(calendar.getTime());
    }

    public static String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
        return formatter.format(new Date());
    }
}

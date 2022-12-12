package com.lea.sdcard.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by druid on 2018/11/1.
 */

public class SDCardTimeUitls {
    private static SimpleDateFormat simpleDateFormat;

    public static String getCurrentDate() {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        return date;
    }

    public static String getCurrentDatechZn() {
        simpleDateFormat = new SimpleDateFormat("yyyy'年'MM'月'dd'日'");
        String date = simpleDateFormat.format(new Date());
        return date;
    }

    public static String getCurrenTime() {
        simpleDateFormat = new SimpleDateFormat("HH:mm");
        String date = simpleDateFormat.format(new Date());
        return date;
    }

    public static String getCurrentDetailTime() {
        simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        return date;
    }

    public static String getCurrentDateTime() {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        return date;
    }

    /**
     * 获取当前年份
     *
     * @return
     */
    public static int getCurrentYear() {
        Calendar now = Calendar.getInstance();
        int year_now = now.get(Calendar.YEAR);
        return year_now;
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String getWeekTime(String time) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date d = df.parse(time);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.DATE, -7);
            return (df.format(cal.getTime()));
        } catch (Exception e) {
        }
        return "";
    }
}

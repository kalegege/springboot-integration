package com.wasu.springboot.integration.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {

    private static final String[] DAYNAMES = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final List<String> DATE_FORMATE_LIST = new ArrayList<>(Arrays.asList(
            "yyyy-MM-dd", "yyyy/MM/dd", "yyyy/MM/dd", "yyyyMMdd"
    ));
    private static final String DATE_FORMATE_1 = "yyyy-MM-dd";
    private static final String DATE_FORMATE_2 = "yyyy/MM/dd";
    private static final String DATE_FORMATE_3 = "yyyy/MM/dd";
    private static final String DATE_FORMATE_4 = "yyyyMMdd";

    public static Date getNow() {
        return new Date();
    }

    public static String formate(Date date){
        return formatDateTime(date,"yyy-MM-dd HH:mm:ss");
    }

    public static Date parseDate(String dateTime) {
        return parse(dateTime, DATE_FORMATE_1);
    }

    public static Date getAfterMonth(Date date, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(2, month);
        return c.getTime();
    }

    public static Date getLastDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(5, 1);
        c.add(2, 1);
        c.add(5, -1);
        return c.getTime();
    }

    public static int getDiffMonth(Date startDate, Date endDate) {
        Calendar cStart = Calendar.getInstance();
        Calendar cEnd = Calendar.getInstance();
        cStart.setTime(startDate);
        cEnd.setTime(endDate);
        int mStart = cStart.get(2) + 1;
        int mEnd = cEnd.get(2) + 1;
        int checkMonth = mEnd - mStart + (cEnd.get(1) - cStart.get(1)) * 12;
        return checkMonth;
    }

    /**
     * @param dateTime
     * @return
     */
    public static Boolean isDate(String dateTime) {
        try {
            parse(dateTime, DATE_FORMATE_1);
        } catch (Exception ex) {
            try {
                parse(dateTime, DATE_FORMATE_2);
            } catch (Exception ex1) {
                try {
                    parse(dateTime, DATE_FORMATE_3);
                } catch (Exception ex2) {
                    try {
                        parse(dateTime, DATE_FORMATE_4);
                    } catch (Exception ex3) {
                        return Boolean.valueOf(false);
                    }
                }
            }
        }
        return Boolean.valueOf(true);
    }

    /**
     *
     * @param date
     * @return
     */
    public static Date getDate(String date) {
        for (int i = 0; i < DATE_FORMATE_LIST.size(); i++) {
            Date d = parse(date, DATE_FORMATE_LIST.get(i));
            if(null == d){
                continue;
            }else{
                return d;
            }
        }
        return null;
    }

    public static Date parseDateTime(String dateTime) {
        return parse(dateTime, DATE_TIME_FORMAT);
    }

    public static Date parseDateTimeForUK(String dateTime) throws ParseException, RuntimeException {
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
        return dateFormat.parse(dateTime);
    }

    public static Date parse(String dateTime, String formate) {
        if (StringUtils.isBlank(dateTime)) return null;

        DateFormat dateFormat = new SimpleDateFormat(formate);
        try {
            return dateFormat.parse(dateTime);
        } catch (ParseException e) {
            throw new RuntimeException("formate date error!", e);
        }
    }

    public static String formatDateTime(Date date, String format) {
        if (date == null) return null;

        DateFormat dateFormat = new SimpleDateFormat(format);

        return dateFormat.format(date);
    }

    /**
     * 获取两个日期之间的年份和月份
     *
     * @param minDate
     * @param maxDate
     * @return
     */
    public static List<String> getMonthBetween(String minDate, String maxDate) {
        List<String> result = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        try {
            min.setTime(simpleDateFormat.parse(minDate));
            min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
            max.setTime(simpleDateFormat.parse(maxDate));
            max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
            Calendar curr = min;
            while (curr.before(max)) {
                result.add(simpleDateFormat.format(curr.getTime()));
                curr.add(Calendar.MONTH, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int getValidMonthInternal(Date fromDate, Date toDate) {
        if (fromDate.after(toDate)) {
            Date t = fromDate;
            fromDate = toDate;
            toDate = t;
        }
        Calendar fromCalender = Calendar.getInstance();
        fromCalender.setTime(fromDate);
        int fromYear = fromCalender.get(Calendar.YEAR);
        int fromMonth = fromCalender.get(Calendar.MONTH);

        Calendar toCalender = Calendar.getInstance();
        toCalender.setTime(fromDate);
        int toYear = toCalender.get(Calendar.YEAR);
        int toMonth = toCalender.get(Calendar.MONTH);

        int internal = (toYear - fromYear) * 12 + (toMonth - fromMonth);
        return internal > 0 ? internal : 0;
    }

}

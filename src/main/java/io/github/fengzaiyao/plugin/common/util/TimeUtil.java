package io.github.fengzaiyao.plugin.common.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.text.DateFormat;
import java.text.ParseException;

public class TimeUtil {

    /**
     * 一天毫秒数
     */
    private static Long dailyTime = 86400000L;

    /**
     * 时间格式化-精确到秒
     */
    private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 时间格式化-精确到日
     */
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 格式化时间戳-精确到秒
     *
     * @param timeStamp 毫秒级时间戳
     */
    public static String formatDateTime(Long timeStamp) {
        return dateTimeFormat.format(new Date(timeStamp));
    }

    /**
     * 格式化时间戳-精确到秒
     *
     * @param time 时间
     */
    public static String formatDateTime(Date time) {
        return dateTimeFormat.format(time);
    }

    /**
     * 格式化时间戳-精确到日
     *
     * @param timeStamp 毫秒级时间戳
     */
    public static String formatDate(Long timeStamp) {
        return dateFormat.format(new Date(timeStamp));
    }

    /**
     * 格式化时间戳-精确到日
     *
     * @param time 时间
     */
    public static String formatDate(Date time) {
        return dateFormat.format(time);
    }

    /**
     * 字符串转为时间
     *
     * @param timeStr 时间字符串
     * @param format  格式化类
     */
    public static Date parseTimeString(String timeStr, DateFormat format) {
        try {
            return format.parse(timeStr);
        } catch (ParseException ex) {
            return null;
        }
    }

    /**
     * 字符串转为时间
     *
     * @param timeStr 时间字符串
     */
    public static Date parseDate(String timeStr) {
        return parseTimeString(timeStr, dateFormat);
    }

    /**
     * 字符串转为时间
     *
     * @param timeStr 时间字符串
     */
    public static Date parseDateTime(String timeStr) {
        return parseTimeString(timeStr, dateTimeFormat);
    }

    /**
     * 获取今年最后一天的结束时间戳
     */
    public static Long getThisYearEndTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取指定某一天的开始时间戳
     *
     * @param timeStamp 毫秒级时间戳
     * @param timeZone  如 GMT+8:00
     */
    public static Long getDailyStartTime(Long timeStamp, String timeZone) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        calendar.setTimeInMillis(timeStamp);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取指定某一天的结束时间戳
     *
     * @param timeStamp 毫秒级时间戳
     * @param timeZone  如 GMT+8:00
     */
    public static Long getDailyEndTime(Long timeStamp, String timeZone) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        calendar.setTimeInMillis(timeStamp);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取多少天前的结束时间戳
     *
     * @param days      天数
     * @param timeStamp 毫秒级时间戳
     * @param timeZone  如 GMT+8:00
     */
    public static Long getBeforeDailyEndTime(Integer days, Long timeStamp, String timeZone) {
        return getDailyEndTime((timeStamp - (days * dailyTime)), timeZone);
    }

    /**
     * 获取多少天后的结束时间戳
     *
     * @param days      天数
     * @param timeStamp 毫秒级时间戳
     * @param timeZone  如 GMT+8:00
     */
    public static Long getAfterDailyEndTime(Integer days, Long timeStamp, String timeZone) {
        return getDailyEndTime((timeStamp + (days * dailyTime)), timeZone);
    }

    /**
     * 获取多少天前的开始时间戳
     *
     * @param days      天数
     * @param timeStamp 毫秒级时间戳
     * @param timeZone  如 GMT+8:00
     */
    public static Long getBeforeDailyStartTime(Integer days, Long timeStamp, String timeZone) {
        return getDailyStartTime((timeStamp - (days * dailyTime)), timeZone);
    }

    /**
     * 获取多少天后的开始时间戳
     *
     * @param days      天数
     * @param timeStamp 毫秒级时间戳
     * @param timeZone  如 GMT+8:00
     */
    public static Long getAfterDailyStartTime(Integer days, Long timeStamp, String timeZone) {
        return getDailyStartTime((timeStamp + (days * dailyTime)), timeZone);
    }

    /**
     * 分割时间
     *
     * @param beginTime 开始时间 例如 "2023-09-01"
     * @param endTime   结束时间 例如 "2023-09-07"
     * @param interval  分割天数 例如 3
     * @return [2023-09-01, 2023-09-03],[2023-09-04, 2023-09-06],[2023-09-07, 2023-09-07]
     */
    public static String[][] intervalTime(String beginTime, String endTime, Integer interval) {
        LocalDate l = parseDate(beginTime).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate r = parseDate(endTime).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return intervalTime(l, r, interval);
    }

    /**
     * 分割时间
     *
     * @param beginTime 开始时间 例如 "2023-09-01"
     * @param endTime   结束时间 例如 "2023-09-07"
     * @param interval  分割天数 例如 3
     * @return [2023-09-01, 2023-09-03],[2023-09-04, 2023-09-06],[2023-09-07, 2023-09-07]
     */
    public static String[][] intervalTime(LocalDate beginTime, LocalDate endTime, Integer interval) {
        int segments = (int) Math.ceil((double) (ChronoUnit.DAYS.between(beginTime, endTime) + 1) / interval);
        String[][] ret = new String[segments][2];
        LocalDate begin = beginTime;
        for (int i = 0; i < segments; i++) {
            LocalDate end = begin.plusDays(interval - 1);
            if (end.isAfter(endTime)) {
                end = endTime;
            }
            ret[i] = new String[]{begin.toString(), end.toString()};
            begin = end.plusDays(1);
        }
        return ret;
    }
}

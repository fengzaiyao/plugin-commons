package io.github.fengzaiyao.plugin.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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
     * 格式化时间戳-精确到日
     *
     * @param timeStamp 毫秒级时间戳
     */
    public static String formatDate(Long timeStamp) {
        return dateFormat.format(new Date(timeStamp));
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
}
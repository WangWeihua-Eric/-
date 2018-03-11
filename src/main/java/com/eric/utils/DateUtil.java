package com.eric.utils;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {

    private static final DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter fmt_ms = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.S");

    private static final DateTimeFormatter fmt_min = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

    public static final String format_1 = "yyyy-MM-dd HH:mm:ss";

    public static final String format_2 = "HH:mm";

    public static final String format_3 = "HH:mm MM月dd日";

    public static final String format_4 = "yyyy-MM-dd HH:mm";

    public static final String format_5 = "yyyy-MM-dd";

    public static final String format_6 = "MM月dd日";

    public static final String format_7 = "yyyy-MM-dd 23:59:59";

    public static final String format_8 = "yyyy-MM-dd 00:00:00";

    public static final String format_9 = "HH时mm分";

    public static final String format_10 = "dd";

    public static String formatDate(Date date, String format) throws RuntimeException {
        try {
            if (date == null) {
                throw new RuntimeException("date is null");
            }
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            String dateStr = sdf.format(date);
            return dateStr;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 线程安全，格式兼容转日期
     * 
     * @param str
     * @return
     */

    public static Date parseToDate(String str) {
        Date date = new Date();
        if (StringUtils.isNotBlank(str)) {
            try {
                DateTime tmp = fmt.parseDateTime(str);
                date = tmp.toDate();
            } catch (Exception e) {
                try {
                    DateTime tmp1 = fmt_ms.parseDateTime(str);
                    date = tmp1.toDate();
                } catch (Exception e1) {
                    DateTime tmp1 = fmt_min.parseDateTime(str);
                    date = tmp1.toDate();
                }
            }
        }
        return date;
    }

    /**
     * 将带有时区参数的时间，转换成时间戳
     * 
     * @param dt “2015-02-03 00:00:00”
     * @param pattern "yyyy-MM-dd HH:mm:ss"
     * @param timeZone "GMT+08"
     * @return Date
     */
    public static Date parserDate(String dt, String pattern, String timeZone) {
        DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern(pattern).withZone(
                DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone)));
        return dateTimeFormat.parseDateTime(dt).toDate();
    }

    /**
     * 将Date按照某个时区转换成相应的str
     * 
     * @param date Date
     * @param pattern "yyyy-MM-dd HH:mm:ss"
     * @param timeZone TimeZone.getTimeZone("GMT+08")
     * @return String
     */
    public static String formatDateByTimeZone(Date date, String pattern, String timeZone) {
        if (date == null) {
            return null;
        }
        try {
            return DateTimeFormat.forPattern(pattern)
                    .withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).print(date.getTime());
        } catch (Exception e) {
            return DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").withZone(DateTimeZone.getDefault())
                    .print(date.getTime());
        }
    }

    /**
     * 将机票接口特定的带有时区信息的时间串，转换成时间戳
     * 
     * @param dt “yyyy-mm-dd HH:MM:ss[+,-]xx” 2016-03-25 17:44:00+08
     * @return Date
     */
    public static Date parseDateWithTimeZoneToDate(String dt) {
        Pattern pattern1 = Pattern.compile("(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})([+,-])(\\d{1,2})");
        Pattern pattern2 = Pattern.compile("(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})");
        Matcher matcher1 = pattern1.matcher(dt);
        Matcher matcher2 = pattern2.matcher(dt);
        String date = null;
        String timeZone = "";
        String zoneType = "";
        if (matcher1.find()) {
            date = matcher1.group(1);
            zoneType = matcher1.group(2);
            timeZone = matcher1.group(3);
            return parserDate(date, format_1, "GMT" + zoneType + timeZone);
        } else if (matcher2.find()) {
            return parseToDate(dt);
        } else {
            return null;
        }
    }

    /**
     * 日期，星期几的状态
     */
    public enum DayOfWeekEnum {
        SUN(1, "周日"), MON(2, "周一"), TUE(3, "周二"), WED(4, "周三"), THU(5, "周四"), FRI(6, "周五"), SAT(7, "周六");

        private Integer code;

        private String desc;

        private DayOfWeekEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public static DayOfWeekEnum getDayOfWeekEnumByCode(Integer code) {
            DayOfWeekEnum result = null;
            for (DayOfWeekEnum dayOfWeekEnum : DayOfWeekEnum.values()) {
                if (dayOfWeekEnum.getCode().equals(code)) {
                    result = dayOfWeekEnum;
                    break;
                }
            }
            return result;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    /**
     * 自定义日期格式
     */
    public static Date parseDate(String dateStr, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = sdf.parse(dateStr);
        return date;
    }

    public static Long getDateDiff(Date firstDate, Date secondDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(firstDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date truncatedFirstDate = calendar.getTime();
        calendar.setTime(secondDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date truncatedSecondDate = calendar.getTime();
        return (truncatedFirstDate.getTime() - truncatedSecondDate.getTime()) / 1000 / 3600 / 24;
    }

    public static Boolean isInTimeInterval(Date startTime, Date endTime, Date time) {
        if (startTime.after(endTime)) {
            return false;
        }
        if (time.before(startTime) || time.after(endTime)) {
            return false;
        }
        return true;
    }

    public static Date getEndTimeOfDate(Date originalTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(originalTime);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date getOneDayBeforeFixHourDate(Date originalTime, Integer hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(originalTime);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getTimeByCount(Date originalTime, Integer timeDelta) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(originalTime);
        calendar.add(Calendar.SECOND, timeDelta);
        return calendar.getTime();
    }

    public static void main(String[] args) throws ParseException {
        Date d = DateUtil.parseDate("2017-04-12 23:02:00", DateUtil.format_1);
        String str = DateUtil.formatDate(d, DateUtil.format_8);
        System.out.println(str);

        // System.out.println(getFlightDay("2017-04-12 23:02:00+07"));
    }
}

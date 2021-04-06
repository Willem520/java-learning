package willem.base.tool;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * @author weiyu
 * @description
 * @create 2018/5/25 14:39
 * @since 1.8
 */
public class DateUtil {
    public static DateTimeFormatter defaultDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static DateTimeFormatter defaultDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 私有化构造器
     */
    private DateUtil() {

    }

    /**
     * 判断日期格式是否为yyyy-MM-dd
     *
     * @param dateStr
     * @return
     * @since 1.8
     */
    public static boolean isValidDate(String dateStr) {
        boolean isValid = true;
        try {
            LocalDate.parse(dateStr).format(DateTimeFormatter.ISO_LOCAL_DATE);
        }
        catch (Exception e) {
            isValid = false;
        }
        return isValid;
    }

    /**
     * 获取昨天日期yyyy-MM-dd
     *
     * @return
     * @since 1.8
     */
    public static String getYesterday() {
        return getDate(LocalDate.now().toString(),-1,ChronoUnit.DAYS);
    }

    /**
     * 获取今天日期yyyy-MM-dd
     *
     * @return
     * @since 1.8
     */
    public static String getToday() {
        return LocalDate.now().format(defaultDateFormat);
    }

    /**
     * 获取明日日期 yyyy-MM-dd
     *
     * @return
     * @since 1.8
     */
    public static String getTomorrow() {
        return getDate(LocalDate.now().toString(),1,ChronoUnit.DAYS);
    }

    /**
     * 获取指定日期 yyyy-MM-dd
     * @param date
     * @param num
     * @param temporalUnit
     * @return
     */
    public static String getDate(String date, int num, TemporalUnit temporalUnit){
        LocalDate localDate = LocalDate.parse(date,defaultDateFormat);
        return localDate.plus(num,temporalUnit).format(defaultDateFormat);
    }

    /**
     * 获取当前时间 yyyy-MM-dd HH:mm:ss
     *
     * @return
     * @since 1.8
     */
    public static String getCurrentDateTime() {
        return getDateTime(LocalDateTime.now().format(defaultDateTimeFormat),0,ChronoUnit.DAYS);
    }

    /**
     * 获取指定时间 yyyy-MM-dd HH:mm:ss
     * @param datetime
     * @param num
     * @param temporalUnit
     * @return
     */
    public static String getDateTime(String datetime, int num, TemporalUnit temporalUnit){
        LocalDateTime dateTime = LocalDateTime.parse(datetime,defaultDateTimeFormat);
        return dateTime.plus(num,temporalUnit).format(defaultDateTimeFormat);
    }
}

package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @ClassName DateUtil
 * @Description 日期转换工具
 * @Author gz
 * @Date 2018/12/26 15:57
 * @Version 1.0
 **/
public class DateUtil {

    //转换到年
    public static String DateConvert2year(Long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-00-00_00:00:00");
        String standardTime = sdf.format(new Date(date));
        return standardTime;
    }

    //转换到月
    public static String DateConvert2mouth(Long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-00_00:00:00");
        String standardTime = sdf.format(new Date(date));
        return standardTime;
    }

    //转换到日
    public static String DateConvert2day(Long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_00:00:00");
        String standardTime = sdf.format(new Date(date));
        return standardTime;
    }

    //转换到小时
    public static String DateConvert2hour(Long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:00:00");
        String standardTime = sdf.format(new Date(date));
        return standardTime;
    }

    //转换到分钟
    public static String DateConvert2minute(Long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:00");
        String standardTime = sdf.format(new Date(date));
        return standardTime;
    }

    //转换到秒
    public static String DateConvert2second(Long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String standardTime = sdf.format(new Date(date));
        return standardTime;
    }

    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /**
     * @param Dec-25 2018 00:27:35.900
     * @return 1545668855000
     */
    public static Long dateToStandTimeStamp(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM-d yyyy K:m:s", Locale.ENGLISH);
        Date date = null;
        try {
            date = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

}

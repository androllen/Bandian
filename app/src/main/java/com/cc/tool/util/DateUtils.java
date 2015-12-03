package com.cc.tool.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by androllen on 2015/8/21.
 */
public class DateUtils {

    private static SimpleDateFormat sf = null;

    /* 获取系统时间 格式为："yyyy/MM/dd " */
    public static String getCurrentDate() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }

    /* 时间戳转换成字符窜 */
    public static String getDateToString(String time) {
        String re_StrTime=null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Long mTime=Long.valueOf(time);
        re_StrTime=sdf.format(new Date(mTime*1000L));
        return re_StrTime;
    }

    public static String getDateToString2(String time) {
        String re_StrTime=null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Long mTime=Long.valueOf(time);
        re_StrTime=sdf.format(new Date(mTime));
        return re_StrTime;
    }

    /* 将字符串转为时间戳 */
    public static long getStringToDate(String time) {
        sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }
}

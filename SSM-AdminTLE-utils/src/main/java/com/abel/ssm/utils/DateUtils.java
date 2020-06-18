package com.abel.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * 日期转换成字符串
     * @param date 日期
     * @param patt 日期转换的 格式
     */
    public static String date2String(Date date,String patt){
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        String format = sdf.format(date);  //format 可以用String进行接收
        return format;
    }

    /**
     * 字符串转换成日期
     * @param str  日期字符串
     * @param patt 格式
     */
    public static Date string2Date(String dataTime,String patt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date parse = sdf.parse(dataTime);  //parse 也可以解析成Date
        return parse;
    }
}

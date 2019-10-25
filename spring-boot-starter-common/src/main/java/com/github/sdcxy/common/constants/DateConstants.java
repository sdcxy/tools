package com.github.sdcxy.common.constants;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * @ClassName DateConstants
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/23 18:38
 **/
public class DateConstants {

    private DateConstants(){}

    public static final String DATE = "date";

    // 时间元素
    public static final String YEAR = "year";
    public static final String MONTH = "month";
    public static final String WEEK = "week";
    public static final String DAY = "day";
    public static final String HOUR = "hour";
    public static final String MINUTE = "minute";
    public static final String SECOND = "second";

    // 星期元素
    public static final String MONDAY = "MONDAY";// 星期一
    public static final String TUESDAY = "TUESDAY";// 星期二
    public static final String WEDNESDAY = "WEDNESDAY";// 星期三
    public static final String THURSDAY = "THURSDAY";// 星期四
    public static final String FRIDAY = "FRIDAY";// 星期五
    public static final String SATURDAY = "SATURDAY";// 星期六
    public static final String SUNDAY = "SUNDAY";// 星期日

    // 字符串中文数字 一到七
    public static final String ONE = "一";
    public static final String TWO = "二";
    public static final String THREE = "三";
    public static final String FOUR = "四";
    public static final String FIVE = "五";
    public static final String SIX = "六";
    public static final String SEVEN = "七";
    public static final String SUN = "日";

    // 数字 1-7
    public static final int NUMBER_ONE = 1;
    public static final int NUMBER_TWO = 2;
    public static final int NUMBER_THREE = 3;
    public static final int NUMBER_FOUR = 4;
    public static final int NUMBER_FIVE = 5;
    public static final int NUMBER_SIX= 6;
    public static final int NUMBER_SEVEN = 7;


    // 根据指定格式显示日期和时间
    /** yyyy-MM-dd */
    public static final DateTimeFormatter yyyyMMdd_EN = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /** yyyy-MM-dd HH */
    public static final DateTimeFormatter yyyyMMddHH_EN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
    /** yyyy-MM-dd HH:mm */
    public static final DateTimeFormatter yyyyMMddHHmm_EN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    /** yyyy-MM-dd HH:mm:ss */
    public static final DateTimeFormatter yyyyMMddHHmmss_EN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /** HH:mm:ss */
    public static final DateTimeFormatter HHmmss_EN = DateTimeFormatter.ofPattern("HH:mm:ss");
    /** yyyy年MM月dd日 */
    public static final DateTimeFormatter yyyyMMdd_CN = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
    /** yyyy年MM月dd日HH时 */
    public static final DateTimeFormatter yyyyMMddHH_CN = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时");
    /** yyyy年MM月dd日HH时mm分 */
    public static final DateTimeFormatter yyyyMMddHHmm_CN = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时mm分");
    /** yyyy年MM月dd日HH时mm分ss秒 */
    public static final DateTimeFormatter yyyyMMddHHmmss_CN = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时mm分ss秒");
    /** HH时mm分ss秒 */
    public static final DateTimeFormatter HHmmss_CN = DateTimeFormatter.ofPattern("HH时mm分ss秒");


    // 本地时间显示格式：区分中文和外文显示
    public static final DateTimeFormatter shotDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
    public static final DateTimeFormatter fullDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
    public static final DateTimeFormatter longDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
    public static final DateTimeFormatter mediumDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);





}

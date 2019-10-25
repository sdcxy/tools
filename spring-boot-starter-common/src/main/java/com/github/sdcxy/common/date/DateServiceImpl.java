package com.github.sdcxy.common.date;


import com.github.sdcxy.common.constants.DateConstants;
import com.github.sdcxy.common.enums.DateType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName DateServiceImpl
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/24 1:01
 **/
public class DateServiceImpl extends AbstractDateService implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *  时间格式转换
     * @param time 字符串时间
     * @param formatter 格式化参数
     * @return 返回时间 LocalDateTime
     */
    @Override
    public LocalDateTime stringToLocalDateTime(String time,DateTimeFormatter formatter) {
        return LocalDateTime.parse(time, formatter);
    }

    /**
     *  时间格式转换
     * @param time LocalDateTime时间
     * @param formatter 格式化参数
     * @return 返回字符串时间
     */
    @Override
    public String localDateTimeToString(LocalDateTime time,DateTimeFormatter formatter) {
        return time.format(formatter);
    }

    /**
     *  获取当前时间
     * @param formatter 格式化参数
     * @return 返回字符串时间
     */
    @Override
    public String getNowDateTime(DateTimeFormatter formatter) {
        return LocalDateTime.now().format(formatter);
    }

    /**
     *  时间中周转换
     * @param enWeek 日期周
     * @param dateType 转换类型 {@link DateType}
     * @return 返回根据转换类型
     */
    @Override
    public Object transformWeekEN2Num(String enWeek, DateType dateType) {
        if (dateType.equals(DateType.NUMBER)){
            switch (enWeek){
                case DateConstants.MONDAY: return DateConstants.NUMBER_ONE;
                case DateConstants.TUESDAY: return DateConstants.NUMBER_TWO;
                case DateConstants.WEDNESDAY: return DateConstants.NUMBER_THREE;
                case DateConstants.THURSDAY: return DateConstants.NUMBER_FOUR;
                case DateConstants.FRIDAY: return DateConstants.NUMBER_FIVE;
                case DateConstants.SATURDAY: return DateConstants.NUMBER_SIX;
                case DateConstants.SUNDAY: return DateConstants.NUMBER_SEVEN;
                default: break;
            }
        }
        if (dateType.equals(DateType.STRING)){
            switch (enWeek){
                case DateConstants.MONDAY: return DateConstants.ONE;
                case DateConstants.TUESDAY: return DateConstants.TWO;
                case DateConstants.WEDNESDAY: return DateConstants.THREE;
                case DateConstants.THURSDAY: return DateConstants.FOUR;
                case DateConstants.FRIDAY: return DateConstants.FIVE;
                case DateConstants.SATURDAY: return DateConstants.SIX;
                case DateConstants.SUNDAY: return DateConstants.SUN;
                default: break;
            }
        }
        return null;
    }

    /**
     *  获取时间节点时间
     * @param time 输入时间
     * @param noteType 节点类型 {@link com.github.sdcxy.common.enums.DateType.NoteType}
     * @return 返回时间节点参数
     */
    @Override
    public int getNodeTime(LocalDateTime time,DateType.NoteType noteType) {
        int resultNode = 0;
        switch (noteType) {
            case YEAR: resultNode = time.getYear();break;
            case MONTH: resultNode = time.getMonthValue();break;
            case DAY: resultNode = time.getDayOfMonth();break;
            case WEEK: resultNode = (int)transformWeekEN2Num(String.valueOf(time.getDayOfWeek()),DateType.NUMBER);break;
            case HOUR: resultNode = time.getHour();break;
            case MINUTE: resultNode = time.getMinute();break;
            case SECOND: resultNode = time.getSecond();break;
            default:break;
        }
        return resultNode;
    }

    /**
     *  获取输入时间之后的时间
     * @param time 输入时间
     * @param noteType 节点类型 {@link com.github.sdcxy.common.enums.DateType.NoteType}
     * @param num 之后的时间参数
     * @param formatter 格式化参数
     * @return 返回之后的时间
     */
    @Override
    public String getAfterTime(LocalDateTime time, DateType.NoteType noteType, Long num,DateTimeFormatter formatter) {
        String resultStr = null;
        switch (noteType) {
            case YEAR: resultStr = time.plusYears(num).format(formatter);break;
            case MONTH: resultStr = time.plusMonths(num).format(formatter);break;
            case DAY: resultStr = time.plusDays(num).format(formatter);break;
            case WEEK: resultStr = time.plusWeeks(num).format(formatter);break;
            case HOUR: resultStr = time.plusHours(num).format(formatter);break;
            case MINUTE: resultStr = time.plusMinutes(num).format(formatter);break;
            case SECOND: resultStr = time.plusSeconds(num).format(formatter);break;
            default:break;
        }
        return resultStr;
    }


}

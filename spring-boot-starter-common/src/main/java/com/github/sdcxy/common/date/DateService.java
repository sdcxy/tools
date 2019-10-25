package com.github.sdcxy.common.date;

import com.github.sdcxy.common.enums.DateType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface DateService {

    // 格式化时间 返回 LocalDateTime
    LocalDateTime stringToLocalDateTime(String time, DateTimeFormatter formatter);

    // 格式化时间 返回字符串
    String localDateTimeToString(LocalDateTime time, DateTimeFormatter formatter);

    // 根据格式方式转换当前时间 返回String
    String getNowDateTime(DateTimeFormatter formatter);

    // 将英文星期根据类型转换 Number String
    Object transformWeekEN2Num(String enWeek, DateType dateType);

    // 获取输入日期的节点时间（年，月，周，日，时，分，秒）
    int getNodeTime(LocalDateTime time,DateType.NoteType noteType);

    // 获取输入日期的之后的节点时间 比如当前时间为：2019-03-30 10:20:30  node="hour",num=5L 之后时间 2019-03-30 15:20:30
    String getAfterTime(LocalDateTime time, DateType.NoteType noteType, Long num,DateTimeFormatter formatter);
}

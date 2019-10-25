package com.github.sdcxy.common.enums;

public enum DateType {

    /**
     * 数字类型
     */
    NUMBER,
    /**
     * 字符串类型
     */
    STRING;

    /**
     *  时间节点中的类型
     */
    public enum NoteType{
        /**
         * 年
         */
        YEAR,
        /**
         * 月
         */
        MONTH,
        /**
         * 周
         */
        WEEK,
        /**
         * 日
         */
        DAY,
        /**
         * 时
         */
        HOUR,
        /**
         * 分
         */
        MINUTE,
        /**
         * 秒
         */
        SECOND;
    }
}

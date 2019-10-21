package com.github.sdcxy.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName SqlServerTable
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/19 16:30
 **/
@Accessors(chain = true)
@Data
public class SqlServerTable {
    /**
     * 表序号
     */
    private int table_serial;
    /**
     * 表名
     */
    private String table_name;
    /**
     * 表注释
     */
    private String table_remark;
    /**
     * 字段序号
     */
    private int column_serial;
    /**
     * 字段名
     */
    private String column_name;
    /**
     * 字段注释
     */
    private String column_remark;
    /**
     * 字段标识
     */
    private String column_identity;
    /**
     * 字段是否为主键
     */
    private String column_primary_key;
    /**
     * 字段类型
     */
    private String column_type;
    /**
     * 字段长度
     */
    private String column_length;
    /**
     * 字段是否为空
     */
    private String  column_nullable;
    /**
     * 字段占用字节
     */
    private int column_occupation_byte;
    /**
     * 字段小数位数
     */
    private int column_decimal;
    /**
     * 字段默认值
     */
    private String column_default;
}

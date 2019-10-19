package com.github.sdcxy.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName Column
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/12 15:21
 **/
@Accessors(chain = true)
@Data
public class Column {

    private int ColumnSerial;

    private String columnName;

    private String columnType;

    private String columnLength;

    private boolean primaryKey;

    private String nullable;

    private String columnRemark;
}

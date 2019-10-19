package com.github.sdcxy.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName Table
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/12 15:22
 **/
@Accessors(chain = true)
@Data
public class Table {

    private int tableSerial;

    private String tableName;

    private String tableRemark;

    private String primaryKey;

    private List<Column> columnList;
}

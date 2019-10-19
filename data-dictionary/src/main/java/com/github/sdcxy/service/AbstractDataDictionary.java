package com.github.sdcxy.service;

import com.github.sdcxy.constants.DataBaseConstants;
import com.github.sdcxy.constants.SignConstants;
import com.github.sdcxy.entity.Column;
import com.github.sdcxy.entity.DataDictionaryDataSource;
import com.github.sdcxy.entity.Table;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName AbstractDataDictionary
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/14 14:46
 **/
@Slf4j
public abstract class AbstractDataDictionary<T> implements DataDictionary{


    protected List<Table> getTableList(Connection conn, DataDictionaryDataSource dataSource, String tableNames){
        DatabaseMetaData databaseMetaData;
        ResultSet table_rs = null;
        ResultSet column_rs = null;
        ResultSet primaryKey_rs = null;
        List<Column> columnList;
        List<Table> tableList = new ArrayList<>();
        Table table;
        try{
            databaseMetaData = conn.getMetaData();
            if (tableNames == null || tableNames.equals(SignConstants.NULL_STRING_SIGN)){
                table_rs = databaseMetaData.getTables(dataSource.getDataBase(), SignConstants.PERCENTAGE_SIGN,SignConstants.PERCENTAGE_SIGN,new String[]{DataBaseConstants.TABLE});
            }else {
                table_rs = databaseMetaData.getTables(dataSource.getDataBase(), SignConstants.PERCENTAGE_SIGN,tableNames,new String[]{DataBaseConstants.TABLE});
            }
            int i = 1;
            while (table_rs.next()){
                // 封装数据库表数据
                table = new Table();
                String tableName = table_rs.getString(DataBaseConstants.TABLE_NAME);
                String table_remarks = table_rs.getString(DataBaseConstants.TABLE_REMARKS);
                if (table_remarks == null ){table_remarks = SignConstants.NULL_STRING_SIGN;}
                table.setTableName(tableName).setTableRemark(table_remarks).setTableSerial(i);
                // 获取数据库表主键并封装
                primaryKey_rs = databaseMetaData.getPrimaryKeys(null,null,tableName);
                while (primaryKey_rs.next()){
                    String primaryKey = primaryKey_rs.getString(DataBaseConstants.COLUMN_NAME);
                    table.setPrimaryKey(primaryKey);
                }
                //封装表字段
                columnList = new ArrayList<>();
                column_rs = databaseMetaData.getColumns(dataSource.getDataBase(),SignConstants.PERCENTAGE_SIGN,tableName,SignConstants.PERCENTAGE_SIGN);
                log.info("----------表名:{}--->{}----------",tableName,table_remarks);
                int k =1;
                while (column_rs.next()){
                    Column column = new Column();
                    String column_name = column_rs.getString(DataBaseConstants.COLUMN_NAME);
                    String column_type = column_rs.getString(DataBaseConstants.COLUMN_TYPE_NAME);
                    String column_remarks = column_rs.getString(DataBaseConstants.COLUMN_REMARKS);
                    String column_size = column_rs.getString(DataBaseConstants.COLUMN_SIZE);
                    String column_isNullable = column_rs.getString(DataBaseConstants.IS_NULLABLE);
                    if (column_remarks == null ){column_remarks = SignConstants.NULL_STRING_SIGN;}
                    column.setColumnName(column_name)
                            .setColumnRemark(column_remarks)
                            .setColumnType(column_type)
                            .setColumnLength(column_size)
                            .setNullable(column_isNullable)
                            .setColumnSerial(k);
                    String primaryKey = table.getPrimaryKey();
                    if (StringUtils.isNotEmpty(primaryKey)) {
                        if (primaryKey.equals(column_name)){
                            column.setPrimaryKey(true);
                        }else{
                            column.setPrimaryKey(false);
                        }
                    }
                    columnList.add(column);
                    log.info("|序号:{}|列字段:{}|列类型:{}|列长度:{}|主键:{}|是否为空:{}|注释:{}|",k,column_name,column_type,column_size,column.isPrimaryKey(),column_isNullable,column_remarks);
                    k++;
                }
                table.setColumnList(columnList);
                tableList.add(table);
                i++;
                log.info("----------获取所有数据表成功----------");
            }
        } catch (Exception e) {
            log.error("获取数据库表异常:[{}]",e.getMessage());
        }finally {
            releaseResources(conn,null,table_rs);
            releaseResources(null,null,column_rs);
        }
        return tableList;
    }


    /**
     *  释放数据库资源
     * @param conn 数据库Connection
     * @param ps 数据库PreparedStatement
     * @param rs 数据库ResultSet
     */
    private void releaseResources(Connection conn, PreparedStatement ps, ResultSet rs){
        try{
            if (rs != null){
                rs.close();
            }
            if (ps != null){
                ps.close();
            }
            if (conn != null){
                conn.close();
            }
        }catch (Exception e){
            log.error("-----------释放数据库资源异常-----------");
            log.error("{}",e.getMessage());
        }
    }
}

package com.github.sdcxy.service.sql;

import com.github.sdcxy.constants.DataBaseConstants;
import com.github.sdcxy.constants.MapConstants;
import com.github.sdcxy.constants.SignConstants;
import com.github.sdcxy.dao.SqlServerConnection;
import com.github.sdcxy.entity.DataDictionaryDataSource;
import com.github.sdcxy.entity.Table;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SqlServerDataDictionary
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/14 16:21
 **/
@Slf4j
public class SqlServerDataDictionary extends AbstractDataDictionary {

    private static final String QUERY_DATABASE_DICTIONARY =
            "SELECT\n"+
                    "     TableName       = Case When A.colorder=1 Then D.name Else '' End,\n"+
                    "     TableRemark     = Case When A.colorder=1 Then isnull(F.value,'') Else '' End,\n"+
                    "     ColumnSerial   = A.colorder,\n"+
                    "     ColumnName     = A.name,\n"+
                    "     ColumnRemark   = isnull(G.value,''),\n"+
                    "     ColumnIdentity       = Case When COLUMNPROPERTY( A.id,A.name,'IsIdentity')=1 Then '√'Else '' End,\n"+
                    "     ColumnPrimaryKey       = Case When exists(SELECT 1 FROM sysobjects Where xtype='PK' and parent_obj=A.id and name in (\n"+
                    "                      SELECT name FROM sysindexes WHERE indid in( SELECT indid FROM sysindexkeys WHERE id = A.id AND colid=A.colid))) then '√' else '' end,\n"+
                    "     ColumnType       = B.name,\n"+
                    "     ColumnOccupationByte = A.Length,\n"+
                    "     ColumnLength       = COLUMNPROPERTY(A.id,A.name,'PRECISION'),\n"+
                    "     ColumnDecimal   = isnull(COLUMNPROPERTY(A.id,A.name,'Scale'),0),\n"+
                    "     ColumnNullable     = Case When A.isnullable=1 Then '√'Else '' End,\n"+
                    "     ColumnDefault     = isnull(E.Text,'')\n"+
                    " FROM\n"+
                    "     syscolumns A\n"+
                    " Left Join\n"+
                    "     systypes B\n"+
                    " On\n"+
                    "     A.xusertype=B.xusertype\n"+
                    " Inner Join\n"+
                    "     sysobjects D\n"+
                    " On\n"+
                    "     A.id=D.id  and D.xtype='U' and  D.name<>'dtproperties'\n"+
                    " Left Join\n"+
                    "     syscomments E\n"+
                    " on\n"+
                    "     A.cdefault=E.id\n"+
                    " Left Join\n"+
                    " sys.extended_properties  G\n"+
                    " on\n"+
                    "     A.id=G.major_id and A.colid=G.minor_id\n"+
                    " Left Join\n"+
                    " \n"+
                    " sys.extended_properties F\n"+
                    " On\n"+
                    "     D.id=F.major_id and F.minor_id=0\n"+
                    "TABLE_NAME"+"\n"+
                    " Order By\n"+
                    "A.id,A.colorder";



    private static final String CONDITION = "where d.name='TABLE_NAME'";

    @Autowired
    private SqlServerConnection sqlServerConnection;

    @Autowired
    private DataDictionaryDataSource dataSource;

    @Override
    public List getTableInfo(String tableNames) {
        return getTableList(sqlServerConnection.getConnection(),dataSource,tableNames);
    }

    /**
     * 自定义查询数据表结构（通过SQL语句）
     * @param currentTableList
     * @return
     */
    public List<Map<String,Object>> getSqlServerTableInfo(List<Table> currentTableList){
        List<String> tableNameList = getTableNameList(currentTableList);
        List<Map<String,Object>> tableList = new ArrayList<>();
        List<Map<String,Object>> columnList;
        Map<String,Object> tableMap;
        Map<String,Object> columnMap;
        String querySql = QUERY_DATABASE_DICTIONARY;
        Connection conn = sqlServerConnection.getConnection();
        int taleSerial = 1;
        try{
            for (String name : tableNameList) {
                String currentTableName = "";
                String currentTableRemark = "";
                tableMap = new HashMap<>();
                columnList = new ArrayList<>();
                String condition = CONDITION.replace(DataBaseConstants.TABLE_NAME,name);
                log.info("{}",name);
                String sql = querySql.replace(DataBaseConstants.TABLE_NAME,condition);
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    String table_name = resultSet.getString(MapConstants.TABLE_NAME);
                    String table_remark = resultSet.getString(MapConstants.TABLE_REMARK);
                    int column_serial = resultSet.getInt(MapConstants.COLUMN_SERIAL);
                    String column_name = resultSet.getString(MapConstants.COLUMN_NAME);
                    String column_remark = resultSet.getString(MapConstants.COLUMN_REMARK);
                    String column_identity = resultSet.getString(MapConstants.COLUMN_IDENTITY);
                    String column_primary_key = resultSet.getString(MapConstants.COLUMN_PRIMARY_KEY);
                    String column_type = resultSet.getString(MapConstants.COLUMN_TYPE);
                    int column_occupation_byte= resultSet.getInt(MapConstants.COLUMN_OCCUPATION_BYTE);
                    String column_length= resultSet.getString(MapConstants.COLUMN_LENGTH);
                    int column_decimal = resultSet.getInt(MapConstants.COLUMN_DECIMAL);
                    String column_nullable = resultSet.getString(MapConstants.COLUMN_NULLABLE);
                    String column_default = resultSet.getString(MapConstants.COLUMN_DEFAULT);
                    if (column_serial == 1){
                        currentTableName = table_name;
                        currentTableRemark = table_remark;
                    }
                    columnMap = new HashMap<>();
                    columnMap.put(MapConstants.COLUMN_SERIAL,column_serial);
                    columnMap.put(MapConstants.COLUMN_NAME,column_name);
                    columnMap.put(MapConstants.COLUMN_REMARK,column_remark);
                    columnMap.put(MapConstants.COLUMN_TYPE,column_type+ SignConstants.LEFT_BRACKETS + column_length + SignConstants.RIGHT_BRACKETS);
                    columnMap.put(MapConstants.COLUMN_LENGTH,column_length);
                    if (column_nullable.equals(SignConstants.NULL_STRING_SIGN)){
                        columnMap.put(MapConstants.COLUMN_NULLABLE,SignConstants.CROSS);
                    }else{
                        columnMap.put(MapConstants.COLUMN_NULLABLE,column_nullable);
                    }
                    columnMap.put(MapConstants.COLUMN_PRIMARY_KEY,column_primary_key);
                    columnMap.put(MapConstants.COLUMN_DECIMAL,column_decimal);
                    columnMap.put(MapConstants.COLUMN_IDENTITY,column_identity);
                    columnMap.put(MapConstants.COLUMN_DEFAULT,column_default);
                    columnMap.put(MapConstants.COLUMN_OCCUPATION_BYTE,column_occupation_byte);
                    columnList.add(columnMap);
                }
                tableMap.put(MapConstants.TABLE_SERIAL,taleSerial++);
                tableMap.put(MapConstants.TABLE_NAME,currentTableName);
                tableMap.put(MapConstants.TABLE_REMARK,currentTableRemark);
                tableMap.put(MapConstants.COLUMN_LIST,columnList);
                log.info("{}",tableMap);
                tableList.add(tableMap);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return tableList;
    }

    private List<String> getTableNameList(List<Table> tableList){
        List<String> list = new ArrayList<>();
        if (tableList != null ){
            for (Table table : tableList) {
                if (table.getTableName().equals("trace_xe_action_map")||table.getTableName().equals("trace_xe_event_map")){break;}
                list.add(table.getTableName());
            }
        }
        return list;
    }
}

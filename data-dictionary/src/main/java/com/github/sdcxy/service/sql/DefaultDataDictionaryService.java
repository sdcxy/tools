package com.github.sdcxy.service.sql;

import com.github.sdcxy.constants.DataBaseConstants;
import com.github.sdcxy.constants.MapConstants;
import com.github.sdcxy.constants.SignConstants;
import com.github.sdcxy.entity.Column;
import com.github.sdcxy.entity.DataDictionaryDataSource;
import com.github.sdcxy.entity.Table;
import com.github.sdcxy.enums.DBDriver;
import com.github.sdcxy.enums.DBType;
import com.github.sdcxy.enums.DBUrl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DefaultDataDictionaryService
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/16 15:16
 **/
public class DefaultDataDictionaryService implements DefaultDataDictionaryServiceFactory {

    @Autowired
    private MySqlDataDictionary mySqlDataDictionary;

    @Autowired
    private SqlServerDataDictionary sqlServerDataDictionary;

    @Autowired
    private PostgreSqlDataDictionary postgreSqlDataDictionary;

    @Autowired
    private OracleDataDictionary oracleDataDictionary;

    @Autowired
    private DB2DataDictionary db2DataDictionary;

    @Autowired
    private DataDictionaryDataSource dataSource;


    /**
     * 获取数据库表信息
     * @param tableName
     * @return
     */
    @Override
    public List<Table> getTableList(String tableName) {
        List<Table> list = new ArrayList<>();
        if (dataSource.getDbType().equals(DBType.MYSQL.getDbName())){
            list = mySqlDataDictionary.getTableInfo(tableName);
        }
        if (dataSource.getDbType().equals(DBType.SQLSERVER.getDbName())){
            list = sqlServerDataDictionary.getTableInfo(tableName);
        }
        if (dataSource.getDbType().equals(DBType.ORACLE.getDbName())){
            list = oracleDataDictionary.getTableInfo(tableName);
        }
        if (dataSource.getDbType().equals(DBType.DB2.getDbName())){
            list = db2DataDictionary.getTableInfo(tableName);
        }
        if (dataSource.getDbType().equals(DBType.POSTGRE_SQL.getDbName())){
            list = postgreSqlDataDictionary.getTableInfo(tableName);
        }
        return list;
    }


    @Override
    public List<Table> getTableList(){
        return getTableList(null);
    }

    /**
     * 获取数据库表名 所有的
     * @param tableList
     * @return
     */
    @Override
    public List<String> getTableNames(List<Table> tableList){
        List<String> list = new ArrayList<>();
        if (tableList != null ){
            for (Table table : tableList) {
                String tableName = table.getTableName();
                if (tableName.equals("trace_xe_action_map")||tableName.equals("trace_xe_event_map")){break;}
                list.add(tableName);
            }
        }
        list.add("All");
        return list;
    }

    /**
     * 返回数据ListMap
     * @param tableList
     * @return
     */
    @Override
    public  List<Map<String,Object>> ListToMap(List<Table> tableList){
        if (dataSource.getDbType().equals(DBType.SQLSERVER.getDbName())){
            return sqlServerDataDictionary.getSqlServerTableInfo(tableList);
        }
        Map<String,Object> TableMap;
        Map<String,Object> ColumnMap;
        List<Map<String,Object>> TableList = new ArrayList<>();
        List<Map<String,Object>> ColumnList;
        if (tableList != null ){
            int i = 1;
            for (Table table : tableList) {
                TableMap = new HashMap<>();
                TableMap.put(MapConstants.TABLE_SERIAL,i);
                TableMap.put(MapConstants.TABLE_NAME,table.getTableName());
                TableMap.put(MapConstants.TABLE_REMARK,table.getTableRemark());
                ColumnList = new ArrayList<>();
                List<Column> columnList = table.getColumnList();
                if (columnList != null) {
                    int k =1;
                    for (Column column : columnList) {
                        ColumnMap = new HashMap<>();
                        ColumnMap.put(MapConstants.COLUMN_SERIAL,k);
                        ColumnMap.put(MapConstants.COLUMN_NAME,column.getColumnName());
                        ColumnMap.put(MapConstants.COLUMN_TYPE,column.getColumnType() + SignConstants.LEFT_BRACKETS + column.getColumnLength() + SignConstants.RIGHT_BRACKETS);
                        if (column.isPrimaryKey()){
                            ColumnMap.put(MapConstants.COLUMN_PRIMARY_KEY,"YES");
                        }else{
                            ColumnMap.put(MapConstants.COLUMN_PRIMARY_KEY,"");
                        }
                        ColumnMap.put(MapConstants.COLUMN_NULLABLE,column.getNullable());
                        ColumnMap.put(MapConstants.COLUMN_REMARK,column.getColumnRemark());
                        ColumnList.add(ColumnMap);
                        k++;
                    }
                }
                TableMap.put(MapConstants.COLUMN_LIST,ColumnList);
                TableList.add(TableMap);
                i++;
            }
        }
        return TableList;
    }

    @Override
    public DataDictionaryDataSource copyProperties(DataDictionaryDataSource dataDictionaryDataSource) {
        String dbType = dataDictionaryDataSource.getDbType();
        String url = null;
        String driver = null;
        if (StringUtils.isNotEmpty(dbType)) {
            if (DBType.MYSQL.getDbName().equals(dbType)){
                url= DBUrl.MYSQL_URL.getUrl()
                        .replace(DataBaseConstants.IP,dataDictionaryDataSource.getIp())
                        .replace(DataBaseConstants.PORT,String.valueOf(dataDictionaryDataSource.getPort()))
                        .replace(DataBaseConstants.DATABASE,dataDictionaryDataSource.getDataBase());
                driver = DBDriver.MYSQL_DRIVER.getDriverName();

            }
            if (DBType.SQLSERVER.getDbName().equals(dbType)){
                url = DBUrl.SQLSERVER_URL.getUrl()
                        .replace(DataBaseConstants.IP,dataDictionaryDataSource.getIp())
                        .replace(DataBaseConstants.PORT,String.valueOf(dataDictionaryDataSource.getPort()))
                        .replace(DataBaseConstants.DATABASE,dataDictionaryDataSource.getDataBase());
                driver = DBDriver.SQLSERVER_DRIVER_PLUS.getDriverName();
            }
            if (DBType.ORACLE.getDbName().equals(dbType)){
                url = DBUrl.ORACLE_URL.getUrl()
                        .replace(DataBaseConstants.IP,dataDictionaryDataSource.getIp())
                        .replace(DataBaseConstants.PORT,String.valueOf(dataDictionaryDataSource.getPort()))
                        .replace(DataBaseConstants.DATABASE,dataDictionaryDataSource.getDataBase());
                driver = DBDriver.ORACLE_DRIVER.getDriverName();
            }
            if (DBType.DB2.getDbName().equals(dbType)){
                url = DBUrl.DB2_URL.getUrl()
                        .replace(DataBaseConstants.IP,dataDictionaryDataSource.getIp())
                        .replace(DataBaseConstants.PORT,String.valueOf(dataDictionaryDataSource.getPort()))
                        .replace(DataBaseConstants.DATABASE,dataDictionaryDataSource.getDataBase());
                driver = DBDriver.DB2_DRIVER.getDriverName();
            }
            if (DBType.POSTGRE_SQL.getDbName().equals(dbType)){
                url = DBUrl.POSTGRESQL_URL.getUrl()
                        .replace(DataBaseConstants.IP,dataDictionaryDataSource.getIp())
                        .replace(DataBaseConstants.PORT,String.valueOf(dataDictionaryDataSource.getPort()))
                        .replace(DataBaseConstants.DATABASE,dataDictionaryDataSource.getDataBase());
                driver = DBDriver.POSTGRESQL_DRIVER.getDriverName();
            }
            BeanUtils.copyProperties(dataDictionaryDataSource,dataSource);
            dataSource.setUrl(url);
            dataSource.setDriver(driver);
        }
        return dataSource;
    }

}

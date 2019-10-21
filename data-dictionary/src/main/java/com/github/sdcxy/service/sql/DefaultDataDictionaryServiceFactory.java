package com.github.sdcxy.service.sql;

import com.github.sdcxy.entity.DataDictionaryDataSource;
import com.github.sdcxy.entity.Table;

import java.util.List;
import java.util.Map;

public interface DefaultDataDictionaryServiceFactory {

    List<Table> getTableList(String tableName);

    List<Table> getTableList();

    List<String> getTableNames(List<Table> tableList);

    List<Map<String,Object>> ListToMap(List<Table> tableList);

    DataDictionaryDataSource copyProperties(DataDictionaryDataSource dataDictionaryDataSource);
}

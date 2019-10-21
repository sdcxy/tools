package com.github.sdcxy.service.sql;

import com.github.sdcxy.dao.PostgreSqlConnection;
import com.github.sdcxy.entity.DataDictionaryDataSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName PostgreSqlDataDictionary
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/21 2:08
 **/
public class PostgreSqlDataDictionary extends  AbstractDataDictionary{

    @Autowired
    private PostgreSqlConnection postgreSqlConnection;

    @Autowired
    private DataDictionaryDataSource dataSource;

    @Override
    public List getTableInfo(String tableNames) {
        return getTableList(postgreSqlConnection.getConnection(),dataSource,tableNames);
    }
}

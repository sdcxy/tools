package com.github.sdcxy.service.sql;

import com.github.sdcxy.dao.OracleConnection;
import com.github.sdcxy.entity.DataDictionaryDataSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName OracleDataDictionary
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/21 11:16
 **/
public class OracleDataDictionary extends AbstractDataDictionary {

    @Autowired
    private OracleConnection oracleConnection;

    @Autowired
    private DataDictionaryDataSource dataSource;

    @Override
    public List getTableInfo(String tableNames) {
        return getTableList(oracleConnection.getConnection(),dataSource,tableNames);
    }
}

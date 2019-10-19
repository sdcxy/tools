package com.github.sdcxy.service;

import com.github.sdcxy.constants.DataBaseConstants;
import com.github.sdcxy.constants.SignConstants;
import com.github.sdcxy.dao.SqlServerConnection;
import com.github.sdcxy.entity.Column;
import com.github.sdcxy.entity.DataDictionaryDataSource;
import com.github.sdcxy.entity.Table;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName SqlServerDataDictionary
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/14 16:21
 **/
@Slf4j
public class SqlServerDataDictionary extends AbstractDataDictionary {

    @Autowired
    private SqlServerConnection sqlServerConnection;

    @Autowired
    private DataDictionaryDataSource dataSource;

    @Override
    public List getTableInfo(String tableNames) {
        return getTableList(sqlServerConnection.getConnection(),dataSource,tableNames);
    }
}

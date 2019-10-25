package com.github.sdcxy.service.sql;

import com.github.sdcxy.dao.DB2Connection;
import com.github.sdcxy.entity.DataDictionaryDataSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName DB2DataDictionary
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/21 23:56
 **/
public class DB2DataDictionary extends AbstractDataDictionary {

    @Autowired
    private DB2Connection db2Connection;

    @Autowired
    private DataDictionaryDataSource dataSource;

    @Override
    public List getTableInfo(String tableNames) {
        return getTableList(db2Connection.getConnection(),dataSource,tableNames);
    }
}

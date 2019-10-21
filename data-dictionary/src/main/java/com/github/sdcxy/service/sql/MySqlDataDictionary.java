package com.github.sdcxy.service.sql;

import com.github.sdcxy.dao.MySqlConnection;
import com.github.sdcxy.entity.DataDictionaryDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName MySqlDataDictionary
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/14 14:51
 **/
@Slf4j
public class MySqlDataDictionary extends AbstractDataDictionary {

    @Autowired
    private MySqlConnection mySqlConnection;

    @Autowired
    private DataDictionaryDataSource dataSource;

    @Override
    public List getTableInfo(String tableNames) {
        return getTableList(mySqlConnection.getConnection(),dataSource,tableNames);
    }
}

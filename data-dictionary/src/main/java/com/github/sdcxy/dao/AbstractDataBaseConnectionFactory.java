package com.github.sdcxy.dao;

import com.github.sdcxy.entity.DataDictionaryDataSource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Getter
/**
 * {@link MySqlConnection}
 * {@link SqlServerConnection}
 * {@link PostgreSqlConnection}
 */
public abstract class AbstractDataBaseConnectionFactory implements DataBaseConnectionFactory{
    @Autowired
    protected DataDictionaryDataSource dataSource;
}

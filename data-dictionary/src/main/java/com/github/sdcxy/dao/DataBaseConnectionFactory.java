package com.github.sdcxy.dao;


import java.sql.Connection;

/**
 * @ClassName DataBaseConnectionFactory
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/14 12:03
 * {@link AbstractDataBaseConnectionFactory}
 **/
public interface DataBaseConnectionFactory {
    // 获取数据库连接
    Connection getConnection();
}

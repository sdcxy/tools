package com.github.sdcxy.dao;


import java.sql.Connection;

/**
 * @ClassName DataBaseConnectionFactory
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/14 12:03
 **/
public interface DataBaseConnectionFactory {
    // 获取数据库连接
//    Connection getConnection(String ip,int port,String dataBaseName,String username,String password);
    Connection getConnection();
}

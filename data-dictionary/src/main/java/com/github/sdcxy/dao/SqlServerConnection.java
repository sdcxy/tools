package com.github.sdcxy.dao;

import com.github.sdcxy.constants.DataBaseConstants;
import com.github.sdcxy.enums.DBDriver;
import com.github.sdcxy.enums.DBUrl;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @ClassName SqlServerConnection
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/14 15:58
 **/
@Slf4j
public class SqlServerConnection extends AbstractDataBaseConnectionFactory {

    private Connection getConnection(String ip, int port, String dataBaseName, String username, String password) {
        Connection conn = null;
        String url = DBUrl.SQLSERVER_URL.getUrl()
                .replace(DataBaseConstants.IP,ip)
                .replace(DataBaseConstants.PORT,String.valueOf(port))
                .replace(DataBaseConstants.DATABASE,dataBaseName);
        try{
            Class.forName(DBDriver.SQLSERVER_DRIVER.getDriverName());
            conn = DriverManager.getConnection(url,username,password);
            log.info("----------开始连接数据库----------");
            if (conn.isClosed()){
                //
                log.info("--------数据库关闭链接--------");
            }
        } catch (Exception e) {
            log.error("数据库链接异常:[{}]",e.getMessage());
        }
        return conn;
    }

    @Override
    public Connection getConnection() {
        return getConnection(dataSource.getIp(), dataSource.getPort(), dataSource.getDataBase(), dataSource.getUsername(), dataSource.getPassword());
    }
}

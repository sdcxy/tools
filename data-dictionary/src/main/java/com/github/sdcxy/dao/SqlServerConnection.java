package com.github.sdcxy.dao;

import com.github.sdcxy.constants.DataBaseConstants;
import com.github.sdcxy.enums.DBDriver;
import com.github.sdcxy.enums.DBUrl;
import com.github.sdcxy.exception.ExceptionEnum;
import com.github.sdcxy.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @ClassName SqlServerConnection
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/14 15:58
 * {@link AbstractDataBaseConnectionFactory}
 **/
@Slf4j
public class SqlServerConnection extends AbstractDataBaseConnectionFactory {

    /**
     * SqlServer数据库链接
     * @param ip IP 地址
     * @param port 端口号
     * @param dataBaseName 数据库名称
     * @param username 数据库用户名
     * @param password 数据库密码
     * @return 返回Connection
     * {@link com.github.sdcxy.service.sql.SqlServerDataDictionary}
     */
    private Connection getConnection(String ip, int port, String dataBaseName, String username, String password) {
        Connection conn = null;
        String url = DBUrl.SQLSERVER_URL.getUrl()
                .replace(DataBaseConstants.IP,ip)
                .replace(DataBaseConstants.PORT,String.valueOf(port))
                .replace(DataBaseConstants.DATABASE,dataBaseName);
        try{
            Class.forName(DBDriver.SQLSERVER_DRIVER_PLUS.getDriverName());
            conn = DriverManager.getConnection(url,username,password);
            log.info("----------开始连接数据库----------");
            if (conn.isClosed()){
                //
                log.info("--------数据库关闭链接--------");
            }
        } catch (Exception e) {
            log.error("数据库链接异常:[{}]",e.getMessage());
            throw new GlobalException(ExceptionEnum.SQLSERVER_CONNECTION_EXCEPTION.getCode(),ExceptionEnum.SQLSERVER_CONNECTION_EXCEPTION.getMsg());
        }
        return conn;
    }

    @Override
    public Connection getConnection() {
        return getConnection(dataSource.getIp(), dataSource.getPort(), dataSource.getDataBase(), dataSource.getUsername(), dataSource.getPassword());
    }
}

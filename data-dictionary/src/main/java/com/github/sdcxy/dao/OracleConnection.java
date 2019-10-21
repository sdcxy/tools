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
 * @ClassName OracleConnection
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/21 11:11
 **/
@Slf4j
public class OracleConnection extends AbstractDataBaseConnectionFactory {


    private Connection getConnection(String ip, int port, String dataBaseName, String username, String password){
        Connection conn = null;
        String url = DBUrl.ORACLE_URL.getUrl()
                .replace(DataBaseConstants.IP,ip)
                .replace(DataBaseConstants.PORT,String.valueOf(port))
                .replace(DataBaseConstants.DATABASE,dataBaseName);
        try{
            Class.forName(DBDriver.ORACLE_DRIVER.getDriverName());
            Properties properties = new Properties();
            properties.setProperty("user",username);
            properties.setProperty("password",password);
            properties.setProperty("remarksReporting","true");//设置可以获取remarks信息
            conn = DriverManager.getConnection(url,properties);
            log.info("----------开始连接数据库----------");
            if (conn.isClosed()){
                //
                log.info("--------数据库关闭链接--------");
            }
        }catch (Exception e){
            log.error("数据库链接异常:[{}]",e.getMessage());
            throw new GlobalException(ExceptionEnum.ORACLE_CONNECTION_EXCEPTION.getCode(),ExceptionEnum.ORACLE_CONNECTION_EXCEPTION.getMsg());
        }
        return conn;
    }

    @Override
    public Connection getConnection() {
        return getConnection(dataSource.getIp(),dataSource.getPort(),dataSource.getDataBase(),dataSource.getUsername(),dataSource.getPassword());
    }
}

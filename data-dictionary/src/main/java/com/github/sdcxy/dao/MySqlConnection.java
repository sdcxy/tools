package com.github.sdcxy.dao;

import com.github.sdcxy.constants.DataBaseConstants;
import com.github.sdcxy.enums.DBDriver;
import com.github.sdcxy.enums.DBUrl;
import com.github.sdcxy.exception.ExceptionEnum;
import com.github.sdcxy.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.Properties;

/**
 * @ClassName MySqlConnection
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/14 14:05
 * {@link AbstractDataBaseConnectionFactory}
 **/
@Slf4j
public class MySqlConnection extends AbstractDataBaseConnectionFactory {

    /**
     *  mysql数据库链接
     * @param ip IP 地址
     * @param port 端口号
     * @param dataBaseName 数据库名称
     * @param username 数据库用户名
     * @param password 数据库密码
     * @return 返回Connection
     * {@link com.github.sdcxy.service.sql.MySqlDataDictionary}
     */
    private Connection getConnection(String ip, int port, String dataBaseName, String username, String password) {
        Connection conn = null;
        String url = DBUrl.MYSQL_URL.getUrl()
                .replace(DataBaseConstants.IP,ip)
                .replace(DataBaseConstants.PORT,String.valueOf(port))
                .replace(DataBaseConstants.DATABASE,dataBaseName);
        try{
            Class.forName(DBDriver.MYSQL_DRIVER.getDriverName());
            Properties properties = new Properties();
            properties.setProperty("user",username);
            properties.setProperty("password",password);
            properties.setProperty("remark","true");//设置可以获取remarks信息
            properties.setProperty("useInformationSchema","true");//设置可以获取tables remarks信息
            conn = DriverManager.getConnection(url,properties);
//            conn = DriverManager.getConnection(url,username,password);
            log.info("----------开始连接数据库----------");
            if (conn.isClosed()){
                //
               log.info("--------数据库关闭链接--------");
            }
        } catch (Exception e) {
            log.error("数据库链接异常:[{}]",e.getMessage());
            throw new GlobalException(ExceptionEnum.MYSQL_CONNECTION_EXCEPTION.getCode(),ExceptionEnum.MYSQL_CONNECTION_EXCEPTION.getMsg());
        }
        return conn;
    }

    @Override
    public Connection getConnection(){
        return getConnection(dataSource.getIp(), dataSource.getPort(), dataSource.getDataBase(), dataSource.getUsername(), dataSource.getPassword());
    }
}

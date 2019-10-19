package com.github.sdcxy.entity;

import com.github.sdcxy.auto.DataDictionaryProperties;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName DataDictionaryDataSource
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/12 11:30
 **/
@Data
@Accessors(chain = true)
public class DataDictionaryDataSource {

    private DataDictionaryDataSource(){}

    public DataDictionaryDataSource(DataDictionaryProperties dataDictionaryProperties){
        this.driver = dataDictionaryProperties.getDriver();
        this.url = dataDictionaryProperties.getUrl();
        this.dbType = dataDictionaryProperties.getDbType();
        this.ip = dataDictionaryProperties.getIp();
        this.port = dataDictionaryProperties.getPort();
        this.dataBase = dataDictionaryProperties.getDataBase();
        this.username = dataDictionaryProperties.getUsername();
        this.password = dataDictionaryProperties.getPassword();
    }

    public DataDictionaryDataSource(String driver,String url,String dbType,String ip,Integer port,String dataBase,String username,String password){
        this.driver = driver;
        this.url = url;
        this.dbType = dbType;
        this.ip = ip;
        this.port = port;
        this.dataBase = dataBase;
        this.username = username;
        this.password = password;
    }

    private String driver;

    private String url;

    private String dbType;

    private String ip;

    private Integer port;

    private String dataBase;

    private String username;

    private String password;
}

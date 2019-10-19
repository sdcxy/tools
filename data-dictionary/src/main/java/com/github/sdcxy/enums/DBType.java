package com.github.sdcxy.enums;

import lombok.Getter;

@Getter
public enum DBType {
    MYSQL("MySql"),

    SQLSERVER("SqlServer"),
    SQLSERVER_2000("SqlServer_2000"),
    SQLSERVER_2005("SqlServer_2005"),
    SQLSERVER_2008("SqlServer_2008"),
    SQLSERVER_2012("SqlServer_2012"),


    ORACLE("Oracle"),

    DB2("DB2"),

    ;
    private String dbName;


    private DBType(){}

    DBType(String dbName){this.dbName = dbName;}

}

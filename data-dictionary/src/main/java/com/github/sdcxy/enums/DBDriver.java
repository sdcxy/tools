package com.github.sdcxy.enums;

import lombok.Getter;

@Getter
public enum DBDriver {
    MYSQL_DRIVER("com.mysql.jdbc.Driver"),

    MYSQL_PLUS_DRIVER("com.mysql.cj.jdbc.Driver"),

    SQLSERVER_DRIVER("com.microsoft.sqlserver.jdbc.SQLServerDriver"),

    ORACLE_DRIVER("oracle.jdbc.driver.OracleDriver"),

    DB2_DRIVER("com.ibm.db2.jcc.DB2Driver")
    ;
    private String driverName;


    DBDriver(String driverName){this.driverName = driverName;}
}

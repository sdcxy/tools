package com.github.sdcxy.enums;

import lombok.Getter;

@Getter
public enum DBUrl {
    MYSQL_URL("jdbc:mysql://IP:PORT/DATABASE?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8"),
    SQLSERVER_URL("jdbc:sqlserver://IP:PORT;DatabaseName=DATABASE;"),
    ORACLE_URL("jdbc:oracle:thin:@//IP:PORT/DATABASE"),
    DB2_URL("jdbc:db2://IP:PORT/DATABASE"),
    POSTGRESQL_URL("jdbc:postgresql://IP:PORT/DATABASE")
    ;
    private String url;

    private DBUrl(){}

    DBUrl(String url){this.url = url;}
}

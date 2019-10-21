package com.github.sdcxy.exception;

import lombok.Getter;

/**
 * @ClassName ExceptionEnum
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/21 16:49
 **/
@Getter
public enum  ExceptionEnum {

    MYSQL_CONNECTION_EXCEPTION(40001,"mysql数据库连接异常"),
    SQLSERVER_CONNECTION_EXCEPTION(40002,"sqlserver数据库连接异常"),
    POSTGRESQL_CONNECTION_EXCEPTION(40003,"postgresql数据库连接异常"),
    ORACLE_CONNECTION_EXCEPTION(40004,"oracle数据库连接异常"),
    DB2_CONNECTION_EXCEPTION(40005,"db2数据库连接异常"),


    ;
    private int code;
    private String msg;

    ExceptionEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
}

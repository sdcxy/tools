package com.github.sdcxy.exception;

import lombok.Data;

/**
 * @ClassName GlobalException
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/21 16:38
 **/
@Data
public class GlobalException extends RuntimeException {

    private int code;

    public GlobalException(int code, String msg){
        super(msg);
        this.code = code;
    }
}

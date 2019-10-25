package com.github.sdcxy.common.exception;


/**
 * @ClassName CustomException
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/24 16:11
 **/
public abstract class CustomException extends Exception {

    CustomException(String msg){
        super(msg);
    }

}

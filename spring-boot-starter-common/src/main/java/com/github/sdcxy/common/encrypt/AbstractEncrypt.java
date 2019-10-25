package com.github.sdcxy.common.encrypt;

import com.github.sdcxy.common.exception.EncryptException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.OutputStream;

/**
 * @ClassName AbstractEncrypt
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/24 15:34
 **/
@Slf4j
public abstract class AbstractEncrypt implements EncryptFactory{

    protected String expiredMethod(){
        try{
            throw new EncryptException("The method has expired");
        }catch (Exception e){
            log.error("AbstractEncrypt:decrypt --->{}",e.getMessage());
        }
        return null;
    }

    protected void throwEncryptException(String str) throws EncryptException {
        // 判断是否为null  抛出异常
        if (StringUtils.isEmpty(str)){throw new EncryptException("Input parameters is Not Null");}
    }

    /**
     *  关闭输出流
     * @param outputStream 输出流
     */
    protected void close(OutputStream outputStream){
        try{
            if (outputStream != null){
                outputStream.close();
            }
        }catch (Exception e){
            log.error("AbstractEncrypt:close --->{}",e.getMessage());
        }
    }
}

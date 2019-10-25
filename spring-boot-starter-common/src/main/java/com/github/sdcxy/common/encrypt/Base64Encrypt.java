package com.github.sdcxy.common.encrypt;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Base64;

/**
 * @ClassName Base64Encrypt
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/24 16:59
 **/
@Slf4j
public class Base64Encrypt extends AbstractEncrypt {

    @Deprecated
    @Override
    public String encrypt(String encryptStr, String encryptKey) {
        return expiredMethod();
    }

    /**
     *  base64加密
     * @param encryptStr 需加密字符串
     * @return
     */
    public String encrypt(String encryptStr) {
        String resultStr = null;
        try{
            if (StringUtils.isNotEmpty(encryptStr)) {
                resultStr = Base64.getEncoder().encodeToString(encryptStr.getBytes("UTF-8"));
            }else{
                throwEncryptException(encryptStr);
            }
        }catch (Exception e){
            log.error("Base64Encrypt:encrypt --->{}",e.getMessage());
        }
        return resultStr;
    }

    /**
     *  Base64解密
     * @param decryptStr 需解密字符串
     * @return
     */
    @Override
    public String decrypt(String decryptStr) {
        String resultStr = null;
        try{
            if (StringUtils.isNotEmpty(decryptStr)){
                resultStr = new String(Base64.getDecoder().decode(decryptStr.getBytes()),"UTF-8");
            }else{
                throwEncryptException(decryptStr);
            }
        }catch (Exception e){
            log.error("Base64Encrypt:decrypt --->{}",e.getMessage());
        }
        return resultStr;
    }

    @Deprecated
    @Override
    public String decrypt(String decryptStr, String encryptKey) {
        return expiredMethod();
    }
}

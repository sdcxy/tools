package com.github.sdcxy.common.encrypt;

import com.github.sdcxy.common.exception.EncryptException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;



/**
 * @ClassName MD5Encrypt
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/24 15:35
 **/
@Slf4j
public class MD5Encrypt extends AbstractEncrypt {

    /**
     *  md5 加密
     * @param encryptStr 加密字符串
     * @param encryptKey 加密密钥
     * @return 返回字符串
     */
    @Override
    public String encrypt(String encryptStr, String encryptKey) {
        String resultStr = null;
        try{
           if (StringUtils.isNotEmpty(encryptStr)){
               if (StringUtils.isNotEmpty(encryptKey)) {
                   resultStr = DigestUtils.md5Hex(encryptStr + encryptKey);
               }else{
                   resultStr = DigestUtils.md5Hex(encryptStr);
               }
           }else{
               throwEncryptException(encryptStr);
           }
        }catch (Exception e){
            log.error("MD5Encrypt:encrypt --->{}",e.getMessage());
        }
        return resultStr;
    }

    @Deprecated
    @Override
    public String decrypt(String decryptStr) {
        return expiredMethod();
    }

    @Deprecated
    @Override
    public String decrypt(String decryptStr, String encryptKey) {
        return expiredMethod();
    }


    public boolean verify(String encryptStr,String encryptKey,String md5Str){
        return encrypt(encryptStr, encryptKey).equals(md5Str);
    }

}

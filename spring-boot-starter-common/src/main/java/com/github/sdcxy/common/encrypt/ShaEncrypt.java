package com.github.sdcxy.common.encrypt;

import com.github.sdcxy.common.constants.EncryptConstants;
import com.github.sdcxy.common.exception.EncryptException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;

/**
 * @ClassName ShaEncrypt
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/25 16:31
 **/
@Slf4j
public class ShaEncrypt {

   public String sha1(String encryptStr){
        String resultStr = null;
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
               'a','b','c','d','e','f'};
        try {
            if (StringUtils.isEmpty(encryptStr)){
                throw  new EncryptException("encryptStr is Not Null");
            }
            MessageDigest mdTemp = MessageDigest.getInstance(EncryptConstants.SHA1);
            mdTemp.update(encryptStr.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j*2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            resultStr = new String(buf);
        } catch (Exception e) {
           log.error("ShaEncrypt:sha1 --->{}",e.getMessage());
        }
        return resultStr;
   }
}

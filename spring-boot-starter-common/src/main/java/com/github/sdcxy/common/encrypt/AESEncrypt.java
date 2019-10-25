package com.github.sdcxy.common.encrypt;

import com.github.sdcxy.common.autoconfigure.properties.EncryptProperties;
import com.github.sdcxy.common.constants.EncryptConstants;
import com.github.sdcxy.common.exception.EncryptException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @ClassName AESEncrypt
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/25 0:03
 **/
@Slf4j
public class AESEncrypt extends AbstractEncrypt {

    @Autowired
    private EncryptProperties encrypt;


    /**
     *  AES 加密
     * @param encryptStr 需要加密的字符串
     * @param encryptKey
     * @return String
     */
    @Override
    public String encrypt(String encryptStr, String encryptKey) {
        String resultStr = null;
        try{
            throwEncryptException(encryptStr);
            throwEncryptException(encryptKey);
            Cipher cipher = Cipher.getInstance(EncryptConstants.AES);
            cipher.init(Cipher.ENCRYPT_MODE,getSecretKey(encryptKey));
            byte[] encrypt = cipher.doFinal(encryptStr.getBytes());
            resultStr = Base64.getEncoder().encodeToString(encrypt);
        }catch (Exception e){
            log.error("AESEncrypt:encrypt --->{}",e.getMessage());
        }
        return resultStr;
    }

    @Deprecated
    @Override
    public String decrypt(String decryptStr) {
        return expiredMethod();
    }

    /**
     * AES 解密
     * @param decryptStr 需要解密字符串
     * @param encryptKey 解密密钥
     * @return String
     */
    @Override
    public String decrypt(String decryptStr, String encryptKey){
        String resultStr = null;
        try{
            throwEncryptException(decryptStr);
            throwEncryptException(encryptKey);
            Cipher cipher = Cipher.getInstance(EncryptConstants.AES);
            cipher.init(Cipher.DECRYPT_MODE,getSecretKey(encryptKey));
            byte[] decrypt = cipher.doFinal(Base64.getMimeDecoder().decode(decryptStr));
            resultStr =  new String(decrypt,"UTF-8");
        }catch (Exception e){
            log.error("AESEncrypt:decrypt --->{}",e.getMessage());
        }
        return resultStr;
    }


    private SecretKey getSecretKey(String encryptKey) throws EncryptException,NoSuchAlgorithmException {
        throwEncryptException(encryptKey);
        KeyGenerator keyGenerator = KeyGenerator.getInstance(EncryptConstants.AES);
        keyGenerator.init(encrypt.getAesKeySize(),new SecureRandom(encryptKey.getBytes()));
        return keyGenerator.generateKey();
    }


}

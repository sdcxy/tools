package com.github.sdcxy.common.encrypt;

public interface EncryptFactory  {

    /** 加密 */
    String encrypt(String encryptStr,String encryptKey);

    /** 解密 */
    String decrypt(String decryptStr);

    /** 解密 */
    String decrypt(String decryptStr,String encryptKey);
}

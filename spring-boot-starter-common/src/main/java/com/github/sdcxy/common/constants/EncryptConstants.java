package com.github.sdcxy.common.constants;

/**
 * @ClassName EncryptConstants
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/25 0:04
 **/
public class EncryptConstants {

    public static final String ENCRYPT = "encrypt";

    /**
     * 公钥文件名称
     */
    public static final String PUBLIC_FILE_NAME = "public.key";

    /**
     * 私钥文件名称
     */
    public static final String PRIVATE_FILE_NAME = "private.key";
    /**
     * MD5加密算法
     */
    public static final String MD5 = "MD5";
    /**
     * AES加密算法
     */
    public static final String AES = "AES";

    /**
     * AES 加密长度
     */
    public static final int AES_KEY_SIZE = 128;

    /**
     * RSA加密算法
     */
    public static final String RSA ="RSA";

    /**
     *  RSA 加密的公钥KEY
     */
    public static final String PUBLIC = "public";
    /**
     *  RSA 解密的私钥KEY
     */
    public static final String PRIVATE = "private";
    /**
     * RSA 加密长度
     */
    public static final int RSA_KEY_SIZE = 1024;

    /**
     * RSA 加密长度
     */
    public static final int RSA_CLEAR_TEXT_SIZE = (RSA_KEY_SIZE / 8) - 11;


    /**
     *  SHA1加密
     */
    public static final String SHA1  = "SHA1";

}

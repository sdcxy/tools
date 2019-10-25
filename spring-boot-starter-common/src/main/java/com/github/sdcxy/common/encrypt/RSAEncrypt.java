package com.github.sdcxy.common.encrypt;

import com.github.sdcxy.common.autoconfigure.properties.EncryptProperties;
import com.github.sdcxy.common.constants.CommonConstants;
import com.github.sdcxy.common.constants.EncryptConstants;
import com.github.sdcxy.common.exception.EncryptException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.Cipher;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName RSAEncrypt
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/25 1:30
 **/
@Slf4j
public class RSAEncrypt extends AbstractEncrypt {

    // 创建一个Map来保存密钥 key: private,public
    public Map<String,Object> keyMap = new ConcurrentHashMap<>();

    private static final String PUBLIC_KEY_FILE =  CommonConstants.PROJECT_FILE_PATH + CommonConstants.RESOURCES_PATH +CommonConstants.ENCRYPT_FILE + EncryptConstants.PUBLIC_FILE_NAME;
    private static final String PRIVATE_KEY_FILE = CommonConstants.PROJECT_FILE_PATH + CommonConstants.RESOURCES_PATH +CommonConstants.ENCRYPT_FILE + EncryptConstants.PRIVATE_FILE_NAME;

    @Autowired
    private EncryptProperties encrypt;

    /**
     *  rsa 加密
     * @param encryptStr 需要加密的字符串
     * @param publicKeyStr 加密公钥
     * @return
     */
    @Override
    public String encrypt(String encryptStr, String publicKeyStr) {
        String resultStr = null;
        try {
            verifyKeyMap();
            verifyClearTextLength(encryptStr);
            throwEncryptException(publicKeyStr);
            // 获取公钥
            PublicKey publicKey = getPublicKey(publicKeyStr);
            Cipher cipher = Cipher.getInstance(EncryptConstants.RSA);
            cipher.init(Cipher.ENCRYPT_MODE,publicKey);
            resultStr = Base64.getEncoder().encodeToString(cipher.doFinal(encryptStr.getBytes("UTF-8")));
        } catch (Exception e) {
            log.error("RSAEncrypt:encrypt --->{}",e.getMessage());
        }
        return resultStr;
    }

    @Deprecated
    @Override
    public String decrypt(String decryptStr) {
        return expiredMethod();
    }

    /**
     * rsa解密
     * @param decryptStr 需解密的字符串
     * @param privateKeyStr 解密私钥
     * @return
     */
    @Override
    public String decrypt(String decryptStr, String privateKeyStr) {
        String resultStr = null;
        try{
            verifyKeyMap();
            throwEncryptException(decryptStr);
            throwEncryptException(privateKeyStr);
            // 获取密钥
            PrivateKey privateKey = getPrivateKey(privateKeyStr);
            Cipher cipher = Cipher.getInstance(EncryptConstants.RSA);
            cipher.init(Cipher.DECRYPT_MODE,privateKey);
            byte[] decryptBytes = cipher.doFinal(Base64.getDecoder().decode(decryptStr));
            resultStr = new String(decryptBytes,"UTF-8");
        }catch (Exception e){
            log.error("RSAEncrypt:decrypt --->{}",e.getMessage());
        }
        return resultStr;
    }

    /**
     *  获取RSA密钥对
     * @param encryptKey 输入参数生成加密密钥
     * @return
     */
    public void getKeyPair(String encryptKey) throws EncryptException,NoSuchAlgorithmException{
        throwEncryptException(encryptKey);
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(EncryptConstants.RSA);
        keyPairGenerator.initialize(encrypt.getRsaKeySize(),new SecureRandom(encryptKey.getBytes()));
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 获取字符串的公私密钥
        String publicKey = getPublicKeyStr(keyPair.getPublic());
        String privateKey = getPrivateKeyStr(keyPair.getPrivate());
        // 将密钥保存到Map中
        keyMap.put(EncryptConstants.PUBLIC,publicKey);
        keyMap.put(EncryptConstants.PRIVATE,privateKey);
        if (encrypt.isCreateRsaKeyFile()){
            createKeyFile();
        }
    }

    /**
     *  获取公钥
     * @param publicKeyStr 公钥字符串
     * @return
     */
    private PublicKey getPublicKey(String publicKeyStr){
        PublicKey publicKey = null;
        try{
            KeyFactory keyFactory = KeyFactory.getInstance(EncryptConstants.RSA);
            byte[] bytes = Base64.getDecoder().decode(publicKeyStr.getBytes());
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
            publicKey = keyFactory.generatePublic(keySpec);
        }catch (Exception e){
            log.error("RSAEncrypt:getPublicKey --->{}",e.getMessage());
        }
        return publicKey;
    }

    /**
     *  获取公钥字符串
     * @param publicKey 公钥
     * @return
     */
    private String getPublicKeyStr(PublicKey publicKey){
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    /**
     * 获取私钥
     * @param privateKeyStr 私钥字符串
     * @return
     */
    private PrivateKey getPrivateKey(String privateKeyStr){
        PrivateKey privateKey= null;
        try{
            KeyFactory keyFactory = KeyFactory.getInstance(EncryptConstants.RSA);
            byte[] bytes = Base64.getDecoder().decode(privateKeyStr.getBytes());
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec (bytes);
            privateKey = keyFactory.generatePrivate(keySpec);
        }catch (Exception e){
            log.error("RSAEncrypt:getPrivateKey --->{}",e.getMessage());
        }
        return privateKey;
    }

    /**
     * 获取私钥字符串
     * @param privateKey 私钥
     * @return
     */
    private String getPrivateKeyStr(PrivateKey privateKey){
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    /**
     * 验证KeyMap是否为null
     * @throws EncryptException
     */
    private void verifyKeyMap() throws EncryptException {
        if (keyMap.isEmpty()){
            throw new EncryptException("keyMap can not be null. You have not use the getKeyPair method");
        }
    }

    /**
     * 验证加密明文的长度  （EncryptConstants.RSA_KEY_SIZE/8 - 11） = EncryptConstants.RSA_CLEAR_TEXT_SIZE
     * @param encryptStr
     * @throws EncryptException
     */
    private void verifyClearTextLength(String encryptStr) throws EncryptException {
        throwEncryptException(encryptStr);
        if (encrypt.getRsaKeySize()<1024){
            throw new EncryptException("set encrypt key length can not less than 1024 bytes");
        }
        int len = (encrypt.getRsaKeySize()/8) - 11;
        if (encryptStr.length() > len ){
            throw new EncryptException("clear text can not more than "+ len + " bytes in length");
        }
    }


    /**
     *  保存文件
     */
    private void createKeyFile(){
        File publicKeyFile = new File(PUBLIC_KEY_FILE);
        File privateKeyFile = new File(PRIVATE_KEY_FILE);
        OutputStream publicOutputStream = null;
        OutputStream privateOutputStream = null;
        try{
            // 创建文件
            isFileExist(publicKeyFile);
            isFileExist(privateKeyFile);
            // 获取密钥对（公钥,私钥）
            String publicKeyStr = (String)keyMap.get(EncryptConstants.PUBLIC);
            String privateKeyStr = (String)keyMap.get(EncryptConstants.PRIVATE);
            // 将公钥写入文件 public.key
            publicOutputStream = new FileOutputStream(publicKeyFile);
            publicOutputStream.write(publicKeyStr.getBytes("UTF-8"));
            // 将私钥写入文件 private.key
            privateOutputStream = new FileOutputStream(privateKeyFile);
            privateOutputStream.write(privateKeyStr.getBytes("UTF-8"));
        }catch (Exception e){
            log.error("RSAEncrypt:createKeyFile --->{}",e.getMessage());
        }finally {
            close(publicOutputStream);
            close(privateOutputStream);
        }
    }

    /**
     * 判断文件是否存在，不存在则创建
     * @param file
     */
    private void isFileExist(File file){
        try{
            if (!file.exists()){
                if (!file.getParentFile().exists()){
                    file.getParentFile().mkdir();
                    file.createNewFile();
                }
            }
        }catch (IOException e) {
            log.error("RSAEncrypt:isFileExist --->{}",e.getMessage());
        }
    }
}

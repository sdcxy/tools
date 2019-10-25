package com.github.sdcxy.common.autoconfigure.properties;

import com.github.sdcxy.common.constants.CommonConstants;
import com.github.sdcxy.common.constants.EncryptConstants;
import com.github.sdcxy.common.constants.SignConstants;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName EncryptProperties
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/25 11:28
 **/
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = CommonConstants.COMMON + SignConstants.POINT + EncryptConstants.ENCRYPT)
public class EncryptProperties {

    /**
     * rsa密钥长度
     */
    private Integer rsaKeySize = EncryptConstants.RSA_KEY_SIZE;

    /**
     * rsa密钥长度
     */
    private Integer aesKeySize = EncryptConstants.AES_KEY_SIZE;

    /**
     *  生成密钥文件保存到，如果设置为true
     *  会在目录下生成public.key和private.key
     */
    private boolean createRsaKeyFile = false;

}

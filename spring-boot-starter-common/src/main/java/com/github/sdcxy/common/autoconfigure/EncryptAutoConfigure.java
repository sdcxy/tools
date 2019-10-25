package com.github.sdcxy.common.autoconfigure;

import com.github.sdcxy.common.autoconfigure.properties.EncryptProperties;
import com.github.sdcxy.common.encrypt.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName EncryptAutoConfigure
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/24 16:22
 **/
@Configuration
@EnableConfigurationProperties(value = EncryptProperties.class)
public class EncryptAutoConfigure {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(value = AbstractEncrypt.class)
    MD5Encrypt md5Encrypt(){
        return new MD5Encrypt();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(value = AbstractEncrypt.class)
    Base64Encrypt base64Encrypt(){
        return new Base64Encrypt();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(value = AbstractEncrypt.class)
    AESEncrypt aesEncrypt(){
        return new AESEncrypt();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(value = AbstractEncrypt.class)
    RSAEncrypt rsaEncrypt(){
        return new RSAEncrypt();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(value = AbstractEncrypt.class)
    ShaEncrypt shaEncrypt(){ return new ShaEncrypt(); }

}

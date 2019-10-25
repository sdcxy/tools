package com.github.sdcxy.common.autoconfigure;

import com.github.sdcxy.common.autoconfigure.properties.EncryptProperties;
import com.github.sdcxy.common.autoconfigure.properties.HttpProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;



/**
 * @ClassName ConfigurationAutoConfigure
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/23 10:17
 **/
@Component
public class ConfigurationAutoConfigure {

    @Autowired
    private HttpProperties http;

    @Autowired
    private EncryptProperties encrypt;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(value = {HttpProperties.class})
    com.github.sdcxy.common.config.Configuration getConfiguration(){
        return com.github.sdcxy.common.config.Configuration.builder().http(http).encrypt(encrypt).build();
    }

}

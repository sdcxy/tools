package com.github.sdcxy.common.config;


import com.github.sdcxy.common.autoconfigure.properties.EncryptProperties;
import com.github.sdcxy.common.autoconfigure.properties.HttpProperties;
import lombok.Builder;
import lombok.Data;

/**
 * @ClassName Configuration
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/23 10:05
 **/
@Data
@Builder
public class Configuration {
    private HttpProperties http;
    private EncryptProperties encrypt;
}

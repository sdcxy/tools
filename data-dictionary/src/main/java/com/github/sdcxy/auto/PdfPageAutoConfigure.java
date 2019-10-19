package com.github.sdcxy.auto;

import com.github.sdcxy.config.PdfPageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName PdfPageAutoConfigure
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/15 11:03
 **/
@Configuration
@EnableConfigurationProperties(value = PdfPageProperties.class)
public class PdfPageAutoConfigure {

    @Autowired
    private PdfPageProperties pdfPageProperties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(value = PdfPageProperties.class)
    PdfPageConfig pdfPageConfig(){
        return new PdfPageConfig(pdfPageProperties);
    }

}

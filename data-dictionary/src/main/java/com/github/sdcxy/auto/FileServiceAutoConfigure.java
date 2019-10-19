package com.github.sdcxy.auto;

import com.github.sdcxy.service.AbstractFileServiceFactory;
import com.github.sdcxy.service.HtmlFileService;
import com.github.sdcxy.service.PdfFileService;
import com.github.sdcxy.service.WordFileService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FileServiceAutoConfigure
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/18 0:53
 **/
@Configuration
public class FileServiceAutoConfigure {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(AbstractFileServiceFactory.class)
    public WordFileService wordFileService(){
        return new WordFileService();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(AbstractFileServiceFactory.class)
    public PdfFileService pdfFileService(){
        return new PdfFileService();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(AbstractFileServiceFactory.class)
    public HtmlFileService htmlFileService(){
        return new HtmlFileService();
    }
}

package com.github.sdcxy.common.autoconfigure;

import com.github.sdcxy.common.date.AbstractDateService;
import com.github.sdcxy.common.date.DateServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName DateAutoConfigure
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/23 18:59
 **/
@Configuration
public class DateAutoConfigure {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(value = AbstractDateService.class)
    DateServiceImpl dateService(){
        return new DateServiceImpl();
    }
}

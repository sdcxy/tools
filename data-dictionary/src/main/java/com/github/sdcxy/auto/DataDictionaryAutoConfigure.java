package com.github.sdcxy.auto;


import com.github.sdcxy.constants.DataBaseConstants;
import com.github.sdcxy.constants.DataDictionaryConstants;
import com.github.sdcxy.dao.AbstractDataBaseConnectionFactory;
import com.github.sdcxy.dao.MySqlConnection;
import com.github.sdcxy.dao.SqlServerConnection;
import com.github.sdcxy.entity.DataDictionaryDataSource;
import com.github.sdcxy.service.DataDictionary;
import com.github.sdcxy.service.DefaultDataDictionaryService;
import com.github.sdcxy.service.MySqlDataDictionary;
import com.github.sdcxy.service.SqlServerDataDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @ClassName DataDictionaryAutoConfigure
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/12 9:42
 **/
@Configuration
@EnableConfigurationProperties(value = DataDictionaryProperties.class)
public class DataDictionaryAutoConfigure {

    @Autowired
    private DataDictionaryProperties dataDictionaryProperties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(value = DataDictionary.class)
    @ConditionalOnProperty(prefix = DataDictionaryConstants.PROJECT_PREFIX,value = DataBaseConstants.ENABLED,havingValue = "true")
    DataDictionaryDataSource dataDictionaryDataSource(){
        return new DataDictionaryDataSource(dataDictionaryProperties);
    }



    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(value = AbstractDataBaseConnectionFactory.class)
    MySqlConnection mySqlConnection(){
        return new MySqlConnection();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(value = MySqlConnection.class)
    MySqlDataDictionary mySqlDataDictionary(){
        return new MySqlDataDictionary();
    }


    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(value = AbstractDataBaseConnectionFactory.class)
    SqlServerConnection sqlServerConnection(){
        return new SqlServerConnection();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(value = SqlServerConnection.class)
    SqlServerDataDictionary sqlServerDataDictionary(){
        return new SqlServerDataDictionary();
    }

    @Bean
    @ConditionalOnMissingBean
    DefaultDataDictionaryService defaultDataDictionaryService(){
        return new DefaultDataDictionaryService();
    }
}

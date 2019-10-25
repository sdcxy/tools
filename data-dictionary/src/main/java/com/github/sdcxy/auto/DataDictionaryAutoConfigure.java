package com.github.sdcxy.auto;


import com.github.sdcxy.constants.DataBaseConstants;
import com.github.sdcxy.constants.DataDictionaryConstants;
import com.github.sdcxy.dao.*;
import com.github.sdcxy.entity.DataDictionaryDataSource;
import com.github.sdcxy.service.sql.*;
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
    @ConditionalOnClass(value = AbstractDataBaseConnectionFactory.class)
    PostgreSqlConnection postgreSqlConnection(){
        return new PostgreSqlConnection();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(value = PostgreSqlConnection.class)
    PostgreSqlDataDictionary postgreSqlDataDictionary(){
        return new PostgreSqlDataDictionary();
    }


    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(value = AbstractDataBaseConnectionFactory.class)
    OracleConnection oracleConnection(){
        return new OracleConnection();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(value = PostgreSqlConnection.class)
    OracleDataDictionary oracleDataDictionary(){
        return new OracleDataDictionary();
    }


    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(value = AbstractDataBaseConnectionFactory.class)
    DB2Connection db2Connection(){
        return new DB2Connection();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(value = PostgreSqlConnection.class)
    DB2DataDictionary db2DataDictionary(){
        return new DB2DataDictionary();
    }


    @Bean
    @ConditionalOnMissingBean
    DefaultDataDictionaryService defaultDataDictionaryService(){
        return new DefaultDataDictionaryService();
    }
}

package com.github.sdcxy.common.autoconfigure;

import com.github.sdcxy.common.autoconfigure.properties.HttpProperties;
import com.github.sdcxy.common.http.AbstractHttpService;
import com.github.sdcxy.common.http.HttpServiceImpl;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName HttpAutoConfigure
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/23 9:40
 **/
@Configuration
@EnableConfigurationProperties(value = HttpProperties.class)
public class HttpAutoConfigure {


    @Autowired
    private HttpProperties http;

    /**
     * @Author lxx
     * @Description //TODO 实例化一个连接池管理，设置最大的连接数，并发连接数
     * @Date  2019/10/23 10:50
     * @Param []
     * @return org.apache.http.impl.conn.PoolingHttpClientConnectionManager
     **/
    @Bean(name = "httpClientConnectionManager")
    PoolingHttpClientConnectionManager getPoolingHttpClientConnectionManager(){
        PoolingHttpClientConnectionManager httpClientConnectionManager
                = new PoolingHttpClientConnectionManager();
        httpClientConnectionManager.setMaxTotal(http.getMaxTotal());
        httpClientConnectionManager.setDefaultMaxPerRoute(http.getDefaultMaxPerRoute());
        return httpClientConnectionManager;
    }

    /**
     * @Author lxx
     * @Description //TODO  实例化连接池，设置连接池管理器。 这里需要以参数形式注入上面实例化的连接池管理器
     * @Date  2019/10/23 10:52
     * @Param [httpClientConnectionManager]
     * @return org.apache.http.impl.client.HttpClientBuilder
     **/
    @Bean(name = "httpClientBuilder")
    HttpClientBuilder getHttpClientBuilder(@Qualifier("httpClientConnectionManager") PoolingHttpClientConnectionManager
                                                          httpClientConnectionManager){
        // HttpClientBuilder中的构造方法被protected修饰，所以这里不能直接使用new来实例化一个HttpClientBuilder，
        // 可以使用HttpClientBuilder提供的静态方法create()来获取HttpClientBuilder对象
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(httpClientConnectionManager);
        return httpClientBuilder;
    }

    /**
     * @Author lxx
     * @Description //TODO Builder是RequestConfig的一个内部
     *                   通过RequestConfig的custom方法来获取到一个Builder对象
     *                  设置builder的连接信息
     *                  这里还可以设置proxy，cookieSpec等属性。有需要的话可以在此设置
     * @Date  2019/10/23 10:56
     * @Param []
     * @return org.apache.http.client.config.RequestConfig.Builder
     **/
    @Bean(name = "builder")
    public RequestConfig.Builder getBuilder(){
        RequestConfig.Builder builder = RequestConfig.custom();
        return builder.
                setConnectTimeout(http.getConnectTimeout())
                .setConnectionRequestTimeout(http.getConnectionRequestTimeout())
                .setSocketTimeout(http.getSocketTimeout());
    }

    /**
     * @Author lxx
     * @Description //TODO 使用builder构建一个RequestConfig对象
     * @Date  2019/10/23 10:57
     * @Param [builder]
     * @return org.apache.http.client.config.RequestConfig
     **/
    @Bean
    public RequestConfig getRequestConfig(@Qualifier("builder")RequestConfig.Builder builder){
        return builder.build();
    }

    /**
     * @Author lxx
     * @Description //TODO 注入连接池，用于获取httpClient
     * @Date  2019/10/23 10:58
     * @Param [httpClientBuilder]
     * @return org.apache.http.impl.client.CloseableHttpClient
     **/
    @Bean
    public CloseableHttpClient getCloseableHttpClient(@Qualifier("httpClientBuilder")HttpClientBuilder httpClientBuilder){
        return httpClientBuilder.build();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(AbstractHttpService.class)
    HttpServiceImpl httpService(){
        return new HttpServiceImpl();
    }

}

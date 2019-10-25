package com.github.sdcxy.common.autoconfigure.properties;

import com.github.sdcxy.common.constants.CommonConstants;
import com.github.sdcxy.common.constants.HttpConstants;
import com.github.sdcxy.common.constants.SignConstants;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName HttpProperties
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/23 9:09
 **/
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = CommonConstants.COMMON + SignConstants.POINT + HttpConstants.HTTP)
public class HttpProperties {

    /**
     * 最大连接数
     * {default:100}
     */
    private Integer maxTotal = HttpConstants.DEFAULT_MAX_TOTAL;

    /**
     * 并发数
     * {default:20}
     */
    private Integer defaultMaxPerRoute = HttpConstants.DEFAULT_MAX_PER_ROUTE;

    /**
     * 创建连接的最长时间
     * {default:1000}
     */
    private Integer connectTimeout = HttpConstants.DEFAULT_CONNECT_TIMEOUT;

    /**
     * 从连接池获取到连接的最长时间
     * {default:500}
     */
    private Integer connectionRequestTimeout = HttpConstants.DEFAULT_CONNECTION_REQUEST_TIMEOUT;

    /**
     * 数据传输最长时间
     * {default:10000}
     */
    private Integer socketTimeout = HttpConstants.DEFAULT_SOCKET_TIMEOUT;

    /**
     * 提交请求前测试连接是否可用
     * {default:true}
     */
    private boolean staleConnectionCheckEnabled = HttpConstants.DEFAULT_STALE_CONNECTION_CHECK_ENABLED;

}

package com.github.sdcxy.common.constants;

/**
 * @ClassName HttpConstants
 * @Description TODO http 常量设定
 * @Author lxx
 * @Date 2019/10/23 9:24
 **/
public class HttpConstants {

    private HttpConstants(){}
    /**
     * http配置文件前缀
     */
    public static final String HTTP = "http";

    /**
     * 最大连接数
     */
    public static final Integer DEFAULT_MAX_TOTAL = 100;

    /**
     * 并发数
     */
    public static final Integer DEFAULT_MAX_PER_ROUTE = 20;

    /**
     * 创建连接的最长时间
     */
    public static final Integer DEFAULT_CONNECT_TIMEOUT = 1000;

    /**
     * 从连接池获取到连接的最长时间
     */
    public static final Integer DEFAULT_CONNECTION_REQUEST_TIMEOUT = 500;

    /**
     * 数据传输最长时间
     */
    public static final Integer DEFAULT_SOCKET_TIMEOUT = 10000;

    /**
     * 提交请求前测试连接是否可用
     */
    public static final boolean DEFAULT_STALE_CONNECTION_CHECK_ENABLED = true;

}

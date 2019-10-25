package com.github.sdcxy.common.http;

import com.github.sdcxy.common.enums.DoPostType;

import java.util.Map;

public interface HttpService {

    /**
     *  doGet方法
     * @param url url 请求地址
     * @param parameterMap map 参数
     * @param header 请求头
     * @return 返回String
     */
    String doGet(String url, Map<String,Object> parameterMap,Map<String,Object> header);

    /**
     *  doPost方法
     * @param type doPost类型 {@link DoPostType}
     * @param url url 地址
     * @param parameterMap map参数
     * @param header 请求头
     * @return 返回String
     */
    String doPost(DoPostType type,String url, Map<String,Object> parameterMap, Map<String,Object> header);

}

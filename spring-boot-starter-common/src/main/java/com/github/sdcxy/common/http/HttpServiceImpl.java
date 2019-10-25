package com.github.sdcxy.common.http;

import com.github.sdcxy.common.enums.DoPostType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.Map;

/**
 * @ClassName HttpServiceImpl
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/23 10:29
 **/
@Slf4j
public class HttpServiceImpl extends AbstractHttpService {

    @Override
    public String doGet(String url, Map<String, Object> parameterMap, Map<String, Object> header) {
        return DefaultDoGet(url, parameterMap, header);
    }

    @Override
    public String doPost(DoPostType type,String url, Map<String, Object> parameterMap, Map<String, Object> header) {
        return DefaultDoPost(type, url, parameterMap, header);
    }

}

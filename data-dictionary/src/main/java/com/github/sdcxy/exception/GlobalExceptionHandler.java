package com.github.sdcxy.exception;

import com.github.sdcxy.constants.DataDictionaryConstants;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName GlobalExceptionHandler
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/21 16:38
 **/
@ControllerAdvice
public class GlobalExceptionHandler implements ExceptionHandler{

    @Override
    public Object doExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        response.setCharacterEncoding(DataDictionaryConstants.ENCODING_CHARACTER_UTF8);
        response.setContentType(DataDictionaryConstants.CONTENT_TYPE_JSON);
        if (e instanceof GlobalException) {
            GlobalException globalException = (GlobalException) e;
            Map<String,Object> map = new HashMap<>();
            map.put("errcode",globalException.getCode());
            map.put("errmsg",globalException.getMessage());
            return map;
        }
        return null;
    }
}

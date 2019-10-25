package com.github.sdcxy.common.http;

import com.github.sdcxy.common.enums.DoPostType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @ClassName AbstractHttpService
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/23 10:42
 **/
@Slf4j
public abstract class AbstractHttpService implements HttpService{

    @Autowired
    protected CloseableHttpClient closeableHttpClient;

    @Autowired
    protected RequestConfig requestConfig;

    /**
     *  释放资源
     * @param response
     */
    protected void close(CloseableHttpResponse response){
        try{
            if (response != null){
                response.close();
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    /**
     * @Author lxx
     * @Description //TODO 发送doGet请求
     * @Date  2019/10/23 15:08
     * @Param [url, parameterMap, header]
     * @return java.lang.String
     **/
    protected String DefaultDoGet(String url, Map<String, Object> parameterMap, Map<String, Object> header) {
        String resultStr = null;
        CloseableHttpResponse response = null;
        try{
            if (StringUtils.isNotEmpty(url)){
                // 声明GET请求
                HttpGet httpGet = new HttpGet(url);
                // 装在配置
                httpGet.setConfig(requestConfig);
                // 判断parameterMap参数
                if (parameterMap != null){
                    // 添加请求参数
                    URIBuilder uriBuilder = new URIBuilder(url);
                    for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
                        uriBuilder.setParameter(entry.getKey(),entry.getValue().toString());
                    }
                }
                // 判断header参数
                if (header != null){
                    for (Map.Entry<String, Object> entry : header.entrySet()) {
                        httpGet.setHeader(entry.getKey(),entry.getValue().toString());
                    }
                }
                // 发送请求
                response = closeableHttpClient.execute(httpGet);
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                    // 返回响应内容
                    resultStr =  EntityUtils.toString(response.getEntity(),"utf-8");
                }else{
                    throw new HttpException(String.valueOf(response.getStatusLine().getStatusCode()));
                }
            }else{
                throw new HttpException("Url不能为NULL");
            }
        }catch (Exception e){
            log.error("doGet---->{}",e.getMessage());
        }finally {
            // 释放资源
            close(response);
        }
        return resultStr;
    }


   /**
    * @Author lxx
    * @Description //TODO 表单模式: type = FORM json模式：type = JSON 文件模式：type = FILE
    * @Date  2019/10/23 15:44
    * @Param [type, url, parameterMap, header]
    * @return java.lang.String
    * {@link DoPostType}
    **/
    protected String DefaultDoPost(DoPostType type,String url, Map<String, Object> parameterMap, Map<String, Object> header){
        String resultStr = null;
        CloseableHttpResponse response = null;
        try{
            if (StringUtils.isNotEmpty(url)) {
                // 声明httpPost请求
                HttpPost httpPost = new HttpPost(url);
                // 加入配置
                httpPost.setConfig(requestConfig);
                // 判断header参数
                if (header != null){
                    for (Map.Entry<String, Object> entry : header.entrySet()) {
                        httpPost.setHeader(entry.getKey(),entry.getValue().toString());
                    }
                }
                // 判断parameterMap参数
                if (parameterMap != null ) {
                    // 判断form形式
                   if (type.equals(DoPostType.FORM)){
                      doPostByForm(httpPost,parameterMap);
                   }
                    // 判断json形式
                   if (type.equals(DoPostType.JSON)){
                       doPostByJson(httpPost,parameterMap);
                   }
                    // 判断文件形式
                    if (type.equals(DoPostType.FILE)){
                        doPostByFile(httpPost,parameterMap);
                    }
                }
                // 发送post请求
                response = closeableHttpClient.execute(httpPost);
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                    resultStr =  EntityUtils.toString(response.getEntity(),"utf-8");
                }else{
                    throw new HttpException(String.valueOf(response.getStatusLine().getStatusCode()));
                }
            }else{
                throw new HttpException("Url不能为NULL");
            }
        }catch (Exception e){
            log.error("doPost-->{}",e.getMessage());
        }finally {
            close(response);
        }
        return resultStr;
    }


    /**
     *  发送post表单请求配置
     * @param httpPost
     * @param parameterMap
     */
    private void doPostByForm(HttpPost httpPost,Map<String, Object> parameterMap){
        // 判断form形式
       try{
           List<NameValuePair> list = new ArrayList<>();
           for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
               list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
           }
           // 构造from表单对象,以表单的形式请求
           UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list,"utf-8");
           httpPost.setEntity(urlEncodedFormEntity);
       }catch (Exception e){
          log.error("doPostByForm --->{}",e.getMessage());
       }
    }

    /**
     * @Author lxx
     * @Description //TODO 发送postJSON数据请求配置
     * @Date  2019/10/23 15:58
     * @Param [httpPost, parameterMap]
     * @return void
     **/
    private void doPostByJson(HttpPost httpPost,Map<String, Object> parameterMap){
       try{
           for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
               StringEntity stringEntity = new StringEntity(entry.getValue().toString());
               httpPost.setEntity(stringEntity);
           }
       }catch (Exception e){
           log.error("doPostByJson --->{}",e.getMessage());
       }
    }

    /**
     * 发送post文件请求配置
     * @param httpPost
     * @param parameterMap
     */
    private void doPostByFile(HttpPost httpPost,Map<String, Object> parameterMap){
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        // 设置浏览器兼容模式
        multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
            multipartEntityBuilder.addBinaryBody(entry.getKey(),new File(entry.getValue().toString()));
        }
        HttpEntity httpEntity = multipartEntityBuilder.build();
        httpPost.setEntity(httpEntity);
    }
}

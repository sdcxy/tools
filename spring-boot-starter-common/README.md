##    spring-boot-starter-common 通用工具类

###  使用说明：

####    1、引入依赖
```
    <dependency>
        <groupId>com.github.sdcxy</groupId>
        <artifactId>spring-boot-starter-common</artifactId>
        <version>0.0.1</version>
    </dependency>
```

####    2、使用说明
1.  包含工具类：Http,Encrypt,Date
```
    @Autowired
    HttpServiceImpl httpService;
   
    @Autowired
    private DateServiceImpl dateService;
    
    @Autowired
    private MD5Encrypt md5Encrypt;

    @Autowired
    private Base64Encrypt base64Encrypt;

    @Autowired
    private RSAEncrypt rsaEncrypt;
```
2.  配置
```
    common:
      #http配置
      http:
        #连接超时
        connect-timeout: 1000
        #默认并发数
        default-max-per-route: 20
        #提交请求前测试
        stale-connection-check-enabled: true
        #最长传输时间
        socket-timeout: 10000
        #请求超时时间
        connection-request-timeout: 500
        #最大连接池数量
        max-total: 100
      #加密配置
      encrypt:
        #rsa加密密文长度
        rsa-key-size: 1024
        #aes加密密文长度
        aes-key-size: 128
        #是否创建rsa密钥文件
        create-rsa-key-file: true
```

####    3、版本说明
1.  0.0.1
    ```
        支持http,date,encrypt使用
        支持http参数配置   
    ```
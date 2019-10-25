# 工具类
### data-dictionary 数据字典
####  (支持数据库类型：mysql sqlserver)
1.  开始使用
    *   下载源码的方式：
        ```
            git clone https://github.com/sdcxy/tools.git
            运行之后，在浏览器输入：http://localhost:9999/
        ```
    *   引入Maven依赖的方式
    ```
        <dependency>
            <groupId>com.github.sdcxy</groupId>
            <artifactId>data-dictionary</artifactId>
            <version>0.0.1</version>
        </dependency>
        
        配置文件：application.yml
        #数据源配置
        data-dictionary:
          enabled: true（必填写）
          #PDF文件打印设置参数(可选填)
          pdf:
              author: 
              title:
              back-color:
              page-size:
              table-title-color:
              table-cell-color: 
    ```
2.  生成数据字典
    ![](https://raw.githubusercontent.com/sdcxy/cdn_repository/master/%E4%B8%AA%E4%BA%BA%E5%8D%9A%E5%AE%A2%E9%9D%99%E6%80%81%E8%B5%84%E6%BA%90/%E6%95%B0%E6%8D%AE%E5%AD%97%E5%85%B8/%E6%95%B0%E6%8D%AE%E5%AD%97%E5%85%B8%E9%A6%96%E9%A1%B5.png)
    ![](https://raw.githubusercontent.com/sdcxy/cdn_repository/master/%E4%B8%AA%E4%BA%BA%E5%8D%9A%E5%AE%A2%E9%9D%99%E6%80%81%E8%B5%84%E6%BA%90/%E6%95%B0%E6%8D%AE%E5%AD%97%E5%85%B8/%E7%94%9F%E6%88%90%E6%95%B0%E6%8D%AE%E5%AD%97%E5%85%B8.png)


### 版本说明
1.  0.0.1
    ```
        该版本只支持mysql,sqlserver生成数据字典,
        支持单表生成或全部表生成(多表生成后续会增加)
        支持打印/预览/下载pdf、生成word、预览html
        sqlserver现仅支持SQL2005以上版本(后续会增加更多的兼容性)
    ```
2.  0.0.2
    ```
        *   增加了sqlserver注释生成功能(上一版本只支持mysql)
        *   增加对PostgreSQL数据库支持
        *   增加对Oracle快捷版数据库支持
        *   显示页面增加对Oracle数据库实例选择或编辑功能
        *   后台增加数据库连接异常处理
        *   前端页面增加数据库连接操作判断
    ```
3.  0.0.3
    ```
        *   增加对DB2数据库支持（但是实际没有进行测试，慎用）
        *   修复日志依赖冲突
        *   修复Oracle数据库页面显示数据库名称错误的Bug
    ```
4.  0.0.4
    ```
        *   修复上一版本DB2连接数据库存在的缺陷
        *   修复界面创建单个表时，数据表个数提示错误
    ```
    
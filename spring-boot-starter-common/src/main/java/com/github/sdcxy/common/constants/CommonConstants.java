package com.github.sdcxy.common.constants;

/**
 * @ClassName CommonConstants
 * @Description TODO 全局常量设定
 * @Author lxx
 * @Date 2019/10/23 9:24
 **/
public class CommonConstants {

    private CommonConstants(){}
    /**
     * 配置文件工程名称前缀
     */
    public static final String COMMON = "common";

    /**
     * 工程目录
     */
    public static final String PROJECT_FILE_PATH = System.getProperty("user.dir").replace("\\","/");

    /**
     * java 目录
     */
    public static final String JAVA_ROOT_PATH = "/src/main/java";

    /**
     * 资源文件目录
     */
    public static final String RESOURCES_PATH = "/src/main/resources";

    /**
     *  加密文件目录
     */
    public static final String ENCRYPT_FILE = "/encrypt/";

    /**
     * 模板文件目录
     */
    public static final String TEMPLATES_PATH = "/templates/";

}

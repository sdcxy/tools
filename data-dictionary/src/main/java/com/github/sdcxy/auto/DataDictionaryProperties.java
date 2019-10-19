package com.github.sdcxy.auto;

import com.github.sdcxy.constants.DataDictionaryConstants;
import com.github.sdcxy.enums.DBDriver;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName DataDictionaryProperties
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/12 9:43
 **/
@Data
@ConfigurationProperties(prefix = DataDictionaryConstants.PROJECT_PREFIX)
public class DataDictionaryProperties {

    private DataDictionaryProperties(){}

    private boolean enabled = true;

    private String driver = "";

    private String url = "";

    private String dbType = "mysql";

    private String ip = "127.0.0.1";

    private Integer port = 3306;

    private String dataBase = "";

    private String username = "";

    private String password = "";

}

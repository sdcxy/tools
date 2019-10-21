package com.github.sdcxy.auto;

import com.github.sdcxy.constants.DataDictionaryConstants;
import com.github.sdcxy.constants.FileConstants;
import com.github.sdcxy.constants.SignConstants;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName PdfPageProperties
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/15 11:00
 **/
@Data
@ConfigurationProperties(prefix = DataDictionaryConstants.PROJECT_PREFIX + SignConstants.POINT + FileConstants.PDF)
public class PdfPageProperties {

    private String title = "DataBase-Dictionary";

    private String author = "sdcxy";

    private Rectangle pageSize = PageSize.A4;

    private int fontSize = 12;

    private BaseColor backColor = BaseColor.ORANGE;

    private BaseColor tableTitleColor = BaseColor.RED;

    private BaseColor tableColor = BaseColor.BLUE;

    private BaseColor tableCellColor = BaseColor.BLACK;
}

package com.github.sdcxy.config;

import com.github.sdcxy.auto.PdfPageProperties;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName PdfPageConfig
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/15 10:41
 **/
@Data
@Accessors(chain = true)
public class PdfPageConfig implements Serializable {

    /**
     * 标题
     */
    private String title = "DataBase-Dictionary";

    /**
     * 作者
     */
    private String author = "sdcxy";

    /**
     * 设置页面大小 默认A4
     */
    private Rectangle pageSize = PageSize.A4;

    /**
     * 文档字体大小 默认12
     */
    private int fontSize = 12;

    /**
     *  默认背景颜色
     */
    private BaseColor backColor = BaseColor.ORANGE;

    /**
     * 设置标题 颜色
     */
    private BaseColor tableTitleColor = BaseColor.RED;

    /**
     * 设置表格头颜色
     */
    private BaseColor tableColor = BaseColor.BLUE;

    /**
     * 设置表格内容颜色
     */
    private BaseColor tableCellColor = BaseColor.BLACK;

    public PdfPageConfig(){}

    public PdfPageConfig(PdfPageProperties pdfPageProperties){
        this.author = pdfPageProperties.getAuthor();
        this.title = pdfPageProperties.getTitle();
        this.pageSize = pdfPageProperties.getPageSize();
        this.backColor = pdfPageProperties.getBackColor();
        this.tableTitleColor =pdfPageProperties.getTableTitleColor();
        this.tableCellColor = pdfPageProperties.getTableCellColor();
        this.tableColor = pdfPageProperties.getTableColor();
        this.fontSize = pdfPageProperties.getFontSize();
    }
}

package com.github.sdcxy.service.file;

import com.github.sdcxy.config.PdfPageConfig;
import com.github.sdcxy.constants.MapConstants;
import com.github.sdcxy.constants.SignConstants;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PdfFileService
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/18 1:08
 * {@link AbstractFileServiceFactory}
 **/
public class PdfFileService extends AbstractFileServiceFactory {

    @Autowired
    private PdfPageConfig pdfPageConfig;

    private final String font = "./static/fonts/微软简仿宋.TTF";

    private final String[] TableHeaderTemplate = {"序列","列名","类型","主键","可空","注释"};

    private Document getDocument(){
        // 设置页面大小
        Rectangle rectangle = new Rectangle(pdfPageConfig.getPageSize());
        // 设置背景颜色
        rectangle.setBackgroundColor(pdfPageConfig.getBackColor());
        // 设置边框颜色
        rectangle.setBorderColor(BaseColor.DARK_GRAY);
        Document document = new Document(rectangle);
        //设置文档属性
        document.addAuthor(pdfPageConfig.getAuthor());
        document.addTitle(pdfPageConfig.getTitle());
        return document;
    }

    private Font getFont(BaseColor color) {
        try{
            BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            //设置字体类型 大小 颜色
            return new  Font(bfChinese,pdfPageConfig.getFontSize(),Font.BOLDITALIC, color);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private PdfPTable setPdfTable(){
        int size = TableHeaderTemplate.length;
        PdfPTable table = new PdfPTable(size);
        // 设置表格百分比
        table.setWidthPercentage(100);
        table.getDefaultCell().setBackgroundColor(BaseColor.GRAY);
        //设置表头据中
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        //设置表格下面空白行
        table.setSpacingBefore(20f);
        table.setSpacingAfter(40f);
        for (int i = 0; i < size; i++) {
            PdfPCell cell = new PdfPCell(new Paragraph(TableHeaderTemplate[i],getFont(pdfPageConfig.getTableColor())));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
        }
        return table;
    }

    private void setPdfCell(PdfPTable table, List<Map<String,Object>> columnList){
        Font font = getFont(pdfPageConfig.getTableCellColor());
        for (Map<String,Object> columnMap : columnList) {
            table.addCell(new Paragraph(String.valueOf(columnMap.get(MapConstants.COLUMN_SERIAL)),font));
            table.addCell(new Paragraph(String.valueOf(columnMap.get(MapConstants.COLUMN_NAME)),font));
            table.addCell(new Paragraph(String.valueOf(columnMap.get(MapConstants.COLUMN_TYPE)),font));
            table.addCell(new Paragraph(String.valueOf(columnMap.get(MapConstants.COLUMN_PRIMARY_KEY)),font));
            table.addCell(new Paragraph(String.valueOf(columnMap.get(MapConstants.COLUMN_NULLABLE)),font));
            table.addCell(new Paragraph(String.valueOf(columnMap.get(MapConstants.COLUMN_REMARK)),font));
        }
    }

    private void setTableInfo(Document document,Map<String,Object> map){
        try{
            // 循环打印内容
            List<Map<String,Object>> tableList = (List<Map<String,Object>>)map.get(MapConstants.TABLES);
            for (Map<String, Object> stringObjectMap : tableList) {
                String tableSerial = String.valueOf(stringObjectMap.get(MapConstants.TABLE_SERIAL));
                String tableName = String.valueOf(stringObjectMap.get(MapConstants.TABLE_NAME));
                String tableRemark = String.valueOf(stringObjectMap.get(MapConstants.TABLE_REMARK));
                // 设置表格标题
                if (StringUtils.isNotEmpty(tableRemark)) {
                    tableName = tableName + SignConstants.COLON + tableRemark;
                }
                // 设置title
                Paragraph paragraph = new Paragraph(tableName, getFont(pdfPageConfig.getTableTitleColor()));
                Chapter chapter = new Chapter(paragraph, Integer.valueOf(tableSerial));
                document.add(chapter);
                PdfPTable table = setPdfTable();
                List<Map<String, Object>> columnList = (List<Map<String, Object>>) stringObjectMap.get(MapConstants.COLUMN_LIST);
                setPdfCell(table, columnList);
                // 添加表格
                document.add(table);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public Template getTemplate() {
        Template template = null;
        try{
            template = configuration.getTemplate(pdfTemplateFile, ENCODING);
        }catch (Exception e){
            e.printStackTrace();
        }
        return template;
    }

    @Override
    public String saveFile(Map<String, Object> map) {
        return saveDefaultFile(map);
    }

    @Override
    public String saveFile(Map<String, Object> map, String saveFilePath) {
        Document document = getDocument();
        PdfWriter writer = null;
        try{
            writer = PdfWriter.getInstance(document, new FileOutputStream(saveFilePath));
            document.open();
            setTableInfo(document,map);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            document.close();
            if (writer != null){
                writer.close();
            }
        }
        return null;
    }


    public String downloadPdf(Map<String, Object> map,HttpServletResponse response){
        Document document = new Document();
        try {
            response.setContentType("application/pdf");
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            setTableInfo(document,map);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            document.close();
        }
        return null;
    }

}

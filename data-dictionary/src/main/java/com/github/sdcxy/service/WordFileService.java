package com.github.sdcxy.service;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.rtf.RtfWriter2;
import freemarker.template.Template;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * @ClassName WordFileService
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/18 0:32
 **/
public class WordFileService extends AbstractFileServiceFactory {

    @Override
    public Template getTemplate() {
        Template template = null;
        try{
            template = configuration.getTemplate(wordTemplateFile, ENCODING);
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
        String content = saveDefaultFile(map);
        OutputStreamWriter outputStreamWriter = null;
        try{
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(new File(saveFilePath)));
            outputStreamWriter.write(content);
            outputStreamWriter.flush();
            return content;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeOut(null,outputStreamWriter);
        }
        return null;

    }

    public String saveDefaultWordFile(Map<String, Object> map){
        return saveFile(map,saveWordFile);
    }


    public String downloadWord(Map<String, Object> map,HttpServletResponse response){
        String content = saveDefaultFile(map);
        com.lowagie.text.Document document = new Document(PageSize.A4);
        try {
            response.setContentType("application/msword");
            RtfWriter2.getInstance(document,response.getOutputStream());
            document.open();
            document.add(new Paragraph(content));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            document.close();
        }
        return null;
    }

}

package com.github.sdcxy.service.file;

import freemarker.template.Template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

/**
 * @ClassName HtmlFileService
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/19 12:21
 * {@link AbstractFileServiceFactory}
 **/
public class HtmlFileService extends AbstractFileServiceFactory {

    @Override
    public Template getTemplate() {
        Template template = null;
        try{
            template = configuration.getTemplate(htmlTemplateFile, ENCODING);
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
        OutputStreamWriter outputStreamWriter = null;
        String content = saveDefaultFile(map);
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(new File(saveFilePath));
            outputStreamWriter = new OutputStreamWriter(fileOutputStream);
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

    public String saveDefaultHtmlFile(Map<String, Object> map){
        return saveFile(map,saveHtmlFile);
    }
}

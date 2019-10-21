package com.github.sdcxy.service.file;

import com.github.sdcxy.constants.FileConstants;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.Map;

/**
 * @ClassName AbstractFileServiceFactory
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/18 0:26
 * {@link PdfFileService}
 * {@link WordFileService}
 * {@link HtmlFileService}
 **/
public abstract class AbstractFileServiceFactory implements FileServiceFactory{

    private String DefaultFilePath = FileConstants.PROJECT_PATH + FileConstants.RESOURCES_PATH + FileConstants.TEMPLATES_PATH;

    protected String wordTemplateFile = FileConstants.TEMPLATE_WORD_FILE;

    protected String pdfTemplateFile = FileConstants.TEMPLATE_PDF_FILE;

    protected String htmlTemplateFile = FileConstants.TEMPLATE_HTML_FILE;

    protected String saveWordFile = DefaultFilePath + FileConstants.WORD_TEMPLATE_FILE;

    protected String savePdfFile = DefaultFilePath + FileConstants.PDF_TEMPLATE_FILE;

    protected String saveHtmlFile = DefaultFilePath + FileConstants.HTML_TEMPLATE_FILE;

    protected static Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

    protected static String ENCODING = "UTF-8";

    static {
        // setEncoding这个方法一定要设置国家及其编码，不然在ftl中的中文在生成html后会变成乱码
        configuration.setDefaultEncoding(ENCODING);
        //设置模板所在文件夹
        configuration.setClassForTemplateLoading(AbstractFileServiceFactory.class,"/templates");
        // 设置对象的包装器
        configuration.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
        // 设置异常处理器,这样的话就可以${a.b.c.d}即使没有属性也不会出错
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
    }



    protected void closeOut(StringWriter stringWriter,OutputStreamWriter outputStreamWriter){
        try{
            if (stringWriter != null){
                stringWriter.close();
            }
            if (outputStreamWriter != null){
                outputStreamWriter.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void closeInput(FileInputStream inputStream){
        try{
            if (inputStream != null){
                inputStream.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 保存文件
     * @param map
     * @param
     */
    public String saveDefaultFile(Map<String, Object> map){
        StringWriter writer = new StringWriter();
        try{
            getTemplate().process(map,writer);
            writer.flush();
            return writer.toString();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeOut(writer,null);
        }
        return null;
    }

    /**
     * 读取文件 返回String
     * @param filePath
     * @return
     */
    public String readDefaultFile(String filePath){
        FileInputStream fos = null;
        byte[] bytes = new byte[1024];
        int i = 0;
        StringBuffer buffer = new StringBuffer();
        try{
            // 获取文件输出流
            fos = new FileInputStream(new File(filePath));
            while ((i = fos.read(bytes))!=-1){
                // 读取文件封装String
                buffer.append(new String(bytes,0,i,"UTF-8"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeInput(fos);
        }
        return buffer.toString();
    }
}

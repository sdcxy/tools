package com.github.sdcxy.service.file;

import freemarker.template.Template;

import java.util.Map;

/**
 * {@link AbstractFileServiceFactory}
 */
public interface FileServiceFactory {

    Template getTemplate();

    String saveFile(Map<String,Object> map);

    String saveFile(Map<String,Object> map,String saveFilePath);

}

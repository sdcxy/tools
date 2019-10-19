package com.github.sdcxy.service;

import freemarker.template.Template;

import java.util.Map;

public interface FileServiceFactory {

    Template getTemplate();

    String saveFile(Map<String,Object> map);

    String saveFile(Map<String,Object> map,String saveFilePath);

}

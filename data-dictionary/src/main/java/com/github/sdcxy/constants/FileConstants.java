package com.github.sdcxy.constants;

/**
 * @ClassName FileConstants
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/17 14:29
 **/
public class FileConstants {

    public static final String PROJECT_PATH = System.getProperty("user.dir").replace("\\","/");

    public static final String ROOT_PATH = "/src/main/java";
    public static final String RESOURCES_PATH = "/src/main/resources";
    public static final String TEMPLATES_PATH = "/templates/";


    public static final String PDF = "pdf";
    public static final String WORD = "word";
    public static final String HTML = "html";

    public static final String TEMPLATE_WORD_FILE = "word.ftl";
    public static final String TEMPLATE_PDF_FILE = "pdf.ftl";
    public static final String TEMPLATE_HTML_FILE = "html.ftl";

    public static final String WORD_TEMPLATE_FILE = "databaseDictionary.doc";
    public static final String PDF_TEMPLATE_FILE = "databaseDictionary.pdf";
    public static final String HTML_TEMPLATE_FILE = "databaseDictionary.html";
}

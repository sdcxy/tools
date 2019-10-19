package com.github.sdcxy.controller;

import com.github.sdcxy.entity.DataDictionaryDataSource;
import com.github.sdcxy.entity.Table;
import com.github.sdcxy.service.DefaultDataDictionaryService;
import com.github.sdcxy.service.HtmlFileService;
import com.github.sdcxy.service.PdfFileService;
import com.github.sdcxy.service.WordFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DataDictionaryController
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/17 0:03
 **/
@Controller
public class DataDictionaryController {


    @Autowired
    private DataDictionaryDataSource dataSource;

    @Autowired
    private DefaultDataDictionaryService dataDictionaryService;

    @Autowired
    private WordFileService wordFileService;

    @Autowired
    private PdfFileService pdfFileService;

    @Autowired
    private HtmlFileService htmlFileService;

    private List<Table> tableList;

    List<Map<String,Object>> mapList;

    List<String> tableNameList;


    @GetMapping("/dataDictionary/login")
    public String index(){
        return "index";
    }


    @PostMapping("/dataDictionary/login")
    @ResponseBody
    public Object index(@RequestBody DataDictionaryDataSource data){
        dataDictionaryService.copyProperties(data);
        tableList = dataDictionaryService.getTableList();
        return dataDictionaryService.getTableNames(tableList);
    }

    @GetMapping("/dataDictionary/getDataDictionary")
    public String dataDictionary(@PathParam("tableName") String tableName, ModelMap map){
        if (tableName.equals("")||tableName.equals("All")){
            tableList = dataDictionaryService.getTableList();
        }else {
            tableList = dataDictionaryService.getTableList(tableName);
        }
        mapList = dataDictionaryService.ListToMap(tableList);
        tableNameList = dataDictionaryService.getTableNames(tableList);
        map.addAttribute("tables",mapList);
        map.addAttribute("tableNames",tableNameList);
        map.addAttribute("dataBaseName",dataSource.getDataBase());
        // 写入文件到本地
//        wordFileService.saveFile(map);
        return "dataDictionary";
    }

    @GetMapping("/dataDictionary/download")
    @ResponseBody
    public Object download(String type, HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        map.put("tables",mapList);
        map.put("dataBaseName",dataSource.getDataBase());
        map.put("tableNames",tableNameList);
        String resultStr = null;
        // 传送文件信息
        if (type.equals("word")){
            resultStr = wordFileService.saveFile(map);
        }
        if (type.equals("pdf")){
            return pdfFileService.downloadPdf(map,response);
        }
        if (type.equals("html")){
            resultStr = htmlFileService.saveFile(map);
        }
        return resultStr;
    }
}

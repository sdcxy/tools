<!DOCTYPE html>
<html>
<head>
    <title>数据字典</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <link rel="stylesheet" href="/css/element.css"/>
    <link rel="stylesheet" href="/css/bootstrap.css"/>
    <link rel="stylesheet" href="/css/style.css"/>
    <link rel="stylesheet" href="/css/data-dictionary.css"/>
</head>
<body>
    <div id="dataDictionary" class="bg wrapper-flex wrapper-flex-justify-content-center">
        <el-card style="width: 80%" >
            <div slot="header" >
                <el-row>
                    <el-page-header style="font-weight: bolder;font-size: 24px"  @back="goBack" content="数据字典"></el-page-header>
                    <el-backtop></el-backtop>
                </el-row>
                <el-row  :gutter="20" class="wrapper-flex wrapper-flex-direction-row margin-top">
                    <el-col :span="6">
                        <#if dataBaseName?exists>
                            <span style="font-weight: bolder;font-size: 24px">数据库名称:</span><span style="font-size: 20px;margin-left: 10px">${dataBaseName}</span>
                        </#if>
                    </el-col>
                    <el-col :span="6">
                        <el-button type="success"  @click="DownLoadPDF">下载PDF</el-button>
                    </el-col>
                    <el-col :span="6">
                        <el-button type="success" @click="DownLoadWord">下载Word</el-button>
                    </el-col>
                    <el-col :span="6">
                        <el-button type="success" @click="DownLoadHtml">下载html</el-button>
                    </el-col>
                </el-row>
            </div>
            <el-divider content-position="left">导航栏</el-divider>
            <#if tableNames?exists>
                <#if (tableNames?size>0) >
                    <dl class="list-group">
                       <#list tableNames as tableName>
                           <#if tableName != 'All'>
                               <li class="list-group-item list-group-item-action"><a href="#${tableName}">${tableName}</a></li>
                           </#if>
                       </#list>
                    </dl>
                </#if>
            </#if>
            <el-divider content-position="left">数据结构</el-divider>
            <#if tables?exists>
                <#if (tables?size>0) >
                <#list tables as table >
                    <el-divider content-position="center">${table.TableName}</el-divider>
                    <span style="font-weight: bolder">${table.TableSerial}、</span> <span id="${table.TableName}" style="font-size: 20px">${table.TableName}  </span><span  style="font-size: 20px;margin-left: 10px">${table.TableRemark}</span>
                        <table class="table table-bordered table-hover" style="width: 100%">
                            <thead>
                                <tr>
                                    <th>序号</th><th>列名</th><th>类型</th><th>主键</th><th>可空</th><th>注释</th>
                                </tr>
                            </thead>
                            <tbody>
                                <#list table.ColumnList as column >
                                   <tr>
                                       <td>${column.ColumnSerial}</td>
                                       <td>${column.ColumnName}</td>
                                       <td>${column.ColumnType}</td>
                                       <td>${column.ColumnPrimaryKey}</td>
                                       <td>${column.ColumnNullable}</td>
                                       <td>${column.ColumnRemark}</td>
                                   </tr>
                                </#list>
                            </tbody>
                        </table>
                </#list>
            </#if>
            </#if>
        </el-card>
        <a href="/dataDictionary/download?type=word" download="数据字典.doc" id="downloadWord" target="_black" style="display: none"></a>
        <a href="/dataDictionary/download?type=pdf"  id="downloadPdf" target="_black" style="display: none"></a>
        <a href="/dataDictionary/download?type=html" id="downloadHtml" target="_black" style="display: none"></a>
    </div>
</body>
<!-- 先引入 Vue -->
<script type="text/javascript" src="/js/vue.js"></script>
<!-- 引入组件库 -->
<script type="text/javascript" src="/js/element.js"></script>
<!-- 引入axios -->
<script type="text/javascript" src="/js/axios.js"></script>
<script>
    var app = new Vue({
        el: '#dataDictionary',
        methods:{
            goBack: function () {
                window.location.href ="/"
            },
            DownLoadPDF: function () {
                document.getElementById("downloadPdf").click();
            },
            DownLoadWord: function () {
                document.getElementById("downloadWord").click();
            },
            DownLoadHtml: function () {
                document.getElementById("downloadHtml").click();
            }
        }
    })
</script>
</html>
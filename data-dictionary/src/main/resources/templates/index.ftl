<!DOCTYPE html>
<html>
<head>
    <title>数据字典</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <link rel="icon" href="favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="/css/element.css">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/index.css">
</head>
<body>
    <div id="app">
        <el-row class="wrapper-flex wrapper-flex-justify-content-center wrapper-flex-align-items-center wrapper-width wrapper-height">
           <el-form v-model="dBForm" style="width: 400px" >
               <el-form-item class="wrapper-flex wrapper-flex-align-items-center wrapper-flex-justify-content-center">
<#--                   <img src="images/title.jpg" alt="" style="width: 50px; height: 50px;border-radius: 25px">-->
                   <span class="data-dictionary"><i class="el-icon-coin" style="margin-right: 2px;"></i>数据字典</span>
               </el-form-item>
               <el-form-item >
                   <el-select class="wrapper-width" placeholder="请选择数据库类型" v-model="dBForm.dBType" @change="ValueChange" :disabled="dBForm.dBTypeEnabled" >
                       <el-option
                               v-for="item in dBForm.dBTypes.options"
                               :key="item.index"
                               :label="item.value"
                               :value="item.value">
                       </el-option>
                   </el-select>
               </el-form-item>
               <el-form-item prop="ip"  >
                   <el-input clearable placeholder="请输入IP地址" v-model="dBForm.ip" :disabled="dBForm.IpEnabled" ></el-input>
               </el-form-item>
               <el-form-item prop="port"  >
                   <el-input clearable placeholder="请输入端口号" v-model="dBForm.port" :disabled="dBForm.PortEnabled"></el-input>
               </el-form-item>
               <el-form-item prop="dataBaseName"  >
                   <el-input v-if="dBForm.dBType!='Oracle'" clearable placeholder="请输入数据库名称" v-model="dBForm.dataBaseName"  :disabled="dBForm.DataBaseNameEnabled"></el-input>
                   <el-select v-else allow-create filterable  default-first-option class="wrapper-width" placeholder="请选择或输入Oracle数据库实例(ServiceName)" v-model="dBForm.OracleServiceName" @change="OracleServiceNameChange"  :disabled="dBForm.OracleServiceNameEnabled" >
                       <el-option
                               v-for="item in dBForm.OracleServiceNameType.options"
                               :key="item.index"
                               :label="item.value"
                               :value="item.value">
                       </el-option>
                   </el-select>
               </el-form-item>
               <el-form-item prop="userName"  >
                   <el-input clearable placeholder="请输入用户名" v-model="dBForm.userName" :disabled="dBForm.UserNameEnabled"></el-input>
               </el-form-item>
               <el-form-item prop="password"  >
                   <el-input show-password type="password" placeholder="请输入密码" v-model="dBForm.password" :disabled="dBForm.PassWordEnabled" ></el-input>
               </el-form-item>
               <el-form-item v-show="isConnection" class="wrapper-flex wrapper-flex-justify-content-center wrapper-flex-align-items-center">
                   <el-button type="success" @click="connectDataBaseHandler()" >连接数据库</el-button>
               </el-form-item>
               <el-form-item v-show="!isConnection">
                   <el-select placeholder="请选择数据库表" v-model="dBForm.tableName" filterable style="width: 100%">
                       <el-option
                               v-for="item in dBForm.tableNameList"
                               :key="item"
                               :label="item"
                               :value="item">
                       </el-option>
                   </el-select>
               </el-form-item>
               <el-form-item v-show="!isConnection" class="wrapper-flex wrapper-flex-align-items-center wrapper-flex-justify-content-space-around">
                   <el-button type="primary" @click="createDataDictionaryHandler()">生成数据字典</el-button>
                   <el-button type="warning" @click="reConnectionHandler()">重连数据库</el-button>
               </el-form-item>
               <el-col>
                   <el-divider content-position="center">
                       <a href="https://github.com/sdcxy/tools">
                           <i class="el-icon-s-help">GitHub</i>
                       </a>
                   </el-divider>
               </el-col>
           </el-form>
        </el-row>
    </div>
</body>
<!-- 先引入 Vue -->
<script type="text/javascript" src="/js/vue.js"></script>
<!-- 引入组件库 -->
<script type="text/javascript" src="/js/element.js"></script>
<!-- 引入axios -->
<script type="text/javascript" src="/js/axios.js"></script>
<!-- 引入vue-router -->
<#--<script type="text/javascript" src="/js/vue-router.js"></script>-->
<script>
    var app = new Vue({
        el: '#app',
        data: {
            isConnection: true,
            dBForm: {
                dBTypeEnabled: false,
                IpEnabled: false,
                PortEnabled: false,
                DataBaseNameEnabled: false,
                OracleServiceNameEnabled: false,
                UserNameEnabled: false,
                PassWordEnabled: false,
                dBType: 'MySql',
                dBTypes: {
                    options: [
                        { index: 1, value: 'MySql' },
                        { index: 2, value: 'SqlServer' },
                        { index: 3, value: 'Oracle' },
                        { index: 4, value: 'DB2' },
                        { index: 5, value: 'PostgreSQL' }
                    ]
                },
                OracleServiceName: 'XE',
                OracleServiceNameType:{
                    options: [
                        { index: 1, value: 'XE' },
                        { index: 2, value: 'ORACLD' }
                    ]
                },
                ip: '127.0.0.1',
                port: '3306',
                dataBaseName: 'test',
                userName: 'root',
                password: 'root',
                tableName: '',
                tableNameList: []
            }
        },
        methods: {
            connectDataBaseHandler: function() {
                //验证数据
                if (this.dBForm.ip === ''|| this.dBForm.port === '' || this.dBForm.dataBaseName === ''|| this.dBForm.userName === ''|| this.dBForm.password === '') {
                    this.$message.error('请输入正确的内容');
                }else{
                    //获取连接数据库数据
                    var data;
                    if (this.dBForm.dBType === 'Oracle'){
                        data = { dbType: this.dBForm.dBType,ip: this.dBForm.ip, port: this.dBForm.port,dataBase: this.dBForm.OracleServiceName, username: this.dBForm.userName, password: this.dBForm.password };
                    } else {
                        data = { dbType: this.dBForm.dBType,ip: this.dBForm.ip, port: this.dBForm.port,dataBase: this.dBForm.dataBaseName, username: this.dBForm.userName, password: this.dBForm.password };
                    }
                    // 发送请求
                    axios.post('/dataDictionary/login',data).then(
                        function (response) {
                            if (response.status === 200){
                                //返回成功数据
                                var errcode = response.data.errcode;
                                if (!errcode) {
                                    app.dBForm.tableNameList = response.data;
                                    app.dBForm.tableName = '';
                                    app.isConnection = false
                                }else{
                                    var errmsg = response.data.errmsg;
                                    app.$message.error(errmsg);
                                }

                            }else{
                                app.$message.error('连接数据库失败');
                            }
                        },
                        function (error) {
                            //失败
                            console.log(error);
                            app.$message.error('连接数据库失败');
                        }
                    );
                }

            },
            createDataDictionaryHandler: function() {
                //定义加载页面
                var loading = this.$loading({
                    lock: true,
                    text: '正在拼命创建中....',
                    spinner: 'el-icon-loading',
                    background: 'rgba(0, 0, 0, 0.7)'
                });
                //获取要生成的数据库表名
                if (this.dBForm.tableName === ''){
                    this.dBForm.tableName = 'All'
                }
                var len = app.dBForm.tableNameList.length;
                if (len > 80){
                    app.$message.success("正在创建"+len+"个表数据,查询和创建时间会比较长,请稍等!...");
                    setTimeout(function () {
                        loading.close();
                    },60000);
                }else{
                    app.$message.success("正在创建"+len+"个表数据,请稍等!....");
                    setTimeout(function () {
                        loading.close();
                    },30000);
                }
                window.location.href = "/dataDictionary/getDataDictionary?tableName=" + this.dBForm.tableName;

            },
            reConnectionHandler: function() {
                //重新连接
                this.isConnection = true
            },
            ValueChange: function () {
                if (this.dBForm.dBType === "SqlServer"){
                    this.dBForm.port = 1433;
                    this.dBForm.userName = "sa";
                    this.dBForm.password = 123;
                    this.dBForm.dataBaseName = "kk2006";
                }
                if (this.dBForm.dBType === "MySql") {
                    this.dBForm.port = 3306;
                    this.dBForm.userName = "root";
                    this.dBForm.password = "root";
                    this.dBForm.dataBaseName = "test";
                }
                if (this.dBForm.dBType === "PostgreSQL") {
                    this.dBForm.port = 5432;
                    this.dBForm.userName = "postgres";
                    this.dBForm.password = "admin";
                    this.dBForm.dataBaseName = "test";
                }
                if (this.dBForm.dBType === "Oracle") {
                    this.dBForm.port = 1521;
                    this.dBForm.userName = "test";
                    this.dBForm.password = "123";
                    this.dBForm.dataBaseName = this.dBForm.OracleServiceName;
                    console.log(this.dBForm.dataBaseName)
                }
                if (this.dBForm.dBType === "DB2") {
                    this.dBForm.port = 0;
                    this.dBForm.userName = "";
                    this.dBForm.password = "";
                    this.dBForm.dataBaseName = "";
                }
            },
            OracleServiceNameChange: function () {
                console.log(this.dBForm.OracleServiceName)
            }
        },
        watch:{
            isConnection: function () {
                if (!this.isConnection){
                    this.dBForm.dBTypeEnabled = true;
                    this.dBForm.IpEnabled = true;
                    this.dBForm.PortEnabled = true;
                    this.dBForm.DataBaseNameEnabled = true;
                    this.dBForm.OracleServiceNameEnabled = true;
                    this.dBForm.UserNameEnabled = true;
                    this.dBForm.PassWordEnabled = true;
                }else {
                    this.dBForm.dBTypeEnabled = false;
                    this.dBForm.IpEnabled = false;
                    this.dBForm.PortEnabled = false;
                    this.dBForm.DataBaseNameEnabled = false;
                    this.dBForm.OracleServiceNameEnabled = false;
                    this.dBForm.UserNameEnabled = false;
                    this.dBForm.PassWordEnabled = false;
                }
            }
        }
    })
</script>
</html>
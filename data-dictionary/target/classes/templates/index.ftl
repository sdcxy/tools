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
           <el-form v-model="dBForm" style="width: 400px">
               <el-form-item class="wrapper-flex wrapper-flex-align-items-center wrapper-flex-justify-content-center">
<#--                   <img src="images/title.jpg" alt="" style="width: 50px; height: 50px;border-radius: 25px">-->
                   <span class="data-dictionary"><i class="el-icon-coin" style="margin-right: 2px;"></i>数据字典</span>
               </el-form-item>
               <el-form-item >
                   <el-select class="wrapper-width" placeholder="请选择数据库类型" v-model="dBForm.dBType" @change="ValeChange">
                       <el-option
                               v-for="item in dBForm.dBTypes.options"
                               :key="item.index"
                               :label="item.value"
                               :value="item.value">
                       </el-option>
                   </el-select>
               </el-form-item>
               <el-form-item prop="ip"  >
                   <el-input clearable placeholder="请输入IP地址" v-model="dBForm.ip" ></el-input>
               </el-form-item>
               <el-form-item prop="port"  >
                   <el-input clearable placeholder="请输入端口号" v-model="dBForm.port"></el-input>
               </el-form-item>
               <el-form-item prop="dataBaseName"  >
                   <el-input clearable placeholder="请输入数据库名称" v-model="dBForm.dataBaseName"></el-input>
               </el-form-item>
               <el-form-item prop="userName"  >
                   <el-input clearable placeholder="请输入用户名" v-model="dBForm.userName"></el-input>
               </el-form-item>
               <el-form-item prop="password"  >
                   <el-input show-password type="password" placeholder="请输入密码" v-model="dBForm.password"></el-input>
               </el-form-item>
               <el-form-item v-show="dBForm.isConnection" class="wrapper-flex wrapper-flex-justify-content-center wrapper-flex-align-items-center">
                   <el-button type="success" @click="connectDataBaseHandler()" >连接数据库</el-button>
               </el-form-item>
               <el-form-item v-show="!dBForm.isConnection">
                   <el-select placeholder="请选择数据库表" v-model="dBForm.tableName" filterable style="width: 100%">
                       <el-option
                               v-for="item in dBForm.tableNameList"
                               :key="item"
                               :label="item"
                               :value="item">
                       </el-option>
                   </el-select>
               </el-form-item>
               <el-form-item v-show="!dBForm.isConnection" class="wrapper-flex wrapper-flex-align-items-center wrapper-flex-justify-content-space-around">
                   <el-button type="primary" @click="createDataDictionaryHandler()">生成数据字典</el-button>
                   <el-button type="warning" @click="reConnectionHandler()">重连数据库</el-button>
               </el-form-item>
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
            dBForm: {
                dBType: 'MySql',
                dBTypes: {
                    options: [
                        { index: 1, value: 'MySql' },
                        { index: 2, value: 'SqlServer' },
                        { index: 3, value: 'Oracle' },
                        { index: 4, value: 'DB2' }
                    ]
                },
                ip: '127.0.0.1',
                port: '3306',
                dataBaseName: 'test',
                userName: 'root',
                password: 'root',
                isConnection: true,
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
                    var data = { dbType: this.dBForm.dBType,ip: this.dBForm.ip, port: this.dBForm.port,dataBase: this.dBForm.dataBaseName, username: this.dBForm.userName, password: this.dBForm.password };
                    // 发送请求
                    axios.post('/dataDictionary/login',data).then(
                        function (response) {
                            //返回成功数据
                            app.dBForm.tableNameList = response.data
                            app.dBForm.isConnection = false
                        },
                        function (error) {
                            //失败
                            console.log(error)
                            app.$message.error('连接数据库失败');
                        }
                    );
                }

            },
            createDataDictionaryHandler: function() {
                //获取要生成的数据库表名
                if (this.dBForm.tableName === ''){
                    this.dBForm.tableName = 'All'
                }
                window.location.href = "/dataDictionary/getDataDictionary?tableName=" + this.dBForm.tableName;
            },
            reConnectionHandler: function() {
                //重新连接
                this.dBForm.isConnection = true
            },
            ValeChange: function () {
                if (this.dBForm.dBType === "SqlServer"){
                    this.dBForm.port = 1433;
                    this.dBForm.userName = "sa";
                    this.dBForm.password = 123;
                    this.dBForm.dataBaseName = "kk2006"
                }
                if (this.dBForm.dBType === "MySql") {
                    this.dBForm.port = 3306;
                    this.dBForm.userName = "root";
                    this.dBForm.password = "root";
                    this.dBForm.dataBaseName = "test"
                }
            }
        }
    })
</script>
</html>
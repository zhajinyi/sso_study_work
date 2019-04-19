<%--
  Created by IntelliJ IDEA.
  User: ZhaJinyi
  Date: 2019-1-21
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/taglib.jsp"%>
<%@ include file="/include/head_include.jsp"%>
<html>
<head>
    <title>苏州工业园区服务外包学院-学工系统-首页</title>
</head>
<body>

<div class="layui-fluid" id="LAY-component-grid-mobile-pc">
    <div class="layui-row layui-col-space10">
        <div class="layui-col-xs4 layui-col-md2">
            <div class="layui-form-item">
                <form class="layui-form">
                    <label id="select_label" class="layui-form-label">年级</label>
                    <div class="layui-input-block">
                        <select name="grade" lay-filter="grade" id="grade">
                        </select>
                    </div>
                </form>
            </div>
            <div class="layui-card">
                <div class="layui-card-body" id="left_tree">
                    <ul id="tree"></ul>
                </div>
            </div>
        </div>
        <div class="layui-col-xs8 layui-col-md10">
            <!-- 填充内容 -->
            <div class="layui-card main-section">
                <div class="layui-card-body">
                    <table id="tableData" lay-filter="tableData"></table>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/html" id="tableToolbar">
    <div class="layui-row">
        <form class="layui-form" id="toolbarForm">
                <%-- 隐藏域，用于数据交互--%>
                <input type="hidden" name="classCode"  class="layui-input">
                <input type="hidden" name="className"  class="layui-input">
                <input type="hidden" name="orgId"  class="layui-input">
                <input type="hidden" name="orgName"  class="layui-input">
                <input type="hidden" name="gender"  class="layui-input">
                <input type="hidden" name="specialtyCode"  class="layui-input">
                <input type="hidden" name="entryYear"  class="layui-input">
                <input type="hidden" name="instructorCode"  class="layui-input">
                <input type="hidden" name="dromCode"  class="layui-input">
                <input type="hidden" name="cardType"  class="layui-input">
                <input type="hidden" name="cardNo"  class="layui-input">
                <input type="hidden" name="bankCard"  class="layui-input">
                <input type="hidden" name="shoolRoll"  class="layui-input">
                <input type="hidden" name="onShool"  class="layui-input">
                <input type="hidden" name="entryScore"  class="layui-input">
                <input type="hidden" name="remark"  class="layui-input">

                    <div class="layui-col-md4">
                <label class="layui-form-label" style="width: 80px">学号</label>
                <div class="layui-input-block" style="margin-left: 110px">
                    <input type="text" name="id" id="id" placeholder="" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-col-md4">
                <label class="layui-form-label" style="width: 80px">学生姓名</label>
                <div class="layui-input-block" style="margin-left: 110px">
                    <input type="text" name="empName" id="empName"  placeholder="" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-col-md2" style="text-align: right">
                <button class="layui-btn layui-btn-normal layui-btn-sm" lay-event="search">查询</button>
                <button class="layui-btn layui-btn-primary layui-btn-sm" type="reset">重置</button>
            </div>
        </form>
        <div class="layui-col-md2" style="text-align: right">
            <button class="layui-btn layui-btn-primary layui-btn-sm layui-btn-radius" lay-event="multiSearch">自定义查询</button>
        </div>
    </div>
    <div class="layui-row" style="padding: 10px 0px 0px 30px">
        <div class="layui-btn-group" style="text-align: left">
            <shiro:hasPermission name="2:add">
            <button class="layui-btn layui-btn-sm" lay-event="add">新增</button>
            </shiro:hasPermission>
            <shiro:hasPermission name="2:update">
            <button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="update">修改</button>
            </shiro:hasPermission>
            <shiro:hasPermission name="2:delete">
            <button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delete">删除</button>
            </shiro:hasPermission>
        </div>
    </div>

</script>
<script src="${ctxStatic}/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '${ctxStatic}/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index', //主入口模块
        commTreeTable:'commTreeTable'
    }).use(['index','commTreeTable', 'tree', 'table', 'layer','jquery','form'], function () {
        var $ = layui.$,
        table = layui.table,
        layer = layui.layer,
        form = layui.form,
        commTreeTable = layui.commTreeTable;
        /*定义tree、table相关的参数*/
        var commTreeUrl,commTreeElem,treeData,
            commTableUrl,commTableId,commTableElem,cols,
            tableToolbar,toolbarForm,grade,formUrl,delTableUrl;
        //指定页面所在元素的id，加载年级选项
        commTreeTable.getSelectGrade("grade");
        grade = commTreeTable.isEmpty($("#grade option:selected").val()) ? "" : $("#grade option:selected").val();
        treeData = {grade:grade};
        //初始化参数
        tableToolbar = "tableToolbar";
        toolbarForm = "toolbarForm";
        formUrl ="${ctx}/models/teacher/stu_manage/form.jsp";
        delTableUrl = "${ctx}/stu/deleteSelectedStudentInfo";
        //指定页面所在元素的id，加载tree的url
        commTreeElem='tree';
        commTreeUrl ='${ctx}/stu/commTree2';
        //指定页面所在元素的id，加载table的url
        commTableElem = 'tableData';
        commTableId='tableDataID';//设置table方法id用于reload
        commTableUrl = '${ctx}/stu/queryByParams';
        cols= [[
            { type: 'checkbox', fixed: 'left', width: 50},
            { type : 'numbers' , title : '编号' , width : 60 },
            { field : 'id' , width : 110 , title : '学号',align:'center' },
            { field : 'empName' , width : 100 , title : '姓名',align:'center' },
            { field : 'gender' , width : 60 , title : '性别',align:'center',templet:'#genderTool'},
            { field : 'orgName' , width : 120 , title : '学院',align:'center' },
            { field : 'className' , width : 120 , title : '班级',align:'center' },
            { field : 'specialtyName' , width : 170 , title : '专业',align:'center' },
            { field : 'dromCode' , width : 135 , title : '宿舍号',align:'center' },
            { field : 'bankCard' , width : 135 , title : '银行卡号',align:'center' },
            { field : 'entryScore' , width : 135 , title : '入学分数',align:'center' },
            { field : 'entryYear' , width : 135 , title : '入学年份',align:'center' },
            { field : 'onShool' , width : 135 , title : '是否在校',align:'center' },
            { field : 'shoolRoll' , width : 135 , title : '学籍',align:'center' },
            { field : 'cardType' , width : 135 , title : '证件类型',align:'center' },
            { field : 'cardNo' , width : 135 , title : '证件号码',align:'center' },
            { field : 'remark' , width : 135 , title : '备注',align:'center' }
        ]];
        Object.defineProperty(window.clickNode,'id', {
            get: function(){},//取值的时候会触发
            set: function(value){
                clickNodeOnChang(value);
            }
        });
        commTreeTable.getCommTree(commTreeElem,commTreeUrl,treeData,commTableId,toolbarForm,commTableUrl);
        commTreeTable.getCommTable(commTableElem,commTableUrl,commTableId,cols,tableToolbar);//getCommTable()的id为'tableDataID'
        commTreeTable.listenToolbar(table,commTableElem,commTableId,commTableUrl,toolbarForm,delTableUrl,formUrl);
        function clickNodeOnChang(val) {
            var nodeId = val;
            Object.defineProperty(window.clickNode,'level', {
                get: function(){},//取值的时候会触发
                set: function(value){
                    if('2' === value){
                        $("input[name='classCode']").val(nodeId);
                        $("input[name='className']").val("");
                        $("input[name='orgId']").val("");
                    }else if('0' === value){
                        $("input[name='orgId']").val("");
                        $("input[name='classCode']").val("");
                        $("input[name='className']").val("");
                        $("input[name='orgName']").val("");
                    }else{
                        $("input[name='classCode']").val("");
                        $("input[name='orgId']").val(nodeId);
                    }
                }
            });
        }


        form.render('select'); //刷新select选择框渲染
        //监听年级搜索选框
        form.on('select(grade)', function(obj){
            grade = obj.value;
            treeData = {grade:grade};
            $("input[name='grade']").val(grade);
            $("#left_tree").children(":first").empty();//清空tree
            commTreeTable.getCommTree(commTreeElem,commTreeUrl,treeData,commTableId,toolbarForm,commTableUrl);
            commTreeTable.Search(toolbarForm,commTableId,commTableUrl);
        })



    });
</script>
<script type="text/html" id="genderTool">
    {{# if(d.gender === 'm'){ d.gender = '男'}}
    男
    {{# } else{d.gender = '女'}}
    女
    {{# } }}
</script>
<script type="text/html" id="titleTpl">
    <a class="layui-btn layui-btn-xs" lay-event="edit" >编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: BaoLing
  Date: 2019-3-1
  Time: 15:12
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
            <input type="hidden" name="classcode"  class="layui-input">
            <input type="hidden" name="classname"  class="layui-input">
            <input type="hidden" name="orgid"  class="layui-input">
            <input type="hidden" name="orgname"  class="layui-input">
            <div class="layui-col-md4">
                <label class="layui-form-label" style="width: 70px;">学号</label>
                <div class="layui-input-block" style="margin-left: 100px;">
                    <input type="text" name="empid" placeholder="" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-col-md4">
                <label class="layui-form-label" style="width: 70px;">姓名</label>
                <div class="layui-input-block" style="margin-left: 100px;">
                    <input type="text" name="empname" placeholder="" autocomplete="off" class="layui-input">
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
            <button class="layui-btn layui-btn-sm" lay-event="add">新增</button>
            <button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="update">修改</button>
            <button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delete">删除</button>
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
        formUrl ="${ctx}/models/teacher/studentAward/highStudentCadres_form.jsp";
        delTableUrl = "${ctx}/highGood/deleteSelectedClasses";
        //指定页面所在元素的id，加载tree的url
        commTreeElem='tree';
        commTreeUrl ='${ctx}/stu/commTree2';
        //指定页面所在元素的id，加载table的url
        commTableElem = 'tableData';
        commTableId='tableDataID';//设置table方法id用于reload
        commTableUrl = '${ctx}/highGood/cadres';
        cols= [[
            { type: 'checkbox', fixed: 'left', width: 50},
            { type : 'numbers' , title : '编号' , width : 60 },
            { field: 'id',hide:true}
            ,{ field: 'empid', width: 100,title: '学生学号', sort: true, align: 'center' }
            , { field: 'empname',width: 100,title: '学生姓名', align: 'center' }
            , { field: 'orgname',width: 110, title: '所属学院', align: 'center',sort: true }
            , { field: 'classname',width: 110, title: '所属班级', align: 'center',sort: true }
            , { field: 'sex', width: 70,title: '性别',sort: true, align: 'center'}
            , { field: 'awardType',width: 100, title: '获奖类型', align: 'center' }
            , { field: 'awardTime',width: 100, title: '获奖时间', sort: true, align: 'center' }
            , { field: 'deteal',width: 100, title: '所在学校、年级及职务', align: 'center' }
            , { field: 'mainContent',width: 100, title: '主要事迹', align: 'center' }
            , { field: 'remark',width: 100, title: '备注', align: 'center' }
        ]];
        Object.defineProperty(window.clickNode,'name', {
            get: function(){},//取值的时候会触发
            set: function(value){
                clickNodeOnChang(value);
            }
        });
        commTreeTable.getCommTree(commTreeElem,commTreeUrl,treeData,commTableId,toolbarForm,commTableUrl);
        commTreeTable.getCommTable(commTableElem,commTableUrl,commTableId,cols,tableToolbar);//getCommTable()的id为'tableDataID'
        commTreeTable.listenToolbar(table,commTableElem,commTableId,commTableUrl,toolbarForm,delTableUrl,formUrl);
        function clickNodeOnChang(val) {
            var nodeName = val;
            Object.defineProperty(window.clickNode,'level', {
                get: function(){},//取值的时候会触发
                set: function(value){
                    if('2' === value){
                        $("input[name='className']").val(nodeName);
                        $("input[name='orgName']").val("");
                    }else if('0' === value){
                        $("input[name='orgName']").val("");
                        $("input[name='className']").val("");
                    }else{
                        $("input[name='className']").val("");
                        $("input[name='orgName']").val(nodeName);
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
<script type="text/html" id="gender">
    {{# if(gender === 'm'){ }}
    男
    {{# } else{}}
    女
    {{# } }}
</script>
<script type="text/html" id="titleTpl">
    <a class="layui-btn layui-btn-xs" lay-event="edit" >编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
</body>
</html>


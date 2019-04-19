<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/22
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/taglib.jsp"%>

<html>
<head>
    <title>苏州工业园区服务外包学院-学工系统-首页</title>
    <%@include file="/include/head_include.jsp"%>
</head>
<body>
<div class="layui-fluid" id="LAY-component-grid-mobile-pc">
    <div class="layui-row layui-col-space10">
        <div class="layui-col-xs4 layui-col-md2">

            <%--组织架构树形--%>
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
<!--表格头部自定义控件-->
<script type="text/html" id="tableToolbar">
    <div class="layui-row">
        <form class="layui-form" id="toolbarForm">
            <%--隐藏域--%>
            <input type="hidden" name="orgId" class="layui-input">
            <input type="hidden" name="orgName" class="layui-input">
            <input type="hidden" name="specialtyCode" class="layui-input">
            <input type="hidden" name="adviserCode" class="layui-input">
            <input type="hidden" name="monitorCode" class="layui-input">

            <div class="layui-col-md4">
                <label class="layui-form-label" style="width: 80px">班级编号</label>
                <div class="layui-input-block" style="margin-left: 110px">
                    <input type="text" name="id" placeholder="" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-col-md4">
                <label class="layui-form-label" style="width: 80px">班级名称</label>
                <div class="layui-input-block" style="margin-left: 110px">
                    <input type="text" name="className" id="className" placeholder="" autocomplete="off" class="layui-input">
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
<!-- 表格操作 -->
<script type="text/html" id="tableOperation">
    <a class="layui-table-link" href="javascript:;" lay-event="edit">编辑</a>
    <a class="layui-table-link btn-danger" href="javascript:;" lay-event="del">删除</a>
</script>
<script src="${ctxStatic}/layuiadmin/layui/layui.js"></script>
<script >
    layui.config({
        base: '${ctxStatic}/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
        ,commTreeTable:'commTreeTable'
    }).use(['index','commTreeTable', 'tree', 'table', 'layer','jquery','form'], function () {
//layui相关模块别名
        var $ = layui.$,
            layer = layui.layer,
            form = layui.form,
            table = layui.table,
            tree = layui.tree,
        commTreeTable = layui.commTreeTable;
            //$body = $('body');
/*定义tree、table相关的参数*/
        var commTreeUrl,commTreeElem,
            commTableUrl,commTableId,commTableElem,cols,
            tableToolbar,toolbarForm,grade,formUrl,delTableUrl;
        //指定页面所在元素的id，加载年级选项
        commTreeTable.getSelectGrade("grade");
        //初始化参数
        tableToolbar = "tableToolbar";
        toolbarForm = "toolbarForm";
        formUrl ="${ctx}/models/teacher/class_manage/form.jsp";
        delTableUrl = "${ctx}/class/deleteSelectedClasses";
        //指定页面所在元素的id，加载tree的url
        commTreeElem='tree';
        commTreeUrl ='${ctx}/class/commTree1';
        //指定页面所在元素的id，加载table的url
        commTableElem = 'tableData';
        commTableId='tableDataID';//设置table方法id用于reload
        commTableUrl = '${ctx}/class/queryByParams';
        cols = [[
            { type: 'checkbox', fixed: 'left', width: 50}
            ,{ field: 'id', width: 100, title: '班级编号', sort: true ,align:'center'}
            , { field: 'className', width: 110, title: '班级', sort: true,align:'center'}
            , { field: 'orgName', width: 150, title: '学院', sort: true ,align:'center'}
            , { field: 'grade', width: 80, title: '年级', sort: true ,align:'center'}
            , { field: 'specialtyName', width: 198, title: '专业', sort: true ,align:'center'}
            , { field: 'adviserName', width: 102, title: '班主任', sort: true ,align:'center'}
            , { field: 'monitorName', width: 100, title: '班长', sort: true ,align:'center'}
        ]];

        Object.defineProperty(window.clickNode,'id', {
            get: function(){},//取值的时候会触发
            set: function(value){
                clickNodeOnChang(value);
            }
        });
        commTreeTable.getCommTree(commTreeElem,commTreeUrl,"",commTableId,toolbarForm,commTableUrl);
        commTreeTable.getCommTable(commTableElem,commTableUrl,commTableId,cols,tableToolbar);//getCommTable()的id为'tableDataID'
        commTreeTable.listenToolbar(table,commTableElem,commTableId,commTableUrl,toolbarForm,delTableUrl,formUrl);
        function clickNodeOnChang(val) {
            var nodeId = val;
            Object.defineProperty(window.clickNode,'level', {
                get: function(){},//取值的时候会触发
                set: function(value){
                    if('2' === value){
                        $("input[name='classCode']").val(nodeId);
                        $("input[name='orgId']").val("");
//                        $("input[name='className']").val(nodeName);
//                        $("input[name='orgName']").val("");
                    }else if('0' === value){
                        $("input[name='orgId']").val("");
                        $("input[name='classCode']").val("");
                        $("input[name='className']").val("");
                        $("input[name='orgName']").val("");
                    }else{
                        $("input[name='classCode']").val("");
                        $("input[name='orgId']").val(nodeId);
//                        $("input[name='className']").val("");
//                        $("input[name='orgName']").val(nodeName);
                    }
                }
            });
        }

    });
</script>

</body>


</html>

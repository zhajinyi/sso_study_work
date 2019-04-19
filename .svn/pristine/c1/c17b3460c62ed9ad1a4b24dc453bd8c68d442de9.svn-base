<%--
  Created by IntelliJ IDEA.
  User: 王佳伟
  Date: 2019-2-1
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/taglib.jsp"%>
<html>
<head>
    <title>苏州工业园区服务外包学院-学工系统-首页</title>
    <%@ include file="/include/head_include.jsp"%>
</head>

<body>
<div class="layui-fluid" id="LAY-component-grid-mobile-pc">
    <div class="layui-row layui-col-space10">
        <div class="layui-col-xs4 layui-col-md2">
            <%--年级搜索表单--%>
            <div class="layui-form-item">
                <form class="layui-form" id="gradeForm">
                    <label class="layui-form-label" style="text-align: center">年级</label>
                    <div class="layui-input-block">
                        <select name="grade" lay-filter="grade" id="grade" >
                        </select>
                    </div>
                </form>
            </div>
            <%--组织架构树形--%>
            <div class="layui-card">
                <div class="layui-card-body">
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
                <input type="hidden" name="empid">
                <input type="hidden" name="empname">
                <input type="hidden" name="sex">
                <input type="hidden" name="awardType">
                <input type="hidden" name="awardTime">
                <input type="hidden" name="deteal">
                <input type="hidden" name="mainContent">
                <input type="hidden" name="remark">
                <input type="hidden" name="grade"  class="layui-input">
                <input type="hidden" name="orgid"  class="layui-input">
                <input type="hidden" name="classid"  class="layui-input">
                <div class="layui-col-md4">
                    <label class="layui-form-label" style="width: 70px;">学号</label>
                    <div class="layui-input-block" style="margin-left: 100px;">
                        <input type="text" name="empid" lay-verify="" placeholder="" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-col-md4">
                    <label class="layui-form-label" style="width: 70px;">姓名</label>
                    <div class="layui-input-block" style="margin-left: 100px;">
                        <input type="text" name="empname" lay-verify=""  placeholder="" autocomplete="off" class="layui-input">
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
            <div class="layui-btn-group" style="text-align: left;visibility: hidden;">
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
    }).use(['index', 'tree', 'table', 'layer', 'form', 'commTreeTable'], function () {
        var $ = layui.$,
            tree = layui.tree,
            table = layui.table,
            layer = layui.layer,
            form = layui.form,
            commTreeTable = layui.commTreeTable,
            treeUrl = "${ctx}/highGood/tree",
            treeElement = "tree",
            tableUrl = "${ctx}/highGood/cadres",
            delTableUrl = "",
            tableElement = "tableData",
            tableId = "tableDataID",
            tableToolbar = "tableToolbar",
            toolbarForm = "toolbarForm",
            formUrl = "${ctx}/models/teacher/studentAward/highStudentCadres_form.jsp";

        var cols = [[
            ,{ field: 'empid', width: 100,title: '学生学号', sort: true, align: 'center' }
            , { field: 'empname',width: 100,title: '学生姓名', align: 'center' }
            , { field: 'orgname',width: 110, title: '所属学院', align: 'center',sort: true }
            , { field: 'classname',width: 110, title: '所属班级', align: 'center',sort: true }
            , { field: 'sex', width: 70,title: '性别',sort: true, align: 'center',templet:'#genderTool' }
            , { field: 'awardType',width: 100, title: '获奖类型', align: 'center' }
            , { field: 'awardTime',width: 100, title: '获奖时间', sort: true, align: 'center' }
            , { field: 'deteal',width: 100, title: '所在学校、年级及职务', align: 'center' }
            , { field: 'mainContent',width: 100, title: '主要事迹', align: 'center' }
            , { field: 'remark',width: 100, title: '备注', align: 'center' }
            /*, { fixed: 'right', title: '操作', width: 100, align: 'center', toolbar: '#tableOperation' }*/ //这里的toolbar值是模板元素的选择器
        ]];
        var grade = commTreeTable.isEmpty($("#grade  option:selected").val()) ? "" : $("#grade  option:selected").val();
        $("input[name='grade']").val(grade);

        Object.defineProperty(window.clickNode,'id', {
            get: function(){},//取值的时候会触发
            set: function(value){
                clickNodeOnChang(value);
            }//更新值的时候会触发
        });

        commTreeTable.getSelectGrade("grade");
        commTreeTable.getCommTree(treeElement,treeUrl,grade,tableId,toolbarForm,tableUrl); //加载tree对象初始化方法；
        commTreeTable.getCommTable(tableElement,tableUrl,tableId,cols,tableToolbar);
        commTreeTable.listenToolbar(table,tableElement,tableId,tableUrl,toolbarForm,delTableUrl,formUrl);

        function clickNodeOnChang(val) {
            var nodeid = val;
            Object.defineProperty(window.clickNode, 'level', {
                get: function () {
                },//取值的时候会触发
                set: function (value) {
                    if (value == '2') {
                        $("input[name='classid']").val(nodeid);
                        $("input[name='orgid']").val('');
                    } else if (value == '1') {
                        $("input[name='orgid']").val(nodeid);
                        $("input[name='classid']").val('');
                    } else {
                        $("input[name='orgname']").val('');
                        $("input[name='classname']").val('');
                        $("input[name='orgid']").val('');
                        $("input[name='classid']").val('');
                    }
                }//更新值的时候会触发
            });
        }


        //监听年级搜索选框
        form.on('select(grade)', function(obj){
            $("input[name='orgname']").val('');
            $("input[name='classname']").val('');
            $("input[name='orgid']").val('');
            $("input[name='classid']").val('');
            grade = obj.value;
            $("input[name='grade']").val(grade);
            commTreeTable.Search(toolbarForm,tableId,tableUrl);
        })
    });

</script>
<script type="text/html" id="genderTool">
    {{# if(d.sex === 'm'){ d.gender = '男'}}
    男
    {{# } else{d.sex = '女'}}
    女
    {{# } }}
</script>
</body>

</html>
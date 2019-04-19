<%--
  Created by IntelliJ IDEA.
  User: 王佳伟
  Date: 2019-2-15
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/taglib.jsp"%>
<html lang="zh-cn">

<head>
    <meta charset="utf-8">
    <title>苏州工业园区服务外包学院-学工系统-奖学金管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${ctxStatic}/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${ctxStatic}/layuiadmin/style/admin.css" media="all">
    <link rel="stylesheet" href="${ctxStatic}/layuiadmin/style/main.css" media="all">
</head>

<body>
<div class="layui-fluid">
    <table id="tableData"></table>
</div>
<!--表格头部自定义控件-->
<script type="text/html" id="tableToolbar">
    <div class="layui-row">
        <div class="layui-col-md3">
            <label class="layui-form-label" style="width: 90px;">获奖时间&nbsp&nbsp</label>
            <div class="layui-input-block" style="margin-left: 90px;">
                <input type="text" name="endtime" lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="layui-btn-group">
                <button class="layui-btn layui-btn-normal layui-btn-sm search">查询</button>
            </div>
        </div>
    </div>
</script>
<!-- 表格操作 -->
<%--<script type="text/html" id="tableOperation">
    <a class="layui-table-link" href="javascript:;" lay-event="edit">编辑</a>
    <a class="layui-table-link btn-danger" href="javascript:;" lay-event="del">删除</a>
</script>--%>
<script src="${ctxStatic}/layuiadmin/layui/layui.js?t=1"></script>
<script>
    layui.config({
        base: '${ctxStatic}/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'table'], function () {
        var table = layui.table
            ,$ = layui.jquery;

        Table();
        $(document).on('click','.search',function(){
            var endtime = $("input[name='endtime']").val();
            qurryByParam(endtime);
        });
        function qurryByParam(endtime){
            var t = {};
            t.endtime = endtime;
            table.reload('awardTableData', {
                where: t
            });
            $("input[name='endtime']").val(endtime);
        }
        function Table() {
            table.render({
                elem: '#tableData'
                , url: '${ctx}/graduate/onestudent' //模拟接口
                //,method: 'post' //如果无需自定义HTTP类型，可不加该参数
                , toolbar: '#tableToolbar'
                ,id:'awardTableData'
                ,request: {
                    pageName: 'currentPage',
                    limitName: 'pageSize'
                }
                , parseData: function(result){ //result 即为原始返回的数据
                    return {
                        "code":result.code, //解析接口状态
                        "msg": result.msg, //解析提示文本
                        "count": result.extend.PageInfo.total, //解析数据长度
                        "data": result.extend.PageInfo.list //解析数据列表
                    };
                }
                ,response: {
                    statusCode: 200 //重新规定成功的状态码为 200，table 组件默认为 0
                }
                , page: {
                    layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
                    , 'prev': '上一页'
                    , 'next': '下一页'
                    , 'limits': [10, 20, 30, 40, 50]
                    , jump: function (obj) {
                        console.log(obj)
                    }
                }
                , loading: true
                , cellMinWidth: 120
                , defaultToolbar: ['filter']
                , cols: [[
                    { field: 'empid', title: '学生学号', align: 'center' }
                    , { field: 'empname',title: '学生姓名', align: 'center' }
                    , { field: 'gender',title: '学生性别', align: 'center', templet: '#lookgender'}
                    , { field: 'orgname', title: '所属学院', align: 'center' }
                    , { field: 'classname', title: '所属班级', align: 'center' }
                    , { field: 'referrer', title: '推荐人', align: 'center' }
                    , { field: 'phone', title: '联系电话', align: 'center' }
                    , { field: 'awardinfo', title: '获奖情况', align: 'center' }
                    , { field: 'endtime', title: '获奖时间', sort: true, align: 'center' }
                    , { field: 'remark', title: '备注', align: 'center' }
                    /*, { fixed: 'right', title: '操作', width: 100, align: 'center', toolbar: '#tableOperation' }*/ //这里的toolbar值是模板元素的选择器
                ]]
            })
        }

    });
</script>
<script type="text/html" id="lookgender">
    {{# if(d.gender=='2'){ }}
    女
    {{# } else{}}
    男
    {{# } }}
</script>
</body>

</html>
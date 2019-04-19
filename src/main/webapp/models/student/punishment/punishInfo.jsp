<%--
  Created by IntelliJ IDEA.
  User: baoling
  Date: 2019/1/25
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <title>苏州工业园区服务外包学院-学工系统-首页</title>
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
<script type="text/html" id="tableToolbar">
    <div class="layui-row">
        <div class="layui-col-md3">
            <label class="layui-form-label">处分级别&nbsp&nbsp</label>
            <div class="layui-input-block">
                <input type="text" name="level" placeholder=""
                       autocomplete="off" class="layui-input" >
            </div>
        </div>
        <%--<div class="layui-inline">--%>
            <%--<label class="layui-form-label">处分级别：</label>--%>
            <%--<div class="layui-input-inline">--%>
                <%--<select name="level" lay-verify="required" lay-search="">--%>
                    <%--<option value="">请选择</option>--%>
                    <%--<option value="警告">警告</option>--%>
                    <%--<option value="严重警告">严重警告</option>--%>
                    <%--<option value="记过">记过</option>--%>
                    <%--<option value="留校察看">留校察看</option>--%>
                <%--</select>--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="layui-col-md1">
            <!-- <div class="layui-btn-container"> -->
            <div class="layui-btn-group">
                <button class="layui-btn layui-btn-normal layui-btn-sm search">查询</button>
            </div>
            <!-- </div> -->
        </div>
    </div>
</script>
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
            var level = $("input[name='level']").val();
            qurryByParam(level);
        });
        function qurryByParam(level){
            var t = {};
            t.level = level;
            table.reload('punishTableData', {
                where: t
            });
        }
        function Table() {
            debugger;
            table.render({
                elem: '#tableData'
                , url: '${ctx}/stu/punishInfo' //模拟接口
                //,method: 'post' //如果无需自定义HTTP类型，可不加该参数
                , toolbar: '#tableToolbar'
                ,id:'punishTableData'
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
                , page: {
                    layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
                    , 'prev': '上一页'
                    , 'next': '下一页'
                    , 'limits': [10, 20, 30, 40, 50]
                    , jump: function (obj) {
                        console.log(obj)
                    }
                }
                ,response: {
                    statusCode: 200 //重新规定成功的状态码为 200，table 组件默认为 0
                }
                , loading: true
                , cellMinWidth: 120
                , defaultToolbar: ['filter']
                , cols: [[
                    { field: 'id', width: 100, title: 'ID', sort: true, align: 'center' }
                    , { field: 'punishTime', width: 180, title: '处分时间', align: 'center' }
                    , { field: 'level', width: 100, title: '处分级别', sort: true , align: 'center'}
                    , { field: 'reason', width: 428, title: '事由', align: 'center' }
                    , { field: 'result', width: 120, title: '是否解除', sort: true, align: 'center' }
                    , { field: 'relieveTime', width: 180, title: '解除时间', sort: true, align: 'center' }
                ]]
            })
        }

    });
</script>
</body>

</html>

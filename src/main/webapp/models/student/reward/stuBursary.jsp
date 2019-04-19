<%--
  Created by IntelliJ IDEA.
  User: baoling
  Date: 2019/1/25
  Time: 14:05
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
<script type="text/html" id="tableToolbar">
    <div class="layui-row">
        <div class="layui-col-md3">
            <label class="layui-form-label">所获奖项&nbsp&nbsp</label>
            <div class="layui-input-block">
                <%--<select name="professional" lay-filter="professional">--%>
                <%--<option value=""></option>--%>
                <%--<option value="0">写作</option>--%>
                <%--<option value="1" selected="">阅读</option>--%>
                <%--<option value="2">游戏</option>--%>
                <%--<option value="3">音乐</option>--%>
                <%--<option value="4">旅行</option>--%>
                <%--</select>--%>
                <input type="text" name="bursaryType" placeholder=""
                       autocomplete="off" class="layui-input" >
            </div>
        </div>
        <div class="layui-col-md3">
            <label class="layui-form-label">获奖时间&nbsp&nbsp</label>
            <div class="layui-input-block">
                <input type="text" name="bursaryTime" placeholder=""
                       autocomplete="off" class="layui-input" >
            </div>
        </div>
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
            var bursaryType = $("input[name='bursaryType']").val();
            var bursaryTime = $("input[name='bursaryTime']").val();
            qurryByParam(bursaryType,bursaryTime);
        });
        function qurryByParam(bursaryType,bursaryTime){
            var t = {};
            t.bursaryType = bursaryType;
            t.bursaryTime = bursaryTime;
            table.reload('awardTableData', {
                where: t
            });
        }
        function Table() {
            debugger;
            table.render({
                elem: '#tableData'
                , url: '${ctx}/bursary/onestudent' //模拟接口
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
                     { field: 'xueKeJiaQuanFen', title: '学科加权平均分', sort: true, align: 'center' }
                    , { field: 'zongHeSuYangFen', title: '综合素养成绩', sort: true, align: 'center' }
                    , { field: 'zongHeFen', title: '综合测评', sort: true, align: 'center' }
                    , { field: 'bursaryType',title: '奖学金类型', align: 'center',sort: true}
                    , { field: 'bursaryTime', title: '获奖时间', sort: true, align: 'center' }
                    , { field: 'remark', title: '备注', align: 'center' }
                ]]
            })
        }

    });
</script>
</body>

</html>

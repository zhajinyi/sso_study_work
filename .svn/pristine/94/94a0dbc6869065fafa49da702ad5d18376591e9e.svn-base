<%--
  Created by IntelliJ IDEA.
  User: BaoLing
  Date: 2019/2/14
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/taglib.jsp"%>
<%@ include file="/include/head_include.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <title>苏州工业园区服务外包学院-学工系统-省级先进班集体</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${ctxStatic}/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${ctxStatic}/layuiadmin/style/admin.css" media="all">
    <link rel="stylesheet" href="${ctxStatic}/layuiadmin/style/main.css" media="all">
</head>
<style>
    #selectBtn{
        float: right;
    }
</style>
<body>
<div class="layui-fluid">
    <table id="tableData"></table>
</div>
<script type="text/html" id="tableToolbar">
    <div class="layui-row">
        <div class="layui-col-md3">
            <label class="layui-form-label">申请人姓名&nbsp&nbsp</label>
            <div class="layui-input-block">
                <input type="text" name="empname" id="empname" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-col-md3">
            <label class="layui-form-label">申请人工号&nbsp&nbsp</label>
            <div class="layui-input-block">
                <input type="text" name="empid" id="empid" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-col-md3">
            <label class="layui-form-label">联系电话&nbsp&nbsp</label>
            <div class="layui-input-block">
                <input type="text" name="phone" id="phone" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-col-md3">
            <label class="layui-form-label">学院名称&nbsp&nbsp</label>
            <div class="layui-input-block">
                <input type="text" name="orgname" id="orgname" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-col-md3">
            <label class="layui-form-label">班级名称&nbsp&nbsp</label>
            <div class="layui-input-block">
                <input type="text" name="banjiName" id="banjiName" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-col-md3">
            <label class="layui-form-label">学校&nbsp&nbsp</label>
            <div class="layui-input-block">
                <input type="text" name="dept" id="dept" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-col-md3">
            <label class="layui-form-label">获奖时间&nbsp&nbsp</label>
            <div class="layui-input-block">
                <input type="text" name="advancedTime" id="advancedTime" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-col-md3">
            <label class="layui-form-label">主要事迹&nbsp&nbsp</label>
            <div class="layui-input-block">
                <input type="text" name="detail" id="detail" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-col-md3" id="selectBtn">
            <!-- <div class="layui-btn-container"> -->
            <div class="layui-btn-group" >
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
    }).use(['index', 'tree', 'table', 'layer','jquery','form'], function () {
        var $ = layui.$,
            table = layui.table,
            layer = layui.layer,
            form = layui.form;

        //定义变量
        var orgid,empid,empname,orgname,banjiName,dept,advancedTime,detail;
        var t = {};
        //加载table
        getOneAdvancedTable();

        //查询按钮点击事件
        $(document).on('click','.search',function(){
            search();
        });

        function getOneAdvancedTable() {
            table.render({
                elem: '#tableData'
                ,url:  '${ctx}/awards/oneAdvancedInfo'
                ,toolbar: '#tableToolbar'
                ,id:'oneAdvancedTable'
                ,contentType: 'application/json'
                ,method: 'post'
                ,request: {
                    pageName: 'currentPage',
                    limitName: 'pageSize'
                }
                ,parseData: function(result){ //result 即为原始返回的数据
                    return {
                        "code":result.code, //解析接口状态
                        "msg": result.msg, //解析提示文本
                        "count": result.extend.PageInfo.total, //解析数据长度
                        "data": result.extend.PageInfo.list //解析数据列表
                    };
                }
                , response: {
                    statusCode: 200 //重新规定成功的状态码为 200，table 组件默认为 0
                }
                , page: {
                    layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'],
                    'prev': '上页',
                    'next': '下页',
                    'limits': [10, 20, 30, 40, 50]
                }
                , loading: true
                , cellMinWidth: 120
                , defaultToolbar: ['filter']
                , cols: [[
                    { field: 'empid', title: '申请人工号', sort: true, align: 'center' }
                    , { field: 'empname',title: '申请人姓名', align: 'center' }
                    , { field: 'orgname', title: '所属学院', align: 'center',sort: true }
                    , { field: 'banjiName', title: '所属班级', align: 'center',sort: true }
                    , { field: 'graduate', title: '年级', align: 'center' }
                    , { field: 'dept', title: '学校名称', align: 'center' }
                    , { field: 'suozaixi', title: '所在系', align: 'center' }
                    , { field: 'advancedTime', title: '获奖时间', sort: true, align: 'center' }
                    , { field: 'detail', title: '主要事迹', align: 'center' }
//                    { fixed: 'right', title: '操作', width: 120 , align:'center', toolbar:'#titleTpl' }
                ]]
            })
        }
        function tableReload() {
            //获取页面上的数据，给t赋值
            empid = $("input[name='empid']").val();
            empname = $("input[name='empname']").val();
            orgname = isEmpty(orgname) ? $("input[name='orgname']").val() : orgname;
            banjiName = isEmpty(banjiName) ? $("input[name='banjiName']").val() : banjiName;
            dept = $("input[name='dept']").val();
            advancedTime = $("input[name='advancedTime']").val();
            detail = $("input[name='detail']").val();
            t = {
                orgid: orgid = isEmpty(orgname)|| isEmpty(orgid) || 0 == orgid ? '' : orgid
                , banjiName: banjiName = isEmpty(banjiName) ? '' : banjiName
                , orgname: orgname = isEmpty(orgname) ? '' : orgname
                , empid: empid = isEmpty(empid) ? '' : empid
                , empname: empname = isEmpty(empname) ? '' : empname
                , dept: dept = isEmpty(dept) ? '' : dept
                , advancedTime: advancedTime = isEmpty(advancedTime) ? '' : advancedTime
                , detail: detail = isEmpty(detail) ? '' : detail

            };
            //根据t更新table
            table.reload('oneAdvancedTable', { //table的id
                where: t
            });
            //搜索框数据继续显示
            for (var k in t){
                $("input[name='"+k+"']").val(t[k]);
            }
        }
        function isEmpty(p) {
            return null == p || '' == p || undefined == p;
        }

        function search(){
            tableReload();
        }

    });
</script>

</body>
</html>

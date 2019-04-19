<%--
  Created by IntelliJ IDEA.
  User: 王佳伟
  Date: 2019-2-19
  Time: 09:48
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
<div class="layui-fluid" id="LAY-component-grid-mobile-pc">
    <div class="layui-row layui-col-space10">
        <div class="layui-col-xs4 layui-col-md2">
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
        <div class="layui-col-md4">
            <label class="layui-form-label" style="width: 80px;">账号</label>
            <div class="layui-input-block" style="margin-left: 110px;">
                <input type="text" name="empid" lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-col-md4">
            <label class="layui-form-label" style="width: 80px;">姓名</label>
            <div class="layui-input-block" style="margin-left: 110px;">
                <input type="text" name="empname" lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
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
<script type="text/html" id="tableOperation">
    <a class="layui-table-link" href="javascript:;" lay-event="edit">编辑</a>
    <a class="layui-table-link btn-danger" href="javascript:;" lay-event="del">删除</a>
</script>
<script src="${ctxStatic}/layuiadmin/layui/layui.js?t=1"></script>
<script>
    layui.config({
        base: '${ctxStatic}/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'tree', 'table', 'layer'], function () {
        var $ = layui.$
            , tree = layui.tree
            , fun = layui.fun
            , layer = layui.layer
            , table = layui.table
            , $body = $('body');//body
        $.ajax({
            url: '${ctx}/sysemp/tree', //模拟接口
            type: 'get',
            dataType: 'json',
            data: {},
            success: TreeFun
        })
        function TreeFun(res) {
            tree({
                elem: '#tree'
                , target: '_blank' //是否新选项卡打开（比如节点返回href才有效）
                , nodes: [res]
                , click: function (node) {
                    console.log(node.id); //node即为当前点击的节点数据
                    table.reload('tableDataID', { //此处是上文提到的 初始化标识id
                        where: {
                            orgId: node.id,
                            empId:null,
                            realname:null
                        }
                    });

                }
            });
        }
        function Table(c) {
            table.render({
                elem: '#tableData'
                , url: '${ctx}/sysemp/emps'
                , toolbar: '#tableToolbar'
                ,id:'tableDataID'
                ,contentType: "application/json"
                ,method: "post"
                ,where: {
                    orgid: "0"
                }
                ,request: {
                    pageName: 'currentPage',
                    limitName: 'pageSize',
                },
                parseData: function(result){ //result 即为原始返回的数据
                    return {
                        "code":result.code, //解析接口状态
                        "msg": result.msg, //解析提示文本
                        "count": result.extend.PageInfo.total, //解析数据长度
                        "data": result.extend.PageInfo.list //解析数据列表
                    };
                },
                response: {
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
                    { field: 'empId', title: '账号', sort: true, align: 'center' }
                    , { field: 'nickname', title: '昵称', align: 'center' }
                    , { field: 'realname', title: '真实姓名', align: 'center' }
                    , { field: 'gender', title: '性别', sort: true, align: 'center',templet:'#sex' }
                    , { field: 'remark', title: '备注', align: 'center' }
                    , { fixed: 'right', title: '操作', width: 100, align: 'center', toolbar: '#tableOperation' } //这里的toolbar值是模板元素的选择器
                ]]
            });
        }

        $=layui.jquery;
        $(document).on('click','.search',function(){
            var empid = $("input[name='empid']").val();
            var empname = $("input[name='empname']").val();
            search(empid,empname);
        });
        function search(empid,empname){
            empid=(empid==""?null:empid);
            empname=(empname==""?null:empname);
            var SysEmpInfoDto = {empId:empid,realname:empname};
            table.reload('tableDataID', { //此处是上文提到的 初始化标识id
                where:SysEmpInfoDto
            });
            $("input[name='empid']").val(empid == null ? "" : empid);
            $("input[name='empname']").val(empname == null ? "" : empname);
        }

        Table(0);
        //监听行工具事件
        table.on('tool(tableData)', function (obj) {
            var data = obj.data;
            //console.log(obj)
            if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    obj.del();
                    layer.close(index);
                });
            } else if (obj.event === 'edit') {
                EditLayer("编辑信息", data);
            }
        });
        //监听选项卡的更多操作
        $body.on('click', '.btn-add', function (elem) {
            EditLayer();
        })
        function EditLayer(title = '编辑信息', data = '') {
            parent.layer.open({
                type: 2 //Page层类型
                , area: ['700px', '500px']
                , title: title
                , shade: 0.6 //遮罩透明度
                , maxmin: true //允许全屏最小化
                , anim: 1 //0-6的动画形式，-1不开启
                , content: '${ctxTeacher}/user/add_edit.jsp?id=' + data.id
                , end:function () {
                    Table(0);
                }
            });
        }
    });
</script>
<script type="text/html" id="sex">
    {{# if(d.gender=='f'){ }}
    女
    {{# } else{}}
    男
    {{# } }}
</script>
</body>

</html>

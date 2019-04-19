<%--
  Created by IntelliJ IDEA.
  User: 王佳伟
  Date: 2019-3-8
  Time: 09:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/taglib.jsp"%>
<head>
    <meta charset="utf-8">
    <title>苏州工业园区服务外包学院-学工系统-角色管理</title>
    <%@include file="/include/head_include.jsp"%>
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
        <div class="layui-col-xs8 layui-col-md10">
            <!-- 填充内容 -->
            <div class="layui-card main-section">
                <div class="layui-card-body">
                    <table id="tableData" lay-filter="tableData" lay-id="demo"></table>
                </div>
            </div>
        </div>
    </div>
</div>

<!--表格头部自定义控件-->
<script type="text/html" id="tableToolbar">
    <div class="layui-row">
        <form  class="layui-form" id="toolbarForm">
            <div class="layui-col-md4">
                <label class="layui-form-label" style="width: 80px">角色名称</label>
                <div class="layui-input-block" style="margin-left: 110px">
                    <input type="text" name="name" placeholder="" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-col-md4">
                <label class="layui-form-label" style="width: 80px">归属机构</label>
                <div class="layui-input-block" style="margin-left: 110px">

                    <input type="text" name="orgName" placeholder="" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-col-md2" style="text-align: right">
                <button class="layui-btn layui-btn-normal layui-btn-sm" lay-event="search">查询</button>
                <button class="layui-btn layui-btn-primary layui-btn-sm" type="reset">重置</button>
            </div>
        </form>
    </div>
    <div class="layui-row" style="padding: 10px 0px 0px 30px">
        <div class="layui-btn-group" style="text-align: left">
            <button class="layui-btn layui-btn-sm" lay-event="add">新增</button>
            <button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="update">修改</button>
            <button class="layui-btn layui-btn-danger layui-btn-sm dic-del" data-type="getCheckData" id="getCheckData">删除</button>
        </div>
    </div>

</script>
<!-- 表格操作 -->
<script type="text/html" id="tableOperation">
    <a class="layui-table-link" href="javascript:;" lay-event="allocateUsers">分配用户</a>
</script>
<script src="${ctxStatic}/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '${ctxStatic}/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
        ,commTreeTable:'commTreeTable'
    }).use(['index','commTreeTable', 'table', 'layer','jquery','form'], function () {
//layui相关模块别名
        var $ = layui.$,
            layer = layui.layer,
            fun = layui.fun,
            form = layui.form,
            table = layui.table,
            commTreeTable = layui.commTreeTable,
            theRequest = fun.GetRequest(),
            $body = $('body');
        /*定义tree、table相关的参数*/
        var commTreeUrl,commTreeElem,
            commTableUrl,commTableId,commTableElem,cols,
            tableToolbar,toolbarForm,formUrl,delTableUrl;
        //初始化参数
        tableToolbar = "tableToolbar";
        toolbarForm = "toolbarForm"
        formUrl ="${ctxTeacher}/role/add_edit.jsp";
        delTableUrl = "";
        commTreeElem='tree';
        commTreeUrl ='';
        //指定页面所在元素的id，加载table的url
        commTableElem = 'tableData';
        commTableId='tableDataID';//设置table方法id用于reload
        commTableUrl = '${ctx}/sysRole/roles';
        cols = [[{ type: 'checkbox',class:'check1',fixed:'left', title: '编号', width: 60 }
            , { field: 'id',align:"center", title: 'ID',width: 80,hide: true }
            , { field: 'name',align:"center", title: '角色名称' }
            , { field: 'roleType',align:"center", title: '角色类型', sort: true }
            , { field: 'orgName',align:"center", title: '归属机构',width: 200, sort: true }
            , { field: 'useable',align:"center", title: '是否可用' }
            , { field: 'remarks',align:"center", title: '备注' }
            , { fixed: 'right', title: '操作', align: 'center', toolbar: '#tableOperation' } //这里的toolbar值是模板元素的选择器
        ]];

        commTreeTable.getCommTree(commTreeElem,commTreeUrl,"",commTableId,toolbarForm,commTableUrl);
        commTreeTable.getCommTable(commTableElem,commTableUrl,commTableId,cols,tableToolbar);//getCommTable()的id为'tableDataID'
        commTreeTable.listenToolbar(table,commTableElem,commTableId,commTableUrl,toolbarForm,delTableUrl,formUrl);


        //批量删除
        $body.on('click', '.dic-del', function () {
            var checkStatus = table.checkStatus('tableDataID');
            if (checkStatus.data.length == 0) {
                parent.layer.msg('请先选择要删除的数据行！', { icon: 2 });
                return;
            } else {
                var delIndex = parent.layer.alert('真的要删除选中的 '+checkStatus.data.length+' 行数据吗',{
                    skin: 'layui-layer-molv' //样式类名  自定义样式
                    ,closeBtn: 1    // 是否显示关闭按钮
                    ,anim: 6 //动画类型
                    ,btn: ['删除','取消'] //按钮
                    ,icon: 3    // icon
                    ,yes:function(){
                        //执行删除事件
                        deleteData(JSON.stringify(checkStatus.data));
                        layui.layer.close(delIndex);
                    }
                    ,btn2:function(){
                        parent.layer.msg('好的,已帮您取消删除请求');
                    }
                });
            }
        });

        //删除功能
        function deleteData(jsonData) {
            $.ajax({
                url:"${ctx}/sysRole/deleteRoles"
                ,type:"post"
                ,dataType:"json"
                ,async:false
                ,contentType: 'application/json;charset=UTF-8'
                ,data:jsonData
                ,success:function(flag){
                    if (flag == true) {
                        parent.layer.msg('删除成功', { icon: 1 });

                    } else {
                        parent.layer.alert('删除失败', {
                            title: '操作结果'
                            ,icon: 2
                        });
                    }
                    commTreeTable.getCommTable(commTableElem,commTableUrl,commTableId,cols,tableToolbar);
                }
            });
        }
        //数据表格操作
        table.on('tool(tableData)', function (obj) {
            var data = obj.data;
            switch (obj.event) {
                case 'allocateUsers':
                    parent.layer.open({
                        type: 2 //Page层类型
                        , area: ['1000px', '600px']
                        , title: '角色信息'
                        , shade: 0.6 //遮罩透明度
                        , maxmin: true //允许全屏最小化
                        , anim: 1 //0-6的动画形式，-1不开启
                        , content: '${ctxTeacher}/role/user_form.jsp?id='+data.id
                        , end:function () {

                        }
                    });

                    break;

            }
        });
    });
</script>
</body>
</html>
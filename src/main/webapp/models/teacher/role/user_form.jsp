<%--
  Created by IntelliJ IDEA.
  User: zhoumeng
  Date: 2019/3/14
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/taglib.jsp"%>
<html lang="zh-cn">
<head>
    <title>角色分配管理</title>
    <%@ include file="/include/head_include.jsp"%>
</head>
<style>
    * {
        padding: 0;
        margin: 0;
    }

    body {
        padding: 14px;
    }

    .people-query .layui-card,
    .people-item .layui-card {
        border: 1px solid #eee;
    }

    .layui-card-header {
        height: 34px;
        line-height: 34px;
        background-color: #e6e6e6;
    }
    .layui-form-item {
        text-align: center;
    }
    .layui-form-select dl {
        font-size: 14px;
    }
    .tableBtn {
        padding-top: 100px;
        box-sizing: border-box;
    }
    #add,
    #delete {
        display: block;
        margin: 20px auto;
    }
</style>
<body>
<div class="people-item">
    <div class="layui-card">
        <div class="layui-card-header">角色分配</div>
        <div class="layui-card-body">
            <div class="layui-btn-container">
                <div class="layui-row">
                    <div class="layui-col-md5"><table class="layui-hide" id="leftTable"></table></div>

                    <div class="layui-col-md2 tableBtn">
                        <button class="layui-btn layui-btn-normal" id="add"   >添加&gt;&gt;</button>
                        <button class="layui-btn layui-btn-danger" id="delete">&lt;&lt;移除</button>
                    </div>

                    <div class="layui-col-md5"><table class="layui-hide" id="rightTable"></table></div>
                </div>
            </div>
        </div>
    </div>
</div>
<div style="display: none" id="layerDiv"></div>

<script src="${ctxStatic}/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '${ctxStatic}/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'table', 'layer', 'form'], function () {
        var table = layui.table,
            fun = layui.fun,
            form = layui.form,
            $ = layui.$,
            leftTableUrl = "${ctx}/userRole/getNotInRole",
            rightTableUrl = "${ctx}/userRole/getInRole",
            theRequest = fun.GetRequest(),
            roleId = theRequest.id;

        table.render({
            elem: '#leftTable',
            id: 'leftTableId',
            toolbar: '#leftToolbar',
            defaultToolbar:null,
            url: leftTableUrl,
            dataType:'json',
            where:{"roleId":roleId},
            contentType: 'application/json',
            method: 'post',
            request: {pageName: 'currentPage',limitName: 'pageSize'},
            parseData: function (result) { return {"code": result.code, "msg": result.msg, "count": result.extend.PageInfo.total,"data": result.extend.PageInfo.list};},
            response: {statusCode: 200},  //重新规定成功的状态码为 200，table 组件默认为 0
            page: {layout: ['count', 'prev', 'page', 'next', 'limit', 'skip'], 'prev': '上一页', 'next': '下一页', 'limits': [10, 20, 30, 40, 50] , jump: function (obj) { } },
            loading: true,
            cellMinWidth: 80,
            cols:[[
                { type: 'checkbox'},
                { field: 'empId', title: '用户账号' ,align:"center"},
                { field: 'realName', title: '用户姓名' ,align:"center"},
                { field: 'orgName', title: '所属部门' ,align:"center"}
            ]],
            first: false, //不显示首页
            last: false, //不显示尾页
            limits: [10, 20, 30],
        });

        table.render({
            elem: '#rightTable',
            id: 'rightTableId',
            toolbar: '#rightToolbar',
            defaultToolbar:null,
            url: rightTableUrl,
            dataType:"json",
            where:{"roleId":roleId},
            contentType: 'application/json',
            method: 'post',
            request: {pageName: 'currentPage',limitName: 'pageSize'},
            parseData: function (result) { return {"code": result.code, "msg": result.msg, "count": result.extend.PageInfo.total,"data": result.extend.PageInfo.list};},
            response: {statusCode: 200},  //重新规定成功的状态码为 200，table 组件默认为 0
            page: {layout: ['count', 'prev', 'page', 'next', 'limit', 'skip'], 'prev': '上一页', 'next': '下一页', 'limits': [10, 20, 30, 40, 50] , jump: function (obj) { } },
            loading: true,
            cellMinWidth: 80,
            cols:[[
                { type: 'checkbox'},
                { field: 'empId', title: '用户账号',align:"center"},
                { field: 'realName', title: '用户姓名' ,align:"center"},
                { field: 'orgName', title: '所属部门' ,align:"center"}
            ]],
            first: false, //不显示首页
            last: false, //不显示尾页
            limits: [10, 20, 30]
        });

        $("#add").on("click", function(){
            var checkData = table.checkStatus("leftTableId").data;
            if(checkData.length >= 1){addRoles();}else {layer.alert("请至少选择一条记录！");}
        });

        function addRoles(){
            var checkStatus = table.checkStatus('leftTableId');
            for (var  i=0; i<checkStatus.data.length ; i++){
                checkStatus.data[i].roleId=roleId;
            }
            choose("${ctx}/userRole/insertRoles",JSON.stringify(checkStatus.data),"add")
        }


        $("#delete").on("click", function(){
            var checkData = table.checkStatus("rightTableId").data;
            if(checkData.length >= 1){deleteRoles();}else {layer.alert("请至少选择一条记录！");}
        });

        function deleteRoles() {
            var checkStatus = table.checkStatus('rightTableId');
            for (var  i=0; i<checkStatus.data.length ; i++){
                checkStatus.data[i].roleId=roleId;
            }
            choose("${ctx}/userRole/deleteRoles",JSON.stringify(checkStatus.data),"delete")
        }
        //添加与移除，刷新table
        function choose(url,data,type){
            $.ajax({
                url:url,
                type:"post",
                dataType:"json",
                data:data,
                contentType: 'application/json;charset=utf-8',
                success: function(flg){
                    if (flg == true) {
                        if(type=="add"){
                            layer.msg('添加成功', { icon: 1 });
                        }else if(type=="delete"){
                            layer.msg('移除成功', { icon: 1 });
                        }
                    }else{
                        if(type=="add"){
                            layer.msg('添加失败', { icon: 2 });
                        }else if(type=="delete"){
                            layer.msg('移除失败', { icon: 2 });
                        }
                    }
                    table.reload("leftTableId", { //此处是上文提到的 初始化标识id
                        where: {
                            "roleId":roleId
                        }
                    });
                    table.reload("rightTableId", { //此处是上文提到的 初始化标识id
                        where: {
                            "roleId":roleId
                        }
                    });

                }
            });
        }
        var orgIdLeft;
        var orgIdRight;
        /*获取所有的部门*/
        getOrgs();

        function getOrgs(){
            var optionHtml = "<option value=''>请选择</option>";
            $.ajax({
                url:'${ctx}/sysRole/findIds',
                type:'post',
                contentType: 'application/json;charset=utf-8',
                success:function(res){
                    var resArray = res;
                    for(var i = 0; i < resArray.length; i++) {
                        optionHtml += "<option value='"+resArray[i].id+"'>"+resArray[i].text+"</option>";
                    }
                    $("#rightorgId").empty();
                    $("#rightorgId").append(optionHtml);
                    $("#leftorgId").empty();
                    $("#leftorgId").append(optionHtml);
                    form.render('select');
                }
            });
        }

        $("#roleId").val(roleId);

        form.on('submit(leftsearch)', function (data) {
            getOrgs();
            orgIdLeft= $("#leftorgId").val();
            var realName = $("#leftname").val();
            table.reload("leftTableId", { //此处是上文提到的 初始化标识id
                where: {
                    "roleId":roleId,
                    "realName":realName,
                    "orgId":orgIdLeft
                }
            });
        });

        form.on('submit(rightsearch)', function (data) {
            getOrgs();
            orgIdRight = $("#rightorgId").val();
            var realName = $("#rightname").val();
            table.reload("rightTableId", { //此处是上文提到的 初始化标识id
                where: {
                    "roleId":roleId,
                    "realName":realName,
                    "orgId":orgIdRight
                }
            });
        });
    });



</script>
<script type="text/html" id="leftToolbar">
    <form id="leftForm">
        <input type="hidden" name="roleId" class="layui-input">
        <div class="layui-row">
            <div class="layui-col-md4" style="height: 30px;">
                <label class="layui-form-label"style="text-align: center">姓名</label>
                <div class="layui-input-block" >
                    <input type="text" name="leftname" id="leftname" class="layui-input">
                </div>
            </div>
            <div class="layui-col-md5">
                <label class="layui-form-label" style="text-align: center">部门名称</label>
                <div class="layui-input-block" >
                    <select name="leftorgId" lay-search id="leftorgId" class="select">
                    </select>
                </div>
            </div>
            <div class="layui-col-md3" style="text-align: right">
                <button class="layui-btn layui-btn-normal layui-btn-sm layui-btn-radius" lay-filter="leftsearch" lay-submit id="leftsearch" >查询</button>
            </div>
        </div>
    </form>
</script>
<script type="text/html" id="rightToolbar">
    <form id="rightForm">
        <input type="hidden" name="roleId" class="layui-input">
        <div class="layui-row">
            <div class="layui-col-md4" style="height: 30px;">
                <label class="layui-form-label"style="text-align: center">姓名</label>
                <div class="layui-input-block" >
                    <input type="text" name="rightname" id="rightname" class="layui-input">
                </div>
            </div>
            <div class="layui-col-md5">
                <label class="layui-form-label" style="text-align: center">部门名称</label>
                <div class="layui-input-block" >
                    <select name="rightorgId" lay-search id="rightorgId" class="select">
                    </select>
                </div>
            </div>
            <div class="layui-col-md3" style="text-align: right">
                <button class="layui-btn layui-btn-normal layui-btn-sm layui-btn-radius" lay-filter="rightsearch" lay-submit id="rightsearch" >查询</button>

            </div>
        </div>
    </form>
</script>
</body>
</html>

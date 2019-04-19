<%--
  Created by IntelliJ IDEA.
  User: ZhaJinyi
  Date: 2019-3-13
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/taglib.jsp"%>
<html lang="zh-cn">
<head>
    <title>角色授权管理</title>
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
    .tableBtn {
        padding-top: 100px;
        box-sizing: border-box;
    }
    #add,
    #delete {
    display: block;
    margin: 20px auto;
    }
    .layui-table-page{
        padding-bottom: 7px;
    }
    .layui-form-select dl {
    font-size: 14px;
    }
</style>
<body>
    <div class="people-item">
        <div class="layui-card">
            <div class="layui-card-header">角色权限分配</div>
                <div class="layui-card-body">
                    <div class="layui-btn-container">
                        <div class="layui-row">
                            <div class="layui-col-md5"><table class="layui-hide" id="leftTable"></table></div>

                            <div class="layui-col-md2 tableBtn">
                                <button class="layui-btn layui-btn-normal" id="add"   >添加&gt;&gt;</button>
                                <button class="layui-btn layui-btn-danger" id="delete">&lt;&lt;移除</button>
                            </div>

                            <div class="layui-col-md5"><table class="layui-hide" id="rightTable" lay-filter="rightTable"></table></div>
                        </div>
                    </div>
                </div>
        </div>
    </div>
    <div style="display: none" id="layerDiv">
        <form class='layui-form' lay-filter='layerForm' id='layerForm'>
            <div class="layui-row" style="height: 30px" id="checkboxDiv"></div>
                <div class="layui-col-md12" style="margin-top: 40px;">
                    <div style="text-align: center">
                        <button class='layui-btn layui-btn-sm layui-btn-normal' lay-submit='' lay-filter='layerSubmit'>确定</button>
                    </div>
                </div>
        </form>

    </div>



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
        leftTableUrl = "${ctx}/sysRole/selectRolesByMenuIds",
        rightTableUrl = "${ctx}/menuRole/entity/select",
        insertUrl = "${ctx}/menuRole/entity/insert",
        updateUrl = "${ctx}/menuRole/entity/update",
        deleteUrl = "${ctx}/menuRole/entity/delete",
        theRequest = fun.GetRequest(),
        menuId = theRequest.menuId,
        updateFlag = false;

    table.render({
        elem: '#leftTable',
        id: 'leftTableId',
        toolbar: '#leftToolbar',
        defaultToolbar:null,
        url: leftTableUrl,
        contentType: 'application/json',
        method: 'post',
        where: {menuId:menuId},
        height: 'full-90', //固定值
        request: {pageName: 'currentPage',limitName: 'pageSize'},
        parseData: function (result) { return {"code": result.code, "msg": result.msg, "count": result.extend.PageInfo.total,"data": result.extend.PageInfo.list};},
        response: {statusCode: 200},  //重新规定成功的状态码为 200，table 组件默认为 0
        page: {layout: ['count', 'prev', 'page', 'next', 'limit', 'skip'], 'prev': '上一页', 'next': '下一页', 'limits': [10, 20, 30, 40, 50] , jump: function (obj) { } },
        loading: true,
        cellMinWidth: 80,
        cols:[[
            { type: 'checkbox',fixed:'left'},
            { field: 'id', title: '角色ID', width:80, align:'center' },
            { field: 'name', title: '角色名称', width:120, align:'left' },
            { field: 'orgName', title: '所属部门', align:'left' }
            ]],
        first: false, //不显示首页
        last: false, //不显示尾页
        limits: [10, 20, 30]
    });

    table.render({
        elem: '#rightTable',
        id: 'rightTableId',
        toolbar: '#rightToolbar',
        defaultToolbar:null,
        url: rightTableUrl,
        contentType: 'application/json',
        method: 'post',
        where: {menuId:menuId},
        height: 'full-90', //固定值
        request: {pageName: 'currentPage',limitName: 'pageSize'},
        parseData: function (result) { return {"code": result.code, "msg": result.msg, "count": result.extend.PageInfo.total,"data": result.extend.PageInfo.list};},
        response: {statusCode: 200},  //重新规定成功的状态码为 200，table 组件默认为 0
        page: {layout: ['count', 'prev', 'page', 'next', 'limit', 'skip'], 'prev': '上一页', 'next': '下一页', 'limits': [10, 20, 30, 40, 50] , jump: function (obj) { } },
        loading: true,
        cellMinWidth: 80,
        cols:[[
            { type: 'checkbox'},
            { field: 'roleId', title: '角色ID', width:80, align:'center'},
            { field: 'roleName', title: '角色名称', width:120, align:'left' },
            { field: 'orgName', title: '所属部门', width:100, align:'left' },
            { field: 'menuPermission',title:'权限',width:100,align:'left'},
            { fixed: 'right', title: '操作',  align: 'center', toolbar: '#tableOperation' }
        ]],
        first: false, //不显示首页
        last: false, //不显示尾页
        limits: [10, 20, 30]
        });

        $("#add").on("click", function(){
            var checkData = table.checkStatus("leftTableId").data;
            if(checkData.length >= 1){getAllRoles();}else {layer.alert("请至少选择一条记录！");}
        });
        $("#delete").on("click", function(){
            var checkData = table.checkStatus("rightTableId").data;
            if(checkData.length >= 1){delAllRoles(checkData);}else {layer.alert("请至少选择一条记录！");}
        });
    /**
    *
    * 实现具体的权限删除功能
    *
    */
    function delAllRoles(checkData){
        var parms = new Array();
        for(var i = 0; i < checkData.length; i++) {
            parms.push({roleId:checkData[i].roleId,menuId:menuId});
        }
        $.ajax({
            url:deleteUrl,
            type:'post',
            data:JSON.stringify(parms),
            contentType: 'application/json;charset=utf-8',
            async : false,
            success : function(res){
                if (res.code == 200){
                    layer.alert("操作成功");
                }else {
                    layer.alert("操作失败");
                }
        }
        });
        /*左表的重新加载*/
        table.reload('leftTableId', { where: getEntity($("#leftForm"))});
        /*右表的重新加载*/
        table.reload('rightTableId', {where: getEntity($("#rightForm"))});
        getOrgs();
    }

    /**
    * 获取左侧所有角色的数据，并加入相应容器中；
    */
    var indexIframe;//定义iframeID值
    function getAllRoles(){
        var checkboxValue = "";
        var checkboxHtml = "";
        $.ajax({
            url:"${ctx}/sys/menu/getEntityByid",
            type: 'post',
            data:JSON.stringify({id:menuId}),
            contentType: 'application/json;charset=utf-8',
            async:false,
            success: function(res){
                checkboxValue = res.extend.permission;
                var checkboxArray = checkboxValue.split(",");
                for(var i = 0; i < checkboxArray.length; i++) {
                    checkboxHtml += "<input name='perm" + i + "' type='checkbox' title='" + checkboxArray[i] + "' lay-skin='primary' value='" + checkboxArray[i] + "'>";
                }
                $("#checkboxDiv").empty();
                $("#checkboxDiv").append(checkboxHtml);
                indexIframe = layer.open({
                    title: '请选择可分配权限',
                    content: $("#layerDiv").html(),
                    btn:[],
                    area: ['300px', '200px'],
                    yes: function(index, layero){
                    }
                });
                form.render("",'layerForm');
            }
        });
    }
    function updateEntity(menuPermission,updateRoleId){
        $.ajax({
            url:updateUrl,
            data:JSON.stringify({roleId:updateRoleId,menuId:menuId,menuPermission:menuPermission}),
            type:'post',
            contentType: 'application/json;charset=utf-8',
            async:false,
            success: function(res){
                if (res.code == 200){
                    layer.alert("操作成功");
                }else {
                    layer.alert("操作失败");
                }
            }
        })
    }
    form.on('submit(layerSubmit)', function(data){
        if (updateFlag){
            updateFlag = false;
            if (date.field == {}){
                layer.alert("请选择相应权限");
            return false;
            } else {
                var eachObj = data.field;
                var menuPermission = "";
            　　    for(var key in eachObj){
                        menuPermission += eachObj[key] + ",";
            　　    }
            menuPermission = menuPermission.substring(0,menuPermission.length - 1);
            updateEntity(menuPermission,updateRoleId);
            /*左表的重新加载*/
            table.reload('leftTableId', { where: getEntity($("#leftForm"))});
            /*右表的重新加载*/
            table.reload('rightTableId', {where: getEntity($("#rightForm"))});
            getOrgs();
            layer.close(indexIframe);
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            }
        } else {
            if (date.field == {}){
                layer.alert("请选择相应权限");
                return false;
            } else {
                var eachObj = data.field;
                var menuPermission = "";
        　　    for(var key in eachObj){
                    menuPermission += eachObj[key] + ",";
        　　    }
                var roleArray = table.checkStatus("leftTableId").data;
                var roleIds = "";
                for(var i = 0; i < roleArray.length ; i++) {
                    roleIds += roleArray[i].id + ",";
                }
                roleIds = roleIds.substring(0,roleIds.length -1);
                menuPermission = menuPermission.substring(0,menuPermission.length - 1);
                insertEntity(menuPermission,roleIds);
                /*左表的重新加载*/
                table.reload('leftTableId', { where: getEntity($("#leftForm"))});
                /*右表的重新加载*/
                table.reload('rightTableId', {where: getEntity($("#rightForm"))});
                getOrgs();
                layer.close(indexIframe);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            }
        }
    });
    /*左表的重新加载*/
    table.reload('leftTableId', { where: getEntity($("#leftForm"))});
    /*右表的重新加载*/
    table.reload('rightTableId', {where: getEntity($("#rightForm"))});

    //将角色添加到角色-菜单关联表中
    function insertEntity(permission,roleIds){
        $.ajax({
            url:insertUrl,
            type:'GET',
            data:{roleIds:roleIds,menuId:menuId,menuPermission:permission},
            contentType: 'application/json;charset=utf-8',
            async:false,
            success:function(res){
                if (res.code == 200){
                layer.alert("操作成功");
                }else {
                layer.alert("操作失败");
                }
            }
        });
    }

    /*获取所有的部门*/
    getOrgs();
    function getOrgs(){
        var optionHtml = "<option value='' selected>全部</option>";
        $.ajax({
            url:'${ctx}/user/org/all',
            type:'post',
            contentType: 'application/json;charset=utf-8',
            async:false,
            success:function(res){
                var resArray = res.extend.orgList;
                for(var i = 0; i < resArray.length; i++) {
                    optionHtml += "<option value='"+resArray[i].id+"'>"+resArray[i].orgName+"</option>";
                }
                $("#leftOrgId").empty();
                $("#leftOrgId").append(optionHtml);
                $("#rightOrgId").empty();
                $("#rightOrgId").append(optionHtml);
                form.render('select');
            }
        });
    }

    //监听行工具事件
    var updateRoleId = "";
    table.on('tool(rightTable)', function (obj) {
        updateFlag = true;//是否在修改状态;
        var checkedPerms = obj.data.menuPermission;
        updateRoleId = obj.data.roleId;
        var checkboxValue = "";
        var checkboxHtml = "";
        $.ajax({
            url:"${ctx}/sys/menu/getEntityByid",
            type: 'post',
            data:JSON.stringify({id:menuId}),
            contentType: 'application/json;charset=utf-8',
            async:false,
            success: function(res){
                checkboxValue = res.extend.permission;
                var checkboxArray = checkboxValue.split(",");
                for(var i = 0; i < checkboxArray.length; i++) {
                    if (checkedPerms.indexOf(checkboxArray[i]) != -1) {
                    checkboxHtml += "<input name='perm" + i + "' type='checkbox' title='" + checkboxArray[i] + "' lay-skin='primary' value='" + checkboxArray[i] + "'checked>";
                    }else {
                    checkboxHtml += "<input name='perm" + i + "' type='checkbox' title='" + checkboxArray[i] + "' lay-skin='primary' value='" + checkboxArray[i] + "'>";
                    }
                }
                $("#checkboxDiv").empty();
                $("#checkboxDiv").append(checkboxHtml);
                indexIframe = layer.open({
                    title: '请选择可分配权限',
                    content: $("#layerDiv").html(),
                    btn:[],
                    area: ['300px', '200px'],
                    yes: function(index, layero){
                        }
                });
                form.render("",'layerForm');
            }
        });
    });

    form.on('submit(leftSearch)', function(data){
        /*左表的重新加载*/
        table.reload('leftTableId', { where: getEntity($("#leftForm"))});
        getOrgs();
        return false;
    });

    form.on('submit(rightSearch)', function(data){
        /*右表的重新加载*/
        table.reload('rightTableId', { where: getEntity($("#rightForm"))});
        getOrgs();
        return false;
    });


    });
</script>
<script type="text/html" id="leftToolbar">
    <form id="leftForm">
        <div class="layui-row" style="height: 30px;">
            <div class="layui-col-md4">
                <label class="layui-form-label"style="text-align: center">角色名称</label>
                <div class="layui-input-block" >
                    <input type="text" name="name" class="layui-input">
                </div>
            </div>
            <div class="layui-col-md5">
                <label class="layui-form-label" style="text-align: center">部门名称</label>
                <div class="layui-input-block" >
                    <select name="organizationId" lay-search id="leftOrgId" class="select">
                    </select>
                </div>
            </div>
            <div class="layui-col-md3" style="text-align: right">
                <button class="layui-btn layui-btn-normal layui-btn-sm layui-btn-radius"lay-submit='' lay-filter='leftSearch'>查询</button>
                <button class="layui-btn layui-btn-primary layui-btn-sm layui-btn-radius" type="reset">重置</button>
            </div>
        </div>
    </form>
</script>
<script type="text/html" id="rightToolbar">
    <form id="rightForm">
        <div class="layui-row" style="height: 30px">
            <div class="layui-col-md4">
                <label class="layui-form-label"style="text-align: center">角色名称</label>
            <div class="layui-input-block" >
                <input type="text" name="roleName" class="layui-input">
            </div>
        </div>
        <div class="layui-col-md5">
            <label class="layui-form-label" style="text-align: center">部门名称</label>
            <div class="layui-input-block" >
                <select name="orgId" lay-search id="rightOrgId" class="select">
                </select>
            </div>
        </div>
        <div class="layui-col-md3" style="text-align: right">
            <button class="layui-btn layui-btn-normal layui-btn-sm layui-btn-radius" lay-submit='' lay-filter='rightSearch'>查询</button>
            <button class="layui-btn layui-btn-primary layui-btn-sm layui-btn-radius" type="reset">重置</button>
        </div>
        </div>
    </form>
</script>
<!-- 表格操作 -->
<script type="text/html" id="tableOperation">
    <a class="layui-table-link" lay-event="edit" id="edit">编辑</a>
</script>
</body>
</html>

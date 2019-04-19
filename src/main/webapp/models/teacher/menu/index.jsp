<%--
  Created by IntelliJ IDEA.
  User: 王佳伟
  Date: 2019-2-21
  Time: 09:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/taglib.jsp"%>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <title>苏州工业园区服务外包学院-学工系统-菜单管理</title>
    <%@ include file="/include/head_include.jsp"%>
</head>

<style>
    #xtree span{
        height: auto;
    }
    .layui-xtree-icon-null{
        display: none;
    }
    .layui-xtree-icon{
        top: 7px !important;
        margin: 0px 5px 0px -23px !important;
    }
    .layui-card-body{
        padding-bottom: 20px;
        padding-left: 15px;
    }
    .layui-xtree-item{
        margin-left: 15px !important;
    }
    .layui-form-label{
        width: 100px;
    }
    .layui-input-block{
        margin-left: 100px;
    }
    #treebuttons .layui-btn + .layui-btn{
        margin-left: 10px;
    }
    .menuname{
        padding-left: 0;
        padding-right: 15px;
        line-height: 18px;
        background: 0 0;
        color: #666;
        padding-top: 10px;
        font-size: 14px;
        border-radius: 2px 0 0 2px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        display: inline-block;
        vertical-align: middle;
        position: relative;
        cursor: pointer;
    }
</style>

<body>
    <div class="layui-fluid" id="LAY-component-grid-mobile-pc">
        <div class="layui-row layui-col-space10">
            <div class="layui-col-xs4 layui-col-md2">
                <div class="layui-card">
                    <div class="layui-card-body">
                        <div style="text-align: center;padding: 10px 0;" id="treebuttons">
                            <button class="layui-btn layui-btn-primary layui-btn-sm tree_add">新增</button>
                            <%--<button class="layui-btn layui-btn-primary layui-btn-sm tree_edit">编辑</button>--%>
                            <button class="layui-btn layui-btn-primary layui-btn-sm tree_del">删除</button>
                        </div>
                        <form class="layui-form" oncontextmenu="return false">
                            <div id="xtree" style="width:200px;"></div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="layui-col-xs8 layui-col-md10">
                <!-- 填充内容 -->
                <div class="layui-card">
                    <div class="layui-card-body" id="addtreemenu">
                        <form class="layui-form" action="" lay-filter="addtree-form-group" id="addtree-form">
                            <input type="hidden" name="delFlag" value="0">
                            <input type="hidden" name="createBy" value="${sessionScope.user.id}">
                            <input type="hidden" name="createDate" value="">
                            <input type="hidden" name="updateBy" value="${sessionScope.user.id}">
                            <input type="hidden" name="updateDate" value="">
                            <div class="layui-row">
                                <div class="layui-col-md6 layui-form-item">
                                    <label class="layui-form-label">菜单级别：</label>
                                    <div class="layui-input-block">
                                        <select name="menuLevel" lay-filter="menuLevel">
                                            <option value="1">一级菜单</option>
                                            <option value="2">二级菜单</option>
                                            <option value="3">三级菜单</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-col-md6 layui-form-item" id="firstMenu" style="display: none">
                                    <label class="layui-form-label">所属一级菜单：</label>
                                    <div class="layui-input-block">
                                        <select name="firstMenu" lay-filter="firstMenu">
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-col-md6 layui-form-item" id="secondMenu" style="display: none">
                                    <label class="layui-form-label">所属二级菜单：</label>
                                    <div class="layui-input-block">
                                        <select name="secondMenu" lay-filter="secondMenu">
                                        </select>
                                    </div>
                                </div>
								<div class="layui-col-md6 layui-form-item">
                                    <label class="layui-form-label">菜单名称：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="name" lay-verify="title" autocomplete="off"
                                        placeholder="请输入菜单名" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md6 layui-form-item">
                                    <label class="layui-form-label">菜单排序：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="sort" lay-verify="sort" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md6 layui-form-item">
                                    <label class="layui-form-label">是否本系统应用：</label>
                                    <div class="layui-input-block">
                                        <select name="target" lay-filter="target">
                                            <option value="1">是</option>
                                            <option value="0">否</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-col-md6 layui-form-item">
                                    <label class="layui-form-label">跳转URL：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="href" lay-verify="href" autocomplete="off"
                                        placeholder="请输入链接地址" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md6 layui-form-item">
                                    <label class="layui-form-label">权限：</label>
                                    <div class="layui-input-block">
                                        <select id="add-permission" name="permission" xm-select="selectId-add" xm-select-max="4">
                                            <option value="view" selected="selected">查看</option>
                                            <option value="add">增加</option>
                                            <option value="update">修改</option>
                                            <option value="delete">删除</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-col-md6 layui-form-item">
                                    <label class="layui-form-label">是否显示：</label>
                                    <div class="layui-input-block">
                                        <select name="isShow" lay-filter="isShow">
                                            <option value="1">是</option>
                                            <option value="0">否</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-col-md6 layui-form-item layui-form-text form-text">
                                    <label class="layui-form-label">备注：</label>
                                    <div class="layui-input-block">
                                        <textarea name="remarks" class="layui-textarea"></textarea>
                                    </div>
                                </div>
                                <div class="layui-col-md12 layui-form-item layui-layout-admin btn-sumbit">
                                    <div class="layui-input-block">
                                        <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="class_form_new_save">保存并新建</button>
                                        <button type="reset" class="layui-btn layui-btn-primary" id="addtree-form-reset">重置</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="layui-card-body" id="edittreemenu" style="display: none;">
                        <form class="layui-form" action="" lay-filter="edittree-form-group" id="edittree-form">
                            <input type="hidden" id="edit-id" name="id" value="">
                            <input type="hidden" id="edit-parentId" name="parentId" value="">
                            <input type="hidden" id="edit-parentIds" name="parentIds" value="">
                            <input type="hidden" id="edit-createBy" name="createBy" value="">
                            <input type="hidden" id="edit-createDate" name="createDate" value="">
                            <input type="hidden" id="edit-updateBy" name="updateBy" value="${sessionScope.user.id}">
                            <input type="hidden" id="edit-updateDate" name="updateDate" value="">
                            <div class="layui-row">
                                <div class="layui-col-md6 layui-form-item">
                                    <label class="layui-form-label">菜单级别：</label>
                                    <div class="layui-input-block">
                                        <select id="edit-menuLevel" name="menuLevel" lay-filter="menuLevel-edit">
                                            <option value="1">一级菜单</option>
                                            <option value="2">二级菜单</option>
                                            <option value="3">三级菜单</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-col-md6 layui-form-item" id="firstMenu-edit" style="display: none">
                                    <label class="layui-form-label">所属一级菜单：</label>
                                    <div class="layui-input-block">
                                        <select id="edit-firstMenu" name="firstMenu" lay-filter="firstMenu-edit">
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-col-md6 layui-form-item" id="secondMenu-edit" style="display: none">
                                    <label class="layui-form-label">所属二级菜单：</label>
                                    <div class="layui-input-block">
                                        <select id="edit-secondMenu" name="secondMenu" lay-filter="secondMenu-edit">
                                        </select>
                                    </div>
                                </div>
								<div class="layui-col-md6 layui-form-item">
                                    <label class="layui-form-label">菜单名称：</label>
                                    <div class="layui-input-block">
                                        <input type="text" id="edit-name" name="name" lay-verify="title-edit" autocomplete="off"
                                        placeholder="请输入菜单名" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md6 layui-form-item">
                                    <label class="layui-form-label">菜单排序：</label>
                                    <div class="layui-input-block">
                                        <input type="text" id="edit-sort" name="sort" lay-verify="sort-edit" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md6 layui-form-item">
                                    <label class="layui-form-label">是否本系统应用：</label>
                                    <div class="layui-input-block">
                                        <select id="edit-target" name="target" lay-filter="target-edit">
                                            <option value="1">是</option>
                                            <option value="0">否</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-col-md6 layui-form-item">
                                    <label class="layui-form-label">跳转URL：</label>
                                    <div class="layui-input-block">
                                        <input type="text" id="edit-href" name="href" lay-verify="href-edit" autocomplete="off"
                                        placeholder="请输入链接地址" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md6 layui-form-item">
                                    <label class="layui-form-label">权限：</label>
                                    <div class="layui-input-block">
                                        <select id="edit-permission" name="permission" xm-select="selectId-edit" xm-select-max="4">
                                            <option value="view" id="edit-view">查看</option>
                                            <option value="add" id="edit-add">增加</option>
                                            <option value="update" id="edit-update">修改</option>
                                            <option value="delete" id="edit-delete">删除</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-col-md6 layui-form-item">
                                    <label class="layui-form-label">是否显示：</label>
                                    <div class="layui-input-block">
                                        <select id="edit-isShow" name="isShow" lay-filter="isShow-edit">
                                            <option value="1">是</option>
                                            <option value="0">否</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-col-md6 layui-form-item layui-form-text form-text">
                                    <label class="layui-form-label">备注：</label>
                                    <div class="layui-input-block">
                                        <textarea id="edit-remarks" name="remarks" class="layui-textarea"></textarea>
                                    </div>
                                </div>
                                <div class="layui-col-md12 layui-form-item layui-layout-admin btn-sumbit">
                                    <div class="layui-input-block">
                                        <button class="layui-btn layui-btn-normal" id="goto_form_edit">授权管理</button>
                                        <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="class_form_save">保存修改</button>
                                        <button type="button" class="layui-btn layui-btn-primary" id="edittree-form-reset">重置</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="${ctxStatic}/layuiadmin/layui/layui.js?t=1"></script>
    <script src="${ctxStatic}/layuiadmin/lib/extend/layui-xtree.js"></script>
    <script>
        var index;
        layuiLoading();
        layui.config({
            base: '${ctxStatic}/layuiadmin/' //静态资源所在路径
        }).extend({
            index: 'lib/index', //主入口模块
            formSelects:'formSelects-v4'
        }).use(['index','formSelects', 'tree', 'table', 'layer', 'form'], function () {
            var $ = layui.$
                , tree = layui.tree
                , formSelects = layui.formSelects
                , fun = layui.fun
                , form = layui.form
                , $body = $('body');//body
            var xtree;
            var xtreechecked = new Array();//数组,存储XTree中被选中的项
            var edito;//用于编辑的源数据，重置表单时用
            var lastChecked = localStorage.getItem("lastChecked");

            init(lastChecked);
            layuiRemoveLoading();

            /* 重新加载并渲染Xtree */
            function renderXtree(){
                xtreechecked = new Array();
                $.ajax({
                    url:"${ctx}/menuManager/xtree"
                    ,type:"post"
                    ,dataType:"json"
                    ,async:false
                    ,contentType: 'application/json;charset=UTF-8'
                    ,data:{}
                    ,success:function(xTreeData){
                        layui.use('form', function () {
							xtree = new layuiXtree({
								elem: 'xtree'   //(必填) 放置xtree的容器id，不要带#号
								, form: form     //(必填) layui 的 from
								, data: xTreeData
								, isopen: true  //加载完毕后的展开状态，默认值：true
								, ckall: false    //启用全选功能，默认值：false
								, ckallback: function () { } //全选框状态改变后执行的回调函数
								/*, icon: {        //三种图标样式，更改几个都可以，用的是layui的图标
									open: "图标代号"       //节点打开的图标
									, close: "图标代号"    //节点关闭的图标
									, end: "图标代号"      //末尾节点的图标
								}*/
								, color: {       //三种图标颜色，独立配色，更改几个都可以
									open: "#EE9A00"        //节点图标打开的颜色
									, close: "#EEC591"     //节点图标关闭的颜色
									, end: "#828282"       //末级节点图标的颜色
								}
								, click: function (data) {  //节点选中状态改变事件监听，全选框有自己的监听事件
									/*console.log(data.elem); //得到checkbox原始DOM对象
									console.log(data.elem.checked); //开关是否开启，true或者false
									console.log(data.value); //开关value值，也可以通过data.elem.value得到
									console.log(data.othis); //得到美化后的DOM对象*/
                                    xtreechecked = xtree.GetAllChecked();
                                    console.log(xtreechecked);
								}
							});
						});
                    }
                });
            }

            function init(value){
                renderXtree();
                if (value == null) {
                    var otree = xtree.GetAllCheckBox();
                    otree[0].checked = true;
                    otree[0].parentNode.parentNode.getElementsByClassName('layui-form-checkbox')[0].classList.add('layui-form-checked');
                    xtreechecked = xtree.GetAllChecked();
                    showEdit(otree[0].value);
                } else {
                    xtree.ClearAllChecked();
                    xtreechecked = new Array();
                    xtreechecked.push(xtree.CheckedByValue(value));
                    showEdit(value);
                }
            }

            /* 监听Xtree中的菜单点击 */
            $body.on('click', '.menuname', function (elem) {
                xtree.ClearAllChecked();
                lastChecked = String(elem.target.id).substring(4);
                xtreechecked = new Array();
                xtreechecked.push(xtree.CheckedByValue(lastChecked));
                localStorage.removeItem("lastChecked");
                showEdit(lastChecked);
            });

            /* 监听Xtree中的添加按钮 */
            $body.on('click', '.tree_add', function (elem) {
                xtree.ClearAllChecked();
                xtreechecked = null;
                $("#addtreemenu").show();
                $("#edittreemenu").hide();
            });

            function showEdit(id) {
                $("#addtreemenu").hide();
                $("#edittreemenu").show();
                layuiLoading();
                $.ajax({
                    url:"${ctx}/menuManager/getOneMenu?id="+id
                    ,type:"post"
                    ,dataType:"json"
                    ,async:false
                    ,contentType: 'application/json;charset=UTF-8'
                    ,data:{}
                    ,success:function(o){
                        edito = o;
                        editInit(o);
                    }
                });
                layuiRemoveLoading();
            }

			/* 监听Xtree中的删除按钮 */
            $body.on('click', '.tree_del', function (elem) {
                if (xtreechecked.length < 1)
                    parent.layer.alert('请先选中需要删除的数据！');
                else {
                    var ids = "";
                    for (var i = 0; i < xtreechecked.length; i++) {
                        ids += String(xtreechecked[i].value) + ",";
                    }
                    ids = ids.substring(0,ids.length-1);
                    var delIndex = parent.layer.alert('真的要删除选中的菜单吗',{
                        skin: 'layui-layer-molv' //样式类名  自定义样式
                        ,closeBtn: 1    // 是否显示关闭按钮
                        ,anim: 6 //动画类型
                        ,btn: ['删除','取消'] //按钮
                        ,icon: 3    // icon
                        ,yes:function(){
                            //执行删除事件
                            layuiLoading();
                            $.ajax({
                                url:"${ctx}/menuManager/delMenu?ids="+ids
                                ,type:"post"
                                ,dataType:"json"
                                ,async:false
                                ,contentType: 'application/json;charset=UTF-8'
                                ,data:{}
                                ,success:function(o){
                                    var flagMsg = o.extend.flagMsg;
                                    parent.layer.alert(flagMsg, {
                                        title: '操作结果'
                                        ,icon: 1
                                    });
                                    layui.layer.close(delIndex);
                                    renderXtree();
                                }
                            });
                            layuiRemoveLoading();
                        }
                        ,btn2:function(){
                            parent.layer.msg('好的,已帮您取消删除请求');
                        }
                    });
                }
                return false;
            });

            /* 添加功能中，菜单级别改变时触发 */
            form.on('select(menuLevel)', function(data) {
                if (data.value == "1") {
                    $("#firstMenu").hide();
                    $("#secondMenu").hide();
                    $("select[name=firstMenu]").val("");
                    $("select[name=secondMenu]").val("");
                } else {
                    $("#firstMenu").show();
                    if (data.value == "2"||$("select[name=firstMenu]").val() == "")
                        $("#secondMenu").hide();
                    else
                        $("#secondMenu").show();
                    $("select[name=secondMenu]").val("");
                    var menuLevel = $("select[name=firstMenu]").val();
                    var html = getMenu("${ctx}/menuManager/getFirstMenu",{});
                    $("select[name=firstMenu]").html(html);
                    $("select[name=firstMenu]").val(menuLevel);
                    menuLevel = null;
                    form.render('select');
                }
            });

            /* 编辑功能中，菜单级别改变时触发 */
			form.on('select(menuLevel-edit)', function(data) {
                if (data.value == "1") {
                    $("#firstMenu-edit").hide();
                    $("#secondMenu-edit").hide();
                    $("select[name=firstMenu]").val("");
                    $("select[name=secondMenu]").val("");
                } else {
                    $("#firstMenu-edit").show();
                    if (data.value == "2"||$("select[name=firstMenu]").val() == "")
                        $("#secondMenu-edit").hide();
                    else
                        $("#secondMenu-edit").show();
                    $("select[name=secondMenu]").val("");
                    var menuLevel = $("select[name=firstMenu]").val();
                    var html = getMenu("${ctx}/menuManager/getFirstMenu",{});
                    $("select[name=firstMenu]").html(html);
                    $("select[name=firstMenu]").val(menuLevel);
                    menuLevel = null;
                    form.render('select');
                }
            });

            /* 添加功能中，所属一级菜单改变时触发 */
            form.on('select(firstMenu)', function(data) {
                var menuLevel = $("select[name=menuLevel]").val();
                if (menuLevel == "3") {
                    $("#secondMenu").show();
                    var html = getMenu("${ctx}/menuManager/getSencondMenu?parentid="+data.value,{});
                    $("select[name=secondMenu]").html(html);
                    $("select[name=secondMenu]").val("");
                    form.render('select');
                }
            });

            /* 编辑功能中，所属一级菜单改变时触发 */
			form.on('select(firstMenu-edit)', function(data) {
                var menuLevel = $("#edit-menuLevel").val();
                if (menuLevel == "3") {
                    $("#secondMenu-edit").show();
                    var html = getMenu("${ctx}/menuManager/getSencondMenu?parentid="+data.value,{});
                    $("select[name=secondMenu]").html(html);
                    $("select[name=secondMenu]").val("");
                    form.render('select');
                }
            });

			/* 获取下拉列表 */
            function getMenu(url,jsonData){
                var html = "";
                $.ajax({
                    url:url
                    ,type:"post"
                    ,dataType:"json"
                    ,async:false
                    ,contentType: 'application/json;charset=UTF-8'
                    ,data:jsonData
                    ,success:function(o){
                        var menus = new Array();
                        menus = o;
                        for (var i = 0; i < menus.length; i++) {
                            html += "<option value='" + menus[i].id + "'>" + menus[i].name + "</option>";
                        }
                    }
                });
                return html;
            }

            /* 编辑界面初始化 */
            function editInit(o){
                $("#edit-id").val(o.id);
                $("#edit-parentId").val(o.parentId);
                $("#edit-parentIds").val(o.parentIds);
                $("#edit-createBy").val(o.createBy);
                $("#edit-createDate").val(o.createDate);
                $("#edit-name").val(o.name);
                formSelects.render("selectId-edit");
                var opermission = String(o.permission).split(",");
                formSelects.value("selectId-edit",opermission,true);
                var parentIds = String(o.parentIds).split(".");
                $("#edit-menuLevel").val(parentIds.length);
                if (parentIds.length == 1) {
                    $("#firstMenu-edit").hide();
                    $("#secondMenu-edit").hide();
                } else if (parentIds.length == 2) {
                    $("#firstMenu-edit").show();
                    $("#secondMenu-edit").hide();
                    var html = getMenu("${ctx}/menuManager/getFirstMenu",{});
                    $("select[name=firstMenu]").html(html);
                    $("select[name=firstMenu]").val(parentIds[0]);
                } else if (parentIds.length == 3) {
                    $("#firstMenu-edit").show();
                    $("#secondMenu-edit").show();
                    var html = getMenu("${ctx}/menuManager/getFirstMenu",{});
                    $("select[name=firstMenu]").html(html);
                    $("select[name=firstMenu]").val(parentIds[0]);
                    html = getMenu("${ctx}/menuManager/getSencondMenu?parentid="+parentIds[0],{});
                    $("select[name=secondMenu]").html(html);
                    $("select[name=secondMenu]").val(parentIds[1]);
                }
                $("#edit-sort").val(o.sort);
                $("#edit-target").val(o.target);
                $("#edit-href").val(o.href);
                $("#edit-isShow").val(o.isShow);
                $("#edit-remarks").val(o.remarks);
                form.render(null,"edittree-form-group");
            }
            
            /* 表单保存 */
            form.on('submit(class_form_save)', function (data) {
                parent.layer.closeAll();
                layuiLoading();
                data.field.updateDate = new Date();
                if (data.field.menuLevel == "1") {
                    data.field.parentId = "0";
                    data.field.parentIds = data.field.id;
                } else if (data.field.menuLevel == "2") {
                    data.field.parentId = data.field.firstMenu;
                    data.field.parentIds = data.field.firstMenu + "." + data.field.id;
                } else {
                    data.field.parentId = data.field.secondMenu;
                    data.field.parentIds = data.field.firstMenu + "." + data.field.secondMenu + "." + data.field.id;
                }
                $.ajax({
                    url:"${ctx}/menuManager/editMenu"
                    ,type:"post"
                    ,dataType:"json"
                    ,async:false
                    ,contentType: 'application/json;charset=UTF-8'
                    ,data:JSON.stringify(data.field)
                    ,success:function(o){
                        var msg;
                        if (o.extend.flag == true)
                            msg = "成功！";
                        else
                            msg = "发生错误！";
                        parent.layer.alert(msg, {
                            title: '操作结果'
                        });
                        renderXtree();
                    }
                });
                layuiRemoveLoading();
                return false;
            });

            /* 表单保存并新建 */
            form.on('submit(class_form_new_save)', function (data) {
                parent.layer.closeAll();
                layuiLoading();
                data.field.createDate = new Date();
                data.field.updateDate = new Date();
                if (data.field.menuLevel == "1") {
                    data.field.parentId = "0";
                    data.field.parentIds = "0"
                } else if (data.field.menuLevel == "2") {
                    data.field.parentId = data.field.firstMenu;
                    data.field.parentIds = data.field.firstMenu;
                } else {
                    data.field.parentId = data.field.secondMenu;
                    data.field.parentIds = data.field.firstMenu + "." + data.field.secondMenu;
                }
                $.ajax({
                    url:"${ctx}/menuManager/addMenu"
                    ,type:"post"
                    ,dataType:"json"
                    ,async:false
                    ,contentType: 'application/json;charset=UTF-8'
                    ,data:JSON.stringify(data.field)
                    ,success:function(o){
                        var msg;
                        if (o.extend.flag == true)
                            msg = "成功！";
                        else
                            msg = "发生错误！";
                        parent.layer.alert(msg, {
                            title: '操作结果'
                        });
                        document.getElementById("addtree-form").reset();
                        $("#firstMenu").hide();
                        $("#secondMenu").hide();
                        renderXtree();
                    }
                });
                layuiRemoveLoading();
                return false;
            });

            /* 添加功能，表单重置 */
            $(document).on('click', '#addtree-form-reset', function() {
                $("#firstMenu").hide();
                $("#secondMenu").hide();
            });

            /* 编辑功能，表单重置 */
            $(document).on('click', '#edittree-form-reset', function() {
                editInit(edito);
            });

            /* 授权管理 */
            $(document).on('click', '#goto_form_edit', function() {
                gotoForm();
            });

            function gotoForm(){
                var menuId = $("#edit-id").val();
                localStorage.setItem("lastChecked",lastChecked);
                parent.layer.open({
                    type: 2 //Page层类型
                    , area: ['90%', '90%']
                    , title: "授权管理"
                    , shade: 0.6 //遮罩透明度
                    , maxmin: true //允许全屏最小化
                    , anim: 1 //0-6的动画形式，-1不开启
                    , content: '${ctxTeacher}/menu/form.jsp?menuId=' + menuId
                    , closeBtn: 1
                    , cancel: function(formindex, layero){
                        showEdit(localStorage.getItem("lastChecked"));
                        localStorage.removeItem("lastChecked");
                    }
                });
            }

        });

        /* 显示遮罩 */
        function layuiLoading(){
            layui.use('layer', function(){
                index = layui.layer.load(0, { shade: [0.5, '#393D49'] });
            });
        }

        /* 关闭遮罩 */
        function layuiRemoveLoading(){
            layui.use('layer', function(){
                layui.layer.close(index);
            });
        }
    </script>
</body>

</html>

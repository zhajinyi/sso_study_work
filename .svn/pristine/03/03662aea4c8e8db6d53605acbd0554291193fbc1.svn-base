<!DOCTYPE html>
<html lang="zh-cn">
<%--
  Created by IntelliJ IDEA.
  User: liupengzheng
  Date: 2019/2/22
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/taglib.jsp"%>
<head>
    <meta charset="utf-8">
    <title>苏州工业园区服务外包学院-学工系统-首页</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${ctxStatic}/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${ctxStatic}/layuiadmin/style/admin.css" media="all">
    <link rel="stylesheet" href="${ctxStatic}/layuiadmin/style/main.css" media="all">
    <style type="text/css"></style>
    <style>
        html {
            background-color: #fff;
        }
        label{
            width: 20%;
        }

        #down{
            width: 683px;
        }
        #treeclass{
            display: flex;
            align-items: center;
        }
    </style>
</head>

<body>
<div class="layui-fluid">
    <form class="layui-form" action="${ctx}/user/org/addExit/orgnaization" lay-filter="component-form-group">
        <input type="hidden" name="parentIds" lay-verify="title" autocomplete="off" class="layui-input">
        <input type="hidden" name="orgGrade" lay-verify="title" autocomplete="off" class="layui-input">

        <div class="layui-form-item">
            <label class="layui-form-label">上级组织名称:</label>
            <div class="layui-input-inline" id="down">
                <div class="layui-unselect layui-form-select downpanel">
                    <div class="layui-select-title">
                        <span class="layui-input layui-unselect" id="treeclass">苏州工业园区服务外包学院</span>
                        <input type="hidden" name="parentId" value="0" id="treeclassId">
                        <i class="layui-edge"></i>
                    </div>
                    <dl class="layui-anim layui-anim-upbit">
                        <dd>
                            <ul id="classtree"></ul>
                        </dd>
                    </dl>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">组织编号：</label>
            <div class="layui-input-block">
                <input type="text" name="id" lay-verify="required" autocomplete="off" placeholder="请输入"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">组织名称：</label>
            <div class="layui-input-block">
                <input type="text" name="orgName" lay-verify="required" autocomplete="off" placeholder="请输入"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">排序：</label>
            <div class="layui-input-block">
                <input type="text" name="sort" lay-verify="required" autocomplete="off" placeholder="请输入"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">机构类型：</label>
            <div class="layui-input-block">
            <select id="orgType" name="orgType" lay-filter="orgType">
                <option value="A">顶级</option>
                <option value="B">二级学院</option>
                <option value="C">其他</option>
            </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">是否启用：</label>
            <div class="layui-input-block">
                <select id="useable" name="useable" lay-filter="useable">
                    <option value="1">已启用</option>
                    <option value="2">未启用</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">备注：</label>
            <div class="layui-input-block">
                <textarea id="remark" name="remark" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>
        <%--<div class="layui-form-item">
            <label class="layui-form-label">状态：</label>
            <div class="layui-input-block">
                <input type="radio" name="sex" value="启用" title="启用">
                <input type="radio" name="sex" value="禁止" title="禁止" checked>
            </div>
        </div>--%>

        <div class="layui-form-item layui-layout-admin btn-sumbit">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-normal" type="button" lay-submit="" lay-filter="class_form_sub">保存</button>
            </div>
        </div>
    </form>
</div>
<script src="${ctxStatic}/layuiadmin/layui/layui.js?t=1"></script>
<script>
    layui.config({
        base: '${ctxStatic}/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'tree', 'table', 'layer', 'form'], function () {
        var $ = layui.$
            , tree = layui.tree
            , fun = layui.fun
            , table = layui.table
            , form = layui.form
            , $body = $('body'); //body
        $.ajax({
            url: '${ctx}/user/org/tree', //模拟接口
            type: 'get',
            dataType: 'json',
            data: {},
            success: TreeFun
        });
        function TreeFun(res){
            tree({
                elem: "#classtree"
                , target: '_blank' //是否新选项卡打开（比如节点返回href才有效）
                , nodes: [res]
                ,click: function (node) {
                    var $select = $($(this)[0].elem).parents(".layui-form-select");
                    $select.removeClass("layui-form-selected").find(".layui-select-title span").html(node.name).end().find("input:hidden[name='parentId']").val(node.id);
                    $("input[name=orgGrade]").val(node.level+1);
                }
            });
        }


        $(".downpanel").on("click", ".layui-select-title", function (e) {
            $(".layui-form-select").not($(this).parents(".layui-form-select")).removeClass("layui-form-selected");
            $(this).parents(".downpanel").toggleClass("layui-form-selected");
            layui.stope(e);
        }).on("click", "dl i", function (e) {
            layui.stope(e);
        });



        //获取url传值
        var theRequest = fun.GetRequest();
        var id = theRequest.id;
        var event = theRequest.event;
        $('input[name=parentIds]').val(event);
        if (id != undefined&&event=="edit") {
            $.ajax({
                "url":"${ctx}/user/org/queryOne",
                'dataType': 'json',
                "data": {"id":id},
                "type":"POST",
                "success":function (re) {
                    var orgOne = re.extend.orgList;
                    $("#treeclassId").val(orgOne.parentId);
                    $.ajax({
                        "url":"${ctx}/user/org/queryOne",
                        'dataType': 'json',
                        "data": {"id":orgOne.parentId},
                        "type":"POST",
                        "success":function (rede) {
                            $("#treeclass").html(rede.extend.orgList.orgName);
                        }
                    });
                    $('input[name=orgName]').val(orgOne.orgName);
                    $('input[name=id]').val(orgOne.id);
                    $('input[name=sort]').val(orgOne.sort);
                    $('#orgType').val(orgOne.orgType);
                    $('#useable').val(orgOne.useable);
                    $("#remark").val(orgOne.remark);
                    $('input[name=id]').attr("disabled","disabled");
                    form.render('select');
                }
            });
        }else {
        }

        /* 表单提交 */
        form.on('submit(class_form_sub)', function (data) {
            var sysDictDto=data.field;
            $.ajax({
                "url":data.form.action,
                'dataType': 'json',
                "data": JSON.stringify(sysDictDto),
                "contentType" : 'application/json',
                "type":"POST",
                "success":function (re) {
                    if(re.extend.falgs.falg2==0){
                        if(re.extend.falgs.falg1){
                            layer.alert('提交成功！', {
                                skin: 'layui-layer-molv' //样式类名  自定义样式
                                ,closeBtn: 1    // 是否显示关闭按钮
                                ,anim: 1 //动画类型
                                ,btn: ['确定'] //按钮
                                ,icon: 6    // icon
                                ,yes:function(){
                                    //window.parent.location.reload();
                                    //当你在iframe页面关闭自身时
                                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                    parent.layer.close(index); //再执行关闭
                                }
                            });
                        }else {
                            layer.alert('提交失败,请联系管理员！', {
                                skin: 'layui-layer-molv' //样式类名  自定义样式
                                ,closeBtn: 1    // 是否显示关闭按钮
                                ,anim: 1 //动画类型
                                ,btn: ['确定'] //按钮
                                ,icon: 6    // icon
                                ,yes:function(){
                                    //当你在iframe页面关闭自身时
                                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                    parent.layer.close(index); //再执行关闭
                                }
                            });
                        }
                    }else{
                        layer.alert('您的业务字典id与之前的重复，请重新输入！');
                        $("input[name=id]").val("");
                    }

                }
            });
            return false;

        });
    })
</script>
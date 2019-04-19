<%--
  Created by IntelliJ IDEA.
  User: 王佳伟
  Date: 2019-3-8
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <title>苏州工业园区服务外包学院-学工系统-角色信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${ctxStatic}/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${ctxStatic}/layuiadmin/style/admin.css" media="all">
    <link rel="stylesheet" href="${ctxStatic}/layuiadmin/style/main.css" media="all">
    <%@ include file="/include/head_include.jsp"%>
</head>
<style>
    .layui-form-label{
        width: 100px;
    }
    .form-text .layui-input-block .layui-textarea{
        width: 505px;
    }
    .layui-form-item{
        width: 100%;
    }
</style>
<body>
<div id="addDiv" style="margin: 15px;">
    <form class="layui-form" action="" lay-filter="component-form-groupp"  id="iframeForm">
        <input type="hidden" name="id" id="id">
        <input type="hidden" id="createBy" name="createBy" value="${sessionScope.user.sysEmpInfoDto.id}">
        <input type="hidden" id="updateBy" name="updateBy" value="${sessionScope.user.sysEmpInfoDto.id}">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">归属机构：</label>
                <div class="layui-input-inline" id="orgName_div">
                    <select id="orgName" name="organizationId" lay-ignore>

                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">角色类型：</label>
                <div class="layui-input-inline">
                    <select name="roleType" id="roleType" style="text-align: center" type="select-one">
                        <option value="" selected>请选择</option>
                        <option value="用户">用户</option>
                        <option value="组织">组织</option>
                    </select>
                </div>

            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">角色名称：</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">英文名称：</label>
                <div class="layui-input-inline">
                    <input type="text" name="enname" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">是否系统数据：</label>
                <div class="layui-input-inline">
                    <select name="isSys" id="isSys" style="text-align: center" type="select-one">
                        <option value="" selected>请选择</option>
                        <option value="是">是</option>
                        <option value="否">否</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">是否可用：</label>
                <div class="layui-input-inline">
                    <select name="useable" id="useable" style="text-align: center" type="select-one">
                        <option value="" selected>请选择</option>
                        <option value="是">是</option>
                        <option value="否">否</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="layui-form-item layui-form-text form-text">
            <label class="layui-form-label">备注：</label>
            <div class="layui-input-block">
                <textarea name="remarks" id="remarks" placeholder="请输入" type="textarea" autocomplete="off" class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item layui-layout-admin btn-sumbit">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="formSubmit" id="formSubmit">保存</button>

            </div>
        </div>
    </form>
</div>

<script src="${ctxStatic}/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '${ctxStatic}/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index', //主入口模块
        commTreeTable:'commTreeTable'
    }).use(['index','commTreeTable', 'form', 'layer'], function () {
        var $ = layui.$
            , $body = $('body')
            , fun = layui.fun
            , layer = layui.layer
            , form = layui.form
            ,commTreeTable = layui.commTreeTable
            ,iframeForm = "iframeForm"
            ,submitBtn = "formSubmit"
            ,formLayFilter = "component-form-group"
            ,tableUrl = '${ctx}/sysRole/roles'
            ,insertUrl = "${ctx}/sysRole/addRole"
            ,updateUrl = "${ctx}/sysRole/updateRole"
            ,theRequest = fun.GetRequest()
            ,type = theRequest.type
            ,id = type == 'update' ? theRequest.id : null
            ,defaultData = {id:id};
        loadSelect2("orgName",'${ctx}/sysRole/findIds');
        if (type == "add")
            addDefaultData();
        else if (type == "update") {
            updateDefaultData();
        }

        //获得当前时间
        function getNowFormatDate() {
            var date = new Date();
            var seperator1 = "-";
            var seperator2 = ":";
            var month = date.getMonth() + 1;
            var strDate = date.getDate();
            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }
            if (strDate >= 0 && strDate <= 9) {
                strDate = "0" + strDate;
            }
            var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
                + " " + date.getHours() + seperator2 + date.getMinutes()
                + seperator2 + date.getSeconds();
            return currentdate;
        }


        function addDefaultData() {
            $("#iframeForm input[name=id]").attr("readonly",false);
            form.verify({id: function(value, item){if(!validateForm(tableUrl,{id:value})){return '编号已存在!';}} });//value：表单的值、item：表单的DOM对象
        }

        function updateDefaultData(){
            $.ajax({
                url: tableUrl,
                data:JSON.stringify(defaultData),
                type: 'post',
                contentType: 'application/json; charset=utf-8',
                success: function(result){
                    if (result.code === 200) {
                        var resObj = result.extend.PageInfo.list[0];
                        setFormWhithSelect(iframeForm,resObj);
                    }
                }
            })
        }

        /**
         * 加载select2插件
         * 结果回调和选中回调名称：formatResult、formatSelection（老版）；
         *                        templateResult、templateSelection（新版）
         */
        function loadSelect2(selId,selUrl,relevant) {
            var selectId = "#"+selId;
            var divId = "#"+selId+"_div";
            $(selectId).select2({
                ajax: {
                    url: selUrl,
                    dataType: 'json',
                    delay: 200,
                    data: function (params) {
                        return {
                            q: params.term
                        };
                    },
                    processResults: function (data) {
                        return {
                            results: data,
                            pagination: {

                            }
                        };
                    },
                    cache: true
                },
                language: "zh-CN",
                placeholder: {id:"",text:"请输入"},
                allowClear: true,//允许清空
                escapeMarkup: function (markup) { return markup; },
                minimumInputLength: 1,
                templateResult: function formatRepo(repo){return repo.text},
                templateSelection: function formatRepoSelection(repo){return repo.text}
            });

            $(selectId).select2("data", relevant);
            //样式美化
//            $(divId+" div:first-child").hide();
            $(divId+" div:last-child").hide();
            $(divId+" span:eq(0)").css('width', '100%');
        }

        /**
         * 解决修改页面select插件赋值问题，
         * 注意：使用select2插件的元素要加上lay-ignore
         * @param iframeForm
         * @param resObj
         */
        function setFormWhithSelect(iframeForm,resObj) {
            $("#"+iframeForm).find("[name]").each(function() {
                var field = $(this).attr("name");//name属性值
                var fieldId = isEmpty($(this).attr("id"))? "" : $(this).attr("id");//id属性值
                var fieldType = $(this).attr("type");//类型
                var fieldSelId = "";//select标签的value值
                var fieldValue = "";//显示的值或select标签的text
                for (var j in resObj){
                    if (isEmpty(fieldId)){
                        if (field === j){
                            fieldValue = isEmpty(resObj[j])? "" : resObj[j];
                            break;
                        }
                    }else{
                        if (fieldId !== field){
                            if (fieldId === j){
                                fieldValue = isEmpty(resObj[j])? "" : resObj[j];
                            }
                            if (field === j){
                                fieldSelId = isEmpty(resObj[j])? "" : resObj[j];
                            }
                        }else {
                            if (field === j){
                                fieldValue = isEmpty(resObj[j])? "" : resObj[j];
                                fieldSelId = fieldValue;
                                break;
                            }
                        }
                    }
                }
                if (fieldType === "checkbox" || fieldType === "radio") {
                    if (isEmpty(fieldValue)){}
                    var radioVal = $('input[name='+field+']').val();
                    if (radioVal === fieldValue){}
                    $('input[name='+field+']').eq(fieldValue).prop("checked",true);
                    $('input[name='+field+']').eq(radioVal).removeAttr("checked");
                    $('input[name='+field+']').eq(fieldValue).click();


                } else if( "text"=== fieldType || "hidden" === fieldType ){
                    $('input[name='+field+']').val(fieldValue);
                }else if( "textarea"=== fieldType ){
                    $('textarea[name='+field+']').val(fieldValue);
                }else if("select-one" === fieldType) {
                    if ('gender' === field) {
                        fieldValue = 'm' === fieldValue ? "男" : "女";
                    }
                    $("#" + fieldId).val(fieldSelId).trigger("change");
                    form.render('select');
                }else {
                    $("#"+fieldId).html('<option value=' + fieldSelId + '>' + fieldValue + '</option>').trigger("change");
                    form.render('select');

                }

            })
        }
        function validateForm(requestUrl,data){
            var flag = false;
            $.ajax({
                url: requestUrl,
                data: JSON.stringify(data),
                async: false,
                type: "post",
                contentType: "application/json;charset=utf-8",
                success: function(res){
                    flag = res.extend.PageInfo.list.length == 0 ? true : false;
                }
            })
            return flag;
        }


        /* 监听提交 */
        form.on('submit('+submitBtn+')', function (data) {
            var index = parent.layer.getFrameIndex(window.name);
            var flagMsg = type == "add" ?  "提交错误" : "修改错误";
            var flag = saveData(type,data.field);
            if(flag){
                parent.layer.close(index);
            }else {
                parent.layer.msg("出错", {
                    time: 5000, //20s后自动关闭
                    content:flagMsg
                })
            }

        });

        function saveData(type,data){
            var requestUrl = type == 'add' ?  insertUrl : updateUrl;
            var result;
            $.ajax({
                url: requestUrl,
                data: JSON.stringify(data),
                async: false,
                type: 'post',
                contentType: 'application/json;charset=utf-8',
                success: function(res){
                    result = res;
                }
            });
            return result;
        }





    });
</script>
</body>

</html>

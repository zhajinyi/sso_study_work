<%--
  Created by IntelliJ IDEA.
  User: BaoLing
  Date: 2019/2/26
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/taglib.jsp"%>
<html>
<head>
    <title>学生信息管理</title>
    <%@ include file="/include/head_include.jsp"%>
</head>
<style>
    .layui-form-label{
        text-align: left;
    }
    .layui-form-item{
        text-align: center;
    }
    input,select{
        padding-left: 0px;
        text-align: center;
    }
    .layui-input{
        padding-left: 0px;
        text-align: center;
    }
</style>
<body>
<div class="layui-fluid" id="divForQuery">
    <div class="layui-card">
        <div class="layui-card-body" style="padding: 0px 15px;" >
            <form class="layui-form" lay-filter="component-form-group" id="iframeForm">
                <div class="layui-form-item">
                    <%--学生基本信息--%>
                    <fieldset class="layui-elem-field layui-field-title">
                        <legend>学生信息</legend>
                    </fieldset>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">学号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="id" placeholder="请输入完整学号" autocomplete="off" class="layui-input" lay-verify="id" style="text-align: center">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label" >姓名</label>
                            <div class="layui-input-inline" id="empName_div">
                                <input type="text" name="empName" id="empName" placeholder="请输入" autocomplete="off" class="layui-input" lay-verify="required">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">性别</label>
                            <div class="layui-input-inline" id="gender_div">
                                <%--<input type="radio" name="gender" value="m" title="男" checked>--%>
                                <%--<input type="radio" name="gender" value="f" title="女">--%>
                                <select name="gender" id="gender" style="text-align: center" type="select-one">
                                    <option value="" selected>请选择</option>
                                    <option value="m">男</option>
                                    <option value="f">女</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">所在班级</label>
                            <div class="layui-input-inline" id="className_div">
                                <select name="classCode" id="className" lay-ignore>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">所在学院</label>
                            <div class="layui-input-inline" id="orgName_div">
                                <select name="orgId" id="orgName" lay-ignore>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">专业</label>
                            <div class="layui-input-inline" id="specialtyName_div">
                                <select name="specialtyCode" id="specialtyName" lay-ignore>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <div class="layui-inline">
                                <label class="layui-form-label">入学年份</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="entryYear" id="entryYear" placeholder="请输入,例如2018" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">辅导员</label>
                            <div class="layui-input-inline" id="instructorName_div">
                                <select name="instructorCode" id="instructorName" lay-ignore>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">年级</label>
                            <div class="layui-input-inline">
                                <select name="grade" lay-filter="grade" style="text-align: center" id="grade" type="select-one">
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">宿舍号</label>
                            <div class="layui-input-inline" id="dromCode_div">
                                <select name="dromCode" id="dromCode"  lay-ignore>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">证件类型</label>
                            <div class="layui-input-inline">
                                <input type="text" name="cardType" id="cardType" placeholder="请输入" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">证件号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="cardNo" id="cardNo" placeholder="请输入" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">银行卡号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="bankCard" id="bankCard" placeholder="请输入完整银行卡号" autocomplete="off" class="layui-input" >
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">学籍</label>
                            <div class="layui-input-inline">
                                <%--<input type="radio" name="shoolRoll" value="有" title="有" checked>--%>
                                <%--<input type="radio" name="shoolRoll" value="无" title="无">--%>
                                    <select name="shoolRoll" id="shoolRoll" style="text-align: center" type="select-one">
                                        <option value="" selected>请选择</option>
                                        <option value="有">有</option>
                                        <option value="无">无</option>
                                    </select>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">是否在校</label>
                            <div class="layui-input-inline">
                                <%--<input type="radio" name="onShool" value="是" title="是" checked>--%>
                                <%--<input type="radio" name="onShool" value="否" title="否">--%>
                                    <select name="onShool" id="onShool" style="text-align: center" type="select-one">
                                        <option value="" selected>请选择</option>
                                        <option value="是">是</option>
                                        <option value="否">否</option>
                                    </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">入学分数</label>
                            <div class="layui-input-inline">
                                <input type="text" name="entryScore" id="entryScore" placeholder="请输入" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">备注</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="remark" id="remark" placeholder="请输入" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"></label>
                                <div class="layui-input-inline">
                                </div>
                            </div>
                        </div>
                    <div class="layui-form-item layui-layout-admin">
                        <div class="layui-input-block">
                            <div class="layui-footer" style="left: 0;">
                                <button class="layui-btn" lay-submit lay-filter="formSubmit" id="formSubmit">保存</button>
                                <button class="layui-btn layui-btn-primary" type="button" id="close">关闭</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="${ctxStatic}/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '${ctxStatic}/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index', //主入口模块
        commTreeTable:'commTreeTable'
    }).use(['index','commTreeTable', 'form', 'layer'], function () {
        var $ = layui.$,
            $body = $('body'),
            fun = layui.fun,
            layer = layui.layer,
            form = layui.form,
            commTreeTable = layui.commTreeTable,
            iframeForm = "iframeForm",
            submitBtn = "formSubmit",
            formLayFilter = "component-form-group",
            tableUrl = '${ctx}/stu/queryByParams',
            insertUrl = "${ctx}/stu/insertOneStudentInfo",
            updateUrl = "${ctx}/stu/updateOneStudentInfo",
            theRequest = fun.GetRequest(),
            type = theRequest.type,
            id = type == 'update' ? theRequest.id : null,
            defaultData = {id:id};
        //指定页面所在元素的id，加载年级选项
        commTreeTable.getSelectGrade("grade");
        loadSelect2("orgName",'${ctx}/user/org/findIds');
        loadSelect2("className",'${ctx}/class/findIds');
        loadSelect2("adviserName",'${ctx}/empInfo/findIds');
        loadSelect2("instructorName",'${ctx}/empInfo/findIds');
        loadSelect2("specialtyName",'${ctx}/stu/speciality/findIds');
        loadSelect2("dromCode",'${ctx}/stu/findDormIdsByAllot');
//        grade = commTreeTable.isEmpty($("#grade  option:selected").val()) ? "" : $("#gradeForm  option:selected").val();
//        var orgName =commTreeTable.isEmpty($("#orgId  option:selected").val()) ? "" : $("#orgId  option:selected").text();


        switch(type){
            case 'add': addDefaultData();
                break;
            case 'update': updateDefaultData();
                break;
            case 'multiSearch':multiSearchDefaultData();
                break;
        };

        function updateDefaultData(){
//            $("#iframeForm input[name=id]").attr("readonly",true);

            $.ajax({
                url: tableUrl,
                data:JSON.stringify(defaultData),
                type: 'post',
                contentType: 'application/json; charset=utf-8',
                success: function(result){
//                    debugger;
                    if (result.code === 200) {
                        var resObj = result.extend.PageInfo.list[0];
                        setFormWhithSelect(iframeForm,resObj);
                    }
                }
            })
        }

        function addDefaultData() {
            $("#iframeForm input[name=id]").attr("readonly",false);

            form.verify({id: function(value, item){if(!validateForm(tableUrl,{id:value})){return '编号已存在!';}} });//value：表单的值、item：表单的DOM对象
        }

        function multiSearchDefaultData(){

            var obj = $("#"+iframeForm).serializeArray();

            for (var i in obj){
                $("#iframeForm input[name="+obj[i].name+"]").removeAttr("lay-verify");
                $("#iframeForm select[name="+obj[i].name+"]").removeAttr("lay-verify");
            }
            $("#formSubmit").html("查询");
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


        $("#close").on("click",function(){parent.layer.closeAll();});




        /* 监听提交 */
        form.on('submit('+submitBtn+')', function (data) {
            var index = parent.layer.getFrameIndex(window.name);
            if(type == 'multiSearch'){
                top.parent.fromIframeData = data.field;
                parent.layer.close(index);
            }else {
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
                    result = res.code == 200 ? true : false;
                }
            });
            return result;
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


                } else if( "text"=== fieldType || "hidden" === fieldType){
                    $('input[name='+field+']').val(fieldValue);
                }else if("select-one" === fieldType){
                    if ( 'gender'=== field){
                        fieldValue =  'm' === fieldValue ? "男" : "女";
                    }
                    $("#"+fieldId).val(fieldSelId).trigger("change");
                    form.render('select');
                }else {
                    if ( 'gender'=== field){
                        fieldValue =  'm' === fieldValue ? "男" : "女";
                    }
                    $("#"+fieldId).html('<option value=' + fieldSelId + '>' + fieldValue + '</option>').trigger("change");
                    //非layui的select要加lay-ignore
                    form.render('select');

                }

            })
        }









    });
</script>
</body>
</html>

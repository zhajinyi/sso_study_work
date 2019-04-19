<%--
  Created by IntelliJ IDEA.
  User: 王佳伟
  Date: 2019-2-27
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/taglib.jsp"%>
<html>
<head>
    <title>奖学金管理</title>
    <%@ include file="/include/head_include.jsp"%>
</head>
<style>
    .layui-form-label{
        text-align: left;
    }
    .layui-form-item{
        text-align: center;
    }
</style>
<body>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-body" style="padding: 15px;">

            <form class="layui-form" lay-filter="component-form-group" id="iframeForm">

                <div class="layui-form-item">


                    <%--查询条件--%>
                    <fieldset class="layui-elem-field layui-field-title">
                        <legend>查询条件</legend>
                    </fieldset>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">学生学号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="empId" placeholder="请输入" autocomplete="off" class="layui-input" lay-verify="id"readonly>
                            </div>
                        </div>

                        <div class="layui-inline">
                            <label class="layui-form-label">学生姓名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="empName" placeholder="请输入" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">所属学院</label>
                            <div class="layui-input-inline" id="orgName_div">
                                <select name="orgId" id="orgName" lay-ignore>
                                </select>
                            </div>
                        </div>

                        <div class="layui-inline">
                            <label class="layui-form-label">所属班级</label>
                            <div class="layui-input-inline">
                                <div class="layui-input-inline" id="className_div">
                                    <select name="classId" id="className" lay-ignore>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">学生性别</label>
                            <div class="layui-input-inline">
                                <input type="radio" name="sex" value="男" title="男">
                                <input type="radio" name="sex" value="女" title="女">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <label class="layui-form-label">政治面貌</label>
                            <div class="layui-input-inline">
                                <input type="text" name="political" placeholder="请输入" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>


                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">奖学金类型</label>
                            <div class="layui-input-inline">
                                <select name="bursaryType" lay-filter="professional">
                                    <option value="">全部</option>
                                    <option value="1">一等奖学金</option>
                                    <option value="2">二等奖学金</option>
                                    <option value="3">三等奖学金</option>
                                    <option value="4">企业奖学金</option>
                                </select>
                            </div>
                        </div>

                        <div class="layui-inline">
                            <label class="layui-form-label">评奖时间</label>
                            <div class="layui-input-inline">
                                <input type="text" name="bursaryTime" placeholder="请输入" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">学科加权分</label>
                            <div class="layui-input-inline">
                                <input type="text" name="xueKeJiaQuanFen" placeholder="请输入" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <label class="layui-form-label">综合素养成绩</label>
                            <div class="layui-input-inline">
                                <input type="text" name="zongHeSuYangFen" placeholder="请输入" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">综合测评</label>
                            <div class="layui-input-inline">
                                <input type="text" name="zongHeFen" placeholder="请输入" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <label class="layui-form-label">备注</label>
                            <div class="layui-input-inline">
                                <textarea name="remark" placeholder="请输入" class="layui-textarea"></textarea>
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
    }).use(['index', 'form', 'layer'], function () {
        var $ = layui.$,
            $body = $('body'),
            fun = layui.fun,
            layer = layui.layer,
            form = layui.form,
            iframeForm = "iframeForm",
            submitBtn = "formSubmit",
            formLayFilter = "component-form-group",
            tableUrl = "${ctx}/bursary/all",
            insertUrl = "",
            updateUrl = "",
            theRequest = fun.GetRequest(),
            type = theRequest.type,
            id = type == 'update' ? theRequest.id : null,
            defaultData = {id:id};

        loadSelect2("orgName",'${ctx}/user/org/findIds');
        loadSelect2("className",'${ctx}/class/findIds');

        multiSearchDefaultData();
        function multiSearchDefaultData(){
            $("#iframeForm input[name=empId]").attr("readonly",false);
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
    });
</script>
</body>
</html>

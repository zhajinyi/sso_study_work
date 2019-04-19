<%--
  Created by IntelliJ IDEA.
  User: ZhaJinyi
  Date: 2019-3-12
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/taglib.jsp"%>
<html>
<head>
    <title>多元选择</title>
    <%@ include file="/include/head_include.jsp"%>
</head>
<body>
<div>
    <form class="layui-form" lay-filter="test-form">
        <select name="functions" xm-select="selectId">
            <option value="view" selected="selected">view</option>
            <option value="add">add</option>
            <option value="update">update</option>
            <option value="delete">delete</option>
        </select>

        <button class="layui-btn" lay-submit lay-filter="formSubmit" id="formSubmit">提交</button>
    </form>
</div>
<script src="${ctxStatic}/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '${ctxStatic}/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index', //主入口模块
        formSelects:'formSelects-v4'
    }).use(['index', 'formSelects', 'form'], function () {
        var formSelects = layui.formSelects,
            form = layui.form;

        /* 监听提交 */
        form.on('submit(formSubmit)', function (data) {
            data.field.functions = data.field.functions.replace(new RegExp(",","g"),":");
        });
    });
</script>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: 王佳伟
  Date: 2019-2-19
  Time: 14:55
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
<div id="addClass">
    <form class="layui-form" action="" lay-filter="component-form-group">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">工号：</label>
                <div class="layui-input-inline">
                    <input type="tel" name="empId" lay-verify="required|phone" autocomplete="off" class="layui-input" disabled="disabled">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">真实姓名：</label>
                <div class="layui-input-inline">
                    <input type="text" name="realname" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">昵称：</label>
                <div class="layui-input-inline">
                    <input type="text" name="nickname" autocomplete="off" class="layui-input" disabled="disabled">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">性别：</label>
                <div class="layui-input-inline">
                    <select name="gender" lay-filter="monitorname">
                        <option value="m">男</option>
                        <option value="f">女</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">部门名称：</label>
                <div class="layui-input-inline">
                    <select id="orgname" lay-filter="orgnamechange">
                        <option value=""></option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">部门编号：</label>
                <div class="layui-input-inline">
                    <input type="text" name="orgId" autocomplete="off" class="layui-input" disabled="disabled">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">出生日期：</label>
                <div class="layui-input-inline">
                    <input type="text" name="birthdate" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">排序：</label>
                <div class="layui-input-inline">
                    <input type="text" name="sortno" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">证件类型：</label>
                <div class="layui-input-inline">
                    <select name="cardtype" lay-filter="monitorname">
                        <option value="id">身份证</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">证件编号：</label>
                <div class="layui-input-inline">
                    <input type="text" name="cardno" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">个人手机：</label>
                <div class="layui-input-inline">
                    <input type="text" name="mobileno" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">家庭电话：</label>
                <div class="layui-input-inline">
                    <input type="text" name="telephone" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">家庭住址：</label>
                <div class="layui-input-inline">
                    <input type="text" name="familyAddress" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">故乡：</label>
                <div class="layui-input-inline">
                    <input type="text" name="nativePlace" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">个人邮箱：</label>
                <div class="layui-input-inline">
                    <input type="text" name="email" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">传真：</label>
                <div class="layui-input-inline">
                    <input type="text" name="faxno" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">QQ：</label>
                <div class="layui-input-inline">
                    <input type="text" name="qq" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">微博：</label>
                <div class="layui-input-inline">
                    <input type="text" name="weibo" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">家庭邮编：</label>
                <div class="layui-input-inline">
                    <input type="text" name="postcode" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">民族：</label>
                <div class="layui-input-inline">
                    <input type="text" name="nation" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">政治面貌：</label>
                <div class="layui-input-inline">
                    <input type="text" name="politicsStatus" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">国籍：</label>
                <div class="layui-input-inline">
                    <input type="text" name="nationality" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">学历：</label>
                <div class="layui-input-inline">
                    <input type="text" name="degree" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">特长：</label>
                <div class="layui-input-inline">
                    <input type="text" name="specialty" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item layui-form-text form-text">
            <label class="layui-form-label">备注：</label>
            <div class="layui-input-block">
                <textarea name="remark" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item layui-layout-admin btn-sumbit">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="class_form_sub">保存</button>
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
    }).use(['index', 'form', 'layer'], function () {
        var $ = layui.$
            , $body = $('body')
            , fun = layui.fun
            , layer = layui.layer
            , form = layui.form;

        //获取url传值
        var theRequest = fun.GetRequest();
        var id = theRequest.id;
        //点击添加按钮弹出弹窗，动态获取下来列表中的下来数据
        fun.AjaxData('json/stu/stu_manage.js', 'get', {}, successData);
        function successData(res) {
            if (res.code === 0) {
                $.ajax({
                   url:"${ctx}/sysemp/getbyid"
                    ,type:"POST"
                    ,dataType:"json"
                    ,data:{"id":id}
                    ,success:function(o){
                        $("input[name=empId]").val(o.empId);
                        $("input[name=realname]").val(o.realname);
                        $("input[name=nickname]").val(o.nickname);
                        $("select[name=gender]").val(o.gender);
                        $("input[name=orgId]").val(o.orgId);
                        $("input[name=birthdate]").val(o.birthdate);
                        $("input[name=sortno]").val(o.sortno);
                        $("select[name=cardtype]").val(o.cardtype);
                        $("input[name=cardno]").val(o.cardno);
                        $("input[name=mobileno]").val(o.mobileno);
                        $("input[name=telephone]").val(o.telephone);
                        $("input[name=familyAddress]").val(o.familyAddress);
                        $("input[name=nativePlace]").val(o.nativePlace);
                        $("input[name=email]").val(o.email);
                        $("input[name=faxno]").val(o.faxno);
                        $("input[name=qq]").val(o.qq);
                        $("input[name=weibo]").val(o.weibo);
                        $("input[name=postcode]").val(o.postcode);
                        $("input[name=nation]").val(o.nation);
                        $("input[name=politicsStatus]").val(o.politicsStatus);
                        $("input[name=nationality]").val(o.nationality);
                        $("input[name=degree]").val(o.degree);
                        $("input[name=specialty]").val(o.specialty);
                        $("textarea[name=remark]").val(o.remark);
                        getOrgData();
                        $("#orgname").val($("input[name=orgId]").val());
                        form.render('select');
                    }
                });
                form.render(null, 'component-form-group');
                DefaultData();
            }
        }
        //点击表格中的修改时，得到相应的id如果id部位undfined，则将改用户对应的数据显示到表单中
        function DefaultData() {
            if (id !== 'undefined') {
                fun.AjaxData('json/stu/stu_manage.js', 'get', { id: id }, successData1);
                function successData1(res) {
                    form.val('component-form-group', {
                        'facuanddep': res.data[1].id
                        , 'professional': 1
                    });
                }
            }
        }

        function getOrgData(){
            $.ajax({
                url:"${ctx}/sysemp/allorg"
                ,type:"post"
                ,dataType:"json"
                ,async:false
                ,contentType: 'application/json;charset=UTF-8'
                ,data:{}
                ,success:function(o){
                    var orgArray = new Array();
                    orgArray = o;
                    var html = "";
                    debugger;
                    for (var i = 0; i < orgArray.length; i++) {
                        if (orgArray[i].id != 0){
                            html += "<option value='" + orgArray[i].id + "'>" + orgArray[i].orgName + "</option>";
                        }
                    }
                    $("#orgname").html(html);
                }
            });
        }

        form.on("select(orgnamechange)", function(data){
            $("input[name=orgId]").val(data.value);
        });

        /* 监听提交 */
        form.on('submit(class_form_sub)', function (data) {
            data.field.id = id;
            var sysEmpInfoDto = {};
            sysEmpInfoDto = data.field;
            $.ajax({
                url:"${ctx}/sysemp/update"
                ,type:"post"
                ,dataType:"json"
                ,async:false
                ,contentType: 'application/json;charset=UTF-8'
                ,data:JSON.stringify(sysEmpInfoDto)
                ,success:function(flag){
                    parent.layer.closeAll();
                    var msg;
                    if (flag == "0")
                        msg = "数据出错了！";
                    else if (flag == "1")
                        msg = "修改成功！";
                    else if (flag == "2")
                        msg = "修改失败！";
                    else
                        msg = "发生了未知错误！";
                    parent.layer.alert(msg, {
                        title: '提交结果'
                    })
                }
            });
            return false;
        });
    });
</script>
</body>

</html>

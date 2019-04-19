<%--
  Created by IntelliJ IDEA.
  User: liupengzheng
  Date: 2019/2/15
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/taglib.jsp"%>
<html>
<head>
    <title>苏州工业园区服务外包学院-学工系统-首页</title>
    <%@ include file="/include/head_include.jsp"%>
</head>
<body>
<div class="layui-fluid" id="LAY-component-grid-mobile-pc">
    <div class="layui-row layui-col-space10">
        <div class="layui-col-xs4 layui-col-md2">
            <%--年级搜索表单--%>
            <div class="layui-form-item">
                <form class="layui-form" id="gradeForm">
                    <label class="layui-form-label" style="text-align: center">年级</label>
                    <div class="layui-input-block">
                        <select name="grade" lay-filter="grade" id="grade" >
                        </select>
                    </div>
                </form>
            </div>
            <%--组织架构树形--%>
            <div class="layui-card">
                <div class="layui-card-body">
                    <ul id="tree"></ul>
                </div>
            </div>
        </div>
        <div class="layui-col-xs8 layui-col-md10">
            <!-- 填充内容 -->
            <div class="layui-card main-section">
                <div class="layui-card-body">
                    <table id="tableData" lay-filter="tableData"></table>
                </div>
            </div>
        </div>
    </div>
</div>
<!--表格头部自定义控件-->
<script type="text/html" id="tableToolbar">
    <div class="layui-row">
        <form class="layui-form" id="toolbarForm">
            <%--隐藏域--%>
            <input type="hidden" name="orgid">
            <input type="hidden" name="grade">
            <input type="hidden" name="classid">
            <input type="hidden" name="orgname">
            <input type="hidden" name="className">
            <input type="hidden" name="phone">
            <input type="hidden" name="cardId">
            <input type="hidden" name="excellYear">
            <input type="hidden" name="excellclassTime">
            <input type="hidden" name="mainStrong">
            <div class="layui-col-md4">
                <label class="layui-form-label" style="width: 80px">工号</label>
                <div class="layui-input-block" style="margin-left: 110px">
                    <input type="text" name="empcode" lay-verify="" placeholder="" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-col-md4">
                <label class="layui-form-label" style="width: 80px">班主任姓名</label>
                <div class="layui-input-block" style="margin-left: 110px">
                    <input type="text" name="empname" lay-verify=""  placeholder="" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-col-md2" style="text-align: right">
                <button class="layui-btn layui-btn-normal layui-btn-sm" lay-event="search">查询</button>
                <button class="layui-btn layui-btn-primary layui-btn-sm" type="reset">重置</button>
            </div>
        </form>
        <div class="layui-col-md2" style="text-align: right">
            <button class="layui-btn layui-btn-primary layui-btn-sm layui-btn-radius" lay-event="multiSearch">自定义查询</button>
        </div>
    </div>
    <div class="layui-row" style="padding: 10px 0px 0px 30px">
        <div class="layui-btn-group" style="text-align: left;visibility: hidden;">
            <button class="layui-btn layui-btn-sm" lay-event="add">新增</button>
            <button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="update">修改</button>
            <button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delete">删除</button>
        </div>
    </div>
</script>

<!-- 表格操作 -->
<%--<script type="text/html" id="tableOperation">
    <a class="layui-table-link" href="javascript:;" lay-event="edit">编辑</a>
    <a class="layui-table-link btn-danger" href="javascript:;" lay-event="del">删除</a>
</script>--%>
<script src="${ctxStatic}/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '${ctxStatic}/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
        ,commTreeTable:'commTreeTable'
    }).use(['index', 'tree', 'table', 'layer', 'form', 'commTreeTable'], function () {
        /*定义tree、table相关的参数*/
        var $ = layui.$,
            tree = layui.tree,
            table = layui.table,
            layer = layui.layer,
            form = layui.form,
            commTreeTable = layui.commTreeTable,
            treeUrl = "${ctx}/excellClass/tree",
            treeElement = "tree",
            tableUrl = "${ctx}/excellClass/all",
            delTableUrl = "",
            tableElement = "tableData",
            tableId = "tableDataID",
            tableToolbar = "tableToolbar",
            toolbarForm = "toolbarForm",
            formUrl = "${ctx}/models/teacher/studentAward/excellClass_form.jsp";
         var cols = [[
            { field: 'empcode', title: '工号', sort: true, align: 'center' }
            , { field: 'empname',title: '姓名', align: 'center' }
            , { field: 'orgname', title: '所属学院', align: 'center',sort: true }
            , { field: 'className', title: '所属班级', align: 'center',sort: true }
            , { field: 'phone', title: '电话',sort: true, align: 'center' }
            , { field: 'cardId', title: '卡号', sort: true, align: 'center' }
            , { field: 'excellYear', title: '获奖学年', align: 'center' }
            , { field: 'excellclassTime', title: '获奖时间', sort: true, align: 'center',templet:function(d){return layui.util.toDateString(d.excellclassTime, 'yyyy-MM-dd');}}
            , { field: 'mainStrong', title: '主要事迹', align: 'center' }
            /*, { fixed: 'right', title: '操作', width: 100, align: 'center', toolbar: '#tableOperation' }*/ //这里的toolbar值是模板元素的选择器
        ]];

        var grade = commTreeTable.isEmpty($("#grade  option:selected").val()) ? "" : $("#grade  option:selected").val();
        $("input[name='grade']").val(grade);

        var nodeid;

        Object.defineProperty(window.clickNode,'id', {
            get: function(){},//取值的时候会触发
            set: function(value){nodeid = value;}//更新值的时候会触发
        });

        Object.defineProperty(window.clickNode,'level', {
            get: function(){},//取值的时候会触发
            set: function(value) {
                if (value == 2) {
                    $("input[name='classid']").val(nodeid);
                    $("input[name='orgid']").val('');
                } else if (value == 1) {
                    $("input[name='orgid']").val(nodeid);
                    $("input[name='classid']").val('');
                } else {
                    $("input[name='orgid']").val('');
                    $("input[name='classid']").val('');
                }//更新值的时候会触发
            }
        });

        commTreeTable.getSelectGrade("grade");
        commTreeTable.getCommTree(treeElement,treeUrl,null,tableId,toolbarForm,tableUrl); //加载tree对象初始化方法；
        commTreeTable.getCommTable(tableElement, tableUrl,tableId,cols,tableToolbar);
        commTreeTable.listenToolbar(table,tableElement,tableId,tableUrl,toolbarForm,delTableUrl,formUrl);

        form.on('select(grade)', function(obj){
            $("input[name='orgname']").val('');
            $("input[name='className']").val('');
            $("input[name='orgid']").val('');
            $("input[name='classid']").val('');
            grade = obj.value;
            $("input[name='grade']").val(grade);
            commTreeTable.Search(toolbarForm,tableId,tableUrl);
        })

    });
   /* var commTreeUrl,commTreeElem,
        commTableUrl,commTableId,commTableElem,cols,
        tableToolbar,toolbarForm,grade,formUrl,delTableUrl;
    //指定页面所在元素的id，加载年级选项
    commTreeTable.getSelectGrade("grade");
    grade = commTreeTable.isEmpty($("#grade  option:selected").val()) ? "" : $("#grade  option:selected").val();
    $("input[name='orgName']").val(grade);
    //初始化参数
    tableToolbar = "tableToolbar";
    toolbarForm = "toolbarForm";
    formUrl ="/models/teacher/class_manage/form.jsp";
    $.ajax({
        url: '/excellClass/tree', //模拟接口
        type: 'get',
        dataType: 'json',
        data: {},
        success: TreeFun
    })
    function TreeFun(res) {
        tree({
            elem: '#tree'
            , target: '_blank' //是否新选项卡打开（比如节点返回href才有效）
            , nodes: [res]
            , click: function (node) {
                console.log(node.id); //node即为当前点击的节点数据
                table.reload('tableDataID', { //此处是上文提到的 初始化标识id
                    where: {
                        orgid: node.id,
                        excellYear:null,empname:null,classname:null
                    }
                });

            }
        });
    }
    function Table(c) {
        table.render({
            elem: '#tableData'
            , url: '/excellClass/all'
            , toolbar: '#tableToolbar'
            ,id:'tableDataID'
            ,contentType: "application/json"
            ,method: "post"
            ,where: {
                orgid: "0"
            }
            ,request: {
                pageName: 'currentPage',
                limitName: 'pageSize',
            },
            parseData: function(result){ //result 即为原始返回的数据
                return {
                    "code":result.code, //解析接口状态
                    "msg": result.msg, //解析提示文本
                    "count": result.extend.PageInfo.total, //解析数据长度
                    "data": result.extend.PageInfo.list //解析数据列表
                };
            },
            response: {
                statusCode: 200 //重新规定成功的状态码为 200，table 组件默认为 0
            }
            , page: {
                layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
                , 'prev': '上一页'
                , 'next': '下一页'
                , 'limits': [10, 20, 30, 40, 50]
                , jump: function (obj) {
                    console.log(obj)
                }
            }
            , loading: true
            , cellMinWidth: 120
            , defaultToolbar: ['filter','exports']
            , cols: [[
                { field: 'empcode', title: '工号', sort: true, align: 'center' }
                , { field: 'empname',title: '姓名', align: 'center' }
                , { field: 'orgname', title: '所属学院', align: 'center',sort: true }
                , { field: 'className', title: '所属班级', align: 'center',sort: true }
                , { field: 'phone', title: '电话',sort: true, align: 'center' }
                , { field: 'cardId', title: '卡号', sort: true, align: 'center' }
                , { field: 'excellYear', title: '获奖学年', align: 'center' }
                , { field: 'excellclassTime', title: '获奖时间', sort: true, align: 'center',templet:function(d){return layui.util.toDateString(d.excellclassTime, 'yyyy-MM-dd');}}
                , { field: 'mainStrong', title: '主要事迹', align: 'center' }
                /!*, { fixed: 'right', title: '操作', width: 100, align: 'center', toolbar: '#tableOperation' }*!/ //这里的toolbar值是模板元素的选择器
            ]]
        });
    }

    $=layui.jquery;
    $(document).on('click','.search',function(){
        var excellYear = $("input[name='excellYear']").val();
        var empname = $("input[name='empname']").val();
        var classname = $("input[name='classname']").val();
        search(excellYear,empname,classname);
    });
    function search(excellYear,empname,classname){
        excellYear=(excellYear==""?null:excellYear);
        empname=(empname==""?null:empname);
        classname=(classname==""?null:classname);
        var sisoExcellclassInfoDto = {excellYear:excellYear,empname:empname,classname:classname};
        table.reload('tableDataID', { //此处是上文提到的 初始化标识id
            url: '/excellClass/all'
            ,where:sisoExcellclassInfoDto
        });
        $("input[name='excellYear']").val(excellYear == null ? "" : excellYear);
        $("input[name='empname']").val(empname == null ? "" : empname);
        $("input[name='classname']").val(classname == null ? "" : classname);
    }

    Table(0);

    //监听行工具事件
    table.on('tool(tableData)', function (obj) {
        var data = obj.data;
        //console.log(obj)
        if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                obj.del();
                layer.close(index);
            });
        } else if (obj.event === 'edit') {
            EditLayer("修改班级", data);
        }
    });
    //监听选项卡的更多操作
    $body.on('click', '.btn-add', function (elem) {
        EditLayer();
    })
    function EditLayer() {
        parent.layer.open({
            type: 2 //Page层类型
            , area: ['700px', '500px']
            , title: '添加班级'
            , shade: 0.6 //遮罩透明度
            , maxmin: true //允许全屏最小化
            , anim: 1 //0-6的动画形式，-1不开启
            , content: 'class_manage/add_edit.html?id=' + data.id
        });
    }*/
</script>
</body>
</html>

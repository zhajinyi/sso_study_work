<%--
  Created by IntelliJ IDEA.
  User: baoling
  Date: 2019/1/21
  Time: 18:12
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

    <script>
        /^http(s*):\/\//.test(location.href) || alert('请先部署到 localhost 下再访问');
    </script>
</head>

<body class="layui-layout-body">

<div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <!-- 头部区域 -->
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item layadmin-flexible" lay-unselect>
                    <a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
                        <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
                    </a>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="http://www.layui.com/admin/" target="_blank" title="前台">
                        <i class="layui-icon layui-icon-website"></i>
                    </a>
                </li>
                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;" layadmin-event="refresh" title="刷新">
                        <i class="layui-icon layui-icon-refresh-3"></i>
                    </a>
                </li>
            </ul>
            <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right" style="right: 50px;">

                <%--<li class="layui-nav-item" lay-unselect>
                    <a lay-href="app/message/index.html" layadmin-event="message" lay-text="消息中心">
                        <i class="layui-icon layui-icon-notice"></i>

                        <!-- 如果有新消息，则显示小圆点 -->
                        <span class="layui-badge-dot"></span>
                    </a>
                </li>--%>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="theme">
                        <i class="layui-icon layui-icon-theme"></i>
                    </a>
                </li>
                <%--<li class="layui-nav-item layui-hide-xs" lay-unselect>--%>
                    <%--<a href="javascript:;" layadmin-event="note">--%>
                        <%--<i class="layui-icon layui-icon-note"></i>--%>
                    <%--</a>--%>
                <%--</li>--%>
                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;">
                        <cite>${sessionScope.user.sysEmpInfoDto.nickname}</cite>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a lay-href="${ctxStudent}/manage/manageInfo.jsp">基本资料</a></dd>
                        <%--<dd><a lay-href="set/user/password.html">修改密码</a></dd>--%>
                        <hr>
                        <dd layadmin-event="logout" style="text-align: center;"><a href="${ctx}/user/logout">退出</a></dd>
                    </dl>
                </li>


                <li class="layui-nav-ite<%--<li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="about"><i class="layui-icon layui-icon-more-vertical"></i></a>
                </li>--%>m layui-show-xs-inline-block layui-hide-sm" lay-unselect>
                    <a href="javascript:;" layadmin-event="more"><i class="layui-icon layui-icon-more-vertical"></i></a>
                </li>
            </ul>
        </div>

        <!-- 侧边菜单 -->
        <div class="layui-side layui-side-menu">
            <div class="layui-side-scroll">
                <div class="layui-logo" lay-href="${ctxStudent}/home/console.jsp">
                    <span>学工管理</span>
                </div>

                <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">
                    <li data-name="home" class="layui-nav-item">
                        <a lay-href="${ctxStudent}/home/console.jsp" lay-tips="主页" lay-direction="2" class="layui-this">
                            <i class="layui-icon layui-icon-home"></i>
                            <cite>主页</cite>
                        </a>
                    </li>
                    <li data-name="StudentManagement" class="layui-nav-item">
                        <a lay-href="${ctxStudent}/manage/manageInfo.jsp" lay-tips="基本信息" lay-direction="2">
                            <i class="layui-icon layui-icon-component"></i>
                            <cite>基本信息</cite>
                        </a>
                    </li>
                    <li data-name="DormitoryManagement" class="layui-nav-item">
                        <a lay-href="${ctxStudent}/punishment/punishInfo.jsp" lay-tips="我的处罚" lay-direction="2">
                            <i class="layui-icon layui-icon-template"></i>
                            <cite>我的处罚</cite>
                        </a>
                    </li>
                    <li data-name="app" class="layui-nav-item">
                        <a href="javascript:;" lay-tips="奖励管理" lay-direction="2">
                            <i class="layui-icon layui-icon-app"></i>
                            <cite>奖励管理</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a lay-href="${ctxStudent}/reward/stuBursary.jsp">奖学金</a>
                            </dd>
                            <dd>
                                <a lay-href="${ctxStudent}/reward/graduate.jsp">优秀毕业生</a>
                            </dd>
                            <dd>
                                <a lay-href="${ctxStudent}/reward/highGoodStudent.jsp">省级三好学生</a>
                            </dd>
                            <dd>
                                <a lay-href="${ctxStudent}/reward/highStudentCadres.jsp">省级优秀学生干部</a>
                            </dd>
                            <dd>
                                <a lay-href="${ctxStudent}/reward/highAdvancedInfo.jsp">省级先进班集体</a>
                            </dd>
                            <dd>
                                <a lay-href="${ctxStudent}/reward/excelLeader.jsp">校级优秀学生干部</a>
                            </dd>
                            <dd>
                                <a lay-href="${ctxStudent}/reward/excellClass.jsp">校级优秀班集体</a>
                            </dd>
                        </dl>
                    </li>
                    <%--<li data-name="senior" class="layui-nav-item">--%>
                        <%--<a href="javascript:;" lay-tips="系统管理" lay-direction="2">--%>
                            <%--<i class="layui-icon layui-icon-senior"></i>--%>
                            <%--<cite>系统管理</cite>--%>
                        <%--</a>--%>
                        <%--<dl class="layui-nav-child">--%>
                            <%--<dd>--%>
                                <%--<a lay-href="${ctxTeacher}/user/index.html">用户管理</a>--%>
                            <%--</dd>--%>
                            <%--<dd>--%>
                                <%--<a lay-href="javascript:;">菜单管理</a>--%>
                            <%--</dd>--%>
                            <%--<dd>--%>
                                <%--<a lay-href="javascript:;">角色管理</a>--%>
                            <%--</dd>--%>
                            <%--<dd>--%>
                                <%--<a lay-href="javascript:;">组织管理</a>--%>
                            <%--</dd>--%>
                            <%--<dd>--%>
                                <%--<a lay-href="javascript:;">字典管理</a>--%>
                            <%--</dd>--%>
                        <%--</dl>--%>
                    <%--</li>--%>
                </ul>
            </div>
        </div>

        <!-- 页面标签 -->
        <div class="layadmin-pagetabs" id="LAY_app_tabs">
            <div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-down">
                <ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
                    <li class="layui-nav-item" lay-unselect>
                        <a href="javascript:;"></a>
                        <dl class="layui-nav-child layui-anim-fadein">
                            <dd layadmin-event="closeThisTabs"><a href="javascript:;">关闭当前标签页</a></dd>
                            <dd layadmin-event="closeOtherTabs"><a href="javascript:;">关闭其它标签页</a></dd>
                            <dd layadmin-event="closeAllTabs"><a href="javascript:;">关闭全部标签页</a></dd>
                        </dl>
                    </li>
                </ul>
            </div>
            <div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
                <ul class="layui-tab-title" id="LAY_app_tabsheader">
                    <li lay-id="${ctxStudent}/home/console.jsp" class="layui-this"><i class="layui-icon layui-icon-home"></i></li>
                </ul>
            </div>
        </div>


        <!-- 主体内容 -->
        <div class="layui-body" id="LAY_app_body">
            <div class="layadmin-tabsbody-item layui-show">
                <iframe src="${ctxStudent}/home/console.jsp" frameborder="0" class="layadmin-iframe"></iframe>
            </div>
        </div>

        <!-- 辅助元素，一般用于移动设备下遮罩 -->
        <div class="layadmin-body-shade" layadmin-event="shade"></div>
    </div>
</div>

<script src="${ctxStatic}/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '${ctxStatic}/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use('index');
</script>
</body>
</html>

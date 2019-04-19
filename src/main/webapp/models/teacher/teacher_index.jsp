<%--
  Created by IntelliJ IDEA.
  User: ZhaJinyi
  Date: 2019-1-21
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/taglib.jsp"%>

<html>
<head>
    <title>苏州工业园区服务外包学院-学工系统-首页</title>
    <%@include file="/include/head_include.jsp"%>
    <%--<script src="${ctxStatic}/layuiadmin/layui/layui.all.js"></script>--%>
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
                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;" layadmin-event="refresh" title="刷新">
                        <i class="layui-icon layui-icon-refresh-3"></i>
                    </a>
                </li>
            </ul>
            <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right" style="right: 30px;">
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="theme">
                        <i class="layui-icon layui-icon-theme"></i>
                    </a>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="note">
                        <i class="layui-icon layui-icon-note"></i>
                    </a>
                </li>

                <%--用户姓名--%>
                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;">
                        <cite id="userinfo">${sessionScope.user.sysEmpInfoDto.nickname}</cite>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a lay-href="#">修改密码</a></dd>
                        <hr>
                        <dd layadmin-event="logout" style="text-align: center;"><a href="${ctx}/user/logout">退出</a></dd>
                    </dl>
                </li>

               <%-- <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="about"><i class="layui-icon layui-icon-more-vertical"></i></a>
                </li>--%>
                <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-unselect>
                    <a href="javascript:;" layadmin-event="more"><i class="layui-icon layui-icon-more-vertical"></i></a>
                </li>
            </ul>
        </div>

        <!-- 侧边菜单 -->
        <div class="layui-side layui-side-menu">
            <div class="layui-side-scroll">
                <div class="layui-logo" lay-href="home/console.jsp">
                    <span>学工系统</span>
                </div>
                <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">

                    <c:forEach var="item1" items="${requestScope.get('topMenus')}">
                        <shiro:hasPermission name="${item1.id}:view">
                        <li  class="layui-nav-item" id="${item1.id}">
                            <c:if test="${empty item1.href}" var="empty1Href"><a href="javascript:;" lay-tips="${item1.name}" lay-direction="2"></c:if>
                            <c:if test="${not empty1Href}"><a lay-href="${ctxTeacher}${item1.href}" lay-tips="${item1.name}" lay-direction="2"></c:if>
                                <c:if test="${empty item1.icon}" var="emptyIcon"><i class="layui-icon layui-icon-template"></i></c:if>
                                <c:if test="${not emptyIcon}"><i class="layui-icon layui-icon-${item1.icon}"></i></c:if>
                                <cite>${item1.name}</cite>
                            </a>
                            <c:if test="${not empty item1.childrenMenu}">
                                <dl class="layui-nav-child">
                                    <c:forEach var="item2" items="${item1.childrenMenu}">
                                    <shiro:hasPermission name="${item2.id}:view">
                                        <dd data-name="${item2.name}">
                                            <c:if test="${empty item2.href}" var="empty2Href"><a href="javascript:;" >${item2.name}</a></c:if>
                                            <c:if test="${not empty2Href}"><a lay-href="${ctxTeacher}${item2.href}">${item2.name}</a></c:if>
                                            <c:if test="${not empty item2.childrenMenu}">
                                                <dl class="layui-nav-child">
                                                    <c:forEach var="item3" items="${item2.childrenMenu}">
                                                        <shiro:hasPermission name="${item3.id}:view">
                                                        <dd data-name="${item3.name}">
                                                            <c:if test="${empty item3.href}" var="empty3Href"><a href="javascript:;" >${item3.name}</a></c:if>
                                                            <c:if test="${not empty3Href}"><a lay-href="${ctxTeacher}${item3.href}">${item3.name}</a></c:if>
                                                            <c:if test="${not empty item3.childrenMenu}">
                                                                <dl class="layui-nav-child">
                                                                    <c:forEach var="item4" items="${item3.childrenMenu}">
                                                                        <shiro:hasPermission name="${item4.id}:view">
                                                                        <dd data-name="${item4.name}">
                                                                            <c:if test="${empty item4.href}" var="empty4Href"><a href="javascript:;" >${item4.name}</a></c:if>
                                                                            <c:if test="${not empty4Href}"><a lay-href="${ctxTeacher}${item4.href}">${item4.name}</a></c:if>
                                                                            <c:if test="${not empty item4.childrenMenu}">
                                                                                <dl class="layui-nav-child">
                                                                                    <c:forEach var="item5" items="${item4.childrenMenu}">
                                                                                        <shiro:hasPermission name="${item5.id}:view">
                                                                                        <c:if test="${empty item5.href}" var="empty5Href"><a href="javascript:;" >${item5.name}</a></c:if>
                                                                                        <c:if test="${not empty5Href}"><a lay-href="${ctxTeacher}${item5.href}">${item5.name}</a></c:if>

                                                                                        </shiro:hasPermission>
                                                                                    </c:forEach>
                                                                                </dl>
                                                                            </c:if>
                                                                        </dd>
                                                                        </shiro:hasPermission>
                                                                    </c:forEach>
                                                                </dl>
                                                            </c:if>
                                                        </dd>
                                                        </shiro:hasPermission>
                                                    </c:forEach>
                                                </dl>
                                            </c:if>
                                        </dd>
                                    </shiro:hasPermission>
                                    </c:forEach>
                                </dl>
                            </c:if>
                        </li>
                        </shiro:hasPermission>
                    </c:forEach>

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
                    <li lay-id="${ctxTeacher}/home/console.jsp" class="layui-this"><i class="layui-icon layui-icon-home"></i><cite style="padding-left: 5px;font-style: normal;font-size: 14px;">主页</cite></li>
                </ul>
            </div>
        </div>


        <!-- 主体内容 -->
        <div class="layui-body" id="LAY_app_body">
            <div class="layadmin-tabsbody-item layui-show">
                <iframe src="${ctxTeacher}/home/console.jsp" frameborder="0" class="layadmin-iframe"></iframe>
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
    }).use(['index','form','layer'],function () {
        var $ = layui.$
            ,layer = layui.layer;


    });
</script>
</body>
</html>

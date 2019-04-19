<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>学工系统—登录</title>
    <script type="text/javascript">
        <!-- 解决登录页面嵌套问题 -->
        if (top != window ){
            top.location.href = location.href;
        }
    </script>
    <link href="<%= request.getContextPath() %>/static/css/login.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />
</head>
<body>
<div class="header">
    <h1 class="headerLogo">
        <a title="用户登录" target="_blank" >学工系统</a>
    </h1>
    <div class="headerNav">
    </div>
</div>

<div class="banner">

    <div class="login-aside">
        <div id="o-box-up"></div>
        <div id="o-box-down" style="table-layout: fixed;">
            <div id="errmsg" class="error-box"></div>
            ${msg}
            <form class="logonform" action="${pageContext.request.contextPath}/user/loginRequest" method="post">
                <div class="fm-item">
                    <label  class="form-label"> 用户名：</label>
                    <input type="text" name="id" class="i-text" tabindex="1"/>
                    <div class="ui-form-explain"></div>
                </div>
                <div class="fm-item">
                    <label  class="form-label"> 密码：</label>
                    <input type="password" name="password" class="i-text" tabindex="3"/>
                    <div class="ui-form-explain"></div>
                </div>
                <div class="fm-item">
                    <label class="form-label"></label>
                    <input type="submit"  class="btn-login" value="" tabindex="3"/>
                    <div class="ui-form-explain"></div>
                </div>

            </form>

        </div>

    </div>

    <div class="bd">
        <ul>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li style="background-color:#BCE0FF"></li>
        </ul>
    </div>

    <div class="hd">
        <ul></ul>
    </div>
</div>

<div class="banner-shadow"></div>
<div class="footer">
    <p>Copyright © 苏州工业园区服务外包职业学院 2018, All Rights Reserved 苏州市工业园区科教创新区若水路99号</p>
</div>
</body>
</html>
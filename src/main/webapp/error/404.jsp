<%--
  Created by IntelliJ IDEA.
  User: ZhaJinyi
  Date: 2019-1-3
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统错误</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>
<style>
    *{
        padding:0;
        margin:0
    }
    a{
        text-decoration:none;
    }
    .notfoud-container .img-404{
        height:155px;
        text-align: center;
        -webkit-background-size:150px auto;
        margin-top:100px;
        margin-bottom:40px;
    }
    .img-404 img {
        width: 180px;
    }
    .notfoud-container .notfound-p{
        line-height:22px;
        font-size:17px;
        padding-bottom:15px;
        border-bottom:1px solid #f6f6f6;
        text-align:center;
        color:#262b31;
    }
    .notfoud-container .notfound-reason{
        color:#9ca4ac;
        font-size:18px;
        line-height:18px;
        text-align:left;
        width:270px;
        margin:0 auto;
    }
    .notfoud-container .notfound-reason p{
        margin-top:13px;
    }
    .notfoud-container .notfound-reason ul li{
        margin-top:10px;
        margin-left:36px
    }
    .notfoud-container .notfound-btn-container{
        margin:40px auto 0;
        text-align:center;
    }
    .notfoud-container .notfound-btn-container .notfound-btn{
        display:inline-block;
        border:1px solid #ebedef;
        background-color:#239bf0;
        color:#fff;font-size:15px;
        border-radius:5px;
        text-align:center;
        padding:10px;
        line-height:16px;
        white-space:nowrap
    }
</style>
<body>

<div class="notfoud-container">
    <div class="img-404">
        <img src="<%=request.getContextPath()%>/static/img/404.png">
    </div>
    <p class="notfound-p">哎呀迷路了...</p>
    <div class="notfound-reason">
        <p>可能的原因：</p>
        <ul>
            <li>原来的页面不存在了</li>
            <li>系统出错，请联系管理员</li>
            <li>我们的服务器被外星人劫持了</li>
        </ul>
    </div>
    <div class="notfound-btn-container">
        <a class="notfound-btn" href="http://127.0.0.1:8080/stuWork/">返回首页</a>
    </div>
</div>

</body>
</html>

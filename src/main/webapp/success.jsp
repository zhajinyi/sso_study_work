<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
恭喜您，登录成功！
欢迎${sessionScope.user.userId}<br/>
当前您的用户信息为：<br/>
用户名：${user.userId}
密码：${user.password}
<form action="${pageContext.request.contextPath}/user/logout" method="post">
    <input type="submit" value="注销" />
</form>
</body>
</html>
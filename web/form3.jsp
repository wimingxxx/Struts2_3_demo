<%--
  Created by IntelliJ IDEA.
  User: qiwenming
  Date: 2017/9/20
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>模型接收参数测试</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/params3Action" method="post">
    用户名：<input type="text" name="name"/><br>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: 15351
  Date: 2023/1/14
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link href="css/login.css" rel="stylesheet">
</head>

<body>
<div id="loginDiv" style="height: 350px" >
    <form action="/brand-demo/loginServlet" id="form" >
        <h1 id="loginMsg">LOGIN IN</h1>
        <div id="errorMsg">${login_msg}</div>
        <p>Username:<input id="username" name="username" type="text" value="${cookie.username.value}"></p>
        <p>Password:<input id="password" name="password" type="password" value="${cookie.password.value}"></p>
        <p>Remember:<input id="remember" name="remember" type="checkbox" value="1" checked></p>
        <div id="subDiv">
            <input type="submit" class="button" value="login up">
            <input type="reset" class="button" value="reset">
            <a href="register.jsp">没有账号？</a>
        </div>
    </form>
</div>
</body>
</html>
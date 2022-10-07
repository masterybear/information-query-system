<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <style>
        .p1 {
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            margin: auto;
            padding: 30px;
            background-color: aliceblue;
        }
        a{
            font-size: 10px;
        }
        body{
            background-color: #7d87d9;
        }
        #error{
            font-size: 10px;
            color: red;
        }
    </style>
</head>
<body>
<div class="p1" style="height: 250px; width: 270px;">
    <form action="/web-case01/loginServlet" method="post">
        <div id="user">
            <h1 id="login">LOGIN IN</h1>
            <div id="error">${loginMsg}</div>
            <p>Username:&nbsp;<input type="text" name="username" value="${cookie.username.value}"></p>
            <p>Password:&nbsp;&nbsp;<input type="password" name="password" value="${cookie.password.value}"></p>
            <p>Remember:&nbsp;<input type="checkbox" value="1" name="remember"></p>
        </div>
        <div id="sub" style="clear: both; text-align: center">
            <input type="submit" value="login up">
            <input type="reset" value="reset">
            <a href="/web-case01/register.jsp">没有账号？点击注册</a>
        </div>
    </form>
</div>
</body>
</html>
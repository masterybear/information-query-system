<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>register</title>
    <style>
        .p1 {
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            margin: auto;
            padding: 15px 30px;
            background-color: aliceblue;
        }
        #error,#usernameError{
            font-size: 10px;
            color: red;
        }
        body{
            background-color: #7d87d9;
        }

        input[type=submit],#username,#password{
            height: 40px;
            width: 170px;
            border-radius: 8px;
            border: #7d87d9 solid;
        }
        input[type=submit]{
            background-color: dodgerblue;
            border: #7d87d9 solid;
            color: white;
            transition-duration: 0.4s;
        }
        input[type=submit]:hover {
            background-color: white;
            color: dodgerblue;
        }
        #checkCode{
            height: 40px;
            width: 80px;
            border-radius: 8px;
            border: #7d87d9 solid;
        }
        .s{
            font-size: 10px;
        }
        img{
            vertical-align: middle;
        }

    </style>
</head>
<body>
<form action="/web-case01/registerServlet" method="post">
    <div class="p1" style="height: 360px; width: 320px;">
        <div style="line-height: 10px">
            <h1>欢迎注册</h1>
            <span class="s">已有账号？</span><a href="login.jsp" class="s">登录</a>
            <p id="error">${registerMsg}</p>
        </div>
        <div style="text-align: center;line-height: 45px;padding-top: 20px; width: 60px; float: left">
            <p><span>用户名</span></p>
            <p><span>密码</span></p>
            <p><span>验证码</span></p>
        </div>
        <div style="text-align: left;line-height: 15px;padding: 10px 0px; width: 260px; float: right">
            <div style="height: 50px">
                <p>
                    <input id="username" name="username" type="text"><br>
                    <span id="usernameError" style="display: none">用户名已存在</span>
                </p>
            </div>
            <p><input id="password" name="password" type="password"></p>
            <p>
                <input id="checkCode" name="checkCode" type="text">&nbsp;
                <img id="checkCodeImg" src="/web-case01/checkCodeServlet"/>&nbsp;
                <a href="#" class="s" id="changeImg">看不清？</a>
            </p>
            <p><input type="submit" value="注册"></p>
        </div>
    </div>
</form>
<script>
    document.getElementById("changeImg").onclick = function () {
        document.getElementById("checkCodeImg").src = "/web-case01/checkCodeServlet?" + new Date().getMilliseconds();
    }
    //当失去焦点时需要执行的动作
    document.getElementById("username").onblur = function () {

        //需要传递的变量：该动作对象的属性值
        var username = this.value;

        //创建与服务器交互的对象
        var xhttp;
        if (window.XMLHttpRequest) {
            xhttp = new XMLHttpRequest();
        } else {
            // code for IE6, IE5
            xhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        //向服务器发送异步请求
        xhttp.open("GET", "http://localhost:8080/web-case01/selectUserServlet?username=" + username, true);
        xhttp.send();

        //当请求接收到应答时要进行的动作
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                // alert(this.responseText);
                if (this.responseText == "true") {
                    //用户名已存在
                    document.getElementById("usernameError").style.display = '';
                } else {
                    //用户名不存在
                    document.getElementById("usernameError").style.display = 'none';
                }
            }
        }
    }

</script>
</body>
</html>

<%-- 
    Document   : enterPass
    Created on : Mar 19, 2023, 11:16:55 PM
    Author     : DevDD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ENTER THE PASSWORD</title>
        <link rel="stylesheet" href="./style/enterPass.css">
    </head>
    <body>
        <div class="main">
        <form action="MainController" method="post" id="form">
            <h2>Nhập lại mật khẩu</h2>
            <div class="password text">
                <input title="pass" type="password" name="password" required=""><br>
                <span></span>
                <label>Password</label>
            </div>
            <div class="confirm text">
                <input title="confirm" type="password" name="confirm" required="" /><br>
                <span></span>
                <label>Confirm password</label>
            </div>
            <p class="errorMessage">${requestScope.ERROR_CONFIRM_PASS}</p>
            <input type="submit" name="action" value="Confirm">
        </form>
    </div>
    </body>
</html>

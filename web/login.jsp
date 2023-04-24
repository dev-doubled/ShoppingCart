<%-- 
    Document   : Login
    Created on : Feb 7, 2023, 9:45:46 AM
    Author     : DevDD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>LOGIN</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./style/login.css">
    </head>
    <body>
        <div class="main">
            <h1>LOGIN FORM</h1>
            <form action="MainController" method="post" id="form">
                <div class="userID text">
                    <input title="UserID" type="text" name="userID" required="" /><br>
                    <span></span>
                    <label>User ID</label>
                </div>
                <div class="password text">
                    <input title="pass" type="password" name="password" required=""><br>
                    <span></span>
                    <label>Password</label>
                </div>
                <p class="errorMessage">${requestScope.ERROR}</p>
                <div class="forget_password">
                    <a href="forgotPass.jsp">Quên mật khẩu?</a>
                </div>
                <br>
                <div class="submit-form">
                    <div class="submit">
                        <input type="submit" name="action" value="Login" />
                    </div>
                </div>
            </form>
            <div class="more-option">
                <div class="login__google">
                    <a title="loginGoogle" href="https://accounts.google.com/o/oauth2/auth?scope=email profile&redirect_uri=http://localhost:8080/PRJ301_Spring23_BLOC3/LoginGoogleController&response_type=code
                       &client_id=719856657703-9hlu1q4rgkf9fa4oh9eptgtpv5v0fb9t.apps.googleusercontent.com&approval_prompt=force">
                        <img src="./img/loginGoogle.png" alt="" target="_self">
                    </a>
                </div>
                <div class="createUser">
                    <label  class="more">Not a member?</label>
                    <a href="createUser.jsp">Sign up</a><br>
                </div>
            </div>
        </div>
    </body>
</html>

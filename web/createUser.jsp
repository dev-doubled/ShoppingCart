<%-- 
    Document   : createUser
    Created on : Feb 24, 2023, 10:47:49 AM
    Author     : DevDD
--%>

<%@page import="com.dd.sampleUser.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./style/signup.css">
        <title>SIGN UP</title>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    </head>
    <body>
        <div class="main">
            <h1>SIGN UP FORM</h1>
            <form action="MainController" method="post" id="form">
                <div class="userID text">
                    <input title="UserID" type="text" name="userID" required="" /><br>
                    <span></span>
                    <label>User ID</label>
                </div>
                <p class="error">${requestScope.USER_ERROR.userIDError}</p>
                <div class="fullName text">
                    <input title="fullName" type="text" name="fullName" required="" /><br>
                    <span></span>
                    <label>Full Name</label>
                </div>
                <p class="error">${requestScope.USER_ERROR.fullNameError}</p>
                <div class="email text">
                    <input title="email" type="email" name="email" required="" /><br>
                    <span></span>
                    <label>Email</label>
                </div>
                <p class="error">${requestScope.USER_ERROR.emailError}</p>
                <div class="roleID text">
                    <input id="detail" title="roleID" type="text" name="roleID" onfocus="showDetails()" readonly="" /><br>
                    <span></span>
                    <label>Role ID</label>
                </div>
                <div class="avatar text">
                    <input title="avatar" type="text" name="avatar" required="" /><br>
                    <span></span>
                    <label>Avatar</label>
                </div>
                <div class="password text">
                    <input title="pass" type="password" name="password" required=""><br>
                    <span></span>
                    <label>Password</label>
                </div>
                <p class="error">${requestScope.USER_ERROR.passwordError}</p>
                <div class="confirm text">
                    <input title="confirm" type="password" name="confirm" required="" /><br>
                    <span></span>
                    <label>Confirm password</label>
                </div>
                <p class="error">${requestScope.USER_ERROR.confirmError}</p>
                <div class="submit-form">
                    <div class="g-recaptcha" data-sitekey="6Lcyh_kkAAAAAEUc6ZYTuHeTaOyKGl3Evq0SvC6u"></div>
                    <div id="recaptcha-error">${requestScope.RECAPTCHA_ERROR}</div>
                    <div class="submit">
                        <input type="submit" name="action" value="Signup" />
                    </div>
                </div>
            </form>
            <div id="mess">${requestScope.ERROR}</div>
        </div>

        <script>
            function showDetails() {
                document.getElementById('detail').value = 'US'
                document.getElementById('detail').removeAttribute("readonly")
            }
        </script>
    </body>
</html>

<%-- 
    Document   : forgotPass
    Created on : Mar 19, 2023, 10:04:33 PM
    Author     : DevDD
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FORGOT PASSWORD</title>
        <link rel="stylesheet" href="./style/forgotPass.css">
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER != null}">
            <c:redirect url="Login.jsp"></c:redirect>
        </c:if>
        <div class="main">
            <form action="MainController" method="post" id="form">
                <h2>Tìm tài khoản của bạn</h2>
                <h3>Vui lòng nhập User ID và email để tìm kiếm tài khoản của bạn</h3>
                <div class="userID text">
                    <input title="UserID" type="text" name="userID" required="" /><br>
                    <span></span>
                    <label>User ID</label>
                </div>
                <div class="email text">
                    <input title="pass" type="email" name="email" required=""><br>
                    <span></span>
                    <label>Email</label>
                </div>
                <p class="errorMessage">${requestScope.ERROR_FORGOT}</p>
                <div class="submit-form">
                    <div class="submit">
                        <input type="submit" name="action" value="Find" />
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>

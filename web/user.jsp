<%-- 
    Document   : user
    Created on : Feb 7, 2023, 9:50:07 AM
    Author     : DevDD
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.dd.sampleUser.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>USER PAGE</title>
        <link rel="stylesheet" href="./style/user.css">
    </head>
    <body>

        <div class="main">
            <h1>USER INFORMATION</h1>
            <div class="user">
                <div class="user__avatar">
                    <img src="${sessionScope.LOGIN_USER.avatar}" alt="">
                </div>
                <div class="user__info">
                    <h4><span class="edit">Full Name: </span>${sessionScope.LOGIN_USER.fullName}</h4>
                    <h4><span class="edit">Email: </span>${sessionScope.LOGIN_USER.email}</h4>
                    <h4><span class="edit">User ID: </span>${sessionScope.LOGIN_USER.userID}</h4>
                    <h4><span class="edit">Role ID: </span>${sessionScope.LOGIN_USER.roleID}</h4>
                    <h4><span class="edit">Password: </span>*******</h4>
                </div>
                <div class="link">
                    <a href="MainController?action=Logout" class="logout">Logout</a>
                    <a href="MainController?search=&action=Phone&currPage=1" class="shopping">Shopping</a>
                    
                </div>
            </div>
        </div>
    </body>
</html>

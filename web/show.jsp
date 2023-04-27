<%-- 
    Document   : show
    Created on : Apr 24, 2023, 5:12:11 PM
    Author     : DevDD
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SHOW USER</title>
    </head>
    <body>
        
        <br>
        <c:if test="${requestScope.SHOW_USER != null}">
            <c:if test="${not empty requestScope.SHOW_USER}">
                <c:forEach var="user" items="${requestScope.SHOW_USER}">
                    User ID: ${user.userID} <br>
                    Full Name: ${user.fullName} <br>
                    Role ID: ${user.roleID} <br>
                    <br>
                </c:forEach>
            </c:if>
        </c:if>
    </body>
</html>

<%-- 
    Document   : admin
    Created on : Feb 7, 2023, 9:51:47 AM
    Author     : DevDD
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="com.dd.sampleUser.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADMIN Page</title>
        <link rel="stylesheet" href="./style/admin.css">
    </head>
    <body>
        <div class="main">
            <c:set var="currPage" value="1" scope="request"></c:set>
            <c:if test="${not empty param.currPage}">
                <c:set var="currPage" value="${param.currPage}"></c:set>
            </c:if>

            <div class="header__search">
                <h1>Welcome: ${sessionScope.LOGIN_USER.fullName}</h1>
                <div class="link">
                    <a href="MainController?action=Logout" class="logout">Logout</a>
                    <!--<a href="MainController?search=&action=Phone&currPage=1" class="shopping">Shopping</a>-->
                </div>
            </div>
            <div class="main__search">   
                <form action="MainController">
                    <div class="search">
                        <input title="search" type="text" name="search" value="${param.search}" placeholder="Search...">
                        <input type="submit" name="action" value="Search">
                        <input hidden="" name="currPage" value="${currPage}">
                    </div>
                </form>
            </div>
            <c:if test="${requestScope.LIST_USER != null}">
                <c:if test="${not empty requestScope.LIST_USER}">
                    <div class="result__search">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>User ID</th>
                                    <th>Full Name</th>
                                    <th>Role ID</th>
                                    <th>Password</th>
                                    <th>Delete</th>
                                    <th>Update</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="count" value="${(currPage - 1) * 10 + 1}" />
                                <c:forEach var="user" varStatus="counter" items="${requestScope.LIST_USER}">
                                <form action="MainController">
                                    <tr>
                                        <td>
                                            ${count}
                                        </td>
                                        <td>
                                            ${user.userID}
                                        </td>
                                        <td>
                                            <input title="fullName" type="text" name="fullName" value="${user.fullName}" id="edit" required="">
                                        </td>
                                        <td>
                                            <input title="roleID" type="text" name="roleID" value="${user.roleID}" id="edit-1">
                                        </td>
                                        <td>
                                            ${user.password}
                                        </td>        

                                        <c:url var="deleteLink" value="MainController">
                                            <c:param name="action" value="Delete"></c:param>
                                            <c:param name="userID" value="${user.userID}"></c:param>
                                            <c:param name="search" value="${param.search}"></c:param>
                                            <c:param name="currPage" value="${currPage}"></c:param>
                                        </c:url>
                                        <td id="delete">
                                            <a href="${deleteLink}">Delete</a> 
                                        </td>

                                        <td>
                                            <input type = "hidden" name="userID" value="${user.userID}" />
                                            <input type="hidden" name="search" value="${param.search}">
                                            <input type="submit" name="action" value="Update" id="update"> 
                                            <input hidden="" name="currPage" value="${currPage}">
                                        </td>
                                    </tr>
                                </form>
                                <c:set var="count" value="${count + 1}" />
                            </c:forEach>
                            </tbody>
                        </table>
                        <br>


                    </div>
                    <p class="errorMessage">${requestScope.ERROR}</p>
                </c:if>
            </c:if>
            <div class="next__page">
                <c:set var="searchValue" value="${param.search}" />
                <c:set var="actionValue" value="${param.action}" />
                <c:set var="urlChuyentrang" value="/PRJ301_Spring23_BLOC3/MainController?search=${searchValue}${not empty actionValue ? '&action=' : ''}${actionValue}" />

                <c:forEach var="i" begin="1" end="4">
                    <c:set var="page" value="${i}" />
                    <a href="${urlChuyentrang}&amp;currPage=${page}" class="page ${currPage == page ? 'current' : ''}">${page}</a>
                </c:forEach>

            </div>
        </div>
    </body>
</html>

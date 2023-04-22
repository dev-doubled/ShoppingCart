<%-- 
    Document   : Shopping
    Created on : Feb 28, 2023, 10:55:48 AM
    Author     : DevDD
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.dd.phone.PhoneDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.dd.phone.Phone"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Shopping Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="shopping.css">
        <link href="https://cdn.jsdelivr.net/gh/hung1001/font-awesome-pro-v6@44659d9/css/all.min.css" rel="stylesheet"
              type="text/css" />
    </head>
    <body>
        <div class="main">
            <c:set var="currPage" value="1" scope="request"></c:set>
            <c:if test="${not empty param.currPage}">
                <c:set var="currPage" value="${param.currPage}"></c:set>
            </c:if>
            <div class="header">
                <a href="MainController?search=&action=Phone&currPage=1">
                    <h3>Welcome <span>${sessionScope.LOGIN_USER.fullName}</span> to my shop</h3>
                </a>
                <form action="MainController">
                    <div class="search">
                        <input title="search" type="text" name="search" value="${param.search}" placeholder="Search...">
                        <input type="submit" name="action" value="Phone">
                        <input hidden="" name="currPage" value="${currPage}">
                    </div>
                </form>
                <div class="option">
                    <a href="viewcart.jsp" id="view">
                        <i class="fa-regular fa-cart-shopping" style="color: #fff"></i>
                        View Cart</a>
                    <a href="addProduct.jsp" class="addPhone">
                        <i class="fa-light fa-cart-plus" style="color: #fff"></i>
                        Add Phone</a>
                    <a href="MainController?action=Logout" class="logout">
                        <i class="fa-regular fa-door-open" style="color: #fff"></i>
                        Logout</a>
                </div>
            </div>
            <div class="result__search">
                <div class="phones">
                    <c:if test="${requestScope.LIST_PHONE != null}">
                        <c:if test="${not empty requestScope.LIST_PHONE}">
                            <c:forEach var="phone" items="${requestScope.LIST_PHONE}">
                                <form action="MainController" method="POST" id="form">
                                    <div class="phone">
                                        <div class="imgs">
                                            <input id="img" type="hidden" name="phoneImg" value="<c:out value='${phone.phoneImage}'/>" readonly="" />
                                            <img src="${phone.phoneImage}" alt="">
                                        </div>
                                        <div class="decs">
                                            <input id="id" type="hidden" name="phoneID" value="<c:out value='${phone.phoneID}'/>" readonly="" />
                                            <span class="names">
                                                <input title="phoneName" id="name" type="text" name="phoneName" value="<c:out value='${phone.phoneName}'/>" />
                                            </span>
                                            <span class="prices">
                                                <input title="price" id="price" type="text" name="phonePrice" value="<c:out value='${phone.phonePrice} $'/>" />
                                            </span>
                                        </div>
                                        <div class="buy">
                                            <div class="addToCart">
                                                <input type="hidden" name="search" value="${param.search}">
                                                <input type="submit" name="action" value="Add" id="add" />
                                                <input hidden="" name="currPage" value="${currPage}">
                                            </div>
                                            <div class="quantitys">
                                                <select title="quantity" name="cmbQuantity" id="quantity">
                                                    <option value="1">1</option>
                                                    <option value="2">2</option>
                                                    <option value="3">3</option>
                                                    <option value="4">4</option>
                                                    <option value="5">5</option>
                                                    <option value="6">6</option>
                                                    <option value="7">7</option>
                                                    <option value="8">8</option>
                                                    <option value="9">9</option>
                                                    <option value="10">10</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </c:forEach>
                        </c:if>
                    </c:if>
                </div>
                <div class="next-page">
                    <c:set var="searchValue" value="${param.search}" />
                    <c:set var="actionValue" value="${param.action}" />
                    <c:set var="urlChuyentrang" value="/PRJ301_Spring23_BLOC3/MainController?search=${searchValue}&action=Phone" />
                    <c:forEach var="i" begin="1" end="4">
                        <c:set var="page" value="${i}" />
                        <a href="${urlChuyentrang}&amp;currPage=${page}" class="next ${currPage == page ? 'current' : ''}">${page}</a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </body>
</html>

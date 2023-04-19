<%-- 
    Document   : viewcart
    Created on : Mar 3, 2023, 9:42:05 AM
    Author     : DevDD
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.dd.phone.Phone"%>
<%@page import="com.dd.phone.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
        <link rel="stylesheet" href="view.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <div class="main">
            <div class="view-card">
                <c:if  test="${sessionScope.CART == null}">
                    <div class="alert alert-success suce" role="alert">
                        <h3>Cảm ơn bạn đã đặt hàng!</h3>
                        <p>Chúng tôi đã nhận được đơn hàng của bạn và sẽ xử lý trong thời gian sớm nhất. ${requestScope.SUCCESS}</p>
                    </div> 
                </c:if>
                <div class="continue-shopping">
                    <a href="MainController?search=&action=Phone&currPage=1">Continue shopping</a>
                </div>
            </div>

        </div>
    </body>
</html>

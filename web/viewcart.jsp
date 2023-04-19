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
        <link rel="stylesheet" href="./style/view.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    </head>
    <body>
        <div class="main">
            <div class="view-card">
                <c:if test="${not empty sessionScope.CART}">
                    <h1>View Cart</h1>
                    <div class="cart">
                        <c:if test="${requestScope.QUANTITY_ERROR != null}">
                            <div class="alert alert-danger" role="alert">
                                ${requestScope.QUANTITY_ERROR}
                            </div>
                        </c:if>
                        <table>
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Product ID</th>
                                    <th>Image</th>
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                    <th>Modify</th>
                                    <th>Remove</th>
                                </tr>
                            </thead> 
                            <tbody>
                                <c:set var="count" value="1"></c:set>
                                <c:set var="total" value="0"></c:set>
                                <c:forEach items="${sessionScope.CART.cart}" var="phone">
                                    <c:set var="subTotal" value="${phone.value.phonePrice * phone.value.phoneQuantity}" />
                                    <c:set var="total" value="${total + subTotal}" />
                                <form action="MainController">
                                    <tr>
                                        <td id="num">${count}</td>
                                        <td>
                                            <input type="text" name="phoneID" value="${phone.value.phoneID}" readonly="" id="id"/>
                                        </td>
                                        <td>
                                            <img src="${phone.value.phoneImage}" alt="" id="img">
                                        </td>
                                        <td id="name">${phone.value.phoneName}</td>

                                        <td id="price">${phone.value.phonePrice}$</td>
                                        <td>
                                            <input type="number" name="phoneQuantity" value="${phone.value.phoneQuantity}" min="1" id="quantity">
                                        </td>
                                        <td>${phone.value.phonePrice * phone.value.phoneQuantity}$</td>
                                        <td>
                                            <input type="submit" name="action" value="Edit" id="edit">
                                        </td>
                                        <td>
                                            <input type="submit" name="action" value="Remove" id="remove">
                                        </td>
                                    </tr>
                                </form>
                                <c:set var="count" value="${count + 1}" />
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <h2 id="sum"><span>Total:</span> ${total}$</h2>
                    <a href="MainController?search=&action=Phone&currPage=1" id="shopping">Back to shopping</a>
                </c:if>
                <c:if  test="${sessionScope.CART == null}">
                    <div class="alert alert-warning warn" role="alert">
                        Chưa có sản phẩm trong giỏ hàng. Vui lòng quay lại trang <a href="MainController?search=&action=Phone&currPage=1" class="alert-link" style="color: red;">Shopping Cart</a> mua và thêm vào giỏ hàng!
                    </div>
                </c:if>
            </div>
            <c:if  test="${not empty sessionScope.CART}">
                <div class="order-info">
                    <h1>Customer information</h1>
                    <form action="MainController" method="POST" id="form-info">
                        <div class="name text">
                            <input title="name" type="text" name="name" required="" /><br>
                            <span></span>
                            <label>Họ và tên</label>
                        </div>
                        <div class="email text">
                            <input title="email" type="email" name="email" required=""><br>
                            <span></span>
                            <label>Email</label>
                        </div>
                        <div class="phone text">
                            <input title="phone" type="text" name="phone" required=""><br>
                            <span></span>
                            <label>Số điện thoại</label>
                        </div>
                        <div class="address text">
                            <input title="address" type="text" name="address" required=""><br>
                            <span></span>
                            <label>Địa chỉ</label>
                        </div>
                        <div class="g-recaptcha" data-sitekey="6Lcyh_kkAAAAAEUc6ZYTuHeTaOyKGl3Evq0SvC6u"></div>
                        <div id="recaptcha-error">${requestScope.RECAPTCHA_ERROR}</div>
                        <div class="submit">
                            <input type="submit" name="action" value="Order" />
                        </div>
                    </form>
                </div>
            </c:if>

        </div>
    </body>
</html>

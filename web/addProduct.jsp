<%-- 
    Document   : AddProduct
    Created on : Mar 11, 2023, 9:51:41 PM
    Author     : DevDD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./style/add.css">
    </head>
    <body>
        <div class="main">
            <h1>ADD PRODUCT</h1>
            <form action="MainController" method="post">
                <div class="pID text">
                    <input title="phoneID" type="text" name="phoneID" required="" /><br>
                    <span></span>
                    <label>Phone ID</label>
                </div>
                <p class="error">${requestScope.PHONE_ERROR}</p>
                <div class="pName text">
                    <input title="phoneName" type="text" name="phoneName" required="" /><br>
                    <span></span>
                    <label>Phone Name</label>
                </div>
                <div class="pPrice text">
                    <input title="phonePrice" type="text" name="phonePrice" required="" /><br>
                    <span></span>
                    <label>Phone Price</label>
                </div>
                <div class="pQuantity text">
                    <input title="phoneQuantity" type="text" name="phoneQuantity" required="" /><br>
                    <span></span>
                    <label>Phone Quantity</label>
                </div>
                <div class="pImg text">
                    <input title="phoneImg" type="text" name="phoneImg" required="" /><br>
                    <span></span>
                    <label>Phone Image</label>
                </div>
                <div class="submit-form">
                    <div class="submit">
                        <input type="submit" name="action" value="Add Phone" />
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>

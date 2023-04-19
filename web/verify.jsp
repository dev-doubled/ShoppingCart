<%-- 
    Document   : verify
    Created on : Mar 19, 2023, 10:10:33 PM
    Author     : DevDD
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VERIFY</title>
        <link rel="stylesheet" href="./style/verify.css">
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER != null}">
            <c:redirect url="Login.jsp"></c:redirect>
        </c:if>
        <div class="main">
            <form action="MainController" method="post">
                <h2>Verification code</h2>
                <div class="counter-block">
                    <p class="counter"></p>
                </div>
                <div class="input_code">
                    <input type="text" id="verification_code" name="code_1" required>
                    <input type="text" id="verification_code" name="code_2" required>
                    <input type="text" id="verification_code" name="code_3" required>
                    <input type="text" id="verification_code" name="code_4" required>
                    <input type="text" id="verification_code" name="code_5" required>
                    <input type="text" id="verification_code" name="code_6" required>
                </div>
                <p class="errorMessage">${requestScope.ERROR_VERIFY}</p>
                <input type="submit" name="action" value="Verify">
            </form>
        </div>
        <c:if test="${requestScope.START_COUNT != null}">
            <script>
                var counter = document.querySelector('.counter');
                var counterBlock = document.querySelector('.counter-block');
                var count = 60;
                var intervalID = setInterval(() => {
                    count = count - 1;
                    counter.innerHTML = count;
                }, 1000)
                setTimeout(() => {
                    clearInterval(intervalID);
                    counter.innerHTML = ''
                    counter.style.border = '1px solid transparent';
//                    let button = document.querySelector('input[name="action"]')
//                    button.disabled = true
                }, 60000)
            </script>
        </c:if>
    </body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

<fmt:message key="form.sign.up.tagline" bundle="${rb}" var="tagline"/>
<fmt:message key="form.sign.up.motivation.message" bundle="${rb}" var="motivationMessage"/>
<fmt:message key="order.congratulations" bundle="${rb}" var="congratulations"/>
<fmt:message key="order.congratulations.message" bundle="${rb}" var="congratulationsMessage"/>
<fmt:message key="order.orders" bundle="${rb}" var="orders"/>


<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <c:choose>
        <c:when test="${sessionScope.userRole == 'CLIENT'}">
            <meta http-equiv="refresh"
                  content="5;${pageContext.request.contextPath}/jsp/pages/client/profile.jsp">${account}</a>
        </c:when>
        <c:when test="${sessionScope.userRole == 'TRAINER'}">
            <meta http-equiv="refresh"
                  content="5;${pageContext.request.contextPath}/jsp/pages/trainer/profile.jsp">${account}</a>
        </c:when>
        <c:when test="${sessionScope.userRole == 'ADMIN'}">
            <meta http-equiv="refresh"
                  content="5;{pageContext.request.contextPath}/jsp/pages/admin/profile.jsp">${account}</a>
        </c:when>
    </c:choose>
</head>

<body>
<c:import url="../fragment/navbar.jsp"/>

<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


<div class="container register">
    <div class="row">
        <div class="col-md-3 register-left">
            <img src="https://i.ibb.co/SsQJHTz/pngegg.png" alt="">
            <h3>${tagline}</h3>
            <p>${motivationMessage}</p>
        </div>
        <div class="col-md-9 register-right">
            <h3 class="register-heading">${congratulations}</h3>
            <div class="row register-form">
                <div class="col-md-6">
                    <p>${congratulationsMessage}</p>
                    <c:choose>
                        <c:when test="${sessionScope.userRole == 'CLIENT'}">
                            <a href="${pageContext.request.contextPath}/jsp/pages/client/profile.jsp">${account}</a>
                        </c:when>
                        <c:when test="${sessionScope.userRole == 'TRAINER'}">
                            <a href="${pageContext.request.contextPath}/jsp/pages/trainer/profile.jsp">${account}</a>
                        </c:when>
                        <c:when test="${sessionScope.userRole == 'ADMIN'}">
                            <a href="${pageContext.request.contextPath}/jsp/pages/admin/profile.jsp">${account}</a>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>

<c:import url="../fragment/footer.jsp"/>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="profile.my.orders" bundle="${rb}" var="myOrder"/>
<fmt:message key="form.sign.up.tagline" bundle="${rb}" var="tagline"/>
<fmt:message key="form.sign.up.motivation.message" bundle="${rb}" var="motivationMessage"/>
<fmt:message key="order.empty.order" bundle="${rb}" var="emptyOrdersList"/>
<fmt:message key="order.register.date" bundle="${rb}" var="registerDate"/>
<fmt:message key="profile.new.order" bundle="${rb}" var="newOrder"/>

<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>

<c:import url="../../fragment/navbar.jsp"/>

<div class="container register">
    <div class="row">
        <div class="col-md-3 register-left">
            <img src="https://i.ibb.co/SsQJHTz/pngegg.png" alt="">
            <h3>${tagline}</h3>
            <p>${motivationMessage}</p>
        </div>
        <div class="col-md-9 register-right">

            <h3 class="register-heading">${myOrder}</h3>

            <div class="row register-form">
                <div class="col-md-6">
                    <c:choose>
                        <c:when test="${requestScope.orders.size() > 0}">
                            <ctg:order_table/>
                        </c:when>

                        <c:otherwise>
                            <div>
                                <h2>${emptyOrdersList}</h2>
                                <a href="${pageContext.request.contextPath}/controller?command=new_order">${newOrder}</a>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="../../fragment/footer.jsp"/>
</body>
</html>
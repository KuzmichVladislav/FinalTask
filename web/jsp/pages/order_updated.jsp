<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

<fmt:message key="form.sign.up.tagline" bundle="${rb}" var="tagline"/>
<fmt:message key="form.sign.up.motivation.message" bundle="${rb}" var="motivationMessage"/>
<fmt:message key="order.updated" bundle="${rb}" var="orderUpdated"/>
<fmt:message key="order.updated.message" bundle="${rb}" var="updatedMessage"/>
<fmt:message key="order.orders" bundle="${rb}" var="orders"/>


<html>
    <head>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <c:if test="${sessionScope.userRole == 'CLIENT'}">
            <meta http-equiv="refresh"
                  content="5;${pageContext.request.contextPath}/controller?command=SHOW_ALL_ORDERS_BY_CLIENT">
        </c:if>
        <c:if test="${sessionScope.userRole == 'TRAINER'}">
            <meta http-equiv="refresh"
                  content="5;${pageContext.request.contextPath}/controller?command=SHOW_ALL_ORDERS_BY_TRAINER">
        </c:if>

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
                    <h3 class="register-heading">${orderUpdated}</h3>
                    <div class="row register-form">
                        <div class="col-md-6">
                            <p>${updatedMessage}</p>

                            <c:if test="${sessionScope.userRole == 'CLIENT'}">
                                <a href="${pageContext.request.contextPath}/controller?command=SHOW_ALL_ORDERS_BY_TRAINER">${orders}</a>
                            </c:if>
                            <c:if test="${sessionScope.userRole == 'TRAINER'}">
                                <a href="${pageContext.request.contextPath}/controller?command=SHOW_ALL_ORDERS_BY_TRAINER">${orders}</a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <c:import url="../fragment/footer.jsp"/>
    </body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="profile.my.orders" bundle="${rb}" var="myOrder"/>
<fmt:message key="form.sign.up.tagline" bundle="${rb}" var="tagline"/>
<fmt:message key="form.sign.up.motivation.message" bundle="${rb}" var="motivationMessage"/>
<fmt:message key="order.empty.order.at.all" bundle="${rb}" var="emptyOrdersListAtAll"/>
<fmt:message key="order.register.date" bundle="${rb}" var="registerDate"/>
<fmt:message key="order.start.date" bundle="${rb}" var="startDate"/>
<fmt:message key="order.end.date" bundle="${rb}" var="endDate"/>
<fmt:message key="order.status" bundle="${rb}" var="status"/>
<fmt:message key="order.client.name" bundle="${rb}" var="clientFullName"/>
<fmt:message key="order.details" bundle="${rb}" var="orderDetails"/>


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
                <div class="col-md-6 ">
                    <c:choose>
                        <c:when test="${requestScope.orders.size() > 0}">
                            <table style="width: auto;">
                                <th>
                                    <c:forEach items="${requestScope.orders}" var="order">
                                    <jsp:useBean id="order" type="com.company.gum.entity.Order"/>

                                    <table class="table table-striped" border="1">
                                        <tr>
                                            <th>${registerDate}</th>
                                            <th>${clientFullName}</th>
                                            <th>${startDate}</th>
                                            <th>${endDate}</th>
                                            <th>${status}</th>
                                        </tr>
                                        <tr>
                                            <td>${order.registerDate}</td>
                                            <td>${order.clientName} ${order.clientSurname}</td>
                                            <td>${order.startDate}</td>
                                            <td>${order.endDate}</td>
                                            <td>${order.orderStatus}</td>
                                        </tr>
                                        <tr>
                                            <td colspan="5">
                                                <a href="${pageContext.request.contextPath}/controller?command=SHOW_ORDER_BY_TRAINER&orderId=${order.id}">
                                                    <button class="btn btn-sm btn-outline-primary w-100">
                                                            ${orderDetails}</button>
                                                </a>
                                            </td>
                                        </tr>
                                        </c:forEach>
                                    </table>
                                </th>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <div>
                                <h2>${emptyOrdersListAtAll}</h2>
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
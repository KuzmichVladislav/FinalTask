<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="profile.my.orders" bundle="${rb}" var="myOrder"/>
<fmt:message key="form.sign.up.tagline" bundle="${rb}" var="tagline"/>
<fmt:message key="form.sign.up.motivation.message" bundle="${rb}" var="motivationMessage"/>
<fmt:message key="order.empty.order" bundle="${rb}" var="emptyOrdersList"/>
<fmt:message key="order.register.date" bundle="${rb}" var="registerDate"/>


<fmt:bundle basename="page_content">
    <fmt:message key="order.register.date" var="registerDate"/>
    <fmt:message key="client.next" var="next"/>
    <fmt:message key="client.previous" var="previous"/>
</fmt:bundle>


<html>
<head>
    <title>${projectName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <script src="${pageContext.request.contextPath}/js/scripts.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/filter.js"></script>
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
<%--                            <button type="button" class="btn btn-info filter-button-show" onclick="clickFilter()">${showFilter}</button>--%>
                            <%--                            <form class="filter-form" id="filterButton" action="${pageContext.request.contextPath}/controller" method="POST">--%>
                            <%--                                <input type="hidden" name="command" value="FIND_ORDERS_BY_FILTER">--%>
                            <%--                                <div class="form-row">--%>
                            <%--                                    <div class="form-group col-md-6">--%>
                            <%--                                        <label for="inputTrainerName">${trainerName}</label>--%>
                            <%--                                        <input type="text" class="form-control" id="inputTrainerName" name="trainerName" placeholder="Name">--%>
                            <%--                                    </div>--%>
                            <%--                                    <div class="form-group col-md-6">--%>
                            <%--                                        <label for="inputTrainerLastName">${trainerLastName}</label>--%>
                            <%--                                        <input type="text" class="form-control" id="inputTrainerLastName" name="trainerLastName" placeholder="Last name">--%>
                            <%--                                    </div>--%>
                            <%--                                </div>--%>

                            <%--                                <div class="form-row">--%>
                            <%--                                    <div class="form-group col-lg-4">--%>
                            <%--                                        <label for="inputStartDate">${filterSartDate}</label>--%>
                            <%--                                        <input type="date" class="form-control" id="inputStartDate" name="startDate" placeholder="Start date">--%>
                            <%--                                    </div>--%>
                            <%--                                    <div class="form-group col-lg-4">--%>
                            <%--                                        <label for="inputEndDate">${filterEndDate}</label>--%>
                            <%--                                        <input type="date" class="form-control" id="inputEndDate" name="endDate" placeholder="End date">--%>
                            <%--                                    </div>--%>
                            <%--                                    <div class="form-group col-lg-4">--%>
                            <%--                                        <label for="inputStatus">${filterStatus}</label>--%>
                            <%--                                        <select id="inputStatus" class="form-control" name="status">--%>
                            <%--                                            <option value="0">${statusNew}</option>--%>
                            <%--                                            <option value="1">${statusReviewed}</option>--%>
                            <%--                                            <option value="2">${statusRejected}</option>--%>
                            <%--                                            <option value="3">${statusAccepted}</option>--%>
                            <%--                                            <option value="4">${statusInProcess}</option>--%>
                            <%--                                            <option value="5">${statusTerminated}</option>--%>
                            <%--                                            <option selected value="">ANY</option>--%>
                            <%--                                        </select>--%>
                            <%--                                    </div>--%>
                            <%--                                </div>--%>
                            <%--                                <button type="submit" class="btn btn-success">${btnFilter}</button>--%>
                            <%--                            </form>--%>
                            <ctg:order_table/>
                        </c:when>

                        <c:otherwise>
                            <div>
                                <h2>${emptyOrdersList}</h2>
                                <a href="${pageContext.request.contextPath}/controller?command=new_order">${newOrder}New order</a>
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
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:setBundle basename="bundle/err" var="err_rb"/>

<fmt:message key="form.sign.up.tagline" bundle="${rb}" var="tagline"/>
<fmt:message key="form.sign.up.motivation.message" bundle="${rb}" var="motivationMessage"/>
<fmt:message key="order.congratulations" bundle="${rb}" var="congratulations"/>
<fmt:message key="order.congratulations.message" bundle="${rb}" var="congratulationsMessage"/>
<fmt:message key="order.orders" bundle="${rb}" var="orders"/>

<fmt:message key="profile.change.password" bundle="${rb}" var="changePassword"/>
<fmt:message key="profile.current.password" bundle="${rb}" var="currentPassword"/>
<fmt:message key="profile.new.password" bundle="${rb}" var="newPassword"/>
<fmt:message key="profile.repeat.password" bundle="${rb}" var="repeatPassword"/>
<fmt:message key="profile.change.password.message" bundle="${rb}" var="changePasswordMessage"/>
<fmt:message key="profile.save.changes" bundle="${rb}" var="saveChanges"/>


<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
            <h3 class="register-heading">${changePassword}</h3>
            <div class="row register-form">
                <div class="col-md-6">
                    <p>${changePasswordMessage}</p>
                    <form name="changePasswordForm"
                          action="${pageContext.request.contextPath}/controller"
                          method="POST">
                        <input type="hidden" name="command" value="CHANGE_PASSWORD">

                        <div class="form-group">
                            <label for="input-current-password">${currentPassword}</label>
                            <input type="password" name="currentPassword" class="form-control"
                                   id="input-current-password"
                                   placeholder="${currentPassword}" value="">
                        </div>

                        <div class="form-group">
                            <label for="input-new-password">${newPassword}</label>
                            <input type="password" name="newPassword" class="form-control"
                                   id="input-new-password"
                                   placeholder="${newPassword}" value="">
                        </div>

                        <div class="form-group">
                            <label for="input-repeat-password">${repeatPassword}</label>
                            <input type="password" name="repeatPassword" class="form-control"
                                   id="input-repeat-password"
                                   placeholder="${repeatPassword}" value="">
                        </div>
                        <c:if test="${requestScope.errMessage != null}">
                            <div class="alert alert-danger">
                                        <span><fmt:message key="${requestScope.errMessage}"
                                                           bundle="${err_rb}"/></span>
                            </div>
                        </c:if>
                        <div>
                            <button type="submit" class="btn btn-primary">${saveChanges}</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<c:import url="../fragment/footer.jsp"/>
</body>
</html>

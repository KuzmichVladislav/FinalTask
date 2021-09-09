<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:setBundle basename="bundle/err" var="err_rb"/>


<fmt:message key="project.name" bundle="${rb}" var="title"/>
<fmt:message key="login.login" bundle="${rb}" var="login"/>
<fmt:message key="login.password" bundle="${rb}" var="password"/>
<fmt:message key="login.login" bundle="${rb}" var="formLogin"/>
<fmt:message key="login.password" bundle="${rb}" var="formPassword"/>
<fmt:message key="account.profile.name" bundle="${rb}" var="formName"/>
<fmt:message key="account.profile.surname" bundle="${rb}" var="formLastName"/>
<fmt:message key="form.phone" bundle="${rb}" var="formPhone"/>
<fmt:message key="form.mail" bundle="${rb}" var="formMail"/>
<fmt:message key="login.button.login" bundle="${rb}" var="buttonLogin"/>
<fmt:message key="project.navigation.home" bundle="${rb}" var="home"/>
<fmt:message key="form.login.placeholder" bundle="${rb}" var="loginPlaceholder"/>
<fmt:message key="form.name.placeholder" bundle="${rb}" var="namePlaceholder"/>
<fmt:message key="form.surname.placeholder" bundle="${rb}" var="surnamePlaceholder"/>
<fmt:message key="form.password.placeholder" bundle="${rb}" var="passwordPlaceholder"/>
<fmt:message key="form.confirm.password.placeholder" bundle="${rb}" var="confirmPasswordPlaceholder"/>
<fmt:message key="form.phone.placeholder" bundle="${rb}" var="phonePlaceholder"/>
<fmt:message key="form.mail.placeholder" bundle="${rb}" var="mailPlaceholder"/>
<fmt:message key="form.sign.up.register" bundle="${rb}" var="register"/>
<fmt:message key="form.sign.up.tagline" bundle="${rb}" var="tagline"/>
<fmt:message key="form.sign.up.motivation.message" bundle="${rb}" var="motivationMessage"/>
<fmt:message key="form.sign.up.message" bundle="${rb}" var="signUpMessage"/>
<fmt:message key="form.log.in.message" bundle="${rb}" var="logInMessage"/>

<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


</head>
<body>
<c:import url="../../fragment/navbar.jsp"/>

<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<div class="container emp-profile">

    <div class="main">
        <div class="container">
            <div class="row">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-10">
                    <div class="main-section">
                        <div class="create-order-title">
                            <h2>${formTitle}</h2>
                        </div>
                        <div class="create-order-form">
                            <form name="createOrderForm" action="${pageContext.request.contextPath}/controller"
                                  method="POST" accept-charset="UTF-8">
                                <input type="hidden" name="command" value="CREATE_NEW_ORDER">
                                <div class="form-group">
                                    <label for="inputTrainerId">${formTrainers}</label>
                                    <select id="inputTrainerId" class="form-control" name="trainerId">
                                        <c:forEach items="${requestScope.trainers}" var="trainer">
                                            <jsp:useBean id="trainer" class="com.company.gum.entity.Trainer"/>
                                            <option value="${trainer.id}">${trainer.surname} ${trainer.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="input-start-date">${formStartDate}</label>
                                    <input type="date" name="startDate" class="form-control create-order-form-date"
                                           id="input-start-date">
                                </div>
                                <div class="form-group" id="training">
                                    <label for="duration">${formDuration}</label>
                                    <select id="duration" class="form-control" name="duration">
                                        <option value="0">${trainingDurationDay}</option>
                                        <option value="1">${trainingDurationWeek}</option>
                                        <option value="2">${trainingDurationMonth}</option>
                                        <option value="3">${trainingDurationThreeMonths}</option>
                                        <option value="4">${trainingDurationHalfYear}</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="input-comment">${formComment}</label>
                                    <textarea name="comment" class="form-control" id="input-comment"
                                              placeholder="${formPlaceholderComment}"></textarea>
                                </div>
                                <button type="submit" class="btn btn-primary">${formSubmit}</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="../../fragment/footer.jsp"/>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:setBundle basename="bundle/err" var="err_rb"/>


<fmt:message key="project.name" bundle="${rb}" var="title"/>
<fmt:message key="form.login.placeholder" bundle="${rb}" var="loginPlaceholder"/>
<fmt:message key="form.name.placeholder" bundle="${rb}" var="namePlaceholder"/>
<fmt:message key="form.surname.placeholder" bundle="${rb}" var="lastNamePlaceholder"/>
<fmt:message key="form.password.placeholder" bundle="${rb}" var="passwordPlaceholder"/>
<fmt:message key="form.confirm.password.placeholder" bundle="${rb}" var="confirmPasswordPlaceholder"/>
<fmt:message key="form.phone.placeholder" bundle="${rb}" var="phonePlaceholder"/>
<fmt:message key="form.mail.placeholder" bundle="${rb}" var="mailPlaceholder"/>
<fmt:message key="form.sign.up.register" bundle="${rb}" var="register"/>
<fmt:message key="form.sign.up.tagline" bundle="${rb}" var="tagline"/>
<fmt:message key="form.sign.up.motivation.message" bundle="${rb}" var="motivationMessage"/>
<fmt:message key="form.sign.up.message" bundle="${rb}" var="signUpMessage"/>

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
                    <h3 class="register-heading">${signUpMessage}</h3>
                    <form name="signUpForm" action="${pageContext.request.contextPath}/controller" method="POST"
                          id="form">
                        <div class="row register-form">
                            <div class="col-md-6">
                                <input type="hidden" name="command" value="sign_up">

                                <div class="form-group">
                                    <label for="input-login"></label>
                                    <input type="text" name="userLogin" class="form-control" id="input-login"
                                           placeholder="${loginPlaceholder}" value="">
                                </div>


                                <div class="form-group">
                                    <label for="input-name"></label>
                                    <input type="text" name="userName" class="form-control" id="input-name"
                                           placeholder="${namePlaceholder}" value="">
                                </div>


                                <div class="form-group">
                                    <label for="input-last-name"></label>
                                    <input type="text" name="userLastName" class="form-control" id="input-last-name"
                                           placeholder="${lastNamePlaceholder}" value="">
                                </div>


                                <div class="form-group">
                                    <label for="input-phone"></label>
                                    <input type="text" name="userPhone" class="form-control" id="input-phone"
                                           placeholder="${phonePlaceholder}" value="">
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="input-mail"></label>
                                    <input type="email" name="userMail" class="form-control" id="input-mail"
                                           placeholder="${mailPlaceholder}" value="">
                                </div>


                                <div class="form-group">
                                    <label for="input-password"></label>
                                    <input type="password" name="userPassword" class="form-control" id="input-password"
                                           placeholder="${passwordPlaceholder}" value="">
                                </div>


                                <div class="form-group">
                                    <label for="input-repeat-password"></label>
                                    <input type="password" name="repeatPassword" class="form-control"
                                           id="input-repeat-password" placeholder="${confirmPasswordPlaceholder}"
                                           value="">
                                </div>
                                <c:if test="${requestScope.errMessage != null}">
                                    <div class="alert alert-danger">
                                        <span><fmt:message key="${requestScope.errMessage}" bundle="${err_rb}"/></span>
                                    </div>
                                </c:if>
                                <input type="submit" class="btnRegister" id="submit" value="${register}">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <c:import url="../fragment/footer.jsp"/>
    </body>
</html>

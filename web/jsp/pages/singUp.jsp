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
<fmt:message key="account.profile.last.name" bundle="${rb}" var="formLastName"/>
<fmt:message key="form.phone" bundle="${rb}" var="formPhone"/>
<fmt:message key="form.mail" bundle="${rb}" var="formMail"/>
<fmt:message key="login.button.login" bundle="${rb}" var="buttonLogin"/>
<fmt:message key="project.navigation.home" bundle="${rb}" var="home"/>
<fmt:message key="form.login.placeholder" bundle="${rb}" var="loginPlaceholder"/>
<fmt:message key="form.name.placeholder" bundle="${rb}" var="namePlaceholder"/>
<fmt:message key="form.last.name.placeholder" bundle="${rb}" var="lastNamePlaceholder"/>
<fmt:message key="form.password.placeholder" bundle="${rb}" var="passwordPlaceholder"/>
<fmt:message key="form.confirm.password.placeholder" bundle="${rb}" var="confirmPasswordPlaceholder"/>
<fmt:message key="form.phone.placeholder" bundle="${rb}" var="phonePlaceholder"/>
<fmt:message key="form.mail.placeholder" bundle="${rb}" var="mailPlaceholder"/>
<fmt:message key="form.sign.up.register" bundle="${rb}" var="register"/>
<fmt:message key="form.sign.up.tagline" bundle="${rb}" var="tagline"/>
<fmt:message key="form.sign.up.motivation.message" bundle="${rb}" var="motivationMessage"/>

<!DOCTYPE html>
<html>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <title>Sign up facundo farm & resort</title>
    </head>

    <body>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

        <div class="container register">
            <div class="row">
                <div class="col-md-3 register-left">
                    <img src="https://i.ibb.co/SsQJHTz/pngegg.png"
                         alt="">
                    <h3>${tagline}</h3>
                    <p>${motivationMessage}</p>
                    <input type="submit" name="" value="Login"><br>
                </div>

                <div class="col-md-9 register-right">
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
                                           id="input-repeat-password" placeholder="${confirmPasswordPlaceholder}" value="">
                                </div>
                                <input type="submit" class="btnRegister" id="submit" value="${register}">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>


<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:setBundle basename="bundle/err" var="err_rb"/>


<fmt:message key="project.name" bundle="${rb}" var="title"/>
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
<fmt:message key="account.profile.name" bundle="${rb}" var="name"/>
<fmt:message key="login.login" bundle="${rb}" var="login"/>
<fmt:message key="invalid.login" bundle="${rb}" var="invalidLogin"/>
<fmt:message key="invalid.password" bundle="${rb}" var="invalidPassword"/>
<fmt:message key="login.password" bundle="${rb}" var="password"/>
<fmt:message key="login.looks.good" bundle="${rb}" var="looksGood"/>
<fmt:message key="account.profile.surname" bundle="${rb}" var="surname"/>
<fmt:message key="form.phone" bundle="${rb}" var="phone"/>
<fmt:message key="form.mail" bundle="${rb}" var="mail"/>
<fmt:message key="form.enter.valid.mail" bundle="${rb}" var="enterValidMail"/>
<fmt:message key="form.confirm.password" bundle="${rb}" var="confirmPassword"/>
<fmt:message key="agree.to.terms.and.conditions" bundle="${rb}" var="agreeToTermsAndConditions"/>
<fmt:message key="agree.before.submitting" bundle="${rb}" var="agreeBeforeSubmitting"/>
<fmt:message key="form.sign.up.register" bundle="${rb}" var="register"/>



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
                    <div class="row register-form">

                        <form class="needs-validation" novalidate name="registerForm"
                              action="${pageContext.request.contextPath}/controller" method="POST" style="width:500px;">
                            <input type="hidden" name="command" value="register_trainer">

                            <div class="form-group">
                                <div class="col-md-6 mb-3">
                                    <label for="validationCustom01">${login}</label>
                                    <input type="text" name="userLogin" class="form-control" id="validationCustom01"
                                           placeholder="${loginPlaceholder}" required
                                           pattern="[A-Za-z0-9]{3,16}">
                                    <div class="invalid-feedback">
                                        ${invalidLogin}
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-6 mb-3">
                                    <label for="validationCustom02">${name}</label>
                                    <input type="text" name="userName" class="form-control" id="validationCustom02"
                                           placeholder="${namePlaceholder}" value="" required
                                           pattern="^[A-ZА-Я]{1}[a-zа-я]{2,20}$">
                                    <div class="valid-feedback">
                                        ${looksGood}
                                    </div>
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="col-md-6 mb-3">
                                    <label for="validationCustom03">${surname}</label>
                                    <input type="text" name="userSurname" class="form-control" id="validationCustom03"
                                           placeholder="${surnamePlaceholder}" value="" required
                                           pattern="^[A-ZА-Я]{1}[a-zа-я]{2,20}$">
                                    <div class="valid-feedback">
                                        ${looksGood}
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-6 mb-3">
                                    <label for="validationCustom04">${phone}</label>
                                    <input type="text" name="userPhone" class="form-control" id="validationCustom04"
                                           placeholder="${phonePlaceholder}" value="" required
                                           pattern="^[+0-9]{1}[0-9]{8,12}$">
                                    <div class="valid-feedback">
                                        ${looksGood}
                                    </div>
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="col-md-6 mb-3">
                                    <label for="validationCustom05">${mail}</label>
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="inputGroupPrepend">@</span>
                                        </div>
                                        <input type="email" name="userMail" class="form-control" id="validationCustom05"
                                               placeholder="${mailPlaceholder}" aria-describedby="inputGroupPrepend" required
                                               pattern="[_0-9a-z][-_.0-9a-z]*@[0-9a-z][-.0-9a-z]*[0-9a-z]\.[a-z]{2,}">
                                        <div class="invalid-feedback">
                                            ${enterValidMail}
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="col-md-6 mb-3">
                                    <label for="validationCustom06">${password}</label>
                                    <input type="password" name="userPassword" class="form-control" id="validationCustom06"
                                           placeholder=${passwordPlaceholder}
                                           required
                                           pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}">
                                    <div class="invalid-feedback">
                                        ${invalidPassword}
                                    </div>
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="col-md-6 mb-3">
                                    <label for="validationCustom07">${confirmPassword}</label>
                                    <input type="password" name="repeatPassword" class="form-control" id="validationCustom07"
                                           placeholder=${confirmPasswordPlaceholder}
                                           required
                                           pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}">
                                    <div class="invalid-feedback">
                                        ${invalidPassword}
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
                                    <label class="form-check-label" for="invalidCheck">
                                        ${agreeToTermsAndConditions}
                                    </label>
                                    <div class="invalid-feedback">
                                        ${agreeBeforeSubmitting}
                                    </div>
                                </div>
                            </div>

                            <c:if test="${requestScope.errMessage != null}">
                                <div class="form-group">
                                    <div class="col-md-6 mb-3">
                                        <div class="err-message-from-server">
                                            <fmt:message key="${requestScope.errMessage}" bundle="${rb}"/>
                                        </div>
                                    </div>
                                </div>
                            </c:if>

                            <div class="form-group">
                                <div class="col-md-6 mb-3">
                                    <button type="submit" class="btn btn-primary">
                                        ${register}
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <script>
                        // Пример стартового JavaScript для отключения отправки форм при наличии недопустимых полей
                        (function () {
                            'use strict'

                            // Получите все формы, к которым мы хотим применить пользовательские стили проверки Bootstrap
                            var forms = document.querySelectorAll('.needs-validation')

                            // Зацикливайтесь на них и предотвращайте отправку
                            Array.prototype.slice.call(forms)
                                .forEach(function (form) {
                                    form.addEventListener('submit', function (event) {
                                        if (!form.checkValidity()) {
                                            event.preventDefault()
                                            event.stopPropagation()
                                        }
                                        form.classList.add('was-validated')
                                    }, false)
                                })
                        })()
                    </script>
                </div>
            </div>
        </div>
        <c:import url="../../fragment/footer.jsp"/>
    </body>
</html>

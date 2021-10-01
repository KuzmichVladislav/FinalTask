<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

<fmt:message key="project.name" bundle="${rb}" var="title"/>
<fmt:message key="login.button.login" bundle="${rb}" var="buttonLogin"/>
<fmt:message key="form.login.placeholder" bundle="${rb}" var="loginPlaceholder"/>
<fmt:message key="form.password.placeholder" bundle="${rb}" var="passwordPlaceholder"/>
<fmt:message key="form.sign.up.tagline" bundle="${rb}" var="tagline"/>
<fmt:message key="form.sign.up.motivation.message" bundle="${rb}" var="motivationMessage"/>
<fmt:message key="form.log.in.message" bundle="${rb}" var="logInMessage"/>
<fmt:message key="invalid.login" bundle="${rb}" var="invalidLogin"/>
<fmt:message key="invalid.password" bundle="${rb}" var="invalidPassword"/>
<fmt:message key="login.login" bundle="${rb}" var="login"/>
<fmt:message key="login.password" bundle="${rb}" var="password"/>

<html>
<head>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>${title}</title>

</head>
<body>
<c:import url="fragment/navbar.jsp"/>

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
            <h3 class="register-heading">${logInMessage}</h3>
            <div class="row register-form">
                <form class="needs-validation" novalidate name="loginForm"
                      action="${pageContext.request.contextPath}/controller" method="POST" style="width:500px;">
                    <input type="hidden" name="command" value="login">

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
                            <label for="validationCustom02">${password}</label>
                            <input type="password" name="userPassword" class="form-control" id="validationCustom02"
                                   placeholder=${passwordPlaceholder}
                                           required
                                   pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}">
                            <div class="invalid-feedback">
                                ${invalidPassword}
                            </div>
                        </div>
                    </div>

                    <c:if test="${requestScope.errorMessage != null}">
                        <div class="form-group">
                            <div class="col-md-6 mb-3">
                                <div class="err-message-from-server">
                                    <fmt:message key="${requestScope.errorMessage}" bundle="${rb}"/>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <div class="form-group">
                        <div class="col-md-6 mb-3">
                            <button type="submit" class="btn btn-primary">
                                ${buttonLogin}
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
<c:import url="fragment/footer.jsp"/>
</body>
</html>

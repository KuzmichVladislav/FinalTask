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
<fmt:message key="form.sign.up.message" bundle="${rb}" var="signUpMessage"/>
<fmt:message key="form.log.in.message" bundle="${rb}" var="logInMessage"/>

<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">

        <%--    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">--%>
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
                    <%--            <img src="https://i.ibb.co/SsQJHTz/pngegg.png"                 alt="">--%>
                    <h3>${tagline}</h3>
                    <p>${motivationMessage}</p>
                    <input type="submit" name="" value="Login"><br>
                </div>
                <div class="col-md-9 register-right">
                    <ul class="nav nav-tabs nav-justified" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab"
                               aria-controls="home" aria-selected="true">${buttonLogin}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab"
                               aria-controls="profile" aria-selected="false">${register}</a>
                        </li>
                    </ul>
                    <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                            <h3 class="register-heading">${logInMessage}</h3>
                            <form name="loginForm" action="${pageContext.request.contextPath}/controller" method="POST">

                                <%--                       some new--%>
                                <%--                        <form>--%>
                                <%--                            <div class="form-row">--%>
                                <%--                                <div class="col-md-4 mb-3">--%>
                                <%--                                    <label for="validationDefault01">First name</label>--%>
                                <%--                                    <input type="text" class="form-control" id="validationDefault01"--%>
                                <%--                                           placeholder="First name" value="Mark" required>--%>
                                <%--                                </div>--%>
                                <%--                                <div class="col-md-4 mb-3">--%>
                                <%--                                    <label for="validationDefault02">Last name</label>--%>
                                <%--                                    <input type="text" class="form-control" id="validationDefault02"--%>
                                <%--                                           placeholder="Last name" value="Otto" required>--%>
                                <%--                                </div>--%>
                                <%--                                <div class="col-md-4 mb-3">--%>
                                <%--                                    <label for="validationDefaultUsername">Username</label>--%>
                                <%--                                    <div class="input-group">--%>
                                <%--                                        <div class="input-group-prepend">--%>
                                <%--                                            <span class="input-group-text" id="inputGroupPrepend2">@</span>--%>
                                <%--                                        </div>--%>
                                <%--                                        <input type="text" class="form-control" id="validationDefaultUsername"--%>
                                <%--                                               placeholder="Username" aria-describedby="inputGroupPrepend2" required>--%>
                                <%--                                    </div>--%>
                                <%--                                </div>--%>
                                <%--                            </div>--%>
                                <%--                            <div class="form-row">--%>
                                <%--                                <div class="col-md-6 mb-3">--%>
                                <%--                                    <label for="validationDefault03">City</label>--%>
                                <%--                                    <input type="text" class="form-control" id="validationDefault03" placeholder="City"--%>
                                <%--                                           required>--%>
                                <%--                                </div>--%>
                                <%--                                <div class="col-md-3 mb-3">--%>
                                <%--                                    <label for="validationDefault04">State</label>--%>
                                <%--                                    <input type="text" class="form-control" id="validationDefault04" placeholder="State"--%>
                                <%--                                           required>--%>
                                <%--                                </div>--%>
                                <%--                                <div class="col-md-3 mb-3">--%>
                                <%--                                    <label for="validationDefault05">Zip</label>--%>
                                <%--                                    <input type="text" class="form-control" id="validationDefault05" placeholder="Zip"--%>
                                <%--                                           required>--%>
                                <%--                                </div>--%>
                                <%--                            </div>--%>
                                <%--                            <div class="form-group">--%>
                                <%--                                <div class="form-check">--%>
                                <%--                                    <input class="form-check-input" type="checkbox" value="" id="invalidCheck2"--%>
                                <%--                                           required>--%>
                                <%--                                    <label class="form-check-label" for="invalidCheck2">--%>
                                <%--                                        Agree to terms and conditions--%>
                                <%--                                    </label>--%>
                                <%--                                </div>--%>
                                <%--                            </div>--%>
                                <%--                            <button class="btn btn-primary" type="submit">Submit form</button>--%>
                                <%--                        </form>--%>
                                <%--                       some new--%>
                                <input type="hidden" name="command" value="login">
                                <div class="row register-form">
                                    <div class="col-md-6">
                                        <input type="hidden" name="command" value="sign_up">

                                        <div class="form-group">
                                            <label for="input-login"></label>
                                            <input type="text" name="userLogin" class="form-control" id="input-login"
                                                   placeholder="${loginPlaceholder}" value="">
                                        </div>

                                        <div class="form-group">
                                            <label for="input-password"></label>
                                            <input type="password" name="userPassword" class="form-control" id="input-password"
                                                   placeholder="${passwordPlaceholder}" value="">
                                        </div>
                                        <input type="submit" class="btnRegister" id="submit" value="${buttonLogin}">
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="tab-pane fade show" id="profile" role="tabpanel" aria-labelledby="profile-tab">
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
                                            <div class="alert alert-danger ">
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
            </div>
        </div>
        <footer class="container"
                style="min-height:200px; background-color:#18bc9c;color:#fff;text-align:center;padding-top:50px;">
            INSPIRED BY PURECSS.IO © 2015
        </footer>
    </body>
</html>

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
<fmt:message key="profile.details" bundle="${rb}" var="profileDetails"/>
<fmt:message key="profile.about" bundle="${rb}" var="profileAbout"/>
<fmt:message key="profile.edit" bundle="${rb}" var="profileEdit"/>
<fmt:message key="profile.work.links" bundle="${rb}" var="workLinks"/>
<fmt:message key="profile.new.order" bundle="${rb}" var="newOrder"/>
<fmt:message key="profile.user.login" bundle="${rb}" var="userLogin"/>
<fmt:message key="profile.full.name" bundle="${rb}" var="fullName"/>
<fmt:message key="profile.mail" bundle="${rb}" var="mail"/>
<fmt:message key="profile.phone" bundle="${rb}" var="phone"/>
<fmt:message key="profile.status" bundle="${rb}" var="status"/>
<fmt:message key="profile.register.date" bundle="${rb}" var="registerDate"/>
<fmt:message key="profile.money" bundle="${rb}" var="money"/>
<fmt:message key="profile.discount" bundle="${rb}" var="discount"/>
<fmt:message key="profile.discount.level" bundle="${rb}" var="discountLevel"/>
<fmt:message key="profile.upload.profile.image" bundle="${rb}" var="uploadProfileImage"/>
<fmt:message key="profile.name" bundle="${rb}" var="name"/>
<fmt:message key="profile.surname" bundle="${rb}" var="surname"/>
<fmt:message key="profile.confirm" bundle="${rb}" var="confirm"/>
<fmt:message key="profile.my.orders" bundle="${rb}" var="myOrder"/>
<fmt:message key="form.money.refill" bundle="${rb}" var="moneyRefill"/>
<fmt:message key="profile.change.password" bundle="${rb}" var="changePassword"/>

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
            <form method="post">
                <div class="row">
                    <div class="col-md-4">

                        <div class="profile-img">
                                <img src="${sessionScope.userPhoto}"/>
                        </div>
                    </div>
                    <div class="col-md-6" style="max-width:42%;">
                        <div class="profile-head">
                            <h5>
                                ${sessionScope.userName} ${sessionScope.userSurname}
                            </h5>
                            <h6>
                                ${sessionScope.userRole}
                            </h6>
                            <p class="proile-rating">${profileDetails}</p>
                            <ul class="nav nav-tabs" id="myTab" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab"
                                       aria-controls="home" aria-selected="true">${profileAbout}</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab"
                                       aria-controls="profile" aria-selected="false">${profileEdit}</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <a href="${pageContext.request.contextPath}/jsp/pages/change_password.jsp">
                            <button type="button" class="btn btn-primary">
                                ${changePassword}
                            </button>
                        </a>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-work">
                            <p>${workLinks}</p>
                            <a href="${pageContext.request.contextPath}/controller?command=new_order">${newOrder}</a>
                            <a href="${pageContext.request.contextPath}/controller?command=show_all_orders_by_client">${myOrder}</a><br/>
                            <a href="${pageContext.request.contextPath}/jsp/pages/client/refill_money.jsp">${moneyRefill}</a><br/>
                            <a href="">Bootply Profile</a>
                            <p>SKILLS</p>
                            <a href="">Web Designer</a><br/>
                            <a href="">Web Developer</a><br/>
                            <a href="">WordPress</a><br/>
                            <a href="">WooCommerce</a><br/>
                            <a href="">PHP, .Net</a><br/>
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="tab-content profile-tab" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>${userLogin}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>${sessionScope.userLogin}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>${fullName}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p> ${sessionScope.userName} ${sessionScope.userSurname}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>${mail}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>${sessionScope.userMail}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>${phone}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>${sessionScope.userPhone}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>${status}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>${sessionScope.userRole}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>${registerDate}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>${sessionScope.userRegisterDate}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>${money}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>${sessionScope.userMoney} BYN</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>${discount}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>${sessionScope.userDiscount} %</p>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                                <form method="post" action="${pageContext.request.contextPath}/uploadServlet"
                                      enctype="multipart/form-data" class="upload-form">
                                    <div class="form-group">
                                        <input type="hidden" name="command" value="upload_image">
                                        <div class="col-lg-4">
                                        </div>
                                    </div>
                                </form>
                                ${uploadProfileImage}
                                <form method="post" action="${pageContext.request.contextPath}/uploadServlet"
                                      enctype="multipart/form-data" class="upload-form">
                                    <input type="hidden" name="command" value="upload_image">
                                    <input type="file" class="btn btn-primary" name="multiPartServlet" id="file"
                                           onchange="this.form.submit();"
                                           accept="image/jpeg,image/png,image/gif"/>
                                </form>
                                <form name="profileEditForm" action="${pageContext.request.contextPath}/controller"
                                      method="POST">
                                    <input type="hidden" name="command" value="edit_client_profile">
                                    <input type="hidden" name="userId" value="${sessionScope.userId}">
                                    <div class="form-group">
                                        <label for="input-name">${name}</label>
                                        <input type="text" name="userName" class="form-control" id="input-name"
                                               placeholder="${name}" value="">
                                    </div>

                                    <div class="form-group">
                                        <label for="input-surname">${surname}</label>
                                        <input type="text" name="userSurname" class="form-control" id="input-surname"
                                               placeholder="${surname}" value="">
                                    </div>

                                    <div class="form-group">
                                        <label for="input-phone">${phone}</label>
                                        <input type="text" name="userPhone" class="form-control" id="input-phone"
                                               placeholder="${phone}" value="">
                                    </div>

                                    <div class="form-group">
                                        <label for="input-mail">${mail}</label>
                                        <input type="email" name="userMail" class="form-control" id="input-mail"
                                               placeholder="${mail}" value="">
                                    </div>
                                    <c:if test="${requestScope.errMessage != null}">
                                        <div class="alert alert-danger">
                                            <span><fmt:message key="${requestScope.errMessage}" bundle="${err_rb}"/></span>
                                        </div>
                                    </c:if>
                                    <button type="submit" class="btn btn-primary">${confirm}</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <c:import url="../../fragment/footer.jsp"/>
    </body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

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
<fmt:message key="user.profile.details" bundle="${rb}" var="userProfileDetails"/>
<fmt:message key="profile.about" bundle="${rb}" var="profileAbout"/>
<fmt:message key="assign.discount" bundle="${rb}" var="assignDiscount"/>
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
<fmt:message key="profile.close" bundle="${rb}" var="close"/>
<fmt:message key="profile.description" bundle="${rb}" var="description"/>
<fmt:message key="profile.description.programs" bundle="${rb}" var="descriptionPrograms"/>
<fmt:message key="profile.experience" bundle="${rb}" var="experience"/>
<fmt:message key="profile.experience.trainer" bundle="${rb}" var="experienceTrainer"/>
<fmt:message key="project.name" bundle="${rb}" var="title"/>

<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>${title}</title>
</head>
<body>
<c:import url="../fragment/navbar.jsp"/>

<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<c:choose>
    <c:when test="${requestScope.user.role == 'TRAINER'}">
        <div class="container emp-profile">
            <c:set var="trainer" value="${requestScope.user}"/>
            <jsp:useBean id="trainer" type="com.company.gum.model.entity.Trainer"/>

            <form method="post">
                <div class="row">
                    <div class="col-md-4">

                        <div class="profile-img">
                                <img src="${trainer.base64Image}"/>
                        </div>
                    </div>
                    <div class="col-md-6" style="max-width:42%;">
                        <div class="profile-head">
                            <h5>
                                    ${trainer.name} ${trainer.surname}
                            </h5>
                            <h6>
                                    ${trainer.role}
                            </h6>
                            <p class="proile-rating">${userProfileDetails}</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                    </div>
                    <div class="col-md-8">
                        <div class="tab-content profile-tab" id="trainerTabContent">
                            <div class="tab-pane fade show active" id="trainer_home" role="tabpanel"
                                 aria-labelledby="trainer-home-tab">
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>${userLogin}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>${trainer.login}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>${fullName}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p> ${trainer.name} ${trainer.surname}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>${mail}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>${trainer.mail}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>${phone}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>${trainer.phone}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>${status}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>${trainer.role}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>${registerDate}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>${trainer.registerDate}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <!-- Button trigger modal -->
                                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                                data-target="#descriptionModal">
                                                ${description}
                                        </button>

                                        <!-- Modal -->
                                        <div class="modal fade" id="descriptionModal" tabindex="-1" role="dialog"
                                             aria-labelledby="descriptionModalLabel" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title"
                                                            id="descriptionModalLabel">${descriptionPrograms}</h5>
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                            ${trainer.description}
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary"
                                                                data-dismiss="modal">
                                                                ${close}
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <!-- Button trigger modal -->
                                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                                data-target="#experienceModal">
                                                ${experience}
                                        </button>
                                        <!-- Modal -->
                                        <div class="modal fade" id="experienceModal" tabindex="-1" role="dialog"
                                             aria-labelledby="experienceModalLabel" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title"
                                                            id="experienceModalLabel">${experienceTrainer}</h5>
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                            ${trainer.experience}
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary"
                                                                data-dismiss="modal">
                                                                ${close}
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </c:when>
    <c:when test="${requestScope.user.role == 'CLIENT'}">
        <div class="container emp-profile">
            <c:set var="client" value="${requestScope.user}"/>
            <jsp:useBean id="client" type="com.company.gum.model.entity.Client"/>

            <form method="post">
                <div class="row">
                    <div class="col-md-4">

                        <div class="profile-img">
                                <img src="${client.base64Image}"/>
                        </div>
                    </div>
                    <div class="col-md-6" style="max-width:42%;">
                        <div class="profile-head">
                            <h5>
                                    ${client.name} ${client.surname}
                            </h5>
                            <h6>
                                    ${client.role}
                            </h6>
                            <p class="proile-rating">${userProfileDetails}</p>
                            <ul class="nav nav-tabs" id="myTab" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab"
                                       aria-controls="home" aria-selected="true">${profileAbout}</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab"
                                       aria-controls="profile" aria-selected="false">${assignDiscount}</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                    </div>
                    <div class="col-md-8">
                        <div class="tab-content profile-tab" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>${userLogin}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>${client.login}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>${fullName}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p> ${client.name} ${client.surname}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>${mail}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>${client.mail}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>${phone}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>${client.phone}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>${status}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>${client.role}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>${registerDate}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>${client.registerDate}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>${money}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>${client.money} BYN</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>${discount}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>${client.discount} %</p>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">

                                <form name="loginForm"
                                      action="${pageContext.request.contextPath}/controller"
                                      method="POST">

                                </form>
                                <div class="col-md-6">
                                    <form name="refill_money_form"
                                          action="${pageContext.request.contextPath}/controller" method="POST">
                                        <input type="hidden" name="command" value="ASSIGN_DISCOUNT">
                                        <input type="hidden" name="clientId" value="${client.id}">

                                        <div class="form-group">
                                            <label for="input_money">${discount}</label>
                                            <input type="text" name="discount" class="form-control" id="input_money"
                                                   value="${client.discount}">
                                        </div>
                                        <c:if test="${requestScope.errMessage != null}">
                                            <div class="alert alert-danger">
                                                        <span><fmt:message key="${requestScope.errMessage}"
                                                                           bundle="${err_rb}"/></span>
                                            </div>
                                        </c:if>
                                        <button type="submit"
                                                class="btn btn-primary">${confirm}
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </c:when>
</c:choose>
<c:import url="../fragment/footer.jsp"/>
</body>
</html>

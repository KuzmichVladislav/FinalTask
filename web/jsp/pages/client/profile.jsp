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
    <form method="post">
        <div class="row">
            <div class="col-md-4">
                <div class="profile-img">
                    <img src="${sessionScope.profileImage}"/>
                    <form id="my_form" method="post" action="${pageContext.request.contextPath}/upload"
                          enctype="multipart/form-data" class="upload-form">
                        <div class="file btn btn-lg btn-primary">
                            <input type="hidden" name="command" value="upload_image">
                            Change Photo
                            <input type="file" name="photo" id="file" onchange="submit_form()"
                                   accept="image/jpeg,image/png,image/gif"/>
                        </div>
                    </form>
                    <script>
                        function submit_form() {
                            var form = document.getElementById("my_form");
                            form.submit();
                        }
                    </script>

                </div>
            </div>
            <div class="col-md-6">
                <div class="profile-head">
                    <h5>
                        ${sessionScope.userName} ${sessionScope.userLastName}
                    </h5>
                    <h6>
                        ${sessionScope.userRole}
                    </h6>
                    <p class="proile-rating">Details about you:</p>
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab"
                               aria-controls="home" aria-selected="true">About</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab"
                               aria-controls="profile" aria-selected="false">Edit profile</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-md-2">
                <!-- Button trigger modal -->
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                    Change password
                </button>
                <!-- Modal -->
                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
                     aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Change password</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form name="changePasswordForm" action="${pageContext.request.contextPath}/controller"
                                      method="POST">
                                    <input type="hidden" name="command" value="CHANGE_PASSWORD">
                                    <input type="hidden" name="userId" value="${sessionScope.userId}">


                                    <div class="form-group">
                                        <label for="input-phone">Current password</label>
                                        <input type="text" name="currentPassword" class="form-control"
                                               id="input-current-password"
                                               placeholder="${currentPasswordPlaceholder}" value="">
                                    </div>

                                    <div class="form-group">
                                        <label for="input-phone">New password</label>
                                        <input type="text" name="newPassword" class="form-control"
                                               id="input-new-password"
                                               placeholder="${newPasswordPlaceholder}" value="">
                                    </div>

                                    <div class="form-group">
                                        <label for="input-phone">Repeat password</label>
                                        <input type="text" name="repeatPassword" class="form-control"
                                               id="input-repeat-password"
                                               placeholder="${repeatPasswordPlaceholder}" value="">
                                    </div>

                                    <c:if test="${requestScope.errMessage != null}">
                                        <div class="alert alert-danger">
                                            <span><fmt:message key="${requestScope.errMessage}"
                                                               bundle="${err_rb}"/></span>
                                        </div>
                                    </c:if>
                                    <%--                                    <button type="submit" class="btn btn-primary">${submit}</button>--%>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close
                                        </button>
                                        <button type="submit" class="btn btn-primary">Save changes</button>
                                    </div>
                                </form>
                            </div>

                        </div>
                    </div>
                </div>
                <%--                <input type="submit" class="profile-edit-btn" name="btnAddMore" value="Edit Profile"/>--%>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4">
                <div class="profile-work">
                    <p>WORK LINK</p>
                    <a href="${pageContext.request.contextPath}/controller?command=new_order">New order</a>
                    <a href="">Website Link</a><br/>
                    <a href="">Bootsnipp Profile</a><br/>
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
                        <form name="profileEditForm" action="${pageContext.request.contextPath}/controller"
                              method="POST">
                            <input type="hidden" name="command" value="EDIT_CLIENT_PROFILE">
                            <input type="hidden" name="userId" value="${sessionScope.userId}">
                            <div class="row">
                                <div class="col-md-6">
                                    <label>User Login</label>
                                </div>
                                <div class="col-md-6">
                                    <p>${sessionScope.userLogin}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <label>Full name</label>
                                </div>
                                <div class="col-md-6">
                                    <p> ${sessionScope.userName} ${sessionScope.userLastName}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <label>Mail</label>
                                </div>
                                <div class="col-md-6">
                                    <p>${sessionScope.userMail}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <label>Phone</label>
                                </div>
                                <div class="col-md-6">
                                    <p>${sessionScope.userPhone}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <label>Status</label>
                                </div>
                                <div class="col-md-6">
                                    <p>${sessionScope.userRole}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <label>Register date</label>
                                </div>
                                <div class="col-md-6">
                                    <p>${sessionScope.userRegisterDate}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <label>Money</label>
                                </div>
                                <div class="col-md-6">
                                    <p>${sessionScope.userMoney} BYN</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <label>Discount</label>
                                </div>
                                <div class="col-md-6">
                                    <p>${sessionScope.userDiscount} %</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <label>Discount level</label>
                                </div>
                                <div class="col-md-6">
                                    <p>${sessionScope.userDiscountLevel}</p>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                        <form name="profileEditForm" action="${pageContext.request.contextPath}/controller"
                              method="POST">
                            <input type="hidden" name="command" value="EDIT_CLIENT_PROFILE">
                            <input type="hidden" name="userId" value="${sessionScope.userId}">
                            <div class="form-group">
                                <label for="input-name">Name</label>
                                <input type="text" name="userName" class="form-control" id="input-name"
                                       placeholder="${namePlaceholder}" value="">
                            </div>


                            <div class="form-group">
                                <label for="input-last-name">Surname</label>
                                <input type="text" name="userLastName" class="form-control" id="input-last-name"
                                       placeholder="${surnamePlaceholder}" value="">
                            </div>


                            <div class="form-group">
                                <label for="input-phone">Phone</label>
                                <input type="text" name="userPhone" class="form-control" id="input-phone"
                                       placeholder="${phonePlaceholder}" value="">
                            </div>

                            <div class="form-group">
                                <label for="input-mail">Mail</label>
                                <input type="email" name="userMail" class="form-control" id="input-mail"
                                       placeholder="${mailPlaceholder}" value="">
                            </div>
                            <c:if test="${requestScope.errMessage != null}">
                                <div class="alert alert-danger">
                                    <span><fmt:message key="${requestScope.errMessage}" bundle="${err_rb}"/></span>
                                </div>
                            </c:if>
                            <button type="submit" class="btn btn-primary">Confirm</button>
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

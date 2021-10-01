<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:setBundle basename="bundle/err" var="err_rb"/>

<fmt:message key="form.sign.up.tagline" bundle="${rb}" var="tagline"/>
<fmt:message key="form.sign.up.motivation.message" bundle="${rb}" var="motivationMessage"/>
<fmt:message key="order.start.date" bundle="${rb}" var="startDate"/>
<fmt:message key="order.end.date" bundle="${rb}" var="endDate"/>
<fmt:message key="order.details" bundle="${rb}" var="oderDetails"/>
<fmt:message key="order.id" bundle="${rb}" var="orderId"/>
<fmt:message key="order.trainer.name" bundle="${rb}" var="trainerFullName"/>
<fmt:message key="order.register.date" bundle="${rb}" var="registerDate"/>
<fmt:message key="order.exercises" bundle="${rb}" var="exercises"/>
<fmt:message key="order.nutrition" bundle="${rb}" var="nutrition"/>
<fmt:message key="order.your.comment" bundle="${rb}" var="yourComment"/>
<fmt:message key="project.navigation.price" bundle="${rb}" var="price"/>
<fmt:message key="profile.status" bundle="${rb}" var="status"/>
<fmt:message key="send.for.confirm" bundle="${rb}" var="sendForConfirm"/>
<fmt:message key="agree.before.confirming" bundle="${rb}" var="agreeBeforeConfirming"/>
<fmt:message key="action.is.paid" bundle="${rb}" var="actionIsPaid"/>
<fmt:message key="profile.close" bundle="${rb}" var="close"/>

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
            <h3 class="register-heading">${oderDetails}</h3>
            <div class="row register-form">
                <div class="col-md-6">
                    <c:set var="order" value="${requestScope.order}"/>
                    <jsp:useBean id="order" type="com.company.gum.entity.Order"/>

                    <div class="row">
                        <div class="col-md-6">
                            <label>${orderId}</label>
                        </div>
                        <div class="col-md-6">
                            <p>${order.id}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label>${trainerFullName}</label>
                        </div>
                        <div class="col-md-6">
                            <p> ${order.trainerName} ${order.trainerSurname}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label>${registerDate}</label>
                        </div>
                        <div class="col-md-6">
                            <p>${order.registerDate}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label>${startDate}</label>
                        </div>
                        <div class="col-md-6">
                            <p>${order.startDate}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label>${endDate}</label>
                        </div>
                        <div class="col-md-6">
                            <p>${order.endDate}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label>${yourComment}</label>
                        </div>
                        <div class="col-md-6">
                            <p>${order.clientComment}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label>${price}</label>
                        </div>
                        <div class="col-md-6">
                            <p>${order.price}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label>${status}</label>
                        </div>
                        <div class="col-md-6">
                            <p>${order.orderStatus.name()}</p>
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <c:if test="${order.orderStatus.ordinal() == 3}">
                        <div class="col-md-6">
                            <!-- Button trigger modal -->
                            <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target="#exercisesModal" style="width:200px;">
                                    ${exercises}
                            </button>

                            <!-- Modal -->
                            <div class="modal fade" id="exercisesModal" tabindex="-1" role="dialog"
                                 aria-labelledby="exercisesModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exercisesModalLabel">${exercises}</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <p>${order.exercises}</p>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary"
                                                    data-dismiss="modal">${close}
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <!-- Button trigger modal -->
                            <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target="#nutritionModal" style="margin-top:10px; width:200px;">
                                    ${nutrition}
                            </button>

                            <!-- Modal -->
                            <div class="modal fade" id="nutritionModal" tabindex="-1" role="dialog"
                                 aria-labelledby="nutritionModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="nutritionModalLabel">${nutrition}</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <p>${order.nutrition}</p>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary"
                                                    data-dismiss="modal">${close}
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <c:if test="${order.orderStatus.ordinal() == 1}">
                        <div class="col-md-6">
                            <form class="needs-validation" novalidate>
                                <input type="hidden" name="command" value="update_order_status">
                                <input type="hidden" name="orderId" value="${order.id}">
                                <input type="hidden" name="orderStatus" value="ACCEPTED">
                                <div class="form-group">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="invalidCheck"
                                               required>
                                        <label class="form-check-label" for="invalidCheck">
                                                ${actionIsPaid}
                                        </label>
                                        <div class="invalid-feedback">
                                                ${agreeBeforeConfirming}
                                        </div>
                                    </div>
                                </div>
                                <c:if test="${requestScope.errMessage != null}">
                                    <div class="err-message-from-server">
                                        <fmt:message key="${requestScope.errMessage}" bundle="${err_rb}"/>
                                    </div>
                                </c:if>
                                <button type="submit" class="btn btn-primary" style="margin-top:10px; width:200px;">
                                        ${sendForConfirm}
                                </button>
                            </form>
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
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

<c:import url="../../fragment/footer.jsp"/>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

<fmt:message key="form.sign.up.tagline" bundle="${rb}" var="tagline"/>
<fmt:message key="form.sign.up.motivation.message" bundle="${rb}" var="motivationMessage"/>
<fmt:message key="order.start.date" bundle="${rb}" var="startDate"/>
<fmt:message key="order.end.date" bundle="${rb}" var="endDate"/>
<fmt:message key="order.details" bundle="${rb}" var="oderDetails"/>
<fmt:message key="order.id" bundle="${rb}" var="orderId"/>
<fmt:message key="order.client.name" bundle="${rb}" var="clientFullName"/>
<fmt:message key="order.register.date" bundle="${rb}" var="registerDate"/>
<fmt:message key="order.exercises" bundle="${rb}" var="exercises"/>
<fmt:message key="order.nutrition" bundle="${rb}" var="nutrition"/>
<fmt:message key="order.your.comment" bundle="${rb}" var="yourComment"/>
<fmt:message key="project.navigation.price" bundle="${rb}" var="price"/>
<fmt:message key="profile.status" bundle="${rb}" var="status"/>
<fmt:message key="order.edit.nutrition" bundle="${rb}" var="editNutrition"/>
<fmt:message key="order.edit.exercises" bundle="${rb}" var="editExercises"/>
<fmt:message key="agree.to.terms.and.conditions" bundle="${rb}" var="agreeToTermsAndConditions"/>
<fmt:message key="agree.before.submitting" bundle="${rb}" var="agreeBeforeSubmitting"/>
<fmt:message key="profile.close" bundle="${rb}" var="close"/>
<fmt:message key="send.for.confirm" bundle="${rb}" var="sendForConfirm"/>
<fmt:message key="save" bundle="${rb}" var="save"/>
<fmt:message key="client.comment" bundle="${rb}" var="clientComment"/>
<fmt:message key="project.name" bundle="${rb}" var="title"/>

<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <title>${title}</title>
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
            <h3 class="register-heading">${oderDetails}</h3>
            <div class="row register-form">
                <div class="col-md-6">
                    <c:set var="order" value="${requestScope.order}"/>
                    <jsp:useBean id="order" type="com.company.gum.model.entity.Order"/>

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
                            <label>${clientFullName}</label>
                        </div>
                        <div class="col-md-6">
                            <p> ${order.clientName} ${order.clientSurname}</p>
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
                            <label>${clientComment}</label>
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

                    <div class="row">
                        <div class="col-md-6">
                            <!-- Button trigger modal -->
                            <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target="#exercisesModal">
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
                                    data-target="#nutritionModal">
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
                    </div>
                </div>
                <c:if test="${order.orderStatus.ordinal() == 0}">
                    <div class="col-md-6">
                        <div class="col-md-6">
                            <!-- Button trigger modal -->
                            <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target="#editExercisesModal" style="width:200px;">
                                    ${editExercises}
                            </button>

                            <!-- Modal -->
                            <div class="modal fade" id="editExercisesModal" tabindex="-1" role="dialog"
                                 aria-labelledby="editExercisesModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="editExercisesModalLabel">${exercises}</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <form name="loginForm"
                                              action="${pageContext.request.contextPath}/controller"
                                              method="POST">
                                            <input type="hidden" name="command" value="edit_exercises">
                                            <input type="hidden" name="orderId" value="${order.id}">
                                            <div class="modal-body">
                                                <div class="form-group">
                                                    <label for="input_exercises">${nutrition}</label>
                                                    <textarea name="exercises" class="form-control"
                                                              id="input_exercises">${order.exercises}</textarea>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary"
                                                        data-dismiss="modal">
                                                        ${close}
                                                </button>
                                                <button type="submit" class="btn btn-primary">${save}</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <!-- Button trigger modal -->
                            <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target="#editNutritionModal" style="margin-top:10px; width:200px;">
                                    ${editNutrition}
                            </button>

                            <!-- Modal -->
                            <div class="modal fade" id="editNutritionModal" tabindex="-1" role="dialog"
                                 aria-labelledby="editNutritionModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="editNutritionModalLabel">${nutrition}</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <form name="loginForm"
                                              action="${pageContext.request.contextPath}/controller"
                                              method="POST">
                                            <input type="hidden" name="command" value="edit_nutrition">
                                            <input type="hidden" name="orderId" value="${order.id}">
                                            <div class="modal-body">
                                                <div class="form-group">
                                                    <label for="input_nutrition">${nutrition}</label>
                                                    <textarea name="nutrition" class="form-control"
                                                              id="input_nutrition">${order.nutrition}</textarea>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary"
                                                        data-dismiss="modal">
                                                        ${close}
                                                </button>
                                                <button type="submit" class="btn btn-primary">${save}</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">


                            <form class="needs-validation" action="${pageContext.request.contextPath}/controller"
                                  method="POST" novalidate>
                                <input type="hidden" name="command" value="update_order_status">
                                <input type="hidden" name="orderId" value="${order.id}">
                                <input type="hidden" name="orderStatus" value="REVIEWED">
                                <div class="form-group">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="invalidCheck"
                                               required>
                                        <label class="form-check-label" for="invalidCheck">
                                                ${agreeToTermsAndConditions}
                                        </label>
                                        <div class="invalid-feedback">
                                                ${agreeBeforeSubmitting}
                                        </div>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary" style="margin-top:10px; width:200px;">
                                        ${sendForConfirm}
                                </button>
                            </form>
                            <script>
                                // ???????????? ???????????????????? JavaScript ?????? ???????????????????? ???????????????? ???????? ?????? ?????????????? ???????????????????????? ??????????
                                (function () {
                                    'use strict'

                                    // ???????????????? ?????? ??????????, ?? ?????????????? ???? ?????????? ?????????????????? ???????????????????????????????? ?????????? ???????????????? Bootstrap
                                    var forms = document.querySelectorAll('.needs-validation')

                                    // ???????????????????????????? ???? ?????? ?? ???????????????????????????? ????????????????
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
                </c:if>
            </div>
        </div>
    </div>
</div>

<c:import url="../fragment/footer.jsp"/>
</body>
</html>

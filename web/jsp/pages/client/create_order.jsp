<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:setBundle basename="bundle/err" var="err_rb"/>

<fmt:message key="order.comment" bundle="${rb}" var="orderComment"/>
<fmt:message key="order.comment.placeholder" bundle="${rb}" var="commentPlaceholder"/>
<fmt:message key="profile.confirm" bundle="${rb}" var="confirm"/>
<fmt:message key="order.start.date" bundle="${rb}" var="startDate"/>
<fmt:message key="order.choose.trainer" bundle="${rb}" var="chooseTrainer"/>
<fmt:message key="order.duration" bundle="${rb}" var="duration"/>
<fmt:message key="form.sign.up.tagline" bundle="${rb}" var="tagline"/>
<fmt:message key="form.sign.up.motivation.message" bundle="${rb}" var="motivationMessage"/>
<fmt:message key="order.new.order.message" bundle="${rb}" var="newOrderMessage"/>

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
            <h3 class="register-heading">${newOrderMessage}</h3>

            <div class="row register-form">
                <div class="col-md-6">

                    <form name="createOrderForm" action="${pageContext.request.contextPath}/controller"
                          method="POST" accept-charset="UTF-8">
                        <input type="hidden" name="command" value="CREATE_NEW_ORDER">
                        <div class="form-group">
                            <label for="inputTrainerId">${chooseTrainer}</label>
                            <select id="inputTrainerId" class="form-control" name="trainerId">
                                <c:forEach items="${requestScope.trainers}" var="trainer">
                                    <jsp:useBean id="trainer" class="com.company.gum.entity.Trainer"/>
                                    <option value="${trainer.id}">${trainer.surname} ${trainer.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="input-start-date">${startDate}</label>
                            <input type="date" name="startDate" class="form-control create-order-form-date"
                                   id="input-start-date">
                        </div>
                        <div class="form-group" id="training">
                            <label for="duration">${duration}</label>
                            <select id="duration" class="form-control" name="duration">
                                <option value="0">${trainingDurationDay}</option>
                                <option value="1">${trainingDurationWeek}</option>
                                <option value="2">${trainingDurationMonth}</option>
                                <option value="3">${trainingDurationThreeMonths}</option>
                                <option value="4">${trainingDurationHalfYear}</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="input-comment">${orderComment}</label>
                            <textarea name="comment" class="form-control" id="input-comment"
                                      placeholder="${commentPlaceholder}"></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">${confirm}</button>
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>
</div>
</div>
<c:import url="../../fragment/footer.jsp"/>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

<fmt:message key="project.name" bundle="${rb}" var="title"/>
<fmt:message key="form.sign.up.tagline" bundle="${rb}" var="tagline"/>
<fmt:message key="form.sign.up.motivation.message" bundle="${rb}" var="motivationMessage"/>
<fmt:message key="pricing.message" bundle="${rb}" var="pricingMessage"/>
<fmt:message key="about.pricing" bundle="${rb}" var="pricing"/>
<fmt:message key="order.new.order.week" bundle="${rb}" var="week"/>
<fmt:message key="order.new.order.month" bundle="${rb}" var="month"/>
<fmt:message key="order.new.order.half.year" bundle="${rb}" var="halfYear"/>
<fmt:message key="order.new.order.year" bundle="${rb}" var="year"/>
<fmt:message key="drawing.up.weekly" bundle="${rb}" var="drawingUpWeekly"/>
<fmt:message key="drawing.up.month" bundle="${rb}" var="drawingUpMonth"/>
<fmt:message key="drawing.up.half.year" bundle="${rb}" var="drawingUpHalfYear"/>
<fmt:message key="drawing.up.year" bundle="${rb}" var="drawingUpYear"/>
<fmt:message key="adjustment.the.plan" bundle="${rb}" var="adjustmentThePlan"/>
<fmt:message key="discount.for.the.next.order" bundle="${rb}" var="discountForTheNextOrder"/>
<fmt:message key="email.support" bundle="${rb}" var="emailSupport"/>

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

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4">${pricing}</h1>
    <p class="lead">%{pricingMessage}</p>
</div>
<div class="container">
    <div class="card-deck mb-3 text-center">
        <div class="card mb-4 box-shadow">
            <div class="card-header">
                <h4 class="my-0 font-weight-normal">${week}</h4>
            </div>
            <div class="card-body">
                <h1 class="card-title pricing-card-title">35 BYN</h1>
                <ul class="list-unstyled mt-3 mb-4">
                    <li>${drawingUpWeekly}</li>
                    <li>${discountForTheNextOrder} 5%</li>
                    <li>${emailSupport}</li>
                </ul>
            </div>
        </div>
        <div class="card mb-4 box-shadow">
            <div class="card-header">
                <h4 class="my-0 font-weight-normal">${month}</h4>
            </div>
            <div class="card-body">
                <h1 class="card-title pricing-card-title">140 BYN</h1>
                <ul class="list-unstyled mt-3 mb-4">
                    <li>${drawingUpMonth}</li>
                    <li>${discountForTheNextOrder} 10%</li>
                    <li>${emailSupport}</li>
                </ul>
            </div>
        </div>
        <div class="card mb-4 box-shadow">
            <div class="card-header">
                <h4 class="my-0 font-weight-normal">${halfYear}</h4>
            </div>
            <div class="card-body">
                <h1 class="card-title pricing-card-title">820 BYN</h1>
                <ul class="list-unstyled mt-3 mb-4">
                    <li>${drawingUpHalfYear}</li>
                    <li>${adjustmentThePlan}</li>
                    <li>${discountForTheNextOrder} 15%</li>
                    <li>${emailSupport}</li>
                </ul>
            </div>
        </div>
        <div class="card mb-4 box-shadow">
            <div class="card-header">
                <h4 class="my-0 font-weight-normal">${year}</h4>
            </div>
            <div class="card-body">
                <h1 class="card-title pricing-card-title">1530 BYN</h1>
                <ul class="list-unstyled mt-3 mb-4">
                    <li>${drawingUpYear}</li>
                    <li>${adjustmentThePlan}</li>
                    <li>${discountForTheNextOrder} 20%</li>
                    <li>${emailSupport}</li>
                </ul>
            </div>
        </div>
    </div>
</div>
<c:import url="fragment/footer.jsp"/>
</body>
</html>

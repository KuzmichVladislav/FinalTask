<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

<fmt:message key="form.money.placeholder" bundle="${rb}" var="moneyPlaceholder"/>
<fmt:message key="form.money.refill" bundle="${rb}" var="moneyRefill"/>
<fmt:message key="form.money.message" bundle="${rb}" var="moneyMessage"/>
<fmt:message key="form.sign.up.tagline" bundle="${rb}" var="tagline"/>
<fmt:message key="form.sign.up.motivation.message" bundle="${rb}" var="motivationMessage"/>
<fmt:message key="project.name" bundle="${rb}" var="title"/>

<html>
<head>
    <link
            href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
            rel="stylesheet" id="bootstrap-css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/bootstrap.css">
    <script
            src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
            type="text/javascript"></script>
    <script
            src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"
            type="text/javascript"></script>
    <title>${title}</title>
</head>
<body>
<c:import url="../fragment/navbar.jsp"/>

<script
        src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
        type="text/javascript"></script>
<script
        src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"
        type="text/javascript"></script>


<div class="container register">
    <div class="row">
        <div class="col-md-3 register-left">
            <img src="https://i.ibb.co/SsQJHTz/pngegg.png" alt="">
            <h3>${tagline}</h3>
            <p>${motivationMessage}</p>
        </div>
        <div class="col-md-9 register-right">
            <h3 class="register-heading">${moneyMessage}</h3>
            <div class="row register-form">
                <div class="col-md-6">
                    <form name="refill_money_form"
                          action="${pageContext.request.contextPath}/controller"
                          method="POST">
                        <input type="hidden" name="command" value="refill_money">
                        <div class="form-group">
                            <label for="input_money"></label> <input type="text"
                                                                     name="money" class="form-control" id="input_money"
                                                                     placeholder="${moneyPlaceholder}">
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
                        <button type="submit" class="btn btn-primary">${moneyRefill}
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="../fragment/footer.jsp"/>
</body>
</html>

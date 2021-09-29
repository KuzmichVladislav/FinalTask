<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

<fmt:message key="form.sign.up.tagline" bundle="${rb}" var="tagline"/>
<fmt:message key="form.sign.up.motivation.message" bundle="${rb}" var="motivationMessage"/>
<fmt:message key="comments.deleted" bundle="${rb}" var="commentDeleted"/>
<fmt:message key="comments.deleted.message" bundle="${rb}" var="commentDeletedMessage"/>
<fmt:message key="comments.comments" bundle="${rb}" var="comments"/>


<html>
    <head>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <meta http-equiv="refresh"
              content="5;${pageContext.request.contextPath}/controller?command=show_all_active_comments&page=1">
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
                    <h3 class="register-heading">${commentDeleted}</h3>
                    <div class="row register-form">
                        <div class="col-md-6">
                            <p>${commentDeletedMessage}</p>
                            <a href="${pageContext.request.contextPath}/controller?command=show_all_active_comments&page=1">${comments}</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <c:import url="../fragment/footer.jsp"/>
    </body>
</html>

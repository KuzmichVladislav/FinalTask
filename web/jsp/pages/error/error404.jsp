<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:message key="project.navigation.home" bundle="${rb}" var="home"/>

<html>
    <head>
        <title>Error 404</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    </head>
    <body>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


        <div class="error-content">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 ">
                        <div class="error-text">
                            <h1 class="error">404 Error</h1>
                            <h4>Oops! This page Could Not Be Found!</h4>
                            <p>Sorry bit the page you are looking for does not exist, have been removed or name changed.</p>
                            <a href="${pageContext.request.contextPath}/index.jsp">Go to homepage</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>

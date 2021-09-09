<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<c:set var="authorization" value="${sessionScope.authorization}"/>
<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="created.user.title" bundle="${rb}" var="userCreatedTitle"/>
<fmt:message key="created.user.text" bundle="${rb}" var="userCreatedText"/>
<fmt:message key="project.navigation.home" bundle="${rb}" var="home"/>

<html>
<head>
    <title>${projectName}</title>
</head>
<body>

<div class="main">
    <div class="container">
        <div class="main-section">
            <div class="welcome">
                <div class="col-lg-10 offset-lg-1">
                    <h2 class="main-section-title">${userCreatedTitle}</h2>
                    <p>${userCreatedText}</p>
                    <a href="${pageContext.request.contextPath}/index.jsp">${home}</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
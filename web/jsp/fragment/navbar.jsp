<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="project.navigation.home" bundle="${rb}" var="home"/>
<fmt:message key="project.navigation.contacts" bundle="${rb}" var="contacts"/>
<fmt:message key="project.navigation.about" bundle="${rb}" var="about"/>
<fmt:message key="project.navigation.language" bundle="${rb}" var="language"/>

<html>
<head>
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
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${pageContext.request.contextPath}">${projectName}</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}">${home}<span
                        class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">${about}</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">${contacts}</a>
            </li>
            <c:if test="${sessionScope.authorization}">
                <li class="nav-item">
                    <c:choose>
                        <c:when test="${sessionScope.userRole == 'CLIENT'}">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}/jsp/pages/client/account/profile.jsp">${account}</a>
                        </c:when>
                        <c:when test="${sessionScope.userRole == 'TRAINER'}">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}/jsp/pages/trainer/account/profile.jsp">${account}</a>
                        </c:when>
                        <c:when test="${sessionScope.userRole == 'ADMIN'}">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}/jsp/pages/admin/account/profile.jsp">${account}</a>
                        </c:when>
                    </c:choose>
                </li>
            </c:if>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    ${language}
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item"
                       href="${pageContext.request.contextPath}/controller?command=change_locale&locale=en_EN">EN
                        (English)</a>
                    <a class="dropdown-item"
                       href="${pageContext.request.contextPath}/controller?command=change_locale&locale=ru_RU">RU
                        (Русский)</a>
                </div>
            </li>
        </ul>
    </div>
</nav>


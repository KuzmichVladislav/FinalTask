<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

<fmt:message key="project.name" bundle="${rb}" var="title"/>
<fmt:message key="form.sign.up.tagline" bundle="${rb}" var="tagline"/>
<fmt:message key="form.sign.up.motivation.message" bundle="${rb}" var="motivationMessage"/>
<fmt:message key="user.id" bundle="${rb}" var="UserId"/>
<fmt:message key="profile.full.name" bundle="${rb}" var="fullName"/>
<fmt:message key="user.role" bundle="${rb}" var="userRole"/>
<fmt:message key="user.is.active" bundle="${rb}" var="isActive"/>
<fmt:message key="profile.mail" bundle="${rb}" var="userMail"/>
<fmt:message key="comment.delete" bundle="${rb}" var="delete"/>
<fmt:message key="comment.restore" bundle="${rb}" var="restore"/>
<fmt:message key="filterable.table" bundle="${rb}" var="filterableTable"/>
<fmt:message key="table.search" bundle="${rb}" var="tableSearch"/>
<fmt:message key="filter.message" bundle="${rb}" var="filterMessage"/>
<fmt:message key="delete.restore.user" bundle="${rb}" var="deleteRestoreUser"/>
<fmt:message key="project.name" bundle="${rb}" var="title"/>

<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
            <h3 class="register-heading">${filterableTable}</h3>
            <div class="row register-form">
                <div class="container mt-3">
                    <p>${filterMessage}</p>
                    <input class="form-control" id="myInput" type="text" placeholder="${tableSearch}">
                    <br>
                    <table class="table table-bordered">

                        <thead>
                        <tr>
                            <th>${UserId}</th>
                            <th>${fullName}</th>
                            <th>${userMail}</th>
                            <th>${userRole}</th>
                            <th>${isActive}</th>
                            <th>${deleteRestoreUser}</th>
                        </tr>
                        </thead>
                        <tbody id="myTable">
                        <c:forEach items="${requestScope.users}" var="user">
                        <jsp:useBean id="user" type="com.company.gum.model.entity.User"/>
                        <tr>
                            <td>${user.id}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/controller?command=SHOW_USER_PROFILE&userId=${user.id}&userRole=${user.role}">${user.name} ${user.surname}</a>
                            </td>

                            <td>${user.mail}</td>
                            <td>${user.role}</td>
                            <td>${user.active}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${user.active}">

                                        <form name="delete_form"
                                              action="${pageContext.request.contextPath}/controller" method="POST">
                                            <input type="hidden" name="command" value="delete_user">
                                            <input type="hidden" name="userId" value=${user.id}>
                                            <button type="submit"
                                                    class="btn btn-sm btn-outline-primary w-100">${delete}</button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <form name="delete_form"
                                              action="${pageContext.request.contextPath}/controller" method="POST">
                                            <input type="hidden" name="command" value="restore_user">
                                            <input type="hidden" name="userId" value=${user.id}>
                                            <button type="submit"
                                                    class="btn btn-sm btn-outline-primary w-100">${restore}</button>
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            </c:forEach>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <script>
                    $(document).ready(function () {
                        $("#myInput").on("keyup", function () {
                            var value = $(this).val().toLowerCase();
                            $("#myTable tr").filter(function () {
                                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                            });
                        });
                    });
                </script>

            </div>
        </div>
    </div>
</div>
<c:import url="../fragment/footer.jsp"/>
</body>
</html>

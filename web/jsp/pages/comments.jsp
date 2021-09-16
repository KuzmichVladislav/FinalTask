<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:setBundle basename="bundle/err" var="err_rb"/>


<fmt:message key="project.name" bundle="${rb}" var="title"/>
<fmt:message key="form.login.placeholder" bundle="${rb}" var="loginPlaceholder"/>
<fmt:message key="form.name.placeholder" bundle="${rb}" var="namePlaceholder"/>
<fmt:message key="form.surname.placeholder" bundle="${rb}" var="lastNamePlaceholder"/>
<fmt:message key="form.password.placeholder" bundle="${rb}" var="passwordPlaceholder"/>
<fmt:message key="form.confirm.password.placeholder" bundle="${rb}" var="confirmPasswordPlaceholder"/>
<fmt:message key="form.phone.placeholder" bundle="${rb}" var="phonePlaceholder"/>
<fmt:message key="form.mail.placeholder" bundle="${rb}" var="mailPlaceholder"/>
<fmt:message key="form.sign.up.register" bundle="${rb}" var="register"/>
<fmt:message key="form.sign.up.tagline" bundle="${rb}" var="tagline"/>
<fmt:message key="form.sign.up.motivation.message" bundle="${rb}" var="motivationMessage"/>
<fmt:message key="form.sign.up.message" bundle="${rb}" var="signUpMessage"/>

<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/comment.css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
            <h3 class="register-heading">${signUpMessage}</h3>
            <div class="row register-form">
                <c:forEach items="${requestScope.comments}" var="comment">
                    <jsp:useBean id="comment" type="com.company.gum.entity.Comment"/>
                    <div class="container">
                        <div class="col-8">
                            <div class="card card-white post">
                                <div class="post-heading">
                                    <div class="float-left image">
                                        <img src="data:image/jpg;base64,${comment.base64Image}"
                                             class="img-circle avatar" alt="user profile image">
                                    </div>
                                    <div class="float-left meta">
                                        <div class="title h5">
                                                ${comment.userName} ${comment.userSurname}
                                        </div>
                                        <h6 class="text-muted time">${comment.commentDate}</h6>
                                    </div>
                                </div>
                                <div class="post-description">
                                    <p>${comment.commentText}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<c:import url="../fragment/footer.jsp"/>
</body>
</html>

<%--            <nav aria-label="Page navigation">--%>
<%--                <ul class="pagination justify-content-center">--%>
<%--                    <c:choose>--%>
<%--                        <c:when test="${requestScope.currentNumberPage > 1}">--%>
<%--                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=find_all_comments&page=${requestScope.currentPage - 1}">${pagingPrevious}</a></li>--%>
<%--                        </c:when>--%>
<%--                        <c:otherwise>--%>
<%--                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=find_all_comments&page=1">${pagingPrevious}</a></li>--%>
<%--                        </c:otherwise>--%>
<%--                    </c:choose>--%>
<%--                    <c:forEach begin="1" end="${requestScope.numberOfPages}" var="i">--%>
<%--                        <c:choose>--%>
<%--                            <c:when test="${requestScope.currentNumberPage eq i}">--%>
<%--                                <li class="page-item"><a class="page-link page-focus" href="${pageContext.request.contextPath}/controller?command=find_all_comments&page=${i}">${i}</a></li>--%>
<%--                            </c:when>--%>
<%--                            <c:otherwise>--%>
<%--                                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=find_all_comments&page=${i}">${i}</a></li>--%>
<%--                            </c:otherwise>--%>
<%--                        </c:choose>--%>
<%--                    </c:forEach>--%>
<%--                    <c:choose>--%>
<%--                        <c:when test="${requestScope.currentNumberPage < requestScope.numberOfPages}">--%>
<%--                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=find_all_comments&page=${requestScope.currentNumberPage + 1}">${pagingNext}</a></li>--%>

<%--                        </c:when>--%>
<%--                        <c:otherwise>--%>
<%--                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=find_all_comments&page=${requestScope.numberOfPages}">${pagingNext}</a></li>--%>
<%--                        </c:otherwise>--%>
<%--                    </c:choose>--%>
<%--                </ul>--%>
<%--            </nav>--%>
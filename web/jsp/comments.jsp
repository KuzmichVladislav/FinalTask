<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

<fmt:message key="form.sign.up.tagline" bundle="${rb}" var="tagline"/>
<fmt:message key="form.sign.up.motivation.message" bundle="${rb}" var="motivationMessage"/>
<fmt:message key="comment.available.comments" bundle="${rb}" var="availableComments"/>
<fmt:message key="comment.edit.comment" bundle="${rb}" var="editComment"/>
<fmt:message key="comment.comment" bundle="${rb}" var="commentComment"/>
<fmt:message key="comment.close" bundle="${rb}" var="close"/>
<fmt:message key="comment.save.changes" bundle="${rb}" var="saveChanges"/>
<fmt:message key="comment.add.your.comment" bundle="${rb}" var="addYourComment"/>
<fmt:message key="comment.send.comment" bundle="${rb}" var="sendComment"/>
<fmt:message key="comment.delete" bundle="${rb}" var="delete"/>
<fmt:message key="project.name" bundle="${rb}" var="title"/>

<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/comment.css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>${title}</title>

</head>

<body>
<c:import url="fragment/navbar.jsp"/>

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
            <h3 class="register-heading">${availableComments}</h3>
            <div class="row register-form">
                <c:if test="${sessionScope.authorization}">
                    <div class="container">
                        <div class="card card-white post" style="width: auto">
                            <div class="post-heading">
                                <div class="float-left image">
                                    <img src="${sessionScope.userPhoto}" class="img-circle avatar"
                                         alt="user profile image">
                                </div>
                                <div class="float-left meta">
                                    <div class="title h5">
                                        <a href="#"><b>${sessionScope.userName} ${sessionScope.userLastName}</b></a>
                                            ${addYourComment}
                                    </div>
                                </div>
                            </div>
                            <div class="post-description">
                                <form name="newCommentForm" action="${pageContext.request.contextPath}/controller"
                                      method="POST"
                                      id="form">
                                    <input type="hidden" name="command" value="create_new_comment">
                                    <div class="form-group">
                                        <label for="new_comment">${commentComment}</label>
                                        <textarea name="comment" class="form_control" id="new_comment"
                                                  placeholder="Insert your comment"
                                                  style="width: -webkit-fill-available;"></textarea>
                                    </div>
                                    <button type="submit"
                                            class="btn btn-primary">${sendComment}</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:if>

                <c:forEach items="${requestScope.comments}" var="comment">
                    <jsp:useBean id="comment" type="com.company.gum.model.entity.Comment"/>
                    <div class="container">
                        <div class="card card-white post" style="width: auto">
                            <div class="post-heading">
                                <div class="float-left image">
                                    <img src="${comment.base64Image}"
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
                            <c:if test="${sessionScope.userId == comment.userId}">
                                <!-- Button trigger modal -->
                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target="#exampleModal">
                                        ${editComment}
                                </button>
                                <!-- Modal -->
                                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
                                     aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">${editComment}</h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <form name="edit_form"
                                                  action="${pageContext.request.contextPath}/controller" method="POST">
                                                <div class="modal-body">
                                                    <input type="hidden" name="command" value="edit_comment">
                                                    <input type="hidden" name="commentId" value=${comment.id}>
                                                    <div class="form-group">
                                                        <label for="input-comment">${commentComment}</label>
                                                        <textarea name="comment" class="form-control"
                                                                  id="input-comment">${comment.commentText}</textarea>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary"
                                                            data-dismiss="modal">${close}
                                                    </button>
                                                    <button type="submit"
                                                            class="btn btn-primary">${saveChanges}</button>
                                                </div>
                                            </form>

                                        </div>
                                    </div>
                                </div>
                                <form name="delete_form"
                                      action="${pageContext.request.contextPath}/controller" method="POST">
                                    <input type="hidden" name="command" value="delete_comment">
                                    <input type="hidden" name="commentId" value=${comment.id}>
                                    <button type="submit"
                                            class="btn btn-sm btn-outline-primary w-100">${delete}</button>
                                </form>
                            </c:if>
                            <c:if test="${sessionScope.userId != comment.userId && sessionScope.userRole == 'ADMIN'}">
                                <form name="delete_form"
                                      action="${pageContext.request.contextPath}/controller" method="POST">
                                    <input type="hidden" name="command" value="delete_comment">
                                    <input type="hidden" name="commentId" value=${comment.id}>
                                    <button type="submit"
                                            class="btn btn-sm btn-outline-primary w-100">${delete}</button>
                                </form>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>

                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <li class="page-item">
                            <c:choose>
                                <c:when test="${requestScope.currentNumberPage > 1}">
                                    <a class="page-link"
                                       href="${pageContext.request.contextPath}/controller?command=show_all_active_comments&page=${requestScope.currentPage - 1}"
                                       aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                        <span class="sr-only">Previous</span>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a class="page-link"
                                       href="${pageContext.request.contextPath}/controller?command=show_all_active_comments&page=1"
                                       aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                        <span class="sr-only">Previous</span>
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </li>
                        <c:forEach begin="1" end="${requestScope.numberOfPages}" var="i">
                            <li class="page-item"><a class="page-link"
                                                     href="${pageContext.request.contextPath}/controller?command=show_all_active_comments&page=${i}">${i}</a>
                            </li>
                        </c:forEach>
                        <li class="page-item">
                            <c:choose>
                                <c:when test="${requestScope.currentNumberPage < requestScope.numberOfPages}">
                                    <a class="page-link"
                                       href="${pageContext.request.contextPath}/controller?command=show_all_active_comments&page=${requestScope.currentNumberPage + 1}"
                                       aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                        <span class="sr-only">Next</span>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a class="page-link"
                                       href="${pageContext.request.contextPath}/controller?command=show_all_active_comments&page=${requestScope.numberOfPages}"
                                       aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                        <span class="sr-only">Next</span>
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
<c:import url="fragment/footer.jsp"/>
</body>
</html>
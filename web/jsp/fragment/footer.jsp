<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

<fmt:message key="form.sign.up.tagline" bundle="${rb}" var="tagline"/>
<fmt:message key="footer.about" bundle="${rb}" var="footerAbout"/>
<fmt:message key="footer.contacts" bundle="${rb}" var="footerContacts"/>
<fmt:message key="footer.address" bundle="${rb}" var="footerAddress"/>
<fmt:message key="footer.full.address" bundle="${rb}" var="footerFullAddress"/>
<fmt:message key="footer.phones" bundle="${rb}" var="footerPhones"/>
<fmt:message key="footer.links" bundle="${rb}" var="footerlinks"/>
<fmt:message key="footer.about.link" bundle="${rb}" var="footerAboutLink"/>
<fmt:message key="footer.email" bundle="${rb}" var="footerEmail"/>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<footer class="section footer-classic context-dark bg-image" style="background: #f8f9fa;">
    <div class="container">
        <div class="row row-30">
            <div class="col-md-4 col-xl-5">
                <div class="pr-xl-4"><a class="brand" href="index.html">
                        <img class="brand-logo-light"
                             src="https://i.ibb.co/7N7qp8j/kisspng-physical-fitness-silhouette-physical-exercise-yoga-sports-silhouette-5a84e265373299-08779795.png"
                             alt="" width="128" height="80"
                             srcset="images/agency/logo-retina-inverse-280x74.png 2x"></a>
                    <p>${footerAbout}</p>
                    <p class="rights"><span>©  </span><span
                            class="copyright-year">2021</span><span> </span><span>Waves</span><span>. </span><span>All Rights Reserved.</span>
                    </p>
                </div>
            </div>
            <div class="col-md-4">
                <h5>${footerContacts}</h5>
                <dl class="contact-list">
                    <dt>${footerAddress}</dt>
                    <dd>${footerFullAddress}</dd>
                </dl>
                <dl class="contact-list">
                    <dt>${footerEmail}</dt>
                    <dd><a href="mailto:kyzmenoid@gmail.com">fitnessgum@gmail.com</a></dd>
                </dl>
                <dl class="contact-list">
                    <dt>${footerPhones}</dt>
                    <dd><a href="tel:#">+375259379992</a> <span>or</span> <a href="tel:#">+375339379992</a>
                    </dd>
                </dl>
            </div>
            <div class="col-md-4 col-xl-3">
                <h5>${footerlinks}</h5>
                <ul class="nav-list">

                    <li><a href="${pageContext.request.contextPath}/jsp/pages/about.jsp">${footerAboutLink}</a></li>
                        <%--                    <li><a href="#">Projects</a></li>--%>
                        <%--                    <li><a href="#">Blog</a></li>--%>
                        <%--                    <li><a href="#">Contacts</a></li>--%>
                        <%--                    <li><a href="#">Pricing</a></li>--%>
                </ul>
            </div>
        </div>
    </div>
    <div class="row no-gutters social-container">
        <div class="col"><a class="social-inner" href="https://www.facebook.com/Ktihii"><span
                    class="icon mdi mdi-facebook"></span><span>Facebook</span></a></div>
        <div class="col"><a class="social-inner" href="https://www.facebook.com/Ktihii"><span
                    class="icon mdi mdi-instagram"></span><span>instagram</span></a></div>
        <div class="col"><a class="social-inner" href="https://www.facebook.com/Ktihii"><span
                    class="icon mdi mdi-twitter"></span><span>twitter</span></a></div>
        <div class="col"><a class="social-inner" href="https://www.facebook.com/Ktihii"><span
                    class="icon mdi mdi-youtube-play"></span><span>google</span></a></div>
    </div>
</footer>
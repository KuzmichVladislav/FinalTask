<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isErrorPage="true" %>

<html>
<head>
    <title>Show Error Page</title>
</head>

<body>
<h1>Opps...</h1>
<table width="100%" border="1">
    <tr valign="top">
        <td width="40%"><strong>Error:</strong></td>
        <td>${pageContext.exception}</td>
    </tr>

    <tr valign="top">
        <td><strong>URI:</strong></td>
        <td>${pageContext.errorData.requestURI}</td>
    </tr>

    <tr valign="top">
        <td><strong>Status code:</strong></td>
        <td>${pageContext.errorData.statusCode}</td>
    </tr>

    <tr valign="top">
        <td><strong>Stack trace:</strong></td>
        <td>
            <c:forEach var="trace"
                       items="${pageContext.exception.stackTrace}">
                <p>${trace}</p>
            </c:forEach>
        </td>
    </tr>
</table>

</body>
</html>
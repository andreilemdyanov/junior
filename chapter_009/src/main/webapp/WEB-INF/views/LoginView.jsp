<%--
  Created by IntelliJ IDEA.
  User: DNS
  Date: 10.05.2018
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/signin" method="post">
    Login: <input type="text" name="login"><br>
    Password: <input type="password" name="password"><br>
    <input type='submit' name='create'/>
</form>
</body>
<head>
    <title>Title</title>
</head>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/editJSP" method="post">
    New Name:
    <input type="text" name="name"><br>
    New Login:
    <input type="text" name="login"><br>
    New Email:
    <input type="text" name="email"><br>
    <center>
        <button type="submit" name="loginForUp" value="${param.loginForUp}">Submit</button>
    </center>
</form>
</body>
</html>

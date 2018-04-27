<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<form action="${pageContext.servletContext.contextPath}/" method="post">
    Name: <input type="text" name="name"><br>
    Login: <input type="text" name="login"><br>
    Email: <input type="text" name="email"><br>
    <button type='submit' name='create'>Create User</button>
</form>
<table style="border: 1px solid black" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>name</th>
        <th>login</th>
        <th>email</th>
        <th>timeCreate</th>
    </tr>
   <c:forEach items="${users}" var="user">
    <tr>
        <td>
            <c:out value="${user.name}"></c:out>
        </td>
        <td>
            <c:out value="${user.login}"></c:out>
        </td>
        <td>
            <c:out value="${user.email}"></c:out>
        </td>
        <td>
            <c:out value="${user.createDate.getTime()}"></c:out>
        </td>
        <td>
            <form action="${pageContext.servletContext.contextPath}/editJSP">
                <button type="submit" value="${user.login}" name="loginForUp">Update</button>
            </form>
        </td>
        <td>
            <form action="${pageContext.servletContext.contextPath}/deleteJSP" method="post">
                <button type="submit" value="${user.login}" name="loginForDelete">Delete</button>
            </form>
        </td>
    </tr>
   </c:forEach>
</table>

</body>
</html>

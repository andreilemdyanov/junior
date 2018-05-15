<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:if test="${sessionScope.role == 'ADMIN'}">
<form action="${pageContext.servletContext.contextPath}/" method="post">
    Name: <input type="text" name="name"><br>
    Login: <input type="text" name="login"><br>
    Password: <input type="text" name="password"><br>
    Email: <input type="text" name="email"><br>
    Role: <select name="select">
    <option value="1">ADMIN</option>
    <option value="2">DEFAULT</option>
</select><br>
    <button type='submit' name='create'>Create User</button>
</form>
</c:if>
<table style="border: 1px solid black" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>login</th>
<c:if test="${sessionScope.role == 'ADMIN'}">
    <th>password</th>
</c:if>
        <th>email</th>
        <th>timeCreate</th>
        <th>role</th>
    </tr>
   <c:forEach items="${users}" var="user">
    <tr>
        <td>
            <c:out value="${user.id}"></c:out>
        </td>
        <td>
            <c:out value="${user.name}"></c:out>
        </td>
        <td>
            <c:out value="${user.login}"></c:out>
        </td>
        <c:if test="${sessionScope.role == 'ADMIN'}">
            <td>
                <c:out value="${user.password}"></c:out>
            </td>
        </c:if>
        <td>
            <c:out value="${user.email}"></c:out>
        </td>
        <td>
            <c:out value="${user.createDate.getTime()}"></c:out>
        </td>
        <td>
            <c:out value="${user.role}"></c:out>
        </td>
        <td>
            <c:if test="${sessionScope.login == user.login || sessionScope.role == 'ADMIN'}">
                <form action="${pageContext.servletContext.contextPath}/editJSP">
                    <button type="submit" value="${user.id}" name="id">Update</button>
                </form>
            </c:if>
        </td>
        <td>
            <c:if test="${sessionScope.login == user.login || sessionScope.role == 'ADMIN'}">
            <form action="${pageContext.servletContext.contextPath}/deleteJSP" method="post">
                <button type="submit" value="${user.id}" name="id">Delete</button>
            </form>
            </c:if>
        </td>
        <td>
            <c:if test="${sessionScope.role == 'ADMIN'}">
                <form action="${pageContext.servletContext.contextPath}/updateRole" method="post">
                    <select name="select" >
                        <option value="1">ADMIN</option>
                        <option value="2">DEFAULT</option>
                    </select>
                    <button type="submit" value="${user.id}" name="id">Change role</button>
                </form>
            </c:if>
        </td>
    </tr>
   </c:forEach>
</table>
<form action="${pageContext.servletContext.contextPath}/signout" method="post">
    <input type="submit" value="Sign Out">
</form>
</body>
</html>

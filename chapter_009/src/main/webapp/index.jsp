<%@ page import="jsp.User" %>
<%@ page import="jsp.UserStore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<form action="<%=request.getContextPath()%>/createJSP" method="post">
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
    <% for (User user : UserStore.INSTANCE.getAllUsers()) { %>
    <tr>
        <td>
            <%=user.getName()%>
        </td>
        <td>
            <%=user.getLogin()%>
        </td>
        <td>
            <%=user.getEmail()%>
        </td>
        <td>
            <%=user.getCreateDate().getTime()%>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/update.jsp">
                <button value="<%=user.getLogin()%>" name="loginForUp">Update</button>
            </form>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/deleteJSP" method="post">
                <button value="<%=user.getLogin()%>" name="loginForDelete">Delete</button>
            </form>
        </td>
    </tr>
    <%}%>
</table>

</body>
</html>

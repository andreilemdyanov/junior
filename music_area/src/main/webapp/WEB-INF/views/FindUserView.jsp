<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Find Page</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <style>
        body {
            background: url('http://farm3.staticflickr.com/2832/12303719364_c25cecdc28_b.jpg') fixed;
            background-size: cover;
            padding: 0;
            margin: 0;
        }

        div#tableContainer {
            display: table;
            border-spacing: 20px;
        }

        div.tableRow {
            display: table-row;
        }

        .wrap {
            width: 100%;
            height: 100%;
            min-height: 100%;
            position: relative;
            top: 0;
            left: 0;
        }

        p.form-title {
            font-family: 'Open Sans', sans-serif;
            font-size: 20px;
            font-weight: 600;
            text-align: center;
            color: #FFFFFF;
            margin-top: 2%;
            text-transform: uppercase;
            letter-spacing: 4px;
        }

        form {
            width: 250px;
            margin: 0 auto;
        }

        form.create input[type="text"], form.create input[type="password"] {
            width: 100%;
            margin: 0;
            padding: 5px 10px;
            background: 0;
            border: 0;
            border-bottom: 1px solid #FFFFFF;
            outline: 0;
            font-style: italic;
            font-size: 12px;
            font-weight: 400;
            letter-spacing: 1px;
            margin-bottom: 5px;
            color: #40ee40;
        }

        form.create input[type="submit"] {
            width: 100%;
            font-size: 14px;
            text-transform: uppercase;
            font-weight: 500;
            margin-top: 16px;
            outline: 0;
            cursor: pointer;
            letter-spacing: 1px;
        }

        form.create input[type="submit"]:hover {
            transition: background-color 0.5s ease;
        }

        form.create select {
            background: transparent;
        }

        option {
            background: transparent;
        }

        form.create {
            font-family: 'Open Sans', sans-serif;
            font-style: italic;
            color: #40ee40
        }

        select[name="country"], select[name="role"], select[name="city"] {
            width: 150px;
            font-family: 'Open Sans', sans-serif;
            font-style: italic;
            color: #40ee40;
            padding: 5px 10px;
            margin-bottom: 5px;
        }

        input::-webkit-input-placeholder {
            color: #dbe3ff;
            font-size: 80%;
        }

        input::-moz-placeholder {
            color: #dbe3ff;
            font-size: 80%;
        }

        table {
            color: white;
            border-spacing: 0px;
        }
    </style>
</head>
<body>
<div id="tableContainer">
    <div class="tableRow">
        <div class="wrap">
            <p class="form-title">
                Find Users</p>
            <table style="border: 1px solid white" cellpadding="1" cellspacing="1" border="1">
                <tr>
                    <th>id</th>
                    <th>login</th>
                    <c:if test="${sessionScope.role == 'ADMIN'}">
                        <th>password</th>
                    </c:if>
                    <th>firstName</th>
                    <th>lastName</th>
                    <th>timeCreate</th>
                </tr>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>
                            <c:out value="${user.id}"></c:out>
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
                            <c:out value="${user.firstName}"></c:out>
                        </td>
                        <td>
                            <c:out value="${user.lastName}"></c:out>
                        </td>
                        <td>
                            <c:out value="${user.timeCreate.getTime()}"></c:out>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <div class="tableRow">
        <form action="${pageContext.servletContext.contextPath}/">
            <input type="submit" value="Back">
        </form>
    </div>
</div>
</body>
</html>
